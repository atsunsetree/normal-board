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

    public List<User> getUserList() {
        return adminRepository.findAllUsers();
    }

    public List<AllUsersInfoResponseDto> getUserInfoList() {
        List<AllUsersInfoResponseDto> allUsersInfoResponseDto = adminRepository.findAllUsersInfo();
        System.out.println(allUsersInfoResponseDto.get(0).toString());

        return adminRepository.findAllUsersInfo();
    }

}
