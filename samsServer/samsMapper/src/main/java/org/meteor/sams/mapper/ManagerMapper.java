package org.meteor.sams.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.meteor.sams.model.Manager;
import org.meteor.sams.model.Role;

import java.util.List;

public interface ManagerMapper {

    @Update("update manager set mname = #{mname}," +
            "mphone = #{mphone}," +
            "memail = #{memail}," +
            "gender = #{gender}, " +
            "birth = #{birth}," +
            "enabled = #{enabled} " +
            "where mid = #{mid}")
    public int update(Manager manager);

    @Delete("delete from manager where mid = #{mid}")
    public int delete(int mid);

    @Select("select * from manager where mid = #{mid}")
    public Manager findById(String mid);

    @Select("select r.* from role r,manage_role mrr where r.id = mrr.rid and mrr.mid = #{mid}")
    List<Role> getManagerRoleById(int mid);

    @Select({
            "<script>",
            "select * from manager where mid != #{mid} ",
            "<if test='keywords != null'>",
            " and manager.mname like concat('%',#{keywords},'%')",
            "</if>",
            "order by manager.mid",
            "</script>"
    })
    @Results({
            @Result(column = "mid",property = "mid"),
            @Result(column = "mid",property = "roles",many = @Many(select = "org.meteor.sams.mapper.RoleMapper.getRolesByMid",fetchType = FetchType.EAGER))
    })
    List<Manager> getAllManagers(int mid,@Param("keywords") String keywords);

    @Select("select * from manager where mname = #{userName}")
    Manager findByName(String userName);

    @Select("select * from manager where mid != #{mid}")
    List<Manager> getAllManagersExceptCurrentManager(Integer mid);
}
