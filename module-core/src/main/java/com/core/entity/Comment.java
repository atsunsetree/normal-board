package com.core.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment_tb")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;
    private Long userId;
    private Long parentId;

    private String content;
    private LocalDateTime createdAt;

}
