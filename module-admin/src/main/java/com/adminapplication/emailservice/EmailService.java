package com.adminapplication.emailservice;

import com.core.entity.Role;
import com.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(User principalUser, Role principalRole, Role role) {

        // 수신 대상을 담을 배열
        List<String> toUserList = new ArrayList<>();

        // 수신 대상 이메일 추가
        toUserList.add(principalUser.getEmail());

        // 단순 텍스트 구성 메일 메시지 생성
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        // 수신자 설정
        simpleMailMessage.setTo(
                (String[]) toUserList.toArray(new String[toUserList.size()])
        );

        // 메일 세팅
        simpleMailMessage.setSubject(principalUser.getNickname() + "님의 회원 권한 변경 안내");
        simpleMailMessage.setText("안녕하세요 \"" + principalUser.getNickname() + "\" 사용자님\n" +
                "사용자님의 권한이 " + principalRole.name() + "에서 " + role.name() + "으로 변경되었습니다.\n" +
                "감사합니다.");
        // 메일 발송
        javaMailSender.send(simpleMailMessage);
    }


}
