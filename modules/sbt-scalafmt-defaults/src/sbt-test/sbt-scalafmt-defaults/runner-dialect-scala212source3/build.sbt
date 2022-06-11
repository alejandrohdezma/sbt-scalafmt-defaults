ThisBuild / scalaVersion  := "2.12.16"
ThisBuild / scalacOptions += "-Xsource:3"

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala212source3")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
