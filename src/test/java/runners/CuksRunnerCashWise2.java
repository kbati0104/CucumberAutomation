package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "html:target/cucumberReports.html",
        features = "src/test/resources/features",
        glue="steps",
        tags="@cashwise2",
        dryRun=false
)

public class CuksRunnerCashWise2 {
}
