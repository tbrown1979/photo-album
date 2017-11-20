scalaVersion in ThisBuild := "2.11.8"

val http4sVersion = "0.18.0-M5"
val circeVersion = "0.9.0-M2"

val log4sVersion = "1.3.4"
val pureConfigVersion = "0.7.0"
val scalaTestVersion = "3.0.1"
val shapelessVersion = "2.3.2"

val scalaCheckVersion = "1.13.4"
val scalaCheckShapelessVersion = "1.1.7"

name := "photo-album"

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "com.chuusai"  %% "shapeless"             % shapelessVersion,
  "com.github.pureconfig" %% "pureconfig"   % pureConfigVersion,
  "org.http4s"   %% "http4s-argonaut"       % http4sVersion,
  "org.http4s"   %% "http4s-blaze-server"   % http4sVersion,
  "org.http4s"   %% "http4s-dsl"            % http4sVersion,
  "org.http4s"   %% "http4s-server-metrics" % http4sVersion,
  "org.http4s"   %% "http4s-blaze-client"   % http4sVersion,
  "org.http4s"   %% "http4s-circe"          % http4sVersion,
  "org.log4s"    %% "log4s"                 % log4sVersion,
  "io.circe"     %% "circe-generic"         % circeVersion,
  "io.circe"       %% "circe-literal"        % circeVersion,
  "io.circe"       %% "circe-generic-extras" % circeVersion,
  "io.circe"       %% "circe-optics"         % circeVersion,
  "io.circe"       %% "circe-parser"         % circeVersion,
  "io.circe"       %% "circe-java8"          % circeVersion,
  "io.circe" %% "circe-generic-extras" % circeVersion,
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % scalaCheckShapelessVersion % Test,
  "eu.timepit" %% "refined"            % "0.8.4",
  "eu.timepit" %% "refined-cats"       % "0.8.4" // optional
)