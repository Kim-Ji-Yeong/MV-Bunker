package com.nurim.mvbunker.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetails;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 시큐리티 거치지 않을 곳
        web.ignoring().antMatchers("/content/**", "/img/**", "/css/**", "/js/**",
                "/pic/**").antMatchers("/favicon.ico", "/resources/**", "/error");
    }
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();

        security.authorizeRequests() // 로그인 없이 갈 수 있는 곳
                .antMatchers("/user/login", "/user/join", "/user/auth").permitAll()
                .anyRequest().authenticated();

        security.formLogin()
                .loginPage("/user/login")
                .usernameParameter("uid")
                .passwordParameter("upw")
                .defaultSuccessUrl("/review/home"); // 로그인 성공시 갈 곳

        security.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 요청 주소
                .logoutSuccessUrl("/user/login")// 로그인 성공시
                .invalidateHttpSession(true);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}
