package com.wez.seata.twopc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Bank2ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(Bank2ApplicationMain.class, args);
    }
}
