package com.transport.cw.domain.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@AllArgsConstructor
public class UserVO {
    private String id;
    private String pw;
    private String name;
}
