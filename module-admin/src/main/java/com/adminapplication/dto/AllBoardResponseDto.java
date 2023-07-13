package com.adminapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AllBoardResponseDto {
    private String nickname;
    private String title;
    private String createdAt;
}
