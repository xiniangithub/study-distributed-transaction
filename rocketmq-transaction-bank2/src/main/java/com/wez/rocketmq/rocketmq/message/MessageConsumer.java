package com.wez.rocketmq.rocketmq.message;

import com.alibaba.fastjson.JSONObject;
import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;
import com.wez.rocketmq.rocketmq.service.AccountInfoService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MessageConsumer
 *
 * @Author wez
 * @Time 2021/10/2 11:57
 */
@Component
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsg_bank2",topic = "topic_txmsg")
public class MessageConsumer implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    AccountInfoService accountInfoService;

    /**
     * 接收消息
     * @param message
     */
    @Override
    public void onMessage(String message) {
        LOGGER.info("开始消费消息:{}",message);
        //解析消息
        JSONObject jsonObject = JSONObject.parseObject(message);
        String accountChangeString = jsonObject.getString("accountChange");
        //转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //设置账号为李四的
        accountChangeEvent.setAccountNo("2");
        //更新本地账户，增加金额
        accountInfoService.addAccountInfoBalance(accountChangeEvent);
    }
}
