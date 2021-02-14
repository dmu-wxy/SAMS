package com.wxy.sams.mapper;

import com.wxy.sams.model.Animal;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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


//    @Select({
//            "<script>",
//            "select * from animal ",
//            "<if test='keywords != null'>",
//            "where aname like concat('%',#{keywords},'%')",
//            "</if>",
//            "order by aid ",
//            "<if test='page != null and size != null'>",
//            "limit #{page},#{size}",
//            "</if>",
//            "</script>"
//    })
//    List<Animal> getAnimalByPage(Integer page, Integer size,@Param("keywords") String keywords);

    @Select({
            "<script>",
            "select * from animal ",
            "<where>",
            "<if test = 'animal.aname != null' >",
            "and aname like concat('%',#{animal.aname},'%') ",
            "</if>",
            "<if test = 'animal.gender != null' >",
            "and gender = #{animal.gender} ",
            "</if>",
            "<if test = 'birthDate != null' >",
            "and birth between #{birthDate[0]} and #{birthDate[1]} ",
            "</if>",
            "</where>",
            "order by aid ",
            "<if test='page != null and size != null'>",
            "limit #{page},#{size}",
            "</if>",
            "</script>"
    })
    List<Animal> getAnimalByPage(Integer page, Integer size,@Param("animal") Animal animal,@Param("birthDate") Date[] birthDate);

//    @Select({
//            "<script>",
//            "select count(aid) from animal ",
//            "<if test = 'keywords != null' >",
//            "where aname like concat('%',#{keywords},'%')",
//            "</if>",
//            "</script>"
//    })
//    Long getTotal(@Param("keywords") String keywords);

    @Select({
            "<script>",
            "select count(aid) from animal ",
            "<where>",
            "<if test = 'animal.aname != null' >",
            "and aname like concat('%',#{animal.aname},'%') ",
            "</if>",
            "<if test = 'animal.gender != null' >",
            "and gender = #{animal.gender} ",
            "</if>",
            "<if test = 'birthDate != null' >",
            "and birth between #{birthDate[0]} and #{birthDate[1]}",
            "</if>",
            "</where>",
            "</script>"
    })
    Long getTotal(@Param("animal") Animal animal, @Param("birthDate") Date[] birthDate);

    @Insert({
            "<script>",
            "insert into animal(aname,breed,p_addr,gender,birth) values ",
            "<foreach collection='animals' separator=',' item='animal'>",
            "(#{animal.aname},#{animal.breed},#{animal.p_addr},#{animal.gender},#{animal.birth})",
            "</foreach>",
            "</script>"
    })
    int insertAnimals(@Param("animals") List<Animal> animals);


}
