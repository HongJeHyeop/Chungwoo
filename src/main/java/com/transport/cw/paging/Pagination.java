package com.transport.cw.paging;

import com.transport.cw.domain.dtos.PagingDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Pagination {
    // 현재 페이지
    private int nowPage;
    // 전체 테이터 수
    private int totalRecordCount;
    // 전체 페이지 수
    private int totalPageCount;
    // 첫 페이지 번호
    private int startPage;

    // 끝 페이지 번호
    private int endPage;
    // LIMIT 시작위치
    private int limitStart;
    // 이전 페이지 존재여부
    private boolean existPrevPage;
    // 다음 페이지 존재여부
    private boolean existNextPage;

    public Pagination(int totalRecordCount, PagingDTO pagingDTO) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.nowPage = pagingDTO.getNowPage();
            carc_page(pagingDTO);
        }
    }

    public void carc_page(PagingDTO pagingDTO) {
        // 전체 페이지수 계산
        totalPageCount = ((totalRecordCount - 1) / pagingDTO.getRecordSize()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (pagingDTO.getNowPage() > totalPageCount) {
            pagingDTO.setNowPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((pagingDTO.getNowPage() - 1) / pagingDTO.getPageSize()) * pagingDTO.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + pagingDTO.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (pagingDTO.getNowPage() - 1) * pagingDTO.getRecordSize();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = (endPage * pagingDTO.getRecordSize()) < totalRecordCount;

    }
}
