ThisBuild / scalaVersion := "3.1.0"

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala3")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
