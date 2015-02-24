import com.sp.data.DataService
import com.sp.data.PooledDataSource

import play.api._
import play.api.mvc._

//TODO CSRFFilter
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")
    Logger.info("=== Starting squeryl data service")

    val driver = app.configuration.getString("db.default.driver").get
    val url = app.configuration.getString("db.default.url").get
    val user = app.configuration.getString("db.default.user").get
    val pass = app.configuration.getString("db.default.password").get

    val ds = PooledDataSource(driver, url, user, pass)
    DataService.init(ds)

    /*SessionFactory.concreteFactory = app.configuration.getString("db.default.driver") match {
      case Some("org.postgresql.Driver") => Some(() => getSession(new CustomPGSQLAdaptater, app))
      case _ => sys.error("Wrong Database driver, it must be org.postgresql.Driver")
    }
    def getSession(adapter: DatabaseAdapter, app: Application) = Session.create(DB.getConnection()(app), adapter)
    */

  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
    Logger.info("=== Shutdown squeryl data service")

    DataService.shutdown()
  }

}
