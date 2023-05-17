package com.transport.cw.controller;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.paging.PagingResponse;
import com.transport.cw.service.BoardService;
import com.transport.cw.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;

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
    public void detail_page() {

    }
    @GetMapping("/community/detail/{no}")
    public String detail(@PathVariable int no, Model model) {
        model.addAttribute("no", no);
        return "/community/detail";
    }
    @ResponseBody
    @GetMapping("/community/restDetail/{boardNo}")
    public BoardVO rest_detail(@PathVariable String boardNo) {
        int no = Integer.parseInt(boardNo);
        log.info(no);
        log.info(">>>>>>>>>>>>" + boardService.get_notice(no));
        return boardService.get_notice(no);
    }

    @ResponseBody
    @GetMapping("/community/notice/list")
    public PagingResponse get_all_notice(
            @RequestParam(defaultValue = "1") int nowPage,
            @RequestParam(defaultValue = "10") int recordSize,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "공지사항") String searchType
    ) {


        log.info("공지사항 목록 접속");
        log.info("controller>>>>" + boardService.find_all(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, searchType)));
        return boardService.find_all(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, searchType));
    }

    @GetMapping("/community/noticeWrite")
    public void notice_write() {}
    @GetMapping("/community/noticeWrite/{no}")
    public String update_notice_write(@PathVariable String no, Model model) {
        model.addAttribute("no", no);
        return "/community/noticeWrite";
    }
    @ResponseBody
    @GetMapping("/community/notice/update/write/{no}")
    public BoardVO update_notice_write(@PathVariable String no){
        return boardService.get_notice(Integer.parseInt(no));
    }

    @ResponseBody
    @PostMapping("/community/noticeWrite/image")
    public void quill_image() {

    }

    @PostMapping("/community/insert")
    public String insert_board(
            BoardVO boardVO,
            @AuthenticationPrincipal UserDetails userDetails,
            MultipartFile file
            ) {
        try {
            boardVO.setFileAddr(fileService.upload_file(file).get(0).toString());
            boardVO.setFileName(fileService.upload_file(file).get(1).toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(boardVO);
        boardService.insert_board(boardVO, userDetails.getUsername());
        return "redirect:/community/notice";
    }

    @PostMapping("/community/notice/update")
    public String update_notice(BoardVO boardVO) {
        log.info(boardVO);
        boolean result = boardService.update_notice(boardVO);
        log.info("수정결과 >> " + result);
        return "redirect:/community/notice";
    }

    @ResponseBody
    @DeleteMapping("/community/notice/delete")
    public boolean delete_notice(@RequestBody BoardVO boardVO) {
        log.info(boardVO.getNo());
        return boardService.delete_notice(boardVO);
    }


//    @ResponseBody
//    @GetMapping("/community/notice/update/write")
//    public void notice_update() {
//        log.info("글쓰기!>>>>");
//        log.info(boardVO);
//        boardService.insert_board(boardVO);
//
//    }


    // 자료실
    @GetMapping("/community/repository")
//    @PreAuthorize("isAuthenticated()")
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
