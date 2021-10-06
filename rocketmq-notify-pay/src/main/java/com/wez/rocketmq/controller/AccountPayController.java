package com.wez.rocketmq.controller;

import com.wez.rocketmq.entity.AccountPay;
import com.wez.rocketmq.service.AccountPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * AccountPayController
 *
 * @Author wez
 * @Time 2021/10/2 23:10
 */
@RestController
public class AccountPayController {

    @Autowired
    private AccountPayService accountPayService;

    //充值
    @GetMapping(value = "/paydo")
    public AccountPay pay(AccountPay accountPay){
        //生成事务编号
        String txNo = UUID.randomUUID().toString();
        accountPay.setId(txNo);
        return accountPayService.insertAccountPay(accountPay);
    }

    //查询充值结果
    @GetMapping(value = "/payresult")
    public AccountPay payresult(@RequestParam("txNo") String txNo){
        return accountPayService.getAccountPay(txNo);
    }

}
