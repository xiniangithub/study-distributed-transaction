package com.wez.rocketmq.message;

import com.alibaba.fastjson.JSON;
import com.wez.rocketmq.entity.AccountPay;
import com.wez.rocketmq.model.AccountChangeEvent;
import com.wez.rocketmq.service.AccountInfoService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
@RocketMQMessageListener(topic = "topic_notifymsg",consumerGroup = "rocketmq_notify_pay")
public class MessageListener implements RocketMQListener<AccountPay> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    AccountInfoService accountInfoService;

    /**
     * 接收消息
     * @param accountPay
     */
    @Override
    public void onMessage(AccountPay accountPay) {
        LOGGER.info("接收到消息：{}", JSON.toJSONString(accountPay));

        if("success".equals(accountPay.getResult())){
            //更新账户金额
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountPay.getAccountNo());
            accountChangeEvent.setAmount(accountPay.getPayAmount());
            accountChangeEvent.setTxNo(accountPay.getId());
            accountInfoService.updateAccountBalance(accountChangeEvent);
        }

        LOGGER.info("处理消息完成：{}", JSON.toJSONString(accountPay));
    }
}
