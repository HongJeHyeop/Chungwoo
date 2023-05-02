package com.transport.cw.service;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.mappers.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public void insert_board(BoardVO boardVO){
        boardMapper.insert_board(boardVO);
    };

    public BoardVO get_board(int no){
        return boardMapper.get_board(no);
    };
}
