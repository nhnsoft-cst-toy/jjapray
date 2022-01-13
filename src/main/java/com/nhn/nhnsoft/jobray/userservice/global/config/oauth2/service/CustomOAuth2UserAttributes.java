package com.nhn.nhnsoft.jobray.userservice.global.config.oauth2.service;

import com.nhn.nhnsoft.jobray.userservice.domain.user.domain.Role;
import com.nhn.nhnsoft.jobray.userservice.domain.user.domain.User;
import lombok.Getter;

import java.util.Map;

@Getter
public class CustomOAuth2UserAttributes {
    public static final String ATTRIBUTE_KEY_OF_LOGIN = "login";
    public static final String ATTRIBUTE_KEY_OF_HTML_URL = "html_url";
    public static final String ATTRIBUTE_KEY_OF_PICTURE = "picture";
    private String name;
    private String email;
    private String picture;

    private CustomOAuth2UserAttributes(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static CustomOAuth2UserAttributes of(Map<String, Object> attributes) {
        return ofGit(attributes);
    }

    private static CustomOAuth2UserAttributes ofGit(Map<String, Object> attributes) {
        return new CustomOAuth2UserAttributes(
                (String) attributes.get(ATTRIBUTE_KEY_OF_LOGIN),
                (String) attributes.get(ATTRIBUTE_KEY_OF_HTML_URL),
                (String) attributes.get(ATTRIBUTE_KEY_OF_PICTURE)
        );
    }

    public User toEntity() {
        return User.of(
                this.name,
                this.email,
                this.picture,
                Role.USER
        );
    }
}
