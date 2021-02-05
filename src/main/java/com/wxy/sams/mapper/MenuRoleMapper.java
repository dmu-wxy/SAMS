package com.wxy.sams.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    @Delete("delete from menu_role where rid = #{rid}")
    void deleteByRid(Integer rid);

    @Insert({
            "<script>",
            "insert into menu_role (rid,mid) values ",
            "<foreach collection='mids' item='mid' separator=','>",
            "(#{rid},#{mid})",
            "</foreach>",
            "</script>"
    })
    Integer insertRecords(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
