package com.wez.rocketmq.rocketmq.message;

import com.alibaba.fastjson.JSONObject;
import com.wez.rocketmq.rocketmq.dal.AccountInfoDao;
import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;
import com.wez.rocketmq.rocketmq.service.AccountInfoService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProducerMessageListener
 *
 * @Author wez
 * @Time 2021/10/2 11:41
 */
@Component
@RocketMQTransactionListener
public class ProducerMessageListener implements RocketMQLocalTransactionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerMessageListener.class);

    @Autowired
    AccountInfoService accountInfoService;
    @Autowired
    AccountInfoDao accountInfoDao;

    /**
     * 执行本地事务逻辑
     * @param message
     * @param o
     * @return
     */
    @Transactional
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 2. Half消息发送成功

        try {
            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");
            //将accountChange（json）转成AccountChangeEvent
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);

            //3. 执行本地事务：扣减金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);

            // 4. Commit or Rollback
            // 4.1 Commit
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            // 4.2 Rollback
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 回查事务状态
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // 5. 未收到4的确认消息，回查事务状态

        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("accountChange");
        //将accountChange（json）转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //事务id
        String txNo = accountChangeEvent.getTxNo();
        int existTx = accountInfoDao.isExistTx(txNo);
        if(existTx>0){
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

}
