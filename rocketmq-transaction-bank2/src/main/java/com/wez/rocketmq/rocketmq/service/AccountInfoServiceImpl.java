package com.wez.rocketmq.rocketmq.service;

import com.wez.rocketmq.rocketmq.dao.AccountInfoDao;
import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountInfoServiceImpl
 *
 * @Author wez
 * @Time 2021/10/2 11:59
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    AccountInfoDao accountInfoDao;

    @Transactional
    @Override
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        LOGGER.info("bank2更新本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());

        // 幂等校验
        if(accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0){
            LOGGER.info("bank2已更新过本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
            return ;
        }

        //增加金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //添加事务记录，用于幂等
        accountInfoDao.addTx(accountChangeEvent.getTxNo());

        if(accountChangeEvent.getAmount() == 4){
            throw new RuntimeException("人为制造异常");
        }
    }
}
