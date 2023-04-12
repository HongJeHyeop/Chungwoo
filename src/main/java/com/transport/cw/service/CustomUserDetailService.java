package com.transport.cw.service;

import com.transport.cw.domain.dtos.UserDTO;
import com.transport.cw.domain.vos.UserVO;
import com.transport.cw.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername가 실행됨 username => " + username);
        UserVO userVO = userMapper.get_user(username);
        if (userVO == null) {
            throw new UsernameNotFoundException(">>>" + username + "이 존재하지 않음");
        }
        Collection<SimpleGrantedAuthority> collection = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userVO.getRole().name()));
        return new UserDTO(userVO.getId(), userVO.getPw(), userVO.getName(), collection);
    }
}
