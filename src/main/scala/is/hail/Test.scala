package is.hail

import org.apache.log4j._
import collection.JavaConverters._

object Test {
  private val log = Logger.getLogger(getClass.getName())

  def main(args: Array[String]): Unit = {
    Runtime.getRuntime().addShutdownHook(
      new Thread() { override def run(): Unit = LogManager.shutdown() })

    log.error("ONE")
    QoBOutputStreamManager.instance.changeFile("file2")
    log.error("TWO")
    QoBOutputStreamManager.instance.changeFile("file3")
    log.error("THREE")
    QoBOutputStreamManager.instance.changeFile("file4")
    LogManager.getRootLogger().error("FOUR")
  }
}
