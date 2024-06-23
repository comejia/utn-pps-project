package flow.core.jira;

import ch.qos.logback.classic.Logger;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import flow.configuration.IProperties;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.File;

/*
Testing and UAT for Flow
10101
 */


public class JiraHttpClient {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final IProperties props;

    public JiraHttpClient(IProperties props) {
        this.props = props;
    }

    public String sendIssue(JSONObject issue) {
        try {
            var response = Unirest.post(props.jiraBaseUrl() + props.jiraPathIssue())
                    .basicAuth(props.jiraUsername(), props.jiraPassword())
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(issue)
                    .asJson();
            if (response.getStatus() == 201) {
                var idIssue = response.getBody().getObject().getString("id");
                var keyIssue = response.getBody().getObject().getString("key");
                logger.info("Successfully send issue JIRA. Created task number: " + keyIssue);
                return idIssue;
            } else {
                logger.info("Error send issue to JIRA. Status code: " + response.getStatus() + " / " + response.getStatusText() + " Body: " + response.getBody());
                return "";
            }
        } catch (UnirestException e) {
            logger.info("Error send issue to JIRA. Error: " + e.getCause().getMessage());
            return "";
        }
    }

    public void addAttachment(String idIssue, File file) {
        try {
            var response = Unirest.post(props.jiraBaseUrl() + props.jiraPathIssue() + idIssue + "/attachments")
                    .basicAuth(props.jiraUsername(), props.jiraPassword())
                    .header("Accept", "application/json")
                    .header("X-Atlassian-Token", "no-check")
                    .field("file", file)
                    .asJson();
            if (response.getStatus() == 200) {
                logger.info("Successfully send attachement to JIRA");
            } else {
                logger.info("Error send attachement to JIRA. Status code: " + response.getStatus() + " / " + response.getStatusText() + " Body: " + response.getBody());
            }
        } catch (UnirestException e) {
            logger.info("Error send issue to JIRA. Error: " + e.getCause().getMessage());
        }
    }
}
