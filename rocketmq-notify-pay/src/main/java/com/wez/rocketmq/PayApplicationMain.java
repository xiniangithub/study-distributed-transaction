package com.wez.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * <br/>业务：
 * <br/>1、充值接口
 * <br/>2、充值完成要通知
 * <br/>3、充值结果查询接口
 */
@SpringBootApplication
public class PayApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(PayApplicationMain.class, args);
    }
}
