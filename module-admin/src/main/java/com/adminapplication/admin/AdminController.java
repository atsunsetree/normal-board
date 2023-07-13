package com.adminapplication.admin;

import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AdminService adminService;

//    @GetMapping("/userList")
//    public String main(Model model) {
//        model.addAttribute("userInfoList", adminService.getUserInfoList());
//        return "/main";
//    }

    @GetMapping("/userList")
    public String main(@RequestParam(name = "target", required = false) String target, Model model) {
        model.addAttribute("userInfoList", adminService.getSortedUserInfoList(target));
        return "/main";
    }

    @GetMapping("/role")
    public String setRole(@RequestParam(name = "id") int id) {
        adminService.setRoleById(id);
        return "redirect:/admin/userList";
    }

    @GetMapping("/boardList")
    public String board(Model model) {
        model.addAttribute("boardList", adminService.getBoardList());
        return "/board";
    }

    @GetMapping("/status")
    public String setStatus(@RequestParam(name = "id") int id) {
        adminService.setStatusById(id);
        return "redirect:/admin/boardList";
    }
}
