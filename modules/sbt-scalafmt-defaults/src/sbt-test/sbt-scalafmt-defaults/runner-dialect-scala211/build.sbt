ThisBuild / scalaVersion := "2.11.12"

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    val expected = sys
      .props("scalafmt.conf.content")
      .replace("runner.dialect = scala212", "runner.dialect = scala211")

    assertNoDiff(IO.read(file(".scalafmt.conf")), expected)
  }

}
