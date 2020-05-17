TaskKey[Unit]("checkScalafmtConfFile") := {
  val content = sys.props("scalafmt.conf.content")

  val header =
    """# This file has been automatically generated and should
      |# not be edited nor added to source control systems.
      |
      |# To edit the original configurations go to
      |# https://github.com/alejandrohdezma/sbt-scalafmt-defaults/edit/master/.scalafmt.conf
      |
      |""".stripMargin

  assert(IO.read(file(".scalafmt.conf")) == header + content)
}