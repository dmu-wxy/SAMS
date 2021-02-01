package com.wxy.sams.mapper;

import com.wxy.sams.model.Menu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MenuMapper {

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
}
