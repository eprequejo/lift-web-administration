package com.sp.data

import org.squeryl.adapters.{ MySQLAdapter, PostgreSqlAdapter }
import org.squeryl.{ Session, SessionFactory }

import scalaz._
import scalaz.Scalaz._

object DataService {

  //postgress connection
  /*def init[T <: DataSource](ds: T) {
    source = Option(ds)
    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        source.get.connection(),
        new PostgreSqlAdapter
      )
    )
  }*/

  //mysql connection
  def init[T <: DataSource](ds: T) {
    source = Option(ds)
    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        source.get.connection(),
        new MySQLAdapter
      )
    )
  }

  def shutdown(): Unit = {
    source map (_.shutdown())
  }

  private[this] var source: Option[DataSource] = none[DataSource]
}

