package com.boardapplication.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateBoardRequestDto {

    private String title;
    private String content;
    private MultipartFile file;
}
