package com.wez.seata.twopc.svc;

import com.wez.seata.twopc.client.Bank2Client;
import com.wez.seata.twopc.dal.AccountInfoRepository;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
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
    @Autowired
    private Bank2Client bank2Client;

    @GlobalTransactional
    @Transactional
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        LOGGER.info("bank1 service begin,XID：{}", RootContext.getXID());

        //扣减张三的金额
        accountInfoRepository.updateAccountBalance(accountNo,amount *-1);
        //调用李四微服务，转账
        String transfer = bank2Client.transfer(amount);
        if("fallback".equals(transfer)){
            //调用李四微服务异常
            throw new RuntimeException("调用李四微服务异常");
        }
        if(amount == 2){
            //人为制造异常
            throw new RuntimeException("bank1 make exception..");
        }
    }
}
