package logging

class DynamicMethodsTests extends GroovyTestCase {
  void testWithMdc() {
    log.info('no mdc')
    log.withMdc('a', null) {
      log.info('mdc1')
    }
    log.withMdc('a', 'foo') {
      log.info('mdc2')
    }
    log.withMdc([a: 'foo', b: 'bar']) {
      log.info('mdc3')
    }
    log.info('no mdc')
  }
  
  void testWithNdc() {
    log.info('no ndc')
    log.withNdc('a') {
      log.info('ndc1')
      log.withNdc(null) {
        log.info('ndc2')
      }
    }
    log.info('no ndc')
  }
}
