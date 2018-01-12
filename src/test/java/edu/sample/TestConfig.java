package edu.sample;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = "edu.sample.service")
@Configuration
@PropertySources({
	 @PropertySource(value = "classpath:jira-sample.properties"),
    @PropertySource(value = "file:///config/sample/jira-sample.properties", ignoreResourceNotFound = true)
})
public class TestConfig {

}
