ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "com.alejandrohdezma"

addCommandAlias("ci-test", "fix --check; mdoc; scripted")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "github; ci-release")

lazy val scalafmt = "org.scalameta" % "sbt-scalafmt" % "[2.0.0,)" % Provided // scala-steward:off

lazy val docs = project
  .in(file("sbt-scalafmt-defaults-docs"))
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := file("."))
  .settings(mdocVariables += "SCALAFMT_CONF" -> IO.read(file(".scalafmt.conf")))

lazy val `sbt-scalafmt-defaults` = module
  .enablePlugins(SbtPlugin)
  .settings(addSbtPlugin(scalafmt))
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(scriptedLaunchOpts += s"-Dscalafmt.conf.content=${IO.read(file(".scalafmt.conf"))}")
  .settings(Compile / unmanagedResources += baseDirectory.in(LocalRootProject).value / ".scalafmt.conf")
