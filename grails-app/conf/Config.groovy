// configuration for plugin testing - will not be included in the packaged plugin

import logging.AnsiConsoleAppender

log4j = {
  appenders {
    appender new AnsiConsoleAppender(
      name: 'stdout', 
      layout: pattern(conversionPattern: '%d{ISO8601} [%15.15t] %-5p %30.30c %x %X{a} %X{b} - %m%n')
    )
  }
  error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
         'org.codehaus.groovy.grails.web.pages', //  GSP
         'org.codehaus.groovy.grails.web.sitemesh', //  layouts
         'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
         'org.codehaus.groovy.grails.web.mapping', // URL mapping
         'org.codehaus.groovy.grails.commons', // core / classloading
         'org.codehaus.groovy.grails.plugins', // plugins
         'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
         'org.springframework',
         'org.hibernate',
         'net.sf.ehcache.hibernate'

  warn   'org.mortbay.log'

  debug  'logging'
  
  root {
    warn 'stdout'
  }
}
