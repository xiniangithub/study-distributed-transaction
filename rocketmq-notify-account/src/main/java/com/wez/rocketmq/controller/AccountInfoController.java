package com.wez.rocketmq.controller;

import com.wez.rocketmq.entity.AccountPay;
import com.wez.rocketmq.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AccountInfoController
 *
 * @Author wez
 * @Time 2021/10/2 23:18
 */
@RestController
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 主动查询充值结果
     * @param txNo
     * @return
     */
    @GetMapping(value = "/payresult")
    public AccountPay result(@RequestParam("txNo") String txNo){
        AccountPay accountPay = accountInfoService.queryPayResult(txNo);
        return accountPay;
    }
}
