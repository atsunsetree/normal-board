package com.adminapplication.admin;

import com.adminapplication.dto.ReportDetailsResponseDto;
import com.core.entity.Blacklist;
import com.core.entity.Category;
import com.core.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin") // 유효성 검사
public class AdminController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AdminService adminService;

    // 사용자 정보 관리 페이지
    @GetMapping("/userList")
    public String main(@RequestParam(name = "target", required = false) String target, Model model) {

        model.addAttribute("categories", Category.values());
        // 조회된 결과물 매핑
        model.addAttribute("userInfoList", adminService.getSortedUserInfoList(target));
        // 응답
        return "/main";
    }

    // 게시판 관리 페이지
    @GetMapping("/boardList")
    public String board(Model model) {

        model.addAttribute("categories", Category.values());
        model.addAttribute("Category", Category.class);
        // 조회 결과물 매핑
        model.addAttribute("boardList", adminService.getBoardList());
        // 응답
        return "/board";
    }

    @PostMapping("/boardList/{id}/status")
    public String setStatus(@PathVariable(name = "id") Integer id) {
        // 유효성 검사

        // 서비스 호출 - 게시글 상태 변경(숨기기/보이기)
        adminService.setStatus(id);
        // 응답
        return "redirect:/admin/boardList";
    }

    @GetMapping("/boardList/{id}/delete")
    public String deleteBoard(@PathVariable(name = "id") Integer id) {
        // 유효성 검사

        // 서비스 호출 - 게시글 및 해당 게시글의 댓글 삭제
        adminService.deleteComments(id);
        adminService.deleteBoard(id);

        // 응답
        return "redirect:/admin/boardList";
    }

    // 신고 목록 페이지
    @GetMapping("/reportList")
    public String report(Model model) {

        // 조회 결과물 매핑
        model.addAttribute("reportList", adminService.getReportList());
        // 응답
        return "/report";
    }

    @PostMapping("/reportList/{id}/hide")
    public String hide(@PathVariable(name = "id") Integer id) {

        // 서비스 호출 - 게시글 숨김
        adminService.setStatus(id);
        // 응답
        return "redirect:/admin/reportList";
    }

    @GetMapping("/reportList/{id}/refuse")
    public String deleteReport(@PathVariable(name = "id") Integer id) {
        // 유효성 검사

        // 서비스 호출 - 신고 삭제
        adminService.deleteReports(id);

        // 응답
        return "redirect:/admin/reportList";
    }

    @GetMapping("/reportList/{id}")
    public String reportDetail(@PathVariable(name = "id") Integer id, Model model) {
        // 유효성 검사

        // 조회 결과물 매핑
        model.addAttribute("reports", adminService.getReports(id));
        model.addAttribute("Category", Category.class);
        // 응답
        return "/reportDetail";
    }

    @GetMapping("/blacklist")
    public String blacklist(Model model) {

        model.addAttribute("blacklists", adminService.getBlacklists());
        model.addAttribute("Category", Category.class);

        return "/blacklist";
    }

    @GetMapping("/blacklist/{id}/register") // 사용처 - board
    public String saveBlacklist(
            @PathVariable(name = "id") Integer id,
            @RequestParam(value = "category", required = false) String category
    ) {
        // 유효성 검사

        // 서비스 호출 - 블랙리스트 등록 또는 해제
        if(!category.equals("undo")) adminService.setRoleById(id, Category.valueOf(category));
        if(category.equals("undo")) adminService.setRoleById(id, null);
        // 응답
        return "redirect:/admin/userList";
    }
}
