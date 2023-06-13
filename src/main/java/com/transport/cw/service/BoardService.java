package com.transport.cw.service;

import com.transport.cw.domain.dtos.PagingDTO;
import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.mappers.BoardMapper;
import com.transport.cw.paging.Pagination;
import com.transport.cw.paging.PagingResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public void insert_board(BoardVO boardVO, String id) {
        boardVO.setId(id);
//        for (int i = 0; i <= 400; i++) {
//            boardVO.setTitle("test" + i);
//        }
        boardMapper.insert_board(boardVO);
    }

    ;

    public List<BoardVO> simple_main_notice() {
        return boardMapper.simple_main_notice();
    }

    ;

    public BoardVO get_notice(int no) {
        return boardMapper.get_notice(no);
    }

    public BoardVO next_notice(int no) {
        return boardMapper.next_notice(no);
    }

    public BoardVO prev_notice(int no) {
        return boardMapper.prev_notice(no);
    }

    public boolean update_notice(BoardVO boardVO) {
//        boardVO.setFileAddr("C:/update/file");
        return boardMapper.update_notice(boardVO);
    }

    public boolean delete_notice(BoardVO boardVO) {
        return boardMapper.delete_notice(boardVO);
    }

    /*** 페이징 ***/
    public PagingResponse find_all(PagingDTO pagingDTO) {

        int count = boardMapper.count(pagingDTO);
        log.info(count);
        if (count < 1) {
            return new PagingResponse(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, pagingDTO);
        pagingDTO.setPagination(pagination);
        List<BoardVO> boardVOS = boardMapper.find_all(pagingDTO);
        log.info(boardVOS);

        return new PagingResponse(boardVOS, pagination);
    }

    ;
}
