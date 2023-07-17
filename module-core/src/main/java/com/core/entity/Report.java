package com.core.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "report_tb")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long boardId;

    private String reasonImage;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private LocalDateTime createdAt;
}
