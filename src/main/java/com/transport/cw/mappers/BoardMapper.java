package com.transport.cw.mappers;

import com.transport.cw.domain.dtos.BoardDTO;
import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.domain.vos.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> simple_main_notice();

    BoardVO get_notice(String no, String boardType);

    List<FileVO> get_files(String no, String boardType);

    FileVO get_file(String no);

    int get_last_insert_no();

    void insert_board(BoardDTO boardDTO);

    void insert_file(BoardDTO boardDTO);

    boolean delete_file(BoardDTO boardDTO);

    boolean update_notice(BoardDTO boardDTO);

    boolean delete_notice(BoardDTO boardDTO);

    /*** 페이징 ***/
    List<BoardVO> find_all(PagingDTO pagingDTO);

    // 전체 게시글 개수
    int count(PagingDTO pagingDTO);

    // 다음 게시물
    BoardVO next_notice(String no, String boardType);

    // 이전 게시물
    BoardVO prev_notice(String no, String boardType);
}
