/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * @author bruno
 */
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class SupplierClientApplication {

    public static void main(String... args) {
        SpringApplication.run(SupplierClientApplication.class, args);
    }

}
