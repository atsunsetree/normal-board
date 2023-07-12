package com.adminapplication.admin;

import com.adminapplication.dto.AllUsersInfoResponseDto;
import com.adminapplication.emailservice.EmailService;
import com.core.entity.Role;
import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService { // 비즈니스 로직

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

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

        emailService.sendMail();

        return adminRepository.findSortedAllUsersInfo(orderBy, target, desc);
    }

//    public int setRoleById(Role role, Integer id) {
//        if (role.equals(Role.BLACK)) {
//            role =
//        } else {
//            role = Role.BLACK;
//        }
//
//        return adminRepository.updateById(role.name(), id);
//    }

}
