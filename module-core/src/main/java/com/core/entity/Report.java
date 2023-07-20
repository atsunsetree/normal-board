package com.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "report_tb")
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_user_board_idx",
                columnNames = {"user_id", "board_id"}
        )
})
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    @Column(nullable = false)
    private String reasonImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
