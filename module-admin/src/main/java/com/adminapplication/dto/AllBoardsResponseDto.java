package com.adminapplication.dto;

import com.core.entity.Role;
import com.core.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllBoardsResponseDto {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private Status status;
    private Role role;
    private String createdAt;
}
