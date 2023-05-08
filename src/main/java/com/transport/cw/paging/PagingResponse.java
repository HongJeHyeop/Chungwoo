package com.transport.cw.paging;

import com.transport.cw.domain.vos.BoardVO;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PagingResponse {

    private List<BoardVO> boardVOS = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<BoardVO> boardVOS, Pagination pagination) {
        this.boardVOS.addAll(boardVOS);
        this.pagination = pagination;
    }
}
