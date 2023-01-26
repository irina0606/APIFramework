package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/cucumber/features",
        glue={"cucumber/stepDefinitions"},
        plugin = "json:target/jsonReports/cucumber-report.json",
        tags= "@DeletePlace"
//        tags= "@AddPlace or @DeletePlace"       //   will execute both scenarios
//        tags= "@AddPlace and @DeletePlace"    //   will not execute if you applied only one tag in features file
//        tags= "not @DeletePlace"              //   will not execute particular scenario
//        tags= "@All"                          //   will execute if you apply before the Feature

)

public class TestRunner {

}
