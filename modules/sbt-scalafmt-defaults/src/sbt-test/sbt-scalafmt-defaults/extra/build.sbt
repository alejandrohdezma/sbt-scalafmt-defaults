ThisBuild / scalaVersion := "2.12.17" // scala-steward:off

munitSuites += "ScriptedSuite" -> new FunSuite {

  test("Files match") {
    val obtained = IO.read(file(".scalafmt.conf"))

    val extra    = IO.read(file(".scalafmt-extra.conf"))
    val expected = s"${sys.props("scalafmt.conf.content")}\n$extra"

    assertNoDiff(obtained, expected, clues(obtained))
  }

}
