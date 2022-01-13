package com.nhn.nhnsoft.jobray.userservice.global.config.oauth2;

import com.nhn.nhnsoft.jobray.userservice.domain.user.domain.Role;
import com.nhn.nhnsoft.jobray.userservice.global.config.oauth2.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화 시킴
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests() // URL 별 권한 관리설정 시작
                .antMatchers("/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 소유자만 접근 가능
                .anyRequest().authenticated() // 설정 외 나머지 URL, authenticated() 인증된 사용자만 허용
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공 시 URL
                .and()
                .oauth2Login() // OAuth2 설정 시작점
                .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보 가져올 때의 설정담당
                .userService(customOAuth2UserService); // 로그인 성공 후 후속 조치 진행
    }
}
