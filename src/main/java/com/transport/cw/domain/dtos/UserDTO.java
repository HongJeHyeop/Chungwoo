package com.transport.cw.domain.dtos;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class UserDTO extends User {
    private String id;
    private String pw;
    private String name;

    public UserDTO(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = username;
        this.pw = password;
        this.name = name;

    }
}
