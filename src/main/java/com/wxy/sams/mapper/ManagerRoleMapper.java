package com.wxy.sams.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface ManagerRoleMapper {

    @Delete("delete from manage_role where mid = #{mid}")
    void deleteByManagerId(Integer mid);

    @Insert({
            "<script>",
            "insert into manage_role (rid,mid) values ",
            "<foreach collection='rids' item='rid' separator=','>",
            "(#{rid},#{mid})",
            "</foreach>",
            "</script>"
    })
    int addRole(Integer mid, Integer[] rids);
}
