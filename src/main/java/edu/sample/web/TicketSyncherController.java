package edu.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sample.service.JiraIntegrationService;

@RestController
@RequestMapping(value="/sync")
@CrossOrigin
public class TicketSyncherController {
    
    @Autowired
    private JiraIntegrationService jiraIntegrationService;
    
    @GetMapping(value="/test", produces = MediaType.ALL_VALUE)
    public String teste(){
        return "Hello world";
    }
    
    @GetMapping(value="/content", produces = MediaType.ALL_VALUE)
    public String getContent(){
        return jiraIntegrationService.searchIssue();
    }

}
