package com.wez.seata.twopc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wez.seata.twopc.client.fallback"})
@SpringBootApplication
public class Bank1ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(Bank1ApplicationMain.class, args);
    }
}
