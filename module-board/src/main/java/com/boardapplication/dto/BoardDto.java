package com.boardapplication.dto;

import com.core.entity.Board;
import com.core.entity.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto{
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String thumbnail;
    private Status status;
    private String userNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .content(content)
                .thumbnail(thumbnail)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, Long userId, String title, String content, String thumbnail, Status status, String userNickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.status = status;
        this.userNickname = userNickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
