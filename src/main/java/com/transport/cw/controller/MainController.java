package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home() {
        log.info("====== 메인페이지 접속! =====");
        return "home/main";
    }

    @GetMapping("/introduce/greeting")
    public void intro() {
        log.info("====== 회사 소개 페이지 접속! =======");

    }

    @GetMapping("/community/notice")
    public void notice() {

    }

}
