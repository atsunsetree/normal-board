package com.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "admin_tb")
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public Admin(
            Long id,
            String username,
            String password,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this. createdAt = createdAt;
    }
}
