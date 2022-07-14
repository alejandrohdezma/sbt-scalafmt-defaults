/*
 * Copyright 2020-2022 Alejandro Hernández <https://github.com/alejandrohdezma>
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

import scala.io.Source

import sbt.Keys._
import sbt._

import org.scalafmt.sbt.ScalafmtPlugin
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

object SbtScalafmtDefaults extends AutoPlugin {

  override def requires: Plugins = ScalafmtPlugin

  override def trigger = allRequirements

  @SuppressWarnings(Array("scalafix:Disable.blocking.io", "scalafix:DisableSyntax.=="))
  override def buildSettings: Seq[Setting[_]] = Seq(
    scalafmtConfig := {
      val xSource3 = scalacOptions.value.seq.exists(_ == "-Xsource:3")

      val dialect = scalaVersion.value match {
        case v if v.startsWith("2.11")             => "scala211"
        case v if v.startsWith("2.12") && xSource3 => "scala212source3"
        case v if v.startsWith("2.12")             => "scala212"
        case v if v.startsWith("2.13") && xSource3 => "scala213source3"
        case v if v.startsWith("2.13")             => "scala213"
        case v if v.startsWith("3")                => "scala3"
      }

      val defaults = Source
        .fromResource(".scalafmt.conf", getClass.getClassLoader)
        .getLines()
        .toList
        .map {
          case line if line.contains("runner.dialect") => s"runner.dialect = $dialect"
          case line                                    => line
        }
        .mkString("\n")

      IO.write(file(".scalafmt.conf"), defaults)

      val extra = file(".scalafmt-extra.conf")

      if (extra.exists())
        IO.append(file(".scalafmt.conf"), "\n" + IO.read(extra))

      file(".scalafmt.conf")
    }
  )

}
