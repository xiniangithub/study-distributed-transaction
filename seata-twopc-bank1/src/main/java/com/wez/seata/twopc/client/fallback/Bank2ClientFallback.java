package com.wez.seata.twopc.client.fallback;

import com.wez.seata.twopc.client.Bank2Client;
import org.springframework.stereotype.Component;

/**
 * Bank2ClientFallback
 *
 * @Author wez
 * @Time 2021/9/30 22:02
 */
@Component
public class Bank2ClientFallback implements Bank2Client {
    @Override
    public String transfer(Double amount) {
        return "fallback";
    }
}
