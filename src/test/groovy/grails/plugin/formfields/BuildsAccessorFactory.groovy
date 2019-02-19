package grails.plugin.formfields

import grails.core.support.proxy.DefaultProxyHandler
import grails.testing.gorm.DataTest
import grails.testing.web.GrailsWebUnitTest
import org.grails.datastore.mapping.model.MappingContext
import org.grails.scaffolding.model.property.DomainPropertyFactoryImpl
import spock.lang.Specification

/**
 * Created by jameskleeh on 5/3/17.
 */
abstract class BuildsAccessorFactory extends Specification implements GrailsWebUnitTest, DataTest {

    void setupSpec() {
        defineBeans { ->
            def dpf = new DomainPropertyFactoryImpl(grailsDomainClassMappingContext: applicationContext.getBean("grailsDomainClassMappingContext", MappingContext), trimStrings: true, convertEmptyStringsToNull: true)

            beanPropertyAccessorFactory(BeanPropertyAccessorFactory) {
                constraintsEvaluator = ref('org.grails.beans.ConstraintsEvaluator')
                proxyHandler = new DefaultProxyHandler()
                grailsDomainClassMappingContext = ref("grailsDomainClassMappingContext")
                fieldsDomainPropertyFactory = dpf
            }
        }
    }

    BeanPropertyAccessorFactory getFactory() {
        applicationContext.getBean(BeanPropertyAccessorFactory)
    }
}