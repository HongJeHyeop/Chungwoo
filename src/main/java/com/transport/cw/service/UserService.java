package com.transport.cw.service;

import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.mappers.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserVO get_user(String id) {

        log.info(">>> 서비스 접근");
        return userMapper.get_user(id);
    };

    // 회원가입 요청
    public void register_user(UserVO userVO){
        userVO.setPw(passwordEncoder.encode(userVO.getPw()));
        log.info("암호화된 패스워드 : " + userVO.getPw());
        userMapper.register_user(userVO);
    };

    // 회원가입 대기 목록 조회
    public List<UserVO> register_request() {
        return userMapper.register_request();
    };
}
