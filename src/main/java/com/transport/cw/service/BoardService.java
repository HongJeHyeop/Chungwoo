package com.transport.cw.service;

import com.transport.cw.domain.vos.BoardVO;
import com.transport.cw.mappers.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public void insert_board(
            BoardVO boardVO,
            String id
            ){
        boardVO.setId(id);
        boardVO.setFileAddr("C:/asdfasdf");
        log.info(boardVO);
        boardMapper.insert_board(boardVO);
    };

    public List<BoardVO> get_all_notice(){
        return boardMapper.get_all_notice();
    };

    public BoardVO get_notice(int no) {
        return boardMapper.get_notice(no);
    };
}
