package com.sp.data

import com.jolbox.bonecp.{ BoneCP, BoneCPConfig }
import java.sql.{ Connection, DriverManager }

sealed trait DataSource {
  def connection(): Connection

  def shutdown(): Unit = {}

}

case class PooledDataSource(driver: String, url: String, user: String, pass: String) extends DataSource {

  def connection(): Connection = {
    pool.getConnection()
  }

  override def shutdown(): Unit = {
    pool.shutdown()
  }

  // Init pool
  ////
  private[PooledDataSource] val pool = {
    Class.forName(driver)
    val conf = new BoneCPConfig()
    conf.setJdbcUrl(url)
    conf.setUsername(user)
    conf.setPassword(pass)
    conf.setMinConnectionsPerPartition(5)
    conf.setMaxConnectionsPerPartition(10)
    conf.setDisableConnectionTracking(true)
    new BoneCP(conf)
  }

}

