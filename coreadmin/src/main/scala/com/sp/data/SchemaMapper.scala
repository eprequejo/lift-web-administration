package com.sp.data

import Schema._

import com.sp.model.Schema._

object SchemaMapper {

  case class SwatchProductsMapped(table: TableSwatchProducts) {
    def mapped: SwatchProduct = {
      SwatchProduct(
        table.id,
        table.refNum,
        table.idSubfamily,
        table.name,
        table.description,
        table.price
      )
    }

  }

}
