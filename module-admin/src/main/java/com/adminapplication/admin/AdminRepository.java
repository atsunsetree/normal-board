package com.adminapplication.admin;

import com.adminapplication.dto.AllBoardResponseDto;
import com.adminapplication.dto.AllUsersInfoResponseDto;
import com.core.entity.Board;
import com.core.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
//    List<User> findAllUsers();
//    List<AllUsersInfoResponseDto> findAllUsersInfo();

    List<AllUsersInfoResponseDto> findSortedAllUsersInfo(String orderBy, String target, String desc);

    int updateRoleById(String role, Integer id);

    User findUserById(Integer id);

    Board findBoardById(Integer id);

    int findBoardSizeByUserId(Integer id);

    List<AllBoardResponseDto> findAllBoards();

    int updateStatusById(String status, Integer id);
}
