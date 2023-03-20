# @DESCRIPTION@

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-scalafmt-defaults" % "@VERSION@")
```

> You'll also need to provide the [`sbt-scalafmt` plugin](https://github.com/scalameta/sbt-scalafmt).

## Usage

The included plugin is automatically activated. It will create a `.scalafmt.conf` in your project's root folder with the following content:

```hocon
@SCALAFMT_CONF@
```

> You can add the `.scalafmt.conf` file to the repository's `.gitignore`, since it's going to be automatically re-created on every build.

## Runner-dialect

Since v3.1.0, [the Scalafmt `runner.dialect` option is mandatory](https://scalameta.org/scalafmt/docs/configuration.html#scala-dialects). This plugin automatically sets this option based on your current `ThisBuild / scalaVersion` setting. The `scala212source3`/`scala213source3` will be set if `scalaVersion` is set to those and `-Xsource:3` option is present under `ThisBuild / scalacOptions`.

If for any reason you want to alter the generated dialect or specify the `runner.dialect` for a subset of files using `fileOverride` you can use the [`.scalafmt-extra.conf` file](#extra-configurations).

### Extra configurations

Extra configurations can be added to a file named `.scalafmt-extra.conf` at the root of your repository. The content of this file will be automatically appended to the auto-generated `.scalafmt.conf`.
