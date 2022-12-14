ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

ThisBuild / resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    name := "SitePages",
    idePackagePrefix := Some("com.torvald.pages")
  )


lazy val websiteJS =
  project.in(file("websiteJS"))
  .settings(
    libraryDependencies ++= List(
      "org.scala-js" %%% "scalajs-dom" % "2.3.0",
      "com.raquo" %%% "laminar" % "0.14.5"),
    (publish / skip) := true,
    webpackBundlingMode := BundlingMode.LibraryOnly(),
    //webpackBundlingMode := BundlingMode.LibraryAndApplication(),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    scalacOptions ~= { options: Seq[String] =>
      options.filterNot { o =>
        o.startsWith("-Wvalue-discard") || o.startsWith("-Ywarn-value-discard") || o.startsWith("-Ywarn-unused") || o.startsWith("-Wunused")
      }
    },
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

lazy val website = project
  .enablePlugins(MdocPlugin, DocusaurusPlugin)
  .settings(
    mdocIn := file("website/docs"),
    mdocJS := Some(websiteJS),
    mdocJSLibraries := (websiteJS / Compile / fullOptJS / webpack).value,
    (publish / skip) := true,
    mdocVariables := Map(
      "js-mount-node" -> "containerNode",
      "js-batch-mode" -> "true"
    )
  )