package org.meteor.sams.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.meteor.sams.model.Money;

import java.util.List;

public interface MoneyMapper {


    //todo: 高级搜索
    @Select({
            "<script>",
            "select m.*,ma.mname as mName from money m,manager_money mm,manager ma where m.id = mm.MoneyId and mm.ManagerId = ma.mid order by id ",
            "<if test='page != null and size != null'>",
            "limit #{page},#{size}",
            "</if>",
            "</script>"
    })
    List<Money> getMoneyByPage(@Param("page") Integer page,@Param("size") Integer size, Money money, String name);

    //todo: 高级搜索
    @Select("select count(id) from money")
    Long getTotal(Money money, String name);
}
