package org.meteor.sams.service.impl;

import org.meteor.sams.mapper.MoneyMapper;
import org.meteor.sams.model.Animal;
import org.meteor.sams.model.Money;
import org.meteor.sams.model.RespPageBean;
import org.meteor.sams.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public RespPageBean getMoneyByPage(Integer page, Integer size, Money money, String name) {
        if(page != null && size != null){
            page = (page - 1) * size;
        }
        List<Money> monies = moneyMapper.getMoneyByPage(page,size,money,name);
        Long total = moneyMapper.getTotal(money,name);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(monies);
        respPageBean.setTotal(total);
        return respPageBean;
    }
}
