package com.adminapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllReportsResponseDto {
    private Integer id; // 신고된 게시글 id
    private String title;
    private Integer userId;
    private Integer qtyOfReport; // 신고 건수
}
