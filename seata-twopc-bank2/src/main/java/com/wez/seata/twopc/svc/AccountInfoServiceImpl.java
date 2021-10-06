package com.wez.seata.twopc.svc;

import com.wez.seata.twopc.dal.AccountInfoRepository;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountInfoServiceImpl
 *
 * @Author wez
 * @Time 2021/9/30 21:57
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Transactional
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        LOGGER.info("bank2 service begin,XID：{}",RootContext.getXID());

        //李四增加金额
        accountInfoRepository.updateAccountBalance(accountNo,amount);
        if(amount==3){
            //人为制造异常
            throw new RuntimeException("bank2 make exception..");
        }
    }
}
