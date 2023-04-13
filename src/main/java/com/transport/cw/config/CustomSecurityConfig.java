//package com.transport.cw.config;
//
//import com.transport.cw.service.CustomUserDetailService;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Log4j2
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class CustomSecurityConfig{
//
//    private final CustomUserDetailService customUserDetailService;
//    private final DataSource dataSource;
//
//    @Autowired
//    public CustomSecurityConfig(CustomUserDetailService customUserDetailService, DataSource dataSource) {
//        this.customUserDetailService = customUserDetailService;
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info(" ============== filterChain 실행 =============== ");
//        http.formLogin().loginPage("/user/login").successForwardUrl("/");
//        http.logout().logoutUrl("/user/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
//        http.exceptionHandling().accessDeniedPage("/error/accessDenied");
//        http.csrf();
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        log.info(">>>>>> create password encoder <<<<<");
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        log.info(" ============= webSecurityCustomizer에 의해 정적 리소스 관리 중 ============== ");
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//}
