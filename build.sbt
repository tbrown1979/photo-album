scalaVersion in ThisBuild := "2.11.8"

val http4sVersion = "0.18.0-M5"
val circeVersion = "0.9.0-M2"

val pureConfigVersion = "0.7.0"
val refinedVersion = "0.8.4"
val scalaTestVersion = "3.0.4"
val shapelessVersion = "2.3.2"

name := "photo-album"

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "eu.timepit"    %% "refined"              % refinedVersion,
  "eu.timepit"    %% "refined-cats"         % refinedVersion,
  "io.circe"      %% "circe-generic"        % circeVersion,
  "io.circe"      %% "circe-literal"        % circeVersion,
  "io.circe"      %% "circe-generic-extras" % circeVersion,
  "io.circe"      %% "circe-optics"         % circeVersion,
  "io.circe"      %% "circe-parser"         % circeVersion,
  "io.circe"      %% "circe-java8"          % circeVersion,
  "io.circe"      %% "circe-generic-extras" % circeVersion,
  "org.http4s"    %% "http4s-blaze-client"  % http4sVersion,
  "org.http4s"    %% "http4s-circe"         % http4sVersion,
  "org.scalactic" %% "scalactic"            % scalaTestVersion,
  "org.scalatest" %% "scalatest"            % scalaTestVersion % "test"
)