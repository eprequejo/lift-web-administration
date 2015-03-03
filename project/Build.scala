import sbt._
import Keys._

//for sbt plugins autoimport https://www.playframework.com/documentation/2.3.7/Build
import play.Play.autoImport._
import PlayKeys._

import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.Import.Assets

object AdminBuild extends Build {

  override def rootProject = Some(webadmin)

  lazy val coreadmin = Project(
    id = "coreadmin",
    base = file("coreadmin"),
    settings = Defaults.defaultSettings ++ baseSettings).dependsOn(modeladmin)

  lazy val modeladmin = Project(
    id = "modeladmin",
    base = file("modeladmin"),
    settings = Defaults.defaultSettings ++ baseSettings)

  val webadmin = Project(
    id = "webadmin",
    base = file("webadmin"),
    settings = baseSettings
  ).enablePlugins(play.PlayScala).enablePlugins(SbtWeb).dependsOn(coreadmin, modeladmin)

  lazy val buildSettings = Seq(
    organization := "Estela Pinto Requejo",
    version := "1.0.0",
    scalaVersion := "2.10.2"
    //name := "sparkle-administration"
  )

  // base settings include the buildSettings (version, org, etc) so that they are applied to all 3 projects
  lazy val baseSettings = Formatting.autoFormatSettings ++ buildSettings

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
