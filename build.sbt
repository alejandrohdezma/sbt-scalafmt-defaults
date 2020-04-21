ThisBuild / scalaVersion := "2.12.11"
ThisBuild / organization := "com.alejandrohdezma"

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("ci-test", "fix --check; mdoc; scripted")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")

skip in publish := true

lazy val docs = project
  .in(file("sbt-scalafmt-defaults-docs"))
  .enablePlugins(MdocPlugin)
  .settings(skip in publish := true)
  .settings(mdocOut := file("."))
  .settings(mdocVariables += "SCALAFMT_CONF" -> IO.read(file(".scalafmt.conf")))

lazy val `sbt-scalafmt-defaults` = project
  .enablePlugins(SbtPlugin)
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(Compile / unmanagedResources += baseDirectory.value.getParentFile / ".scalafmt.conf")
