package com.adminapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllBoardResponseDto {
    private Integer id;
    private String nickname;
    private String title;
    private String createdAt;
}
