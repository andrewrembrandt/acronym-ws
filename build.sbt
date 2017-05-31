name := "acronym-ws"
organization := "cake"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  filters,
  ws,
  "com.typesafe.play" %% "play-json" % "2.5.15",
  "org.scalaz" %% "scalaz-core" % "7.2.13",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0-RC1" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "cake.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "cake.binders._"
