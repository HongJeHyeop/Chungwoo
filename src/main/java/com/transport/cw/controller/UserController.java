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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
        return "redirect:/user/login";
    }

    @GetMapping("/userList")
    public void user_list() {}

    @GetMapping("/userManagement")
    public void user_management() {
        log.info("유저관리페이지");
    }

    // 전체 회원 리스트
    @ResponseBody
    @GetMapping("/allList")
    public List<UserVO> get_all_user() {
        return userService.get_all_user();
    }


    // 회원가입요청 리스트
    @ResponseBody
    @GetMapping("/list")
    public List<UserVO> user_management(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("회원가입 관리");
        return userService.register_request();
    }

    // 회원가입 승인
    @ResponseBody
    @PutMapping("/approval")
    public boolean approval_register(
            @RequestBody UserVO userVO
    ) {
        log.info("승인할 user >>>>>>" + userVO);
        return userService.register_approval(userVO);
    }

    // 회원가입 거절 (회원가입 요청 리스트에서 삭제)
    @ResponseBody
    @DeleteMapping("/refusal")
    public boolean register_refusal(
            @RequestBody UserVO userVO
    ){
        log.info("거절할 user >>>>>>>" + userVO);
        return userService.register_refusal(userVO);
    }

}
