package com.transport.cw.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("service")
public class ServiceController {
    @GetMapping("/inquiryWrite")
    public void inquiry_write() {
        log.info("온라인문의 글쓰기");
    }
}
