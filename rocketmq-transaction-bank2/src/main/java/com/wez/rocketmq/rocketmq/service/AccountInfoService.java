package com.wez.rocketmq.rocketmq.service;

import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;

/**
 * AccountInfoService
 *
 * @Author wez
 * @Time 2021/10/2 11:58
 */
public interface AccountInfoService {

    /**
     * 更新账户，增加金额
     * @param accountChangeEvent
     */
    void addAccountInfoBalance(AccountChangeEvent accountChangeEvent);

}
