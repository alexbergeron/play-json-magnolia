lazy val root = (project in file("."))
  .settings(
    name := "play-json-magnolia",
    scalaVersion := "2.11.12",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.4.11",
      "com.propensive" %% "magnolia" % "0.6.1" %  "provided"
    )
  )
