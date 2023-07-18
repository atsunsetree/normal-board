package com.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "board_tb")
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private String title;
    private String content;
    private String thumbnail;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Board(Long id, User user, String title, String content, String thumbnail, Status status, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
