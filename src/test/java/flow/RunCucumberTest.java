package flow;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "flow.core.cucumber.SendMetricsPlugin"
                //"html:target/cucumber-html-report.html",
                //"json:target/cucumber-json-report.json",
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features"
)
public class RunCucumberTest {
}
