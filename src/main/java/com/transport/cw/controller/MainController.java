package com.transport.cw.controller;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BoardService boardService;

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
    public void notice() {}

    // 공지사항 게시글 상세페이지
    @GetMapping("/community/detail")
    public void detail() {

    }

    @ResponseBody
    @GetMapping("/community/aa")
    public BoardVO detail_test() {
        log.info("상세페이지 접속");
        log.info(boardService.get_board(14).getContents());
        return boardService.get_board(14);
    }

    @GetMapping("/community/noticeWrite")
    public void notice_write(BoardVO boardVO) {


    }

    @ResponseBody
    @GetMapping("/community/insert")
    public void notice_write_test(@RequestBody BoardVO boardVO) {
        log.info("글쓰기!>>>>");
        log.info(boardVO.getContents());
        boardService.insert_board(boardVO);

    }


    // 자료실
    @GetMapping("/community/repository")
    @PreAuthorize("isAuthenticated()")
    public void repository() {

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
