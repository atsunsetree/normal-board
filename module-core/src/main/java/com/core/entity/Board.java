package com.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "board_tb")
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;
    private String content;
    private String thumbnail;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Board(Long id, Long userId, String title, String content, String thumbnail, Status status, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
