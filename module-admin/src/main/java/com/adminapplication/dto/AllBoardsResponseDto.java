package com.adminapplication.dto;

import com.core.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllBoardsResponseDto {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String status;
    private Role role;
    private String createdAt;
}
