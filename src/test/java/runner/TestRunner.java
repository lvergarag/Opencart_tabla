package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "src/test/resources/features",
 glue = {"runner", "stepdefs"},
 plugin = {"pretty"}

)
public class TestRunner {
}