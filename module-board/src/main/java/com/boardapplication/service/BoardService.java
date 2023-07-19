package com.boardapplication.service;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.repository.BoardRepository;
import com.boardapplication.repository.UserRepository;
import com.boardapplication.vo.CreateBoardForm;
import com.core.entity.Board;
import com.core.entity.Status;
import com.core.entity.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Value("${file.dir}")
    private String fileDir;

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Page<BoardDto> getBoardList(Pageable pageable){
        Page<Board> boards = boardRepository.findByStatus(Status.NORMAL, pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boards){
            if(board.getStatus().equals(Status.NORMAL)){
                User user = userRepository.findById(board.getUser().getId()).orElse(null);
                String nickname = user != null ? user.getNickname() : null;

                BoardDto dto = BoardDto.builder()
                        .id(board.getId())
                        .user(user)
                        .title(board.getTitle())
                        .content(board.getContent())
                        .thumbnail(board.getThumbnail())
                        .status(board.getStatus())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .userNickname(nickname)
                        .build();
                boardDtoList.add(dto);
            }
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }

    public Page<BoardDto> searchBoardList(String searchType, String keyword, Pageable pageable){
        Page<Board> boards = null;
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(searchType.equals("boardTitle")){
            boards = boardRepository.findByTitleAndStatus(keyword, Status.NORMAL, pageable);
        }
        else if(searchType.equals("boardWriter")){
            boards = boardRepository.findByNicknameAndStatus(keyword, Status.NORMAL, pageable);
        }
        else {
            boards = boardRepository.findByContentAndStatus(keyword, Status.NORMAL, pageable);
        }
        for(Board board : boards){
            if(board.getStatus().equals(Status.NORMAL)){
                User user = userRepository.findById(board.getUser().getId()).orElse(null);
                String nickname = user != null ? user.getNickname() : null;
                BoardDto dto = BoardDto.builder()
                        .id(board.getId())
                        .user(user)
                        .title(board.getTitle())
                        .content(board.getContent())
                        .thumbnail(board.getThumbnail())
                        .status(board.getStatus())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .userNickname(nickname)
                        .build();
                boardDtoList.add(dto);
            }
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }


    @Transactional
    public Long save(Long userId, CreateBoardForm createBoardForm, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다"));

        Board board = null;
        if (file.isEmpty()) {
            board = Board.builder()
                    .title(createBoardForm.getTitle())
                    .content(createBoardForm.getContent())
                    .user(user)
                    .build();
            return boardRepository.save(board).getId();
        }


        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String fullPath = fileDir + fileName;
        file.transferTo(new File(fullPath));
        board = Board.builder()
                .title(createBoardForm.getTitle())
                .content(createBoardForm.getContent())
                .thumbnail("images/" + fileName)
                .user(user)
                .build();
        return boardRepository.save(board).getId();
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.getReferenceById(boardId);
    }
}
