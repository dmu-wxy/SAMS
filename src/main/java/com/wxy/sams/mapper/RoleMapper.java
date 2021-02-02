package com.wxy.sams.mapper;

import com.wxy.sams.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role where id = #{id}")
    public List<Role> getRolesById(Integer id);
}
