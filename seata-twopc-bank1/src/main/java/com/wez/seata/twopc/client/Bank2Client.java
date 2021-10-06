package com.wez.seata.twopc.client;

import com.wez.seata.twopc.client.fallback.Bank2ClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Bank2Client
 *
 * @Author wez
 * @Time 2021/9/30 22:01
 */
@FeignClient(name="service-bank2", fallback = Bank2ClientFallback.class)
public interface Bank2Client {

    /**
     * 远程调用李四的微服务
     * @param amount 转账金额
     * @return
     */
    @GetMapping("/service-bank2/transfer")
    String transfer(@RequestParam("amount") Double amount);

}
