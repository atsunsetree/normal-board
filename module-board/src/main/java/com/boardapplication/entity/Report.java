package com.boardapplication.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long boardId;

    private String reasonImage;

    @Enumerated
    private Category category;

    private LocalDateTime createdAt;
}
