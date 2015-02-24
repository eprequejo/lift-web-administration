import sbt._
import Keys._

//for sbt plugins autoimport https://www.playframework.com/documentation/2.3.7/Build
import play.Play.autoImport._
import PlayKeys._

import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.Import.Assets

object AdminBuild extends Build {

  lazy val buildSettings = Seq(
    organization := "Estela Pinto Requejo",
    version := "1.0.0",
    scalaVersion := "2.10.2"
    //name := "sparkle-administration"
  )

  override def rootProject = Some(webadmin)

  lazy val coreadmin = Project(
    id = "coreadmin",
    base = file("coreadmin"),
    settings = Defaults.defaultSettings ++ baseSettings
  ).settings(
    libraryDependencies ++= Seq(
      Dependencies.commons_lang3,
      Dependencies.scalaTest,
      Dependencies.scalazCore,
      Dependencies.joda_time,
      Dependencies.joda_convert,
      Dependencies.bonecp,
      //Dependencies.postgres,
      Dependencies.mysql,
      Dependencies.cglib_nodep,
      Dependencies.logback
    )
  ).dependsOn(modeladmin)

  lazy val modeladmin = Project(
    id = "modeladmin",
    base = file("modeladmin"),
    settings = Defaults.defaultSettings ++ baseSettings
  ).settings(
    libraryDependencies ++= Seq(
      Dependencies.commons_lang3,
      Dependencies.scalazCore,
      Dependencies.joda_time,
      Dependencies.joda_convert,
      Dependencies.logback
    )
  )

  val webadmin = Project(
    id = "webadmin",
    base = file("webadmin"),
    settings = baseSettings
  ).settings(
    libraryDependencies ++=  Seq(
      Dependencies.scalazCore,
      Dependencies.joda_time,
      Dependencies.joda_convert,
      Dependencies.scalaTest,
      Dependencies.postgres,
      Dependencies.commons_lang3)
  ).settings(libraryDependencies ~= (_.map(excludeSpecs2)))
  .enablePlugins(play.PlayScala)
  .enablePlugins(SbtWeb)
  .dependsOn(coreadmin, modeladmin)

  def excludeSpecs2(module: ModuleID): ModuleID = module.excludeAll(ExclusionRule(organization = "org.specs2"))

  // base settings include the buildSettings (version, org, etc) so that they are applied to all 3 projects
  lazy val baseSettings = Formatting.autoFormatSettings ++ buildSettings

  object Dependencies {

    val joda_time     = "joda-time"           % "joda-time"             % "2.7"
    val joda_convert  = "org.joda"            % "joda-convert"          % "1.7"
    val commons_lang3 = "org.apache.commons"  % "commons-lang3"         % "3.3.2"
    val logback       = "ch.qos.logback"      % "logback-classic"       % "1.1.2"
    val postgres      = "postgresql"          % "postgresql"            % "9.0-801.jdbc4"
    val mysql         = "mysql"               % "mysql-connector-java"  % "5.1.10"
    val bonecp        = "com.jolbox"          % "bonecp"                % "0.7.1.RELEASE"
    val cglib_nodep   = "cglib"               % "cglib-nodep"           % "2.2.2"
    val scalaTest     = "org.scalatest"       % "scalatest_2.10"        % "2.0"           % "test"
    val scalazCore    = "org.scalaz"          %% "scalaz-core"          % "7.1.0"

  }

  object Formatting {
    import com.typesafe.sbt.SbtScalariform
    import SbtScalariform._

    lazy val autoFormatSettings = scalariformSettings ++ Seq(
      ScalariformKeys.preferences := formattingPreferences
    )

    lazy val settings = defaultScalariformSettings ++ Seq(
      ScalariformKeys.preferences := formattingPreferences
    )

    lazy val formattingPreferences = {
      import scalariform.formatter.preferences._

      FormattingPreferences().
        setPreference(AlignParameters, true).
        setPreference(AlignSingleLineCaseStatements, false).
        setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40).
        setPreference(CompactControlReadability, false).
        setPreference(CompactStringConcatenation, false).
        setPreference(DoubleIndentClassDeclaration, true).
        setPreference(FormatXml, true).
        setPreference(IndentLocalDefs, false).
        setPreference(IndentPackageBlocks, true).
        setPreference(IndentSpaces, 2).
        setPreference(IndentWithTabs, false).
        setPreference(MultilineScaladocCommentsStartOnFirstLine, false).
        setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false).
        setPreference(PreserveSpaceBeforeArguments, false).
        setPreference(PreserveDanglingCloseParenthesis, true).
        setPreference(RewriteArrowSymbols, false).
        setPreference(SpaceBeforeColon, false).
        setPreference(SpaceInsideBrackets, false).
        setPreference(SpaceInsideParentheses, false).
        setPreference(SpacesWithinPatternBinders, true)
    }
  }

}
