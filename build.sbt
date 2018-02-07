organization := "com.eed3si9n"
name := "sbt-dirty-money"
version := "0.2.1-SNAPSHOT"

sbtPlugin := true

crossSbtVersions := List("0.13.17", "1.1.0")

description := "sbt plugin for cleaning Ivy2 cache"
licenses := Seq("MIT License" -> url("https://github.com/sbt/sbt-dirty-money/blob/master/LICENSE"))
scmInfo := Some(ScmInfo(url("https://github.com/sbt/sbt-dirty-money"), "git@github.com:sbt/sbt-dirty-money.git"))

scalacOptions := Seq("-deprecation", "-unchecked")

bintrayOrganization := None
bintrayRepository := "sbt-plugins"
