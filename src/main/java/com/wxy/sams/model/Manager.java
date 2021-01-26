package com.wxy.sams.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class Manager implements Serializable, UserDetails {

    private int mid;

    private String mname;

    private String mphone;

    private String password;

    private String memail;

    private String duty;

    private int gender;

    private Date birth;

    /**
     * 返回用户所有角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //角色格式：ROLE_admin
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //加入角色，现在没有
        return authorities;
    }

    @Override
    public String getUsername() {
        return mname;
    }

    /**
     * 账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; //是 没有过期
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; //是 未锁定
    }

    /**
     * 凭证（密码）是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; //是 未过期
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true; //可用
    }
}
