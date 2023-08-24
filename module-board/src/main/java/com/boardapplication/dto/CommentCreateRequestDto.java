package com.boardapplication.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentCreateRequestDto {
    private String content;
}
