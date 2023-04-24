package com.transport.cw.controller;

import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/user/logout")
    public void logout() {
        log.info("logout 완료!");
    }

    @GetMapping("/user/register")
    public void register_page() {
        log.info("회원가입 페이지");
    }

    @PostMapping("/user/register")
    public String register_user(
            @Validated UserVO userVO,
            BindingResult bindingResult,
            HttpSession session
    ) {
        log.info(">>>>>> register_user <<<<<<<<");
        log.info("받아온 userVO >>>>> " + userVO);
        if (bindingResult.hasErrors()) {
            log.info("bindingResult에서 에러 발생");
            return "/user/register";
        }
        userService.register_user(userVO);
        return "redirect:/";
    }

}
