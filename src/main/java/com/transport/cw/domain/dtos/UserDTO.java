package com.transport.cw.domain.dtos;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class UserDTO extends User {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String branch;
    private String position;

    public UserDTO(String username, String password, String name, String phone, String email,
                   String branch, String position, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = username;
        this.pw = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.position = position;
    }
}
