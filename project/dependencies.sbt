// For using the plugin in its own build
Compile / unmanagedSourceDirectories +=
  (ThisBuild / baseDirectory).value.getParentFile / "modules" / "sbt-scalafmt-defaults" / "src" / "main" / "scala"

Compile / unmanagedResources +=
  (ThisBuild / baseDirectory).value.getParentFile / ".scalafmt.conf"
