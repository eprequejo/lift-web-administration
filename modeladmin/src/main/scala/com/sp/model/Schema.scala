package com.sp.model

object Schema {

  case class SwatchProduct(
    id: Int,
    refNum: String,
    idSubfamily: Int,
    name: Option[String] = None,
    description: Option[String] = None,
    price: Option[Double] = None,
    label: Option[Label] = None,
    gender: Option[Gender] = None,
    colour: Option[String] = None //colour case objects ?? TODO
    )

  trait Label {
    def desc: String
  }
  case object NewLabel extends Label {
    val desc = "New"
  }
  case object BestSellersLabel extends Label {
    val desc = "Best Sellers"
  }
  case object SalesLabel extends Label {
    val desc = "Sales"
  }
  object Label {
    def apply(desc: String): Option[Label] = desc match {
      case NewLabel.desc => Some(NewLabel)
      case BestSellersLabel.desc => Some(BestSellersLabel)
      case SalesLabel.desc => Some(SalesLabel)
      case _ => None
    }
  }

  trait Gender {
    def desc: String
  }
  case object Femenine extends Gender {
    val desc = "Femenino"
  }
  case object Masculine extends Gender {
    val desc = "Masculino"
  }
  object Gender {
    def apply(desc: String): Option[Gender] = desc match {
      case Femenine.desc => Some(Femenine)
      case Masculine.desc => Some(Masculine)
      case _ => None
    }
  }

}
