package com.boardapplication.service;

import com.boardapplication.dto.CommentCreateRequestDto;
import com.boardapplication.repository.BoardRepository;
import com.boardapplication.repository.CommentRepository;
import com.boardapplication.repository.UserRepository;
import com.core.entity.Board;
import com.core.entity.Comment;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public Comment createParent(Long userId, String content, Long boardId) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        Comment comment = Comment.builder()
                .board(board)
                .createdAt(LocalDateTime.now())
                .parent(null)
                .content(content)
                .user(user)
                .build();

        return commentRepository.save(comment);

    }
    public void createChildren(Long userId, Long boardId, Long commentId, CommentCreateRequestDto commentCreateRequestDto) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        Comment comment = commentRepository.getReferenceById(commentId);
        Comment childComment = Comment.builder()
                .user(user)
                .board(board)
                .createdAt(LocalDateTime.now())
                .parent(comment)
                .content(commentCreateRequestDto.getContent())
                .build();
        commentRepository.save(childComment);
    }

    public List<Comment> getAllByBoardId(Long boardId) {
        List<Comment> list = commentRepository.findAllByBoardIdAndParentIsNull(boardId);
        return list;
    }


}
