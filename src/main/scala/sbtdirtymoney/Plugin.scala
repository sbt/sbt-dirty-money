package sbtdirtymoney

import sbt._
import Keys._
import Project.Initialize
import complete.Parser

object Plugin extends sbt.Plugin {
  lazy val cleanCache      = InputKey[Unit]("clean-cache")
  lazy val cleanCacheFiles = InputKey[Seq[File]]("clean-cache-files")
  lazy val cleanCacheIvy2Directory = SettingKey[File]("clean-cache-ivy-directory")
  lazy val cleanLocal      = InputKey[Unit]("clean-local")
  lazy val cleanLocalFiles = InputKey[Seq[File]]("clean-local-files")

  object DirtyMoney {
    import complete.DefaultParsers._
    case class ModuleParam(organization: String, name: Option[String])
    def parseParam: Parser[Option[ModuleParam]] =
      ((parseOrg ~ parseName.?) map {
        case o ~ n => ModuleParam(o, n)
      }).?
    private def parseOrg: Parser[String] =
      (Space ~> token(StringBasic.examples("\"organization\"")))
    private def parseName: Parser[String] =
      (Space ~> token(token("%") ~> Space ~> StringBasic.examples("\"name\"")))
    def query(base: File, param: Option[ModuleParam], org: String, name: String): Seq[File] =
      (param match {
        case None                                   => base ** ("*" + org + "*") ** ("*" + name + "*")
        case Some(ModuleParam("*", None))           => base ** "*"
        case Some(ModuleParam(o, None | Some("*"))) => base ** ("*" + o + "*") ** "*"
        case Some(ModuleParam(o, Some(n)))          => base ** ("*" + o + "*") ** ("*" + n + "*")
      }).get
  }

  override val settings: Seq[Def.Setting[_]] = Seq(
    cleanCacheIvy2Directory <<= ivyPaths(_.ivyHome getOrElse(Path.userHome / ".ivy2")),
    cleanCache <<= (cleanCacheFiles) map { files => IO.delete(files) },
    cleanLocal <<= (cleanLocalFiles) map { files => IO.delete(files) },
    cleanCacheFiles := {
      val base = cleanCacheIvy2Directory.value / "cache"
      val param = DirtyMoney.parseParam.parsed
      DirtyMoney.query(base, param, organization.value, name.value)
    },
    cleanLocalFiles := {
      val base = cleanCacheIvy2Directory.value / "local"
      val param = DirtyMoney.parseParam.parsed
      DirtyMoney.query(base, param, organization.value, name.value)
    }
  )
}
