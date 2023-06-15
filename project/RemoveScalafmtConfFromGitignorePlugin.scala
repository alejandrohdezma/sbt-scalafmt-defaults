import java.nio.file.Files
import java.nio.file.Path

import sbt._
import sbt.ci.SbtCiPlugin
import sbt.ci.SbtCiPlugin._

object RemoveScalafmtConfFromGitignorePlugin extends AutoPlugin {

  override def trigger = allRequirements

  override def requires = SbtCiPlugin

  override def buildSettings = Seq(
    generateCiFiles := Def.sequential(generateCiFiles, removeScalafmtConfFromGitignore).value
  )

  val removeScalafmtConfFromGitignore = Def.task {
    val `.gitignore` = Path.of(".gitignore")

    val content = Files.readString(`.gitignore`)

    val newContent = content.stripSuffix {
      """### sbt-scalafmt-defaults ###
        |# Scalafmt configuration file is automatically downloaded by `sbt-scalafmt-defaults`
        |# https://github.com/alejandrohdezma/sbt-scalafmt-defaults
        |
        |.scalafmt.conf
        |""".stripMargin
    }

    if (newContent.contains(".scalafmt.conf")) sys.error(".gitignore still contains `.scalafmt.conf` file")

    Files.writeString(`.gitignore`, newContent)
  }

}
