package com.adminapplication.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String basicException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();"); // 알림창 띄우고 페이지는 그대로
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(AuthException.class)
    public String authException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("location.href='/admin/login';"); // 알림창 띄우고 로그인창으로 이동
        sb.append("</script>");
        return sb.toString();
    }
}
