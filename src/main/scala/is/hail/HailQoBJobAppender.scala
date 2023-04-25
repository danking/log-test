package is.hail

import java.io._
// import org.apache.log4j._
// import org.apache.log4j.spi._
import org.apache.logging.log4j.core._
import org.apache.logging.log4j.core.layout._
import org.apache.logging.log4j.core.appender._
import org.apache.logging.log4j.core.config.plugins._

object HailQoBJobAppender {
  @PluginFactory
  def createAppender(
    @PluginAttribute("name") name: String,
    @PluginAttribute("ignoreExceptions") ignoreExceptions: Boolean,
    @PluginElement("Layout") layout: Layout[_],
    @PluginElement("Filters") filter: Filter
  ): HailQoBJobAppender = {
    return new HailQoBJobAppender()
  }

  private var theOneHailQoBJobAppender: HailQoBJobAppender = null

  def getTheOneHailQoBJobAppender: HailQoBJobAppender = theOneHailQoBJobAppender
}

@Plugin(name = "HailQoBJobAppender", category = "Core", elementType = "appender", printObject = true)
class HailQoBJobAppender extends AbstractAppender(
  "HailQoBJobAppender", null, PatternLayout.createDefaultLayout(), false, null
) {
  private[this] val logFormat: String = "%d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}: %p: %m%n"
  private[this] val layout = PatternLayout.createDefaultLayout()
  private[this] var fio = new PrintWriter(new BufferedOutputStream(new FileOutputStream("file1")))
  System.err.println("I am alive")

  if (HailQoBJobAppender.theOneHailQoBJobAppender != null) {
    throw new RuntimeException("absolutely not.")
  }

  HailQoBJobAppender.theOneHailQoBJobAppender = this

  override def append(event: LogEvent): Unit = {
    val s = layout.toSerializable(event)
    System.err.println("STDERR: " + s)
    fio.write(s)
  }

  def close() = {
    fio.flush()
    fio.close()
  }

  def requiresLayout(): Boolean = false

  def changeFile(file: String): Unit = {
    System.err.println("changeFile")
    fio.flush()
    fio.close()
    fio = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))
  }
}
