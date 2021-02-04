package com.wxy.sams.mapper;

import com.wxy.sams.model.Menu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据manager的id返回可访问的菜单
     * @param id
     * @return
     */
    @Select("select distinct " +
            "m1.*,mrr.mid " +
            "from menu m1,menu m2,manage_role mrr,menu_role mr " +
            "where m1.id = m2.parentId and mrr.mid = #{id} and mrr.rid = mr.rid and mr.mid = m2.id and m2.enabled = true order by m1.id,m2.id;")
    @Results({
            @Result(column = "{id = id,mid = mid}",property = "children",many = @Many(select="com.wxy.sams.mapper.MenuMapper.getMenusById",fetchType= FetchType.EAGER)),
            @Result(column = "{id = id,mid = mid}",property = "meta",one=@One(select="com.wxy.sams.mapper.MetaMapper.getMetaById",fetchType= FetchType.EAGER))
    })
    public List<Menu> getMenuById(Integer id);


    @Select("select distinct " +
            "m2.*,mrr.mid " +
            "from menu m1,menu m2,manage_role mrr,menu_role mr " +
            "where m1.id = m2.parentId and m1.id = #{id} and mrr.mid = #{mid} and mrr.rid = mr.rid and mr.mid = m2.id and m2.enabled = true")
    @Result(column = "{id = id,mid = mid}",property = "meta",one=@One(select="com.wxy.sams.mapper.MetaMapper.getMetasById",fetchType= FetchType.EAGER))
    public List<Menu> getMenusById(Integer id,Integer mid);


    /**
     * 获得所有url所需的角色，登录用
     * @return 带有角色的菜单
     */
    @Select("select m.*,mr.rid as rid " +
            "from menu m,menu_role mr where m.id = mr.mid order by m.id")
    @Result(column = "rid",property = "roles",many = @Many(select = "com.wxy.sams.mapper.RoleMapper.getRolesById",fetchType = FetchType.EAGER))
    public List<Menu> getAllMenusWithRole();



    @Select("select * from menu where parentId is null")
    @Result(column = "id",property = "children",many = @Many(select = "com.wxy.sams.mapper.MenuMapper.getAllMenusChildren",fetchType = FetchType.EAGER))
    public List<Menu> getAllMenus();

    @Select("select id,name from menu where parentId = #{id}")
    @Result(column = "id",property = "children",many = @Many(select = "com.wxy.sams.mapper.MenuMapper.getAllMenusGrandchild",fetchType = FetchType.EAGER))
    public List<Menu> getAllMenusChildren(Integer id);

    @Select("select id,name from menu where parentId = #{id}")
    public List<Menu> getAllMenusGrandchild(Integer id);

    @Select("select mid from menu_role where rid = #{rid}")
    List<Integer> getMidByRid(Integer rid);
}
