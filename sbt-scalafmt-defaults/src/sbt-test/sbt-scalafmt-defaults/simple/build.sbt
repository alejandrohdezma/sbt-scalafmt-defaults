TaskKey[Unit]("checkScalafmtConfFile") := {
  assert(IO.read(file(".scalafmt.conf")) == sys.props("scalafmt.conf.content"))
}
