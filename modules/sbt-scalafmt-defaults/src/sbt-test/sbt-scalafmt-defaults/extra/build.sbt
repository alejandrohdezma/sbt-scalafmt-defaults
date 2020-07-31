TaskKey[Unit]("checkScalafmtConfFile") := {
  val extra = IO.read(file(".scalafmt-extra.conf"))
  assert(IO.read(file(".scalafmt.conf")) == sys.props("scalafmt.conf.content") + "\n" + extra)
}
