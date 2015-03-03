resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "joda-time"           % "joda-time"             % "2.7",
  "org.joda"            % "joda-convert"          % "1.7",
  "org.apache.commons"  % "commons-lang3"         % "3.3.2",
  "ch.qos.logback"      % "logback-classic"       % "1.1.2",
  "postgresql"          % "postgresql"            % "9.0-801.jdbc4",
  "mysql"               % "mysql-connector-java"  % "5.1.10",
  "com.jolbox"          % "bonecp"                % "0.7.1.RELEASE",
  "cglib"               % "cglib-nodep"           % "2.2.2",
  "org.scalatest"       % "scalatest_2.10"        % "2.0"           % "test",
  "org.scalaz"          %% "scalaz-core"          % "7.1.0"
)

