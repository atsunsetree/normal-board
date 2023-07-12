package com.core.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "board_tb")
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

}
