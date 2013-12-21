package com.courseportal.project.account;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    
    String auth;
    
    public MyGrantedAuthority(String auth) {
        this.auth = auth;
    }

    @Override
    public String getAuthority() {
        return this.auth;
    }

}
