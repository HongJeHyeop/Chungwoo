package com.transport.cw.mappers;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.InquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    void insert_inquiry(InquiryVO inquiryVO);

    // 문의내역 목록
    List<InquiryVO> find_all_inquiry(PagingDTO pagingDTO);

    // 전체 문의내역 수
    int count(PagingDTO pagingDTO);

    // 메인페이지용 문의내역 3개
    List<InquiryVO> simple_main_inquiry();

    // 문의번호로 하나 가져오기
    InquiryVO get_inquiry(String no);

    // 접수확인 업데이트
    boolean update_process(InquiryVO inquiryVO);

    // 접수 삭제
    boolean delete_inquiry(InquiryVO inquiryVO);

}
