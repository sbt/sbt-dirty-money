package sbtdirtymoney

import sbt._
import Keys._
import Project.Initialize

object Plugin extends sbt.Plugin {
  lazy val cleanCache      = TaskKey[Unit]("clean-cache")
  lazy val cleanCacheFiles = TaskKey[Seq[File]]("clean-cache-files")
  lazy val cleanCacheIvy2Directory = SettingKey[File]("clean-cache-ivy-directory")
  lazy val cleanLocal      = TaskKey[Unit]("clean-local")
  lazy val cleanLocalFiles = TaskKey[Seq[File]]("clean-local-files")

  override val settings: Seq[sbt.Project.Setting[_]] = Seq(
    cleanCacheIvy2Directory <<= ivyPaths(_.ivyHome getOrElse(Path.userHome / ".ivy2")),
    cleanCache <<= (cleanCacheFiles) map { files => IO.delete(files) },
    cleanLocal <<= (cleanLocalFiles) map { files => IO.delete(files) },
    cleanCacheFiles <<= (cleanCacheIvy2Directory, organization, name) map { (dir, org, name) =>
      ((dir / "cache") ** ("*" + org + "*") ** ("*" + name + "*")).get
    },
    cleanLocalFiles <<= (cleanCacheIvy2Directory, organization, name) map { (dir, org, name) =>
      ((dir / "local") ** ("*" + org + "*") ** ("*" + name + "*")).get
    }
  )
}
