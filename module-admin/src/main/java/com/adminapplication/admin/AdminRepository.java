package com.adminapplication.admin;

import com.adminapplication.dto.AllBoardsResponseDto;
import com.adminapplication.dto.AllReportsResponseDto;
import com.adminapplication.dto.AllUsersInfoResponseDto;
import com.adminapplication.dto.ReportDetailsResponseDto;
import com.core.entity.Board;
import com.core.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
//    List<User> findAllUsers();

    List<AllUsersInfoResponseDto> findSortedAllUsersInfo(String orderBy, String target, String desc);

    Integer updateRoleById(String role, Integer id);

    User findUserById(Integer id);

    Board findBoardById(Integer id);

    Integer countBoardSizeByUserId(Integer id);

    List<AllBoardsResponseDto> findAllBoards();

    Integer updateStatusById(String status, Integer id);

    Integer deleteBoardById(Integer id);

    void deleteAllCommentsByBoardId(Integer id);

    Integer countCommentSizeByBoardId(Integer id);

    List<AllReportsResponseDto> findAllReports();

    Integer deleteReportByBoardId(Integer id);

    Integer countReportSizeByBoardId(Integer id);

    List<ReportDetailsResponseDto> findReportsByBoardId(Integer id);
}
