package com.boardapplication.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateBoardForm {

    private String title;
    private String content;
    private MultipartFile file;
}
