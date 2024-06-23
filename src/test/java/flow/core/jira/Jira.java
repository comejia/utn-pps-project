package flow.core.jira;

import flow.configuration.IProperties;
import flow.utils.UtilsCucumber;
import io.cucumber.java.Scenario;

import java.io.File;
import java.util.ArrayList;

public class Jira {

    private final Scenario scenario;
    private final IProperties props;

    public Jira(Scenario scenario, IProperties props) {
        this.scenario = scenario;
        this.props = props;
    }

    public void createTask() {
        var jiraHttpClient = new JiraHttpClient(props);
        var title = getTitle();
        var description = getDescripcion();
        var task_model = IssueModel.create(title, description, TypeIssue.TASK, get_labels());
        var taskKey = jiraHttpClient.sendIssue(task_model);
        jiraHttpClient.addAttachment(taskKey, new File(props.fileNameVideo()));
    }

    public Boolean canSendTask() {
        var tag = this.scenario.getSourceTagNames().stream().filter(t -> t.equals("@severity=critical")).findFirst();
        return this.scenario.isFailed() && tag.isPresent() && this.props.jiraCreateBugs();
    }

    private ArrayList<String> get_labels() {
        var labels = new ArrayList<String>();
        labels.add("automation-webclient");
        var stream_label = UtilsCucumber.getTag("@stream", this.scenario.getSourceTagNames());
        if (!stream_label.isEmpty()) {
            labels.add(stream_label);
            return labels;
        }
        return labels;
    }

    private String getDescripcion() {
        return "Step fallido: " + props.stepBroken() + "\n " +
                "URL TUF: " + props.jiraBaseUrl() + "/browse/" + UtilsCucumber.getTuf(this.scenario.getSourceTagNames());
    }

    private String getTitle() {
        return "automation-webclient - " + UtilsCucumber.getTuf(this.scenario.getSourceTagNames()) + " - Escenario: " + props.testName();
    }
}