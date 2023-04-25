package com.transport.cw.controller;

import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public void login() {

    }

    @PostMapping("/login")
    public void login(String id) {

    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout 완료!");
    }

    @GetMapping("/register")
    public void register_page() {
        log.info("회원가입 페이지");
    }

    @PostMapping("/register")
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
    // 회원가입 관리
    @ResponseBody
    @GetMapping("/registerAuthorization")
    public List<UserVO> register_Authorization(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("회원가입 관리");
        log.info(userDetails.getAuthorities());
        log.info(userService.register_request());
        return userService.register_request();
    }

}
