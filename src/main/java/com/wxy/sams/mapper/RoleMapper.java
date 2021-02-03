package com.wxy.sams.mapper;

import com.wxy.sams.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleMapper {
    //todo: 这个地方貌似只能返回一个
    @Select("select * from role where id = #{id}")
    public List<Role> getRolesById(Integer id);

    @Select("select * from role")
    public List<Role> getAllRoles();

    @Update("insert into role(name,nameZh) value(#{name},#{nameZh})")
    public Integer addRole(Role role);

    @Update("update role set name = #{name},nameZh = #{nameZh} where id = #{id}")
    Integer updateRole(Role role);

    @Delete("delete from role where id = #{id}")
    Integer deleteRoleById(Integer id);

    @Delete({
            "<script>",
            "delete from role where id in ",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    Integer deleteRolesByIds(@Param("ids") Integer[] ids);
}
