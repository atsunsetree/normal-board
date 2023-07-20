package com.boardapplication.dto;

import com.core.entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateReportRequestDto {

    private Category category;
    private MultipartFile file;
}
