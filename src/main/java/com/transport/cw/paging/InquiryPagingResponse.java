package com.transport.cw.paging;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.InquiryVO;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Data
@Log4j2
public class InquiryPagingResponse {
    private List<InquiryVO> inquiryVOS = new ArrayList<>();
    private Pagination pagination;

    public InquiryPagingResponse(List<InquiryVO> inquiryVOS, Pagination pagination) {
        this.inquiryVOS.addAll(inquiryVOS);
        this.pagination = pagination;
    }
}
