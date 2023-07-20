package com.boardapplication.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UpdateBoardRequestDto {

    private String title;
    private String content;
}
