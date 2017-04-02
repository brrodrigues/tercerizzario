package tercerizzario.service.gateway.zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import tercerizzario.service.gateway.zull.filter.AppFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bruno
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayApp {

    public static void main(String... args) {
        SpringApplication.run(GatewayApp.class, args);
    }

    @Bean
    public AppFilter appFilter() {
        return new AppFilter();
    }

}
