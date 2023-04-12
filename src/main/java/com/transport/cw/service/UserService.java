package com.transport.cw.service;

import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserVO get_user(String id) {

        log.info(">>> 서비스 접근");
        return userMapper.get_user(id);
    };
}
