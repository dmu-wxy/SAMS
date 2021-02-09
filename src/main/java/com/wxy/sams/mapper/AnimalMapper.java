package com.wxy.sams.mapper;

import com.wxy.sams.model.Animal;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AnimalMapper {

    @Select("select * from animal")
    public List<Animal> findAll();

    @Select("select * from animal where aid = #{aid}")
    public Animal findById(int aid);

    @Insert("insert into animal(aname,breed,p_addr,gender,birth) " +
            "values(#{aname},#{breed},#{p_addr},#{gender},#{birth})")
    public int insert(Animal animal);

    @Update("update animal set aname = #{aname}," +
            "breed = #{breed}," +
            "p_addr = #{p_addr}," +
            "gender = #{gender}," +
            "birth = #{birth} where aid = #{aid}")
    public Integer update(Animal animal);

    @Delete("delete from animal where aid = #{aid}")
    public Integer delete(int aid);


    @Select({
            "<script>",
            "select * from animal ",
            "<if test='keywords != null'>",
            "where aname like concat('%',#{keywords},'%')",
            "</if>",
            "order by aid limit #{page},#{size}",
            "</script>"
    })
    List<Animal> getAnimalByPage(Integer page, Integer size,@Param("keywords") String keywords);

    @Select({
            "<script>",
            "select count(aid) from animal ",
            "<if test = 'keywords != null' >",
            "where aname like concat('%',#{keywords},'%')",
            "</if>",
            "</script>"
    })
    Long getTotal(@Param("keywords") String keywords);
}
