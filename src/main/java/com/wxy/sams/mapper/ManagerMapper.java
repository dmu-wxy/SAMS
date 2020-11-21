package com.wxy.sams.mapper;

import com.wxy.sams.model.Manager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ManagerMapper {

    @Insert("insert into manager(mname,mphone,memail,duty,gender,birth,password) " +
            "values(#{mname},#{mphone},#{memail},#{duty},#{gender},#{birth},#{password})")
    public void insert(Manager manager);

    @Update("update manager set mname = #{mname}," +
            "mphone = #{mphone}," +
            "memail = #{memail}," +
            "duty = #{duty}," +
            "gender = #{gender}, " +
            "birth = #{birth} " +
            "where mid = #{mid}")
    public void update(Manager manager);

    @Delete("delete from manager where mid = #{mid}")
    public void delete(int mid);

    @Select("select * from manager where mphone = #{mphone}")
    public Manager findByPhone(String mphone);

    @Select("select * from manager where memail = #{memail}")
    public Manager findByEmail(String memail);
    @Select("select * from manager where mid = #{mid}")
    public Manager findById(String mid);
}
