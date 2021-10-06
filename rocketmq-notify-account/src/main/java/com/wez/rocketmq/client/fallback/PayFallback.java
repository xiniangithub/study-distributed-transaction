package com.wez.rocketmq.client.fallback;

import com.wez.rocketmq.client.PayClient;
import com.wez.rocketmq.entity.AccountPay;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 **/
@Component
public class PayFallback implements PayClient {
    @Override
    public AccountPay payresult(String txNo) {
        AccountPay accountPay = new AccountPay();
        accountPay.setResult("fail");
        return accountPay;
    }
}
