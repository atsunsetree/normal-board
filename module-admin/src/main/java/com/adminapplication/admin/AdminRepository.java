package com.adminapplication.admin;

import com.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<User> findAllUsers();
}
