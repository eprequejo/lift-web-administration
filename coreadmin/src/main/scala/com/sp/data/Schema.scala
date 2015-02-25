package com.sp.data

import org.squeryl.annotations.Column
import org.squeryl.{ Schema => SquerylSchema }
import org.squeryl.{ KeyedEntityDef }

import com.sp.data.MyTypeMode._

object Schema extends SquerylSchema {

  override def name = Some("sparkle")

  implicit object swatchProductsKED extends KeyedEntityDef[TableSwatchProducts, Int] {
    def getId(t: TableSwatchProducts): Int = t.id
    def isPersisted(t: TableSwatchProducts): Boolean = t.id > 0 | false
    def idPropertyName: String = "id"
  }
  val swatchProducts = table[TableSwatchProducts]("swatch_products")
  on(swatchProducts)(t => declare(
    t.id is autoIncremented,
    t.refNum is unique
  ))

}

case class TableSwatchProducts(
    @Column("id") id: Int,
    @Column("ref_num") refNum: String,
    @Column("id_subfamily") idSubfamily: Int,
    @Column("name") name: Option[String] = None,
    @Column("description") description: Option[String] = None,
    @Column("price") price: Option[Double] = None,
    @Column("id_label") label: Option[Int] = None,
    @Column("id_gender") gender: Option[Int] = None,
    @Column("id_colour") colour: Option[Int] = None) {

  def this() = this(0, "", 0, Some(""), Some(""), Some(0.0), Some(0), Some(0), Some(0))
}

