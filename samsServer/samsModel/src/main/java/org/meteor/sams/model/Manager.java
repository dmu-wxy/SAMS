package org.meteor.sams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private boolean enabled;

    private int gender;

    private Date birth;

    private List<Role> roles;
    /**
     * 返回用户所有角色
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override
    @JsonIgnore
    public String getUsername() {
        return mname;
    }

    /**
     * 账户是否未过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true; //是 没有过期
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true; //是 未锁定
    }

    /**
     * 凭证（密码）是否未过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true; //是 未过期
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled; //可用
    }
}
