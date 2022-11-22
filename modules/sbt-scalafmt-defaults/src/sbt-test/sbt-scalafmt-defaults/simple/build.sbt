munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    assertNoDiff(IO.read(file(".scalafmt.conf")), sys.props("scalafmt.conf.content"))
  }

}
