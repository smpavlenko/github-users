enablePlugins(JavaServerAppPackaging)

name := "github-users-service"

version := "0.1"

organization := "com.pavlenko"

scalaVersion := "2.11.7"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                  Resolver.bintrayRepo("hseeberger", "maven"))

libraryDependencies ++= {
  val AkkaVersion = "2.4.18"
  val AkkaHttpVersion = "10.0.6"
  val Json4sVersion = "3.5.2"
  Seq(
    "org.typelevel" % "scala-library" % "2.11.7",
    "com.typesafe.akka" %% "akka-slf4j"      % AkkaVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.json4s"        %% "json4s-native"   % Json4sVersion,
    "org.json4s"        %% "json4s-ext"      % Json4sVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.16.0",
    "com.typesafe.play" % "play-json_2.11" % "2.6.7"
  )
}

// Assembly settings
mainClass in Global := Some("com.pavlanko.github.Main")

assemblyJarName in assembly := "github-users-server.jar"