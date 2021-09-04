# Default (and sane) configurations for Scalafmt

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward]

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-scalafmt-defaults" % "0.4.4")
```

> You'll also need to provide the [`sbt-scalafmt` plugin](https://github.com/scalameta/sbt-scalafmt).

## Usage

The included plugin is automatically activated. It will enable `scalafmtOnCompile` by default (except when running on CI) and will create a `.scalafmt.conf` in your project's root folder with the following content:

```hocon
# This file has been automatically generated and should
# not be edited nor added to source control systems.

# To edit the original configurations go to
# https://github.com/alejandrohdezma/sbt-scalafmt-defaults/edit/master/.scalafmt.conf

version = 3.0.2

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
project.includePaths."+" = ["glob:**.md"]
```

> You can add the `.scalafmt.conf` file to the repository's `.gitignore`, since it's going to be automatically re-created on every build.

### Extra configurations

Extra configurations can be added to a file named `.scalafmt-extra.conf` at the root of your repository. The content of this file will be automatically appended to the auto-generated `.scalafmt.conf`.

[github-action]: https://github.com/alejandrohdezma/sbt-scalafmt-defaults/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-scalafmt-defaults%2Fbadge%3Fref%3Dmaster&style=flat
[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-scalafmt-defaults
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-scalafmt-defaults/badge.svg?kill_cache=1
[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
