package com.transport.cw.mappers;

import com.transport.cw.domain.vos.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserVO get_user(String id);

    void register_user(UserVO userVO);
}
