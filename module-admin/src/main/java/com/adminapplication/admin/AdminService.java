package com.adminapplication.admin;

import com.adminapplication.dto.AllUsersInfoResponseDto;
import com.adminapplication.emailservice.EmailService;
import com.core.entity.Role;
import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public List<AllUsersInfoResponseDto> getUserInfoList() {
//        return adminRepository.findAllUsersInfo();
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

    // TODO: 예외 처리, 이메일 메세지에 대한 enum
    /**
     * 사용자의 권한을 수정합니다.
     * 화면에서 권한 버튼을 누를 때 사용자 권한이 BLACK이 아니면 BLACK으로 변경하고
     * BLACK이 아니면 게시글 수를 체크해 우수회원 또는 새싹회원으로 변경하며
     * 이를 이메일로 고객에게 알립니다.
     * @param id
     * @return
     */
    public int setRoleById(Integer id) {
        Role role = Role.BLACK;

        User userData = adminRepository.findById(id);
        Role principalRole = userData.getRole();

        // 사용자 권한 체크 후 id 의 게시글 수 검색
        if (principalRole.equals(Role.BLACK)) {
            // blacklist 테이블을 하나 만들어두고 참조해서 원래의 권한을 찾아오는 방법도 생각했지만
            // 여기서는 이런 방법으로 구현해봄.
            if(adminRepository.findBoardSizeByUserId(id) >= 10) role = Role.VIP;
            if(adminRepository.findBoardSizeByUserId(id) < 10) role = Role.NORMAL;
        }

        // 변경 전 - 후 권한 안내 메일 전송
        emailService.sendMail(userData, principalRole, role);

        return adminRepository.updateRoleById(role.name(), id);
    }

}
