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

    // 회원가입 요청
    public void register_user(UserVO userVO){
        userVO.setPw(passwordEncoder.encode(userVO.getPw()));
        log.info("암호화된 패스워드 : " + userVO.getPw());
        userMapper.register_user(userVO);
    };

    // 아이디 중복확인
    public boolean duplicate_check(String id) {
        int count = userMapper.duplicate_check(id);
        if (count > 0) {
            return true;
        }else {
            return false;
        }
    }

    // 전체 회원 조회
    public List<UserVO> get_all_user() {
        return userMapper.get_all_user();
    }

    // 회원가입 대기 목록 조회
    public List<UserVO> register_request() {
        return userMapper.register_request();
    };

    // 회원가입 승인
    public boolean register_approval(UserVO userVO) {
        return userMapper.register_approval(userVO);
    }

    // 회원가입 거절
    public boolean register_refusal(UserVO userVO){
        return userMapper.register_refusal(userVO);
    };
}
