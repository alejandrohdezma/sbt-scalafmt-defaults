ThisBuild / scalaVersion  := "2.12.17" // scala-steward:off
ThisBuild / scalacOptions += "-Xsource:3"

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    val expected = sys
      .props("scalafmt.conf.content")
      .replace("runner.dialect = scala212", "runner.dialect = scala212source3")

    assertNoDiff(IO.read(file(".scalafmt.conf")), expected)
  }

}
