package com.boardapplication.repository;

import com.core.entity.Board;
import com.core.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByStatus(Status status, Pageable pageable);

    Page<Board> findByTitleAndStatus(String keyword, Status status, Pageable pageable);

    Page<Board> findByUserIdAndStatus(Long keyword, Status status, Pageable pageable);

    Page<Board> findByContentAndStatus(String keyword, Status status, Pageable pageable);
}
