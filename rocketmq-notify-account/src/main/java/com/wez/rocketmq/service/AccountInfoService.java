package com.wez.rocketmq.service;

import com.wez.rocketmq.entity.AccountPay;
import com.wez.rocketmq.model.AccountChangeEvent;

/**
 * AccountInfoService
 *
 * @Author wez
 * @Time 2021/10/2 23:20
 */
public interface AccountInfoService {

    /**
     * 更新账户金额
     * @param accountChange
     */
    void updateAccountBalance(AccountChangeEvent accountChange);

    /**
     * 查询充值结果（远程调用）
     * @param txNo
     * @return
     */
    AccountPay queryPayResult(String txNo);

}
