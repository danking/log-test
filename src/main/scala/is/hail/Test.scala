package is.hail

import org.apache.log4j._
import collection.JavaConverters._

object Test {
  private val log = Logger.getLogger(getClass.getName())

  def main(args: Array[String]): Unit = {
    log.error("ONE")
    HailQoBJobAppender.getTheOneHailQoBJobAppender.changeFile("file2")
    log.error("TWO")
    HailQoBJobAppender.getTheOneHailQoBJobAppender.changeFile("file3")
    log.error("THREE")
    HailQoBJobAppender.getTheOneHailQoBJobAppender.changeFile("file4")
    LogManager.getRootLogger().error("FOUR")
  }
}
