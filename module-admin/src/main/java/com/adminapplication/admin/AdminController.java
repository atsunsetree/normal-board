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

    /**
     * 회원 정보를 받아 출력합니다.
     * 요청받은 target 값이 null 또는 빈공간 일 때 회원 id로 오름차순 정렬 하고
     * target 값이 board 또는 comment 일 때 게시글 수 또는 댓글 수로 내림차순 정렬 합니다.
     * localhost:8081/admin/userList?target={}
     * @param target
     * @param model
     * @return
     */
    @GetMapping("/userList")
    public String main(@RequestParam(required = false) String target, Model model) {
        model.addAttribute("userInfoList", adminService.getSortedUserInfoList(target));
        return "/main";
    }
}
