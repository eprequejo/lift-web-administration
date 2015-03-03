
libraryDependencies ++= Seq(
  "org.scalaz"          %% "scalaz-core"          % "7.1.0",
  "org.webjars"         % "angularjs"             % "1.2.13",
  "org.webjars"         % "angular-ui-bootstrap"  % "0.11.0-2",
  "org.webjars"         % "bootstrap"             % "3.2.0",
  "org.webjars"         % "font-awesome"          % "4.2.0",
  "joda-time"           % "joda-time"             % "2.7",
  "org.joda"            % "joda-convert"          % "1.7",
  "org.apache.commons"  % "commons-lang3"         % "3.3.2",
  "org.scalatest"       % "scalatest_2.10"        % "2.0"           % "test"
)

pipelineStages := Seq(rjs, digest, gzip)

