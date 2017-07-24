organization := "com.eed3si9n"
name := "sbt-dirty-money"
version := "0.1.1-SNAPSHOT"

sbtPlugin := true

description := "sbt plugin for cleaning Ivy2 cache"
licenses := Seq("MIT License" -> url("https://github.com/sbt/sbt-dirty-money/blob/master/LICENSE"))
scmInfo := Some(ScmInfo(url("https://github.com/sbt/sbt-dirty-money"), "git@github.com:sbt/sbt-dirty-money.git"))

scalacOptions := Seq("-deprecation", "-unchecked")

bintrayOrganization := Some("sbt")
bintrayRepository := "sbt-plugin-releases"
