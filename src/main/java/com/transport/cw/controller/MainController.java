package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Log4j2
@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        log.info("====== 메인페이지 접속! =====");
        return "home/main";
    }

}
