package flow.core.jira;


/*
{
    "fields": {
       "project":
       {
          "id": "11219"
       },
       "summary": "Prueba automation",
       "description": "Es una prueba para la creacion automatica de tareas",
       "issuetype": {
          "name": "Task"
       }
   }
}
TUF: 10101
FAT: 11219
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class IssueModel {
    public static JSONObject create(String title, String description, TypeIssue typeIssue, ArrayList<String> labels) {
        var components = new JSONArray();
        var component = new JSONObject();
        component.put("name", "Automation_Failed");
        components.put(component);
        var data = new JSONObject();
        var fields = new JSONObject();
        var project = new JSONObject();
        var issueType = new JSONObject();
        issueType.put("name", typeIssue.getName());
        project.put("id", "10101");
        fields.put("project", project);
        fields.put("issuetype", issueType);
        fields.put("summary", title);
        fields.put("description", description);
        fields.put("components", components);
        fields.put("labels", labels);
        data.put("fields", fields);
        return data;
    }
}