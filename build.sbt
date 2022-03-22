ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"


lazy val akkaVersion = "2.6.13"
lazy val akkaHttpVersion = "10.2.4"
lazy val circeVersion = "0.14.0"
lazy val cassandraDriverVersion = "4.12.0"

lazy val root = (project in file("."))
  .settings(
    name := "AkkaTest")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)
