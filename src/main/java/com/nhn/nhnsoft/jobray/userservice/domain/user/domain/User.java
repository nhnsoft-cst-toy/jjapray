package com.nhn.nhnsoft.jobray.userservice.domain.user.domain;

import com.nhn.nhnsoft.jobray.userservice.global.audit.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private User(String name, String email, String picture, Role role) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.picture = picture;
        this.role = Objects.requireNonNull(role);
    }

    public static User of(String name, String email, String picture, Role role) {
        return new User(name, email, picture, role);
    }

    public String getEmail() {
        return email;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }
}
