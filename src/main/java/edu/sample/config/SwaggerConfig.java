package edu.sample.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(getJiraIntegrationApiInfo())
          .globalResponseMessage(RequestMethod.PUT, buildVaquinhaDefaultResponseMessageList())
          .globalResponseMessage(RequestMethod.POST, buildPostDefaultResponseMessageList())
          .globalResponseMessage(RequestMethod.GET, buildVaquinhaDefaultResponseMessageList())
          .select()
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) 
          .paths(PathSelectors.any()) 
          .build();                                           
    }

    private ApiInfo getJiraIntegrationApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Jira Integration", "Sample of Jira Integration", "1.0", "Internal Terms", getJiraIntegrationApiInfoContact(), "Bradesco Internal use only", null);
        return apiInfo;
    }

    private Contact getJiraIntegrationApiInfoContact() {
        Contact apiContact = new Contact("Fernando Machado", null, "https://github.com/fernandomac");
        return apiContact;
    }
    
    private List<ResponseMessage> buildVaquinhaDefaultResponseMessageList() {
        List<HttpStatus> defaultHttpStatuses = getVaquinhaDefaultResponseHttpStatus();
        List<ResponseMessage> defaultResponses = buildResponseMessageList(defaultHttpStatuses);
        return defaultResponses;
    }
    
    private List<ResponseMessage> buildPostDefaultResponseMessageList() {
        List<HttpStatus> defaultHttpStatuses = Arrays.asList(HttpStatus.OK, HttpStatus.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR);
        List<ResponseMessage> defaultResponses = buildResponseMessageList(defaultHttpStatuses);
        return defaultResponses;
    }

    private List<ResponseMessage> buildResponseMessageList(List<HttpStatus> defaultHttpStatuses) {
        List<ResponseMessage> defaultResponses = new ArrayList<ResponseMessage>();
        defaultHttpStatuses.stream()
                .forEach(status -> defaultResponses.add(new ResponseMessageBuilder().code(status.value())
                        .message(status.getReasonPhrase()).build()));
        return defaultResponses;
    }

    private List<HttpStatus> getVaquinhaDefaultResponseHttpStatus() {
        return Arrays.asList(HttpStatus.OK, HttpStatus.UNAUTHORIZED, HttpStatus.FORBIDDEN, HttpStatus.NOT_FOUND, HttpStatus.GONE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
