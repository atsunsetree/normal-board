package com.boardapplication.service;

import com.boardapplication.repository.BoardRepository;
import com.boardapplication.repository.ReportRepository;
import com.boardapplication.repository.UserRepository;
import com.core.entity.Board;
import com.core.entity.Category;
import com.core.entity.Report;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    @Value("${file.dir}")
    private String fileDir;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;


    public void create(long userId, Long boardId, MultipartFile file, Category category) throws IOException {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String fullPath = fileDir + "report/" + fileName;
        file.transferTo(new File(fullPath));

        Report report = Report.builder()
                .board(board)
                .user(user)
                .reasonImage("report/" + fileName)
                .category(category)
                .createdAt(LocalDateTime.now())
                .build();

        reportRepository.save(report);

    }
}
