import org.apache.commons.logging.impl.SLF4JLog
import org.apache.log4j.MDC
import org.apache.log4j.NDC

class LoggingGrailsPlugin {
  // the plugin version
  def version = '0.1-SNAPSHOT'
  // groupId used by 'grails maven-install'
  //def groupId = 'org.grails.plugins'
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "2.1 > *"
  // the other plugins this plugin depends on
  def dependsOn = [
    pluginConfig: '0.1.5' // Plugin to simplify configuration of dependent applications
  ]
  // resources that are excluded from plugin packaging
  def pluginExcludes = [
    "grails-app/views/error.gsp"
  ]
  // package as a jar not a zip
  def packaging = 'binary'

  def title = "Logging Plugin" // Headline display name of the plugin
//def author = ""
//def authorEmail = ""
  def description = 'Common logging facilities for projects'

  // URL to the plugin's documentation
//def documentation = "http://grails.org/plugin/logging"

  // Extra (optional) plugin metadata

  // License: one of 'APACHE', 'GPL2', 'GPL3'
  def license = "APACHE"

  // Details of company behind the plugin (if there is one)
//def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

  // Any additional developers beyond the author specified above.
//def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

  // Location of the plugin's issue tracker.
//def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

  // Online location of the plugin's browseable source code.
//def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

  def doWithWebDescriptor = { xml ->
    // Implement additions to web.xml (optional), this event occurs before
  }

  def doWithSpring = {
    // Implement runtime spring config (optional)
  }

  def doWithDynamicMethods = { ctx ->
    // add useful methods to the 'log' object that Grails provides to our application
    
    SLF4JLog.metaClass.withMdc = { String key, Object value, Closure op ->
      if (key) MDC.put(key, value == null ? '' : value.toString())
      try {
        op()
      } finally {
        if (key) MDC.remove(key)
      }
    }
    
    SLF4JLog.metaClass.withNdc = { Object message, Closure op ->
      NDC.push(message?.toString())
      try {
        op()
      } finally {
        NDC.pop()
      }
    }
  }

  def doWithApplicationContext = { applicationContext ->
    // Implement post initialization spring config (optional)
  }

  def onChange = { event ->
    // Implement code that is executed when any artefact that this plugin is
    // watching is modified and reloaded. The event contains: event.source,
    // event.application, event.manager, event.ctx, and event.plugin.
  }

  def onConfigChange = { event ->
    // Implement code that is executed when the project configuration changes.
    // The event is the same as for 'onChange'.
  }

  def afterConfigMerge = {config, mergeCtx ->
    // Validate that the application has provided all the required settings (optional)
  }

  def onShutdown = { event ->
    // Implement code that is executed when the application shuts down (optional)
  }  
}
