package com.transport.cw.mappers;

import com.transport.cw.domain.vos.BoardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    BoardVO get_board(int no);

    void insert_board(BoardVO boardVO);
}
