/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author bruno
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:supplierrepository.integration",
        tags = {"@restApiIntegration", "~@ignore"},
        plugin = {"html:target/cucumber-report/supplierInvetoryIntegration",
            "json:target/cucumber-report/supplierInvetoryIntegration.json"})
public class SupplierInventoryIntegrationTest {

}
