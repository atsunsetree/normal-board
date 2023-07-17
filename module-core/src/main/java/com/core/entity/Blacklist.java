package com.core.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "blacklist_tb")
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 블랙리스트 사용자

    @Enumerated(value = EnumType.STRING)
    private Role role; // 이전 권한

    @Enumerated
    private Category category; // 등록 사유

    private LocalDateTime createdAt;
}
