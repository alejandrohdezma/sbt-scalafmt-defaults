ThisBuild / scalaVersion := "2.11.12"

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala211")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
