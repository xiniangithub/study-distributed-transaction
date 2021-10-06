package com.wez.rocketmq.client;

import com.wez.rocketmq.client.fallback.PayFallback;
import com.wez.rocketmq.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator.
 * 远程调用pay充值系统
 */
@FeignClient(value = "rocketmq-notify-pay", fallback = PayFallback.class)
public interface PayClient {

    //远程调用充值系统的接口查询充值结果
    @GetMapping(value = "/rocketmq-notify-pay/payresult", produces = MediaType.APPLICATION_JSON_VALUE)
    AccountPay payresult(@RequestParam("txNo") String txNo);
}
