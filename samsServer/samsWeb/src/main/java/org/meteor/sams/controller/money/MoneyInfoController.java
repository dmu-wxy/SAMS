package org.meteor.sams.controller.money;

import org.meteor.sams.model.Money;
import org.meteor.sams.model.RespPageBean;
import org.meteor.sams.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/money/info")
public class MoneyInfoController {

    @Autowired
    private MoneyService moneyService;

    @GetMapping("/")
    public RespPageBean getMoneyByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Money money, String name){
        return moneyService.getMoneyByPage(page,size,money,name);
    }
}
