package com.adminapplication.admin;

import com.boardapplication.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<User> findAllUsers();
}
