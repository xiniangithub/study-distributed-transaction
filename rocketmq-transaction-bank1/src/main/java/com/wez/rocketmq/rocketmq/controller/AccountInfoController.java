package com.wez.rocketmq.rocketmq.controller;

import com.wez.rocketmq.rocketmq.model.AccountChangeEvent;
import com.wez.rocketmq.rocketmq.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * AccountInfoController
 *
 * @Author wez
 * @Time 2021/10/2 11:21
 */
@RestController
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping(value = "/transfer")
    public String transfer(@RequestParam("accountNo")String accountNo, @RequestParam("amount") Double amount){
        //创建一个事务id，作为消息内容发到mq
        String txNo = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(accountNo, amount, txNo);
        //发送消息
        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功";
    }
}
