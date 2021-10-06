package com.wez.rocketmq.rocketmq.service;

import com.alibaba.fastjson.JSONObject;
import com.wez.rocketmq.rocketmq.dal.AccountInfoDao;
import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountInfoServiceImpl
 *
 * @Author wez
 * @Time 2021/10/2 11:24
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     * 向mq发送转账消息
     * @param accountChangeEvent
     */
    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        // 1. 发送Half消息

        //将accountChangeEvent转成json
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        String jsonString = jsonObject.toJSONString();
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();
        //发送一条事务消息
        /**
         * String destination topic，
         * Message<?> message, 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("topic_txmsg", message,null);
    }

    /**
     * 3. 执行本地事务：更新账户，扣减金额
     * <br/>注意：需要做幂等判断
     * @param accountChangeEvent
     */
    @Transactional
    @Override
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        // 幂等判断
        if(accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0){
            return;
        }

        // 扣减金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount() * -1);
        // 添加事务日志
        accountInfoDao.addTx(accountChangeEvent.getTxNo());

        // 人为制造异常
        if(accountChangeEvent.getAmount() == 3){
            throw new RuntimeException("人为制造异常");
        }
    }
}
