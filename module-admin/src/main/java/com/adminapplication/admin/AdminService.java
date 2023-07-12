package com.adminapplication.admin;

import com.adminapplication.dto.AllUsersInfoResponseDto;
import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService { // 비즈니스 로직

    @Autowired
    private AdminRepository adminRepository;

//    public List<User> getUserList() {
//        return adminRepository.findAllUsers();
//    }

//    public List<AllUsersInfoResponseDto> getUserInfoList() {
//        return adminRepository.findAllUsersInfo();
//    }

    public List<AllUsersInfoResponseDto> getSortedUserInfoList(String target) {
        String orderBy = "ORDER BY";
        String desc ="DESC";

        if (target == null || target.isBlank()) {
            target = "";
            orderBy = "";
            desc = "";
        }
        if (target.equals("board")) target = "qty_of_board";

        if (target.equals("comment")) target = "qty_of_comment";


        return adminRepository.findSortedAllUsersInfo(orderBy, target, desc);
    }



}
