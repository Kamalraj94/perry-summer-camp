package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", 
                 glue = { "stepdefination" },
                 plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
                 tags = "@SmokeTest" )


public class TestRunner {

}
