ThisBuild / scalaVersion := "2.12.17" // scala-steward:off

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    assertNoDiff(IO.read(file(".scalafmt.conf")), sys.props("scalafmt.conf.content"))
  }

}
