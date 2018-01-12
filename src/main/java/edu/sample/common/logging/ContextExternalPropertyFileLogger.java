package edu.sample.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public final class ContextExternalPropertyFileLogger {
    
    private static Logger LOG = LoggerFactory.getLogger(ContextExternalPropertyFileLogger.class);
    
    public static void logApplicationPropertyResources(ApplicationContext context) {
        if(context instanceof ConfigurableApplicationContext){
            LOG.info("External property files loading, in non dev environments it should contain 1 file loaded from classe path and 1 from file URL");
            ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) context;
            MutablePropertySources propertySources = configContext.getEnvironment().getPropertySources();
            propertySources.forEach(source -> printSourceNameIfExternalPropertyFile(source));
        }
    }
    
    private static void printSourceNameIfExternalPropertyFile(org.springframework.core.env.PropertySource<?> source) {
        if (source instanceof CompositePropertySource  || source instanceof ResourcePropertySource ) {
            LOG.info("Loaded file: " + source.getName());
        }
    }

}
