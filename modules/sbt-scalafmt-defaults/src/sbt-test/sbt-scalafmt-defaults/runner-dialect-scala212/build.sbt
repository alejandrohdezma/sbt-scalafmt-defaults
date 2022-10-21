ThisBuild / scalaVersion := "2.12.17" // scala-steward:off

TaskKey[Unit]("checkScalafmtConfFile") := {
  val expected = sys
    .props("scalafmt.conf.content")
    .replace("runner.dialect = scala212", "runner.dialect = scala212")

  assert(IO.read(file(".scalafmt.conf")) == expected)
}
