package controllers

import com.sp.data.Repository

import play.api._
import play.api.mvc._

object Swatch extends Controller {

  def products = Action {

    val products = Repository.getSwatchProducts
    Ok(views.html.swatch_product_list(products))
  }

}

