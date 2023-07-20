package com.boardapplication.dto;


import com.core.entity.User;
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
