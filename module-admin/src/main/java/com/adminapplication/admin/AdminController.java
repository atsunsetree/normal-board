package com.adminapplication.admin;

import com.adminapplication.dto.AllReportsResponseDto;
import com.adminapplication.dto.LoginRequestDto;
import com.adminapplication.dto.ReportDetailsResponseDto;
import com.adminapplication.email.EmailService;
import com.adminapplication.exception.AuthException;
import com.adminapplication.exception.CustomException;
import com.core.entity.Admin;
import com.core.entity.Category;
import com.core.entity.Role;
import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    // 사용자 정보 관리 페이지
    @GetMapping("/userList")
    public String main(
            @RequestParam(name = "target", required = false) String target,
            Model model
    ) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(target != null &&
                (!target.equals("qty_of_board") && !target.equals("qty_of_comment")))
            throw new CustomException("정렬 기준이 잘못되었습니다.");

        // 블랙리스트 등록 사유 입력하기 위해 Category 매핑
        model.addAttribute("categories", Category.values());
        // 조회된 결과물 매핑
        model.addAttribute("userInfoList", adminService.getSortedUserInfoList(target));
        // 응답
        return "/main";
    }

    // 게시판 관리 페이지
    @GetMapping("/boardList")
    public String board(Model model) {

        // 비로그인 -> 로그인 페이지로 이동
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");

        model.addAttribute("categories", Category.values());
        model.addAttribute("Category", Category.class);
        // 조회 결과물 매핑
        model.addAttribute("boardList", adminService.getBoardList());
        // 응답
        return "/board";
    }

    @PostMapping("/boardList/{id}/status")
    public String setStatus(@PathVariable(name = "id") Long id) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(adminService.getUser(id) == null)
            throw new CustomException("해당 게시글이 존재하지 않습니다.");

        // 서비스 호출 - 게시글 상태 변경(숨기기/보이기)
        adminService.setStatus(null, id);

        // 응답
        return "redirect:/admin/boardList";
    }

    @GetMapping("/boardList/{id}/delete")
    public String deleteBoard(@PathVariable(name = "id") Long id) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(adminService.getBoard(id) == null)
            throw new CustomException("해당 게시글이 존재하지 않습니다.");

        // 서비스 호출 - 게시글 및 해당 게시글의 댓글, 신고내역 삭제
        adminService.deleteComments(id);
        adminService.deleteBoard(id);
        adminService.deleteReports(id);

        // 응답
        return "redirect:/admin/boardList";
    }

    // 신고 목록 페이지
    @GetMapping("/reportList")
    public String report(Model model) {

        // 비로그인 -> 로그인 페이지로 이동
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");

        model.addAttribute("categories", Category.values());
        // 조회 결과물 매핑
        model.addAttribute("reportList", adminService.getReportList());

        // 응답
        return "/report";
    }

    @GetMapping("/reportList/{id}/refuse")
    public String deleteReport(@PathVariable(name = "id") Long id) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(adminService.getBoard(id) == null)
            throw new CustomException("해당 게시글이 존재하지 않습니다.");
        if(adminService.getReports(id).size() == 0)
            throw new CustomException("해당 게시글의 신고 내역이 없습니다.");

        // 서비스 호출 - 신고 삭제
        adminService.deleteReports(id);
        // 응답
        return "redirect:/admin/reportList";
    }

    @GetMapping("/reportList/{id}")
    public String reportDetail(
            @PathVariable(name = "id") Long id,
            Model model
    ) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(adminService.getBoard(id) == null)
            throw new CustomException("해당 게시글이 존재하지 않습니다.");
        if(adminService.getReports(id).size() == 0)
            throw new CustomException("해당 게시글의 신고 내역이 없습니다.");

        // 조회 결과물 매핑
        model.addAttribute("reports", adminService.getReports(id));
        model.addAttribute("Category", Category.class);
        // 응답
        return "/reportDetail";
    }

    @GetMapping("/blacklist")
    public String blacklist(Model model) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");

        model.addAttribute("blacklists", adminService.getBlacklists());
        model.addAttribute("Category", Category.class);

        return "/blacklist";
    }

    @GetMapping("/blacklist/{id}/register")
    public String saveBlacklist(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "reportedBoardId", required = false) Long reportedId
    ) {

        // 유효성 검사
        if(session.getAttribute("principal") == null)
            throw new AuthException("로그인이 필요합니다.");
        if(adminService.getUser(id) == null)
            throw new CustomException("해당 사용자가 존재하지 않습니다.");
        if(category == null || category.equals("등록 사유 선택") || category.isEmpty() || category.isBlank())
            throw new CustomException("등록 사유를 입력해주세요.");
        for(int index = 0; index < Category.values().length; index++) {
            if(Category.values()[index].name().equals(category)) break;
            if(!category.equals("undo") && index == Category.values().length - 1) throw new CustomException("등록 사유를 잘못 입력하였습니다.");
        }

        // 서비스 호출 1- 블랙리스트 등록/해제
        if(!category.equals("undo")) adminService.setRoleById(id, Category.valueOf(category));
        if(category.equals("undo")) adminService.setRoleById(id, null);

        // 서비스 호출 2- 권한 변경 시 이메일 전송 / 신고내역 있는 사용자는 신고자에게도 메일 전송
        List<User> reporters = new ArrayList<>();
        if(reportedId != null) {
            for(ReportDetailsResponseDto reportDetailsResponseDto : adminService.getReports(reportedId)) {
                reporters.add(adminService.getUser(reportDetailsResponseDto.getUserId()));
            }
        }
        emailService.sendMail(adminService.getUser(id), reporters, Role.NORMAL, Role.BLACK);

        // 서비스 호출 3- 게시글 숨김/보임
        adminService.setStatus(category, id);

        // 응답
        return "redirect:/admin/userList";
    }

    @GetMapping("/login")
    public String login() {

        if(session.getAttribute("principal") != null)
            throw new CustomException("이미 로그인되었습니다.");

        return "/login";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {

        // 유효성 검사
        if (loginRequestDto.getUsername() == null || loginRequestDto.getUsername().isEmpty())
            throw new CustomException("계정을 입력하세요.");
        if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isEmpty())
            throw new CustomException("비밀번호를 입력하세요.");
        if (adminService.isNotExistId(loginRequestDto.getUsername()))
            throw new CustomException("아이디가 존재하지 않습니다.");
        if (adminService.isWrongPassword(loginRequestDto))
            throw new CustomException("비밀번호가 틀렸습니다.");

        // 서비스 호출
        Admin principal = adminService.login(loginRequestDto);

        // 로그인 인증 처리 - 세션, 인증 유지 시간 지정
        session.setAttribute("principal", principal);
        session.setMaxInactiveInterval(60 * 30);

        return "redirect:/admin/userList";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/admin/login";
    }

}
