package com.ricardovz.learning.checkout;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = "com.ricardovz.learning.checkout"
)
public class AcceptanceTestsIT {
}
