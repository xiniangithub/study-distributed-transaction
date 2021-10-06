package com.wez.rocketmq.service;

import com.wez.rocketmq.entity.AccountPay;

/**
 * AccountPayService
 *
 * @Author wez
 * @Time 2021/10/2 23:12
 */
public interface AccountPayService {

    /**
     * 充值
     * @param accountPay
     * @return
     */
    AccountPay insertAccountPay(AccountPay accountPay);

    /**
     * 查询充值结果
     * @param txNo
     * @return
     */
    AccountPay getAccountPay(String txNo);

}
