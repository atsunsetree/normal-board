package com.adminapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllBoardsResponseDto {
    private Integer id;
    private String nickname;
    private String title;
    private String status;
    private String createdAt;
}
