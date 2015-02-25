package com.sp.data

import com.sp.data.MyTypeMode._
import com.sp.data.Schema._
import com.sp.data.SchemaMapper._

import com.sp.model.Schema._

object Repository {

  def getSwatchProducts(): Seq[SwatchProduct] = {
    inTransaction {
      from(swatchProducts)(t =>
        select(t) orderBy (t.name)
      ).map { SwatchProductsMapped(_).mapped }.toList
    }
  }

}
