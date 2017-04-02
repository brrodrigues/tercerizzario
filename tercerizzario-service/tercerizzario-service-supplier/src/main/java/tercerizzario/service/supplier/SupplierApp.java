/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author bruno
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SupplierApp {

    public static void main(String... args) {
        SpringApplication.run(SupplierApp.class, args);
    }

}
