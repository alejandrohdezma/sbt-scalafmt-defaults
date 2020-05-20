# Default (and sane) configurations for Scalafmt

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward]  [![][mergify-badge]][mergify]

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-scalafmt-defaults" % "0.2.0")
```

> You'll also need to provide the [`sbt-scalafmt` plugin](https://github.com/scalameta/sbt-scalafmt). 

## Usage

The included plugin is automatically activated. It will create a `.scalafmt.conf` in your project's root folder with the following content:

```hocon
version = 2.5.2

maxColumn = 120

newlines {
  alwaysBeforeTopLevelStatements = true
  sometimesBeforeColonInMethodReturnType = false
}

style = defaultWithAlign

align {
  tokens.add = [
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
}

binPack {
  literalArgumentLists = true
  literalsExclude = [ "Term.Name" ]
}

docstrings = JavaDoc

rewrite {
  rules = [
    ExpandImportSelectors,
    PreferCurlyFors
    RedundantBraces,
    RedundantParens,
    SortModifiers
  ]
  redundantBraces.maxLines = 1
  redundantBraces.stringInterpolation = true
}

includeCurlyBraceInSelectChains = false

includeNoParensInSelectChains = false

optIn.blankLineBeforeDocstring = true

```

> You can add the `.scalafmt.conf` file to the repository's `.gitignore`, since it's going to be automatically re-created on every build.

[github-action]: https://github.com/alejandrohdezma/sbt-scalafmt-defaults/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-scalafmt-defaults%2Fbadge%3Fref%3Dmaster&style=flat

[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-scalafmt-defaults
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-scalafmt-defaults/badge.svg?kill_cache=1

[mergify]: https://mergify.io
[mergify-badge]: https://img.shields.io/endpoint.svg?url=https://gh.mergify.io/badges/alejandrohdezma/sbt-scalafmt-defaults&style=flat

[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
