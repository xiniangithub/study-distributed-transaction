package com.wez.rocketmq.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RocketMQBank1ApplicationMain
 *
 * <br/>1. 发送Half消息
 * <br/>2. Half消息发送成功
 * <br/>3. 执行本地事务
 * <br/>4. Commit or Rollback
 * <br/>5. 未收到4的确认消息，回查事务状态
 * <br/>6. 检查本地事务的状态
 * @Author wez
 * @Time 2021/10/2 11:13
 */
@SpringBootApplication
public class RocketMQBank2ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(RocketMQBank2ApplicationMain.class, args);
    }
}
