package com.transport.cw.service;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.InquiryVO;
import com.transport.cw.mappers.InquiryMapper;
import com.transport.cw.paging.Pagination;
import com.transport.cw.paging.PagingResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    public PagingResponse<InquiryVO> find_all_inquiry(PagingDTO pagingDTO) {

        int count = inquiryMapper.count(pagingDTO);
        log.info(count);
        if (count < 1) {
            return new PagingResponse(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, pagingDTO);
        pagingDTO.setPagination(pagination);
        List<InquiryVO> inquiryVOS = inquiryMapper.find_all_inquiry(pagingDTO);

        return new PagingResponse<InquiryVO>(inquiryVOS, pagination);
    }

    public List<InquiryVO> simple_main_inquiry(){
        return inquiryMapper.simple_main_inquiry();
    };

    public InquiryVO get_inquiry(String no) {return inquiryMapper.get_inquiry(no);}
    public InquiryVO next_inquiry(String no) {return inquiryMapper.next_inquiry(no);}
    public InquiryVO prev_inquiry(String no) {return inquiryMapper.prev_inquiry(no);}

    public void insert_inquiry(InquiryVO inquiryVO) {
            inquiryMapper.insert_inquiry(inquiryVO);
    }

    // 접수확인 업데이트
    public boolean update_process(InquiryVO inquiryVO) {
        return inquiryMapper.update_process(inquiryVO);
    }

    // 접수 삭제
    public boolean delete_inquiry(InquiryVO inquiryVO) {
        return inquiryMapper.delete_inquiry(inquiryVO);
    }
}
