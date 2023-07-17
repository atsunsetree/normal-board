package com.adminapplication.dto;

import com.core.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllReportsResponseDto {
    private Integer id; // 신고된 게시글 id
    private String title;
    private Integer userId;
    private Status status;
    private Integer qtyOfReport; // 신고 건수
}
