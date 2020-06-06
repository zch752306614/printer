package com.lilanz.printer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication()
//@EnableEurekaClient
//@EnableFeignClients
@MapperScan(basePackages = "com.lilanz.printer.dao")
public class PrinterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrinterApplication.class, args);
    }

}
