package com.wez.seata.twopc.ctrl;

import com.wez.seata.twopc.svc.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bank1Controller
 *
 * @Author wez
 * @Time 2021/9/30 21:54
 */
@RestController
public class Bank2Controller {

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 接收张三的转账
     * @param amount
     * @return
     */
    @GetMapping("/transfer")
    public String transfer(Double amount){
        //李四增加金额
        accountInfoService.updateAccountBalance("2", amount);
        return "bank2"+amount;
    }
}
