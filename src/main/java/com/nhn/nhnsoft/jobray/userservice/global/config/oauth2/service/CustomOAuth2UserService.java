package com.nhn.nhnsoft.jobray.userservice.global.config.oauth2.service;

import com.nhn.nhnsoft.jobray.userservice.domain.user.application.UserJoinService;
import com.nhn.nhnsoft.jobray.userservice.domain.user.domain.User;
import com.nhn.nhnsoft.jobray.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final UserJoinService userJoinService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = new DefaultOAuth2UserService().loadUser(userRequest).getAttributes();
        CustomOAuth2UserAttributes userAttributes = CustomOAuth2UserAttributes.of(attributes);

        // 사용자 조회 및 회원가입
        User user = findOrJoinUser(userAttributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes,
                // OAuth2 로그인 진행 시 키가 되는 필드값 (P.K 와 같은 역할, 구글은 지원하지만 기본적으로 네이버, 카카오는 지원 X, default : sub)
                userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName()
        );
    }

    private User findOrJoinUser(CustomOAuth2UserAttributes userAttributes) {
        // e-mail 기준 사용자 조회
        User user = userRepository.findByEmail(userAttributes.getEmail())
                // 있으면 정보 업데이트 (이름, 사진)
                .map(entity -> {
                    entity.update(userAttributes.getName(), userAttributes.getPicture());
                    return userRepository.save(entity);
                })
                // 없으면 신규 가입
                .orElseGet(() -> userJoinService.join(userAttributes.toEntity()));
        return user;
    }
}
