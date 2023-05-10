package com.transport.cw.domain.dtos;

import com.transport.cw.paging.Pagination;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@ToString
public class PagingDTO {

    // 현재 페이지 번호
    private int nowPage;
    // 페이지당 출력할 데이터 개수
    private int recordSize;
    // 화면 하단에 출력할 페이지 사이즈
    private int pageSize;
    // 검색 키워드
    private String keyword;
    // 검색 유형
    private String searchType;
    // 페이지네이션 클래스
    private Pagination pagination;

    public PagingDTO(int nowPage, int recordSize, int pageSize, String keyword, String searchType) {
        this.nowPage = nowPage;
        this.recordSize = recordSize;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.searchType = searchType;
    }
}