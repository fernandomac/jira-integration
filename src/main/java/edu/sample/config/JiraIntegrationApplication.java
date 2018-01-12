package edu.sample.config;

import static edu.sample.common.logging.ContextExternalPropertyFileLogger.logApplicationPropertyResources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = "edu.sample")
@PropertySources({
	 @PropertySource(value = "classpath:jira-sample.properties"),
     @PropertySource(value = "file:///config/sample/jira-sample.properties", ignoreResourceNotFound = true)
})
public class JiraIntegrationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JiraIntegrationApplication.class, args);
        logApplicationPropertyResources(context);
    }
}
