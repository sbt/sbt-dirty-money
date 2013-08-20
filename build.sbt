sbtPlugin := true

name := "sbt-dirty-money"

organization := "com.eed3si9n"

version := "0.0.2"

sbtVersion in Global := "0.13.0-RC1" 

scalaVersion in Global := "2.10.2" 

description := "sbt plugin for cleaning Ivy2 cache"

licenses := Seq("MIT License" -> url("https://github.com/sbt/sbt-dirty-money/blob/master/LICENSE"))

scalacOptions := Seq("-deprecation", "-unchecked")

publishArtifact in (Compile, packageBin) := true

publishArtifact in (Test, packageBin) := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT") Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/snapshots/")
  else Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishMavenStyle := false

publishTo <<= (version) { version: String =>
   val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
   val (name, u) = if (version.contains("-SNAPSHOT")) ("sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
                   else ("sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, url(u))(Resolver.ivyStylePatterns))
}

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

// seq(ScriptedPlugin.scriptedSettings: _*)

// seq(lsSettings :_*)

// LsKeys.tags in LsKeys.lsync := Seq("sbt", "utility")

// licenses in LsKeys.lsync <<= licenses
