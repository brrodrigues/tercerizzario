package tercerizzario.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

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
@EnableEurekaServer
public class ApplicationServer {

    public static void main(String... args) {
        SpringApplication.run(ApplicationServer.class, args);
    }
}
