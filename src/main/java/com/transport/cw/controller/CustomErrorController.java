package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handle_error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.info("Securing CustomError : " + status + " >> 에러페이지 이동");
        return "error.html";
    }
}
