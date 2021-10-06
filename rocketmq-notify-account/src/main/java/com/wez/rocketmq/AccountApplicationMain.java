package com.wez.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 * <br/>业务：
 * <br/>1、监听MQ，接收充值结果，根据充值结果完成账户金额修改。
 * <br/>2、主动查询充值系统，根据充值结果完成账户金额修改。
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AccountApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplicationMain.class, args);
    }
}
