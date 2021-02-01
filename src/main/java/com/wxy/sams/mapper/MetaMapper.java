package com.wxy.sams.mapper;

import com.wxy.sams.model.Meta;
import org.apache.ibatis.annotations.Select;

public interface MetaMapper {
    @Select("select distinct " +
            "m1.id,m1.keepAlive,m1.requireAuth " +
            "from menu m1,menu m2,manage_role mrr,menu_role mr " +
            "where m1.id = m2.parentId and m1.id = #{mid} and mrr.mid = #{id} and mrr.rid = mr.rid and mr.mid = m2.id and m2.enabled = true order by m1.id,m2.id;")
    public Meta getMetaById(int id,int mid);

    @Select("select distinct " +
            "m2.id,m2.keepAlive,m2.requireAuth " +
            "from menu m1,menu m2,manage_role mrr,menu_role mr " +
            "where m1.id = m2.parentId and mrr.mid = #{id} and mrr.rid = mr.rid and mr.mid = m2.id and m2.id = #{mid} and m2.enabled = true order by m1.id,m2.id;")
    public Meta getMetasById(int id,int mid);
}
