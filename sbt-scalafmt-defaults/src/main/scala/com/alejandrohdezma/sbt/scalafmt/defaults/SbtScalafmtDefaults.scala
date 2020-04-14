/*
 * Copyright 2020 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
