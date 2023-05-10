package com.transport.cw.mappers;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> get_all_notice();

    BoardVO get_notice(int no);

    void insert_board(BoardVO boardVO);

    boolean update_notice(BoardVO boardVO);

    boolean delete_notice(BoardVO boardVO);

    /*** 페이징 ***/
    List<BoardVO> find_all(PagingDTO pagingDTO);

    // 전체 게시글 개수
    int count(PagingDTO pagingDTO);
}
