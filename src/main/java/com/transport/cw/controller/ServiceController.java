package com.transport.cw.controller;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.InquiryVO;
import com.transport.cw.paging.InquiryPagingResponse;
import com.transport.cw.paging.PagingResponse;
import com.transport.cw.service.InquiryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        log.info("controller>>>>" + inquiryService.find_all_inquiry(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, searchType)));
        return inquiryService.find_all_inquiry(new PagingDTO(nowPage, recordSize, pageSize, searchKeyword, searchType));
    }

    // 온라인 문의 글쓰기 저장
    @PostMapping("/inquiryWrite/insert")
    public String insert_inquiry(InquiryVO inquiryVO) {
        inquiryService.insert_inquiry(inquiryVO);
        log.info(inquiryVO);
        return "redirect:/service/inquiryList";
    }
}


