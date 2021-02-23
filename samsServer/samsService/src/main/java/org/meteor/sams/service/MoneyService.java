package org.meteor.sams.service;

import org.meteor.sams.model.Money;
import org.meteor.sams.model.RespPageBean;

public interface MoneyService {

    RespPageBean getMoneyByPage(Integer page, Integer size, Money money, String name);
}
