ThisBuild / scalaVersion := "2.13.8"

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    val expected = sys
      .props("scalafmt.conf.content")
      .replace("runner.dialect = scala212", "runner.dialect = scala213")

    assertNoDiff(IO.read(file(".scalafmt.conf")), expected)
  }

}
