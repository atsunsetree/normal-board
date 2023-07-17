package com.core.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    private String email;
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
