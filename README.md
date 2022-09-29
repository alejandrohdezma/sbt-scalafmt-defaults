# Default (and sane) configurations for Scalafmt

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-scalafmt-defaults" % "0.7.1")
```

> You'll also need to provide the [`sbt-scalafmt` plugin](https://github.com/scalameta/sbt-scalafmt).

## Usage

The included plugin is automatically activated. It will create a `.scalafmt.conf` in your project's root folder with the following content:

```hocon
# This file has been automatically generated and should
# not be edited nor added to source control systems.

# To edit the original configurations go to
# https://github.com/alejandrohdezma/sbt-scalafmt-defaults/edit/master/.scalafmt.conf

version = 3.5.8

# This value is automatically set based on your current `ThisBuild / scalaVersion` setting.
# `scala212source3`/`scala213source3` will be set if `scalaVersion` is set to any of those versions
#  and `-Xsource:3` option is present under `ThisBuild / scalacOptions`.
runner.dialect = scala212

# Number of maximum characters in a column
maxColumn = 120

# Ensure newlines are added arround top level body definitions always
newlines.topLevelBodyIfMinStatements = [before,after]
newlines.topLevelBodyMinStatements = 1

# Allow line-overflow for comments and lines that would overflow even with a newline.
newlines.avoidForSimpleOverflow=[slc, tooLong]

# Ensure newlines around every statement except `case object` definitions
newlines.topLevelStatementBlankLines = [
  {
    blanks = 1,
    minBreaks = 0,
    regex = "^(?!((Term\.Apply)|(Defn\.Object)))"
  }
]

# Ensure lines starting with the margin character `|` are indented differently
assumeStandardLibraryStripMargin = true

# Align everything that can be aligned
align.preset = most
align.multiline = false
align.tokens."+" = [
  {
    code = ":=", owner = "Term.ApplyInfix"
  },
  {
    code = "+=", owner = "Term.ApplyInfix"
  },
  {
    code = "++=", owner = "Term.ApplyInfix"
  },
  {
    code = "--=", owner = "Term.ApplyInfix"
  },
  {
    code = "-=", owner = "Term.ApplyInfix"
  }
]

# Allow literal argument lists with newlines only once `maxColumn` is reached
binPack.literalArgumentLists = true
binPack.literalsIncludeSimpleExpr = true
binPack.literalsExclude = [ "Term.Name" ]

# Use ScalaDoc style and enable wrapping when reaching `maxColumn`
docstrings.style = "SpaceAsterisk"
docstrings.wrap = yes
docstrings.oneline = fold

# Avoid infix calls (except for operators)
rewrite.rules += AvoidInfix

# Ensure redundant braces are removed
rewrite.rules += RedundantBraces
rewrite.redundantBraces.maxLines = 1
rewrite.redundantBraces.stringInterpolation = true

# Ensure redundant parentheses are removed
rewrite.rules += RedundantParens

# Ensure modifiers like `implicit` or `final` are sorted the same
rewrite.rules += SortModifiers

# Replaces parentheses into curly braces in for comprehensions that contain multiple enumerator generators
rewrite.rules += PreferCurlyFors

# Ensure a separate line is created for each selector within a `{...}` import.
rewrite.rules += Imports
rewrite.imports.expand = true

# Avoid ASCII tokens
rewriteTokens = {
  "⇒": "=>"
  "→": "->"
  "←": "<-"
}

# Select followed by curly braces should not start a chain
includeCurlyBraceInSelectChains = false

# Ensure code blocks inside markdown files get formated too
project.includePaths = ["glob:**.scala", "glob:**.sbt", "glob:**.sc", "glob:**.md"]
project.excludePaths = ["glob:**metals.sbt"]
```

> You can add the `.scalafmt.conf` file to the repository's `.gitignore`, since it's going to be automatically re-created on every build.

## Runner-dialect

Since v3.1.0, [the Scalafmt `runner.dialect` option is mandatory](https://scalameta.org/scalafmt/docs/configuration.html#scala-dialects). This plugin automatically sets this option based on your current `ThisBuild / scalaVersion` setting. The `scala212source3`/`scala213source3` will be set if `scalaVersion` is set to those and `-Xsource:3` option is present under `ThisBuild / scalacOptions`.

If for any reason you want to alter the generated dialect or specify the `runner.dialect` for a subset of files using `fileOverride` you can use the [`.scalafmt-extra.conf` file](#extra-configurations).

### Extra configurations

Extra configurations can be added to a file named `.scalafmt-extra.conf` at the root of your repository. The content of this file will be automatically appended to the auto-generated `.scalafmt.conf`.
