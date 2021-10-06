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
public class Bank1Controller {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping("/transfer")
    public String transfer(Double amount) {
        accountInfoService.updateAccountBalance("1", amount);
        return "bank1 transfer "+amount;
    }
}
