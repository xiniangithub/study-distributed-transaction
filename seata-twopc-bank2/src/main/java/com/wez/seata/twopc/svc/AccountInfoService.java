package com.wez.seata.twopc.svc;

/**
 * AccountInfoService
 *
 * @Author wez
 * @Time 2021/9/30 21:56
 */
public interface AccountInfoService {

    /**
     * 张三扣减金额
     * @param accountNo 账户序号
     * @param amount 转账金额
     */
    void updateAccountBalance(String accountNo, Double amount);

}
