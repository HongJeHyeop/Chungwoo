package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handle_error(HttpServletRequest request) {
        log.info("에러페이지 이동");
        return "/error/accessDenied";
    }
}
