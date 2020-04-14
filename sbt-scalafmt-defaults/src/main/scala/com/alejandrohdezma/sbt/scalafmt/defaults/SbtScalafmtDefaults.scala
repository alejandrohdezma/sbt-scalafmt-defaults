package com.alejandrohdezma.sbt.scalafmt.defaults

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

import scala.sys.process._

object SbtScalafmtDefaults extends AutoPlugin {

  override def requires: Plugins = JvmPlugin

  override def trigger = allRequirements

  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    onLoad := onLoad.value andThen { state =>
      getClass.getResource("/.scalafmt.conf") #> file(".scalafmt.conf") ! sLog.value
      state
    }
  )

}
