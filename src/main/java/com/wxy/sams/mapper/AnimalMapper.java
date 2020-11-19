package com.wxy.sams.mapper;

import com.wxy.sams.model.Animal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AnimalMapper {

    @Select("select * from animal")
    public List<Animal> findAll();

    @Select("select * from animal where aid = #{aid}")
    public Animal findById(int aid);

    @Insert("insert into animal(aname,breed,p_addr,gender,birth) " +
            "values(#{aname},#{breed},#{p_addr},#{gender},#{birth})")
    public void insert(Animal animal);

    @Update("update animal set aname = #{aname}," +
            "breed = #{breed}," +
            "p_addr = #{p_addr}," +
            "gender = #{gender}," +
            "birth = #{birth} where aid = #{aid}")
    public void update(Animal animal);

    @Delete("delete from animal where aid = #{aid}")
    public void delete(int aid);
}
