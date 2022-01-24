ThisBuild / scalaVersion  := "2.13.8"
ThisBuild / scalacOptions += "-Xsource:3"

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala213source3")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
