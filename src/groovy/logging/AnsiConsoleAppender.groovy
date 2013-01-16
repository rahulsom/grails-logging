package logging

import org.apache.log4j.ConsoleAppender
import org.apache.log4j.Priority
import org.apache.log4j.Level
import org.apache.log4j.spi.LoggingEvent

/**
 * Uses Ansi Colors to log.
 */
class AnsiConsoleAppender extends ConsoleAppender {
  private static final int NORMAL = 0
  private static final int BRIGHT = 1
  private static final int FOREGROUND_BLACK = 30
  private static final int FOREGROUND_RED = 31
  private static final int FOREGROUND_GREEN = 32
  private static final int FOREGROUND_YELLOW = 33
  private static final int FOREGROUND_BLUE = 34
  private static final int FOREGROUND_MAGENTA = 35
  private static final int FOREGROUND_CYAN = 36
  private static final int FOREGROUND_WHITE = 37

  private static final String PREFIX = "\u001b["
  private static final String SUFFIX = "m"
  private static final char SEPARATOR = ';'
  
  private static final String END_COLOR = PREFIX + SUFFIX

  private static final String FATAL_COLOR = PREFIX + BRIGHT + SEPARATOR + FOREGROUND_RED + SUFFIX
  private static final String ERROR_COLOR = PREFIX + NORMAL + SEPARATOR + FOREGROUND_RED + SUFFIX
  private static final String WARN_COLOR = PREFIX + NORMAL + SEPARATOR + FOREGROUND_YELLOW + SUFFIX
  private static final String INFO_COLOR = PREFIX + NORMAL + SEPARATOR + FOREGROUND_GREEN + SUFFIX
  private static final String DEBUG_COLOR = PREFIX + NORMAL + SEPARATOR + FOREGROUND_CYAN + SUFFIX
  private static final String TRACE_COLOR = PREFIX + NORMAL + SEPARATOR + FOREGROUND_BLUE + SUFFIX

  private static final String[] COLORS = [ TRACE_COLOR, DEBUG_COLOR, INFO_COLOR, WARN_COLOR, ERROR_COLOR, FATAL_COLOR ]
    
  boolean useColors

  AnsiConsoleAppender() {
    if (System.getenv('TERM')) {
      useColors = true
    }
  }

  /**
   * Wraps the ANSI control characters around the
   * output from the super-class Appender.
   */
  @Override
  protected void subAppend(LoggingEvent event) {
    if (useColors) {
      this.qw.write(getColor(event.level))
      super.subAppend(event)
      this.qw.write(END_COLOR)

      if (this.immediateFlush) {
        // second flush
        this.qw.flush()
      }
    } else {
      super.subAppend(event)
    }
  }

  /**
   * Get the appropriate control characters to change
   * the color for the specified logging level.
   */
  private String getColor(Level level) {
    int levelIndex = (int) (level.toInt() / 10000); // log4j uses multiples of 10000
    if (levelIndex < 1) {
      COLORS[0]
    } else if (levelIndex >= 5) {
      COLORS[5]
    } else {
      COLORS[levelIndex]
    }
  }
}
