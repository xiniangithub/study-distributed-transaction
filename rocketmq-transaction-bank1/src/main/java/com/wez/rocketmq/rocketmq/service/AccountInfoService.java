package com.wez.rocketmq.rocketmq.service;

import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;

/**
 * AccountInfoService
 *
 * @Author wez
 * @Time 2021/10/2 11:24
 */
public interface AccountInfoService {

    /**
     * 向mq发送转账消息
     * @param accountChangeEvent
     */
    void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

    /**
     * 更新账户，扣减金额
     * @param accountChangeEvent
     */
    void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

}
