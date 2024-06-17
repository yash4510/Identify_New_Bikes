package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features"},
glue ={"StepDefinition","Hooks"},
plugin= {
		"pretty","html:reports/myreport.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
},
monochrome = true)
public class TestRunner {

}