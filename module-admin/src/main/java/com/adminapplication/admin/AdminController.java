package com.adminapplication.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private HttpSession session;


}
