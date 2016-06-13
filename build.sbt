import ScalaxbKeys._

name := "SparkXML"
version := "1.0"
 
//scalaVersion := "2.11.8"
lazy val commonSettings = Seq(
  organization  := "com.telia.saprk",
  scalaVersion  := "2.11.8"
)

libraryDependencies ++= Seq(
  "org.oedura" %% "scavro" % "1.0.1",
  "org.specs2" %% "specs2-core" % "3.7.2" % "test"
)

lazy val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
lazy val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
lazy val dispatchV = "0.11.2"
lazy val dispatch = "net.databinder.dispatch" %% "dispatch-core" % dispatchV
 
lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name          := "SparkXML",
    libraryDependencies ++= Seq(dispatch),
    libraryDependencies ++= {
      if (scalaVersion.value startsWith "2.11") Seq(scalaXml, scalaParser)
      else Seq()
    }).
  settings(scalaxbSettings: _*).
  settings(
    sourceGenerators in Compile += (scalaxb in Compile).taskValue,
    dispatchVersion in (Compile, scalaxb) := dispatchV,
    async in (Compile, scalaxb)           := true,
    packageName in (Compile, scalaxb)     := "generated")

libraryDependencies += "com.databricks" %% "spark-xml" % "0.3.3"
libraryDependencies += "org.apache.avro" % "avro" % "1.7.6" exclude("org.mortbay.jetty", "servlet-api")
libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.7"  % "provided" classifier("hadoop2") exclude("org.mortbay.jetty", "servlet-api")
libraryDependencies += "com.databricks" %% "spark-avro" % "2.0.1"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.4.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.4.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-streaming" % "2.7.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.3"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.2.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.1"
