ThisBuild / scalaVersion := "3.1.0"

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    val expected = sys
      .props("scalafmt.conf.content")
      .replace("runner.dialect = scala212", "runner.dialect = scala3")

    assertNoDiff(IO.read(file(".scalafmt.conf")), expected)
  }

}
