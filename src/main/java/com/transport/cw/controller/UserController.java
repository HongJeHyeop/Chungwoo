package com.transport.cw.controller;

import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public void login() {

    }

    @PostMapping("/user/login")
    public void login(String id) {

    }

}
