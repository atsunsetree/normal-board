package com.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "blacklist_tb")
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 블랙리스트 사용자

    @Enumerated(value = EnumType.STRING)
    private Category category; // 등록 사유

    private LocalDateTime createdAt;

    @Builder
    public Blacklist(Long id, Long userId, Category category, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.createdAt = createdAt;
    }
}
