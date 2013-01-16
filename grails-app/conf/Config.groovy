// configuration for plugin testing - will not be included in the plugin zip
import org.apache.log4j.Level
import logging.AnsiConsoleAppender

log4j = {
  appenders {
    appender new AnsiConsoleAppender(name:'stdout', threshold: Level.ALL,
        layout:pattern(conversionPattern: '%d{ISO8601} [%15.15t] %-5p %30.30c - %m%n'))
  }
}
