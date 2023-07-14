package com.transport.cw.controller;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.InquiryVO;
import com.transport.cw.paging.InquiryPagingResponse;
import com.transport.cw.paging.PagingResponse;
import com.transport.cw.service.InquiryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping("/inquiryWrite")
    public void inquiry_write() {
        log.info("온라인문의 글쓰기 접속");
    }
    // 온라인 문의 목록 불러오기
    @GetMapping("/findAllInquiry")
    @ResponseBody
    public InquiryPagingResponse get_all_notice(
            @RequestParam(defaultValue = "1") int nowPage,
            @RequestParam(defaultValue = "10") int recordSize,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "온라인문의") String searchType
    ) {


        log.info("공지사항 목록 접속");
        return inquiryService.find_all_inquiry(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, searchType));
    }
    @GetMapping("/mainInquiry")
    @ResponseBody
    public List<InquiryVO> simple_main_inquiry() {
       return inquiryService.simple_main_inquiry();
    }

    // 온라인 문의 글쓰기 저장
    @PostMapping("/inquiryWrite/insert")
    public String insert_inquiry(InquiryVO inquiryVO) {
        inquiryService.insert_inquiry(inquiryVO);
        return "redirect:/service/inquiryList";
    }

    // 온라인 문의 상세정보
    @GetMapping("/inquiryDetailCheck")
    public void inquiry_datail_check(@RequestParam String no) {

        log.info("글쓰기 상세정보 체크 페이지 접속!");
    }

    @GetMapping("/inquiryDetail")
    public void inquiry_detail(@RequestParam String no, Model model) {
        InquiryVO inquiryVO =inquiryService.get_inquiry(no);
        model.addAttribute("inquiryVO", inquiryVO);
    }

    // 문의 접수하기
    @ResponseBody
    @PutMapping("/updateProcess")
    public boolean update_process(@RequestBody InquiryVO inquiryVO) {
        log.info(inquiryVO);
        return inquiryService.update_process(inquiryVO);
    }

    // 문의 삭제하기
    @ResponseBody
    @DeleteMapping("/deleteInquiry")
    public boolean delete_inquiry(@RequestBody InquiryVO inquiryVO) {
        return inquiryService.delete_inquiry(inquiryVO);
    }
}


