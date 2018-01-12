package edu.sample.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

@Service
public class JiraIntegrationServiceImpl implements JiraIntegrationService {
 	
	@Value("${jira.authentication.username}")
    private String username;
	
	@Value("${jira.authentication.password}")
	private String password;
	
	@Value("${jira.host.url}")
	private String url;
	
	public JiraIntegrationServiceImpl() {
		super();
	}

	@Override
	public String searchIssue() {
		StringBuilder sb = new StringBuilder();
		try {
    		JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
			JiraRestClient restClient = restClientFactory.createWithBasicHttpAuthentication(
					new URI(url), username, password);
			Promise<SearchResult> result = 
					restClient.getSearchClient().searchJql("(project = 'Project Id' AND  status = 'READY FOR REVIEW')", 1, 0, null);
					
			SearchResult searchResult = result.get();
			searchResult.getIssues().forEach(i -> sb.append(i.getKey() + " - " + i.getField("customfield_10501").getValue() + " </br> "));
		} catch (URISyntaxException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


}
