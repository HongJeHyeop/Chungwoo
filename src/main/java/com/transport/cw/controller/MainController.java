package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home() {
        log.info("====== 메인페이지 접속! =====");
        return "home/main";
    }

    /*** 회사소개페이지 ***/
    @GetMapping("/introduce/greeting")
    public void greeting() {
        log.info("====== 회사 소개 페이지 접속! =======");
    }

    @GetMapping("/introduce/history")
    public void history() {log.info("====== 연혁 ======");}

    /*** 커뮤니티 페이지 ***/
    // 공지사항
    @GetMapping("/community/notice")
    public void notice() {

    }

    @GetMapping("/community/detail")
    public void detail() {
        log.info("상세페이지 접속");
    }

    // 자료실
    @GetMapping("/community/repository")
    public void dataroom() {
        log.info("자료실 접속");
    }


    /*** 고객센터 ***/
    // 온라인 문의
    @GetMapping("/service/inquiry")
    public void inquiry() {
        log.info("온라인 문의 접속");
    }

    @GetMapping("/service/mailsend")
    public void mailsend() {
        log.info("메일보내기 접속");
    }
}
