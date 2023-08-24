package com.boardapplication.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChildrenCommentCreateRequestDto {
    private Long userId;
    private Long parentId;
    private String content;
}
