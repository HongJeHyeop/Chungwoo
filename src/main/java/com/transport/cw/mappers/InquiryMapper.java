package com.transport.cw.mappers;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.InquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    void insert_inquiry(InquiryVO inquiryVO);

    List<InquiryVO> find_all_inquiry(PagingDTO pagingDTO);

    List<InquiryVO> simple_main_inquiry();

    int count(PagingDTO pagingDTO);
}
