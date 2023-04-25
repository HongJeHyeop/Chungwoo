package com.transport.cw.mappers;

import com.transport.cw.domain.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserVO get_user(String id);

    // 회원가입 대기 목록
    List<UserVO> register_request();

    void register_user(UserVO userVO);
}
