package com.adminapplication.dto;

import com.core.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailsResponseDto {
    private Integer id;
    private String nickname;
    private Integer category;
    private String reasonImage;
    private String createdAt;
}
