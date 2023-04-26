package com.transport.cw.domain.vos;

import com.transport.cw.domain.enums.UserRole;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotBlank;

@Log4j2
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserVO {
    @NotBlank
    private String id;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String branch;
    @NotBlank
    private String position;

    private UserRole role;
    private int authorization;
}
