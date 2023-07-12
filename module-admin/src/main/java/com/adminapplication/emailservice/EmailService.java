package com.adminapplication.emailservice;

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

    public void sendMail() {

        // 수신 대상을 담을 배열
        List<String> toUserList = new ArrayList<>();

        // 수신 대상 추가
        toUserList.add("k1m2njun@naver.com");

        // 단순 텍스트 구성 메일 메시지 생성
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        // 수신자 설정
        simpleMailMessage.setTo(
                (String[]) toUserList.toArray(new String[toUserList.size()])
        );

        // 메일 세팅
        simpleMailMessage.setSubject("Subject sample");
        simpleMailMessage.setText("Text Sample");

        // 메일 발송
        javaMailSender.send(simpleMailMessage);
    }


}
