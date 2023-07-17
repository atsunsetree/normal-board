package com.boardapplication.service;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.repository.BoardRepository;
import com.boardapplication.repository.UserRepository;
import com.core.entity.Board;
import com.core.entity.Status;
import com.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
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
                User user = userRepository.findById(board.getUserId()).orElse(null);
                String nickname = user != null ? user.getNickname() : null;

                BoardDto dto = BoardDto.builder()
                        .id(board.getId())
                        .userId(board.getId())
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
                User user = userRepository.findById(board.getUserId()).orElse(null);
                String nickname = user != null ? user.getNickname() : null;
                BoardDto dto = BoardDto.builder()
                        .id(board.getId())
                        .userId(board.getId())
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
}
