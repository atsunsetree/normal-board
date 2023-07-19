package com.adminapplication.admin;

import com.adminapplication.dto.*;
import com.adminapplication.emailservice.EmailService;
import com.adminapplication.exception.CustomException;
import com.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 메인 페이지 요청 시 DB에서 회원 정보를 받아 옵니다.
     * 요청 받은 target 값이 null 또는 빈공간 일 때 회원 id로 오름차순 정렬 하고
     * target 값이 board 또는 comment 일 때 게시글 수 또는 댓글 수로 내림차순 정렬 합니다.
     * localhost:8081/admin/userList?target={}
     * @param target
     * @return
     */
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

        return adminRepository.findSortedAllUsersInfo(orderBy, target, desc);
    }

    /**
     * 사용자의 권한을 수정합니다.
     * 화면에서 권한 버튼을 누를 때 사용자 권한이 BLACK이 아니면 BLACK으로 변경하고
     * BLACK이면 게시글 수를 체크해 우수회원 또는 새싹회원으로 변경하며
     * 이를 이메일로 고객에게 알립니다.
     * @param id
     * @return
     */
    @Transactional
    public int setRoleById(Long id, Category category) {
        Role role = Role.BLACK;

        User userData = adminRepository.findUserById(id);
        Role principalRole = userData.getRole();

        // 사용자 권한 체크 후 id 의 게시글 수 검색
        if (principalRole == Role.BLACK) {
            // blacklist 테이블을 하나 만들어두고 참조해서 원래의 권한을 찾아오는 방법도 생각했지만
            // 여기서는 이런 방법으로 구현해봄.
            if(adminRepository.countBoardSizeByUserId(id) >= 10) role = Role.VIP;
            if(adminRepository.countBoardSizeByUserId(id) < 10) role = Role.NORMAL;
        }

        // 블랙리스트 등록
        if (role.equals(Role.BLACK)) {
            Blacklist blacklist = Blacklist.builder()
                    .userId(userData.getId())
                    .category(category)
                    .build();

            adminRepository.insertBlacklist(blacklist);
        }
        // 블랙리스트 해제
        if (!role.equals(Role.BLACK)) {
            adminRepository.deleteBlacklistById(id);
        }

        // 변경 전 - 후 권한 안내 메일 전송
        emailService.sendMail(userData, principalRole, role);

        return adminRepository.updateRoleById(role.name(), id);
    }

    /**
     * 모든 게시글 정보를 받아옵니다.
     * @return
     */
    public List<AllBoardsResponseDto> getBoardList() {
        return adminRepository.findAllBoards();
    }

    /**
     * 게시글 숨김/보이기
     * @param category
     * @param id
     */
    public void setStatus(String category, Long id) {
        Status status = Status.BLACK;

        // 숨김 처리만 요청할 때 로직
        if(category == null) {
            if (getBoard(id).getStatus().equals(Status.NORMAL)) adminRepository.updateStatusById(status, id);
            if (getBoard(id).getStatus().equals(Status.BLACK)) {
                if (getUser(getBoard(id).getUserId()).getRole().equals(Role.BLACK)) // 블랙리스트인데 숨김 해제할 경우
                    throw new CustomException("블랙리스트입니다. 숨김 해제할 수 없습니다.");
                adminRepository.updateStatusById(Status.NORMAL, id);
            }
            return;
        }

        // 블랙리스트 등록할 때 로직
        if(category.equals("undo")) status = Status.NORMAL;

        for(Board board : getBoards(id)) {
            adminRepository.updateStatusById(status, board.getId());
        }
    }

    /**
     * 게시글을 삭제합니다.
     * @param id
     * @return
     */
    public int deleteBoard(Long id) {
        return adminRepository.deleteBoardById(id);
    }

    /**
     * 게시글에 포함된 모든 댓글을 삭제합니다.
     * @param id
     */
    public void deleteComments(Long id) {
        for(int index = 0; index < adminRepository.countCommentSizeByBoardId(id); index++) {
            adminRepository.deleteAllCommentsByBoardId(id);
        }
    }

    /**
     * 신고 목록 페이지 요청 시 DB에서 신고 정보를 받아 옵니다.
     * localhost:8081/admin/reportList
     * @return
     */
    public List<AllReportsResponseDto> getReportList() {
        return adminRepository.findAllReports();
    }

    /**
     * 게시글의 신고를 거절 처리하면 해당 신고를 삭제합니다.
     * @param id
     */
    public void deleteReports(Long id) {
        for(int index = 0; index < adminRepository.countReportSizeByBoardId(id); index++) {
            adminRepository.deleteReportByBoardId(id);
        }
    }

    /**
     * 신고 상세 페이지에 나열할 특정 게시글의 신고목록을 받아 옵니다.
     * localhost:8081/admin/reportList/{id}
     * @param id
     * @return
     */
    public List<ReportDetailsResponseDto> getReports(Long id) {
        return adminRepository.findReportsByBoardId(id);
    }

    /**
     * 등록된 블랙리스트 목록을 불러옵니다.
     * localhost:8081/admin/blacklist
     * @return
     */
    public List<AllBlacklistsResponseDto> getBlacklists() {
        return adminRepository.findAllBlacklists();
    }

    /**
     * 사용자가 작성한 모든 게시글을 불러옵니다.
     * @param id
     * @return
     */
    public List<Board> getBoards(Long id) {
        return adminRepository.findAllBoardsById(id);
    }

    /**
     * 사용자의 존재 유무를 확인합니다.
     * @param username
     * @return
     */
    public boolean isNotExistId(String username) {
        return adminRepository.findByUsername(username) == null;
    }

    /**
     * 사용자의 계정을 먼저 확인 한 뒤 이 메서드를 호출하면
     * 비밀번호가 틀렸는지 확인합니다.
     * @param loginRequestDto
     * @return
     */
    public boolean isWrongPassword(LoginRequestDto loginRequestDto) {
        return adminRepository.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword()) == null;
    }

    /**
     * 사용자의 계정과 비밀번호를 찾아 사용자 정보를 리턴합니다.
     * @param loginRequestDto
     * @return
     */
    public Admin login(LoginRequestDto loginRequestDto) {
        return adminRepository.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
    }

    /**
     * 사용자를 찾습니다.
     * @param id
     * @return
     */
    public User getUser(Long id) {
        return adminRepository.findUserById(id);
    }

    /**
     * 게시글을 찾습니다.
     * @param id
     * @return
     */
    public Board getBoard(Long id) {
        return adminRepository.findBoardById(id);
    }

}
