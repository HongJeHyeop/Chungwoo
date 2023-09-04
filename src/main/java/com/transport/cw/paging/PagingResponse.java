package com.transport.cw.paging;

import com.transport.cw.domain.vos.BoardVO;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PagingResponse<T> {

    private List<T> vos = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> vos, Pagination pagination) {
        this.vos.addAll(vos);
        this.pagination = pagination;
    }
}
