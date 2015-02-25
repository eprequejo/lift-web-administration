package com.sp.data

import java.sql.Timestamp

import org.joda.time.{ DateTime, LocalDate }

import org.squeryl.PrimitiveTypeMode
import org.squeryl.dsl._

object MyTypeMode extends PrimitiveTypeMode {
  // DateTime
  ////
  implicit val dateTimeTEF = new NonPrimitiveJdbcMapper[Timestamp, DateTime, TTimestamp](timestampTEF, this) {
    def convertFromJdbc(t: Timestamp) = if (t == null) null else new DateTime(t)
    def convertToJdbc(t: DateTime) = new Timestamp(t.getMillis)
  }

  implicit val optionDateTimeTEF = new TypedExpressionFactory[Option[DateTime], TOptionTimestamp] with DeOptionizer[Timestamp, DateTime, TTimestamp, Option[DateTime], TOptionTimestamp] {
    val deOptionizer = dateTimeTEF
  }

  implicit def dateTimeToTE(s: DateTime) = dateTimeTEF.create(s)

  implicit def optionDateTimeToTE(s: Option[DateTime]) = optionDateTimeTEF.create(s)
  ////

  // LocalDate
  ////
  implicit val localDateTEF = new NonPrimitiveJdbcMapper[Timestamp, LocalDate, TTimestamp](timestampTEF, this) {
    def convertFromJdbc(t: Timestamp) = if (t == null) null else LocalDate.fromDateFields(t)
    def convertToJdbc(t: LocalDate) = new Timestamp(t.toDateTimeAtStartOfDay.getMillis)
  }

  implicit val optionLocalDateTEF = new TypedExpressionFactory[Option[LocalDate], TOptionTimestamp] with DeOptionizer[Timestamp, LocalDate, TTimestamp, Option[LocalDate], TOptionTimestamp] {
    val deOptionizer = localDateTEF
  }

  implicit def localDateToTE(s: LocalDate) = localDateTEF.create(s)

  implicit def optionLocalDateToTE(s: Option[LocalDate]) = optionLocalDateTEF.create(s)
  ////
}
