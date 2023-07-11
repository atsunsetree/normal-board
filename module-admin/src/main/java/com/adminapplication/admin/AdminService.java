package com.adminapplication.admin;

import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<User> getUserList() {
        return adminRepository.findAllUsers();
    }

}
