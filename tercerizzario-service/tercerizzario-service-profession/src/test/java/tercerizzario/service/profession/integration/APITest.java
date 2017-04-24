/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author bruno
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:api.integration",
        tags = {"@restApiIntegration", "~@ignore"},
        plugin = {"html:target/cucumber-report/professionInvetoryIntegration",
            "json:target/cucumber-report/professionInvetoryIntegration.json"})
public class APITest {

}
