package com.adminapplication.dto;

import com.core.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllReportsResponseDto {
    private Long boardId; // 신고된 게시글 id
    private String title; // 신고된 게시글 제목
    private Long userId; // 신고된 사용자
    private Status status; // 신고된 게시글 상태(보임/숨김)
    private Integer qtyOfReport; // 신고 건수
}
