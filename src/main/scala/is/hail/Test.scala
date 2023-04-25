package is.hail

import org.apache.log4j._

object Test {
  private val log = Logger.getLogger(getClass.getName())
  private[this] val logFormat: String = "%d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}: %p: %m%n"

  def main(args: Array[String]): Unit = {
    log.error("ONE")

    LogManager.getRootLogger().removeAllAppenders()
    val fa = new FileAppender()
    fa.setName("Hail File Appender: ")
    fa.setFile("/tmp/abc")
    fa.setLayout(new PatternLayout(logFormat))
    fa.setThreshold(Level.INFO)
    fa.activateOptions()
    LogManager.getRootLogger().addAppender(fa)

    log.error("TWO")
  }
}
