package edu.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import edu.sample.service.JiraIntegrationService;

@ContextConfiguration(classes = {TestConfig.class})
@RunWith(SpringRunner.class)
public class JiraIntegrationServiceTest {
	
	@Autowired
	private JiraIntegrationService jiraIntegrationService;
	
	@Test
	public void shouldSearchIssue(){
		System.out.println(jiraIntegrationService.searchIssue());
	}

}
