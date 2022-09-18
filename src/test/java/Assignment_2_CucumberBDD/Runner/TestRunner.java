package Assignment_2_CucumberBDD.Runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
	        features="classpath:Features",           //Path of Feature folder which hold feature file
	        glue="Assignment_2_CucumberBDD.StepDef", // Path of StepDefinition file
	        tags= "@loginPositive", 
	        plugin = {"pretty",                      
	            "html:target/html/htmlReport.html",
	            "json:target/json/jsonReport.json",
	            },
	        monochrome=true,
	        publish= true,
	        dryRun=false
	        )

public class TestRunner {

}
