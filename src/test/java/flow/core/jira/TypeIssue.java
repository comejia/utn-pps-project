package flow.core.jira;

public enum TypeIssue {

    TASK("Task"),
    BUG("Bug");

    private final String name;

    TypeIssue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}