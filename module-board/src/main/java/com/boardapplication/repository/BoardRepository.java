package com.boardapplication.repository;

import com.core.entity.Board;
import com.core.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByStatus(Status status, Pageable pageable);
    Page<Board> findByTitleAndStatus(String keyword, Status status, Pageable pageable);

    Page<Board> findByContentAndStatus(String keyword, Status status, Pageable pageable);

    @Query("SELECT b FROM board_tb b " +
            "JOIN user_tb u on b.userId = u.id " +
            "WHERE b.status = :status " +
            "AND u.nickname = :keyword " +
            "order by b.id DESC")
    Page<Board> findByNicknameAndStatus(@Param("keyword") String keyword, @Param("status") Status status, Pageable pageable);
}
