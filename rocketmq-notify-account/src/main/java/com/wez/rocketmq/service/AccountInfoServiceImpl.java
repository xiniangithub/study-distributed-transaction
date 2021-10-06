package com.wez.rocketmq.service;

import com.wez.rocketmq.client.PayClient;
import com.wez.rocketmq.dao.AccountInfoDao;
import com.wez.rocketmq.entity.AccountPay;
import com.wez.rocketmq.model.AccountChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountInfoServiceImpl
 *
 * @Author wez
 * @Time 2021/10/2 23:22
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private PayClient payClient;

    /**
     * 更新账户金额
     * @param accountChange
     */
    @Transactional
    @Override
    public void updateAccountBalance(AccountChangeEvent accountChange) {
        //幂等校验
        if(accountInfoDao.isExistTx(accountChange.getTxNo())>0){
            return ;
        }
        int i = accountInfoDao.updateAccountBalance(accountChange.getAccountNo(), accountChange.getAmount());
        //插入事务记录，用于幂等控制
        accountInfoDao.addTx(accountChange.getTxNo());
    }

    /**
     * 远程调用查询充值结果
     * @param txNo
     * @return
     */
    @Override
    public AccountPay queryPayResult(String txNo) {
        //远程调用
        AccountPay payresult = payClient.payresult(txNo);
        if("success".equals(payresult.getResult())){
            //更新账户金额
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(payresult.getAccountNo());//账号
            accountChangeEvent.setAmount(payresult.getPayAmount());//金额
            accountChangeEvent.setTxNo(payresult.getId());//充值事务号
            updateAccountBalance(accountChangeEvent);
        }
        return payresult;
    }

}
