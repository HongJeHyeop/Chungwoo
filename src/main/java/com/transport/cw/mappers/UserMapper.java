package com.transport.cw.mappers;

import com.transport.cw.domain.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // 유저 조회
    UserVO get_user(String id);

    // 전체 회원 조회
    List<UserVO> get_all_user();

    // 회원가입 대기 목록
    List<UserVO> register_request();

    // 관리자에게 회원가입 요청
    void register_user(UserVO userVO);

    // 관리자가 회원가입 승인
    boolean register_approval(UserVO userVO);

    // 관리자가 회원가입 거절
    boolean register_refusal(UserVO userVO);


}
