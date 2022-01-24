ThisBuild / scalaVersion := "2.13.8"

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala213")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
