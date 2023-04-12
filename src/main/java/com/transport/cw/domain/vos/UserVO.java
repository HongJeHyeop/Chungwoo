package com.transport.cw.domain.vos;

import com.transport.cw.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserVO {
    private String id;
    private String pw;
    private String name;
    private UserRole role;
}
