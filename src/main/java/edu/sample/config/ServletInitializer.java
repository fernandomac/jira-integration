package edu.sample.config;

import static edu.sample.common.logging.ContextExternalPropertyFileLogger.logApplicationPropertyResources;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.web(true);
        return application.sources(JiraIntegrationApplication.class);
    }

    @Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    WebApplicationContext foundWebApplicationContext = WebApplicationContextUtils.findWebApplicationContext(servletContext);
	    logApplicationPropertyResources(foundWebApplicationContext);
	}
}
