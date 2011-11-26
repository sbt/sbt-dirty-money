sbtPlugin := true

name := "sbt-dirty-money"

organization := "com.eed3si9n"

version := "0.0.1"

description := "sbt plugin for cleaning Ivy2 cache"

licenses := Seq("MIT License" -> url("https://github.com/sbt/sbt-dirty-money/blob/master/LICENSE"))

scalacOptions := Seq("-deprecation", "-unchecked")

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT") Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/snapshots/")
  else Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishArtifact in (Compile, packageBin) := true

publishArtifact in (Test, packageBin) := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

// seq(ScriptedPlugin.scriptedSettings: _*)

seq(lsSettings :_*)

LsKeys.tags in LsKeys.lsync := Seq("sbt", "utility")

licenses in LsKeys.lsync <<= licenses
