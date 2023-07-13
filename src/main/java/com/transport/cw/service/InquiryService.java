package com.transport.cw.service;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.InquiryVO;
import com.transport.cw.mappers.InquiryMapper;
import com.transport.cw.paging.InquiryPagingResponse;
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

    public InquiryPagingResponse find_all_inquiry(PagingDTO pagingDTO) {

        int count = inquiryMapper.count(pagingDTO);
        log.info(count);
        if (count < 1) {
            return new InquiryPagingResponse(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, pagingDTO);
        pagingDTO.setPagination(pagination);
        List<InquiryVO> inquiryVOS = inquiryMapper.find_all_inquiry(pagingDTO);

        return new InquiryPagingResponse(inquiryVOS, pagination);
    }

    public List<InquiryVO> simple_main_inquiry(){
        return inquiryMapper.simple_main_inquiry();
    };

    public void insert_inquiry(InquiryVO inquiryVO) {
            inquiryMapper.insert_inquiry(inquiryVO);
    }
}
