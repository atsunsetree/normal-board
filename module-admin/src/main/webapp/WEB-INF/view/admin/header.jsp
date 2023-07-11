<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>관리자 페이지입니다.</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>
            <ul>
                <c:choose>
                    <%-- 인증 필요 --%>
                    <c:when test="${principal != null}"> <%-- el 표현식 -> 서버 request, session 영역에 접근 가능. --%>
                        <li><a href="/account">계좌목록(인증)</a></li>
                        <li><a href="/account/saveForm">계좌생성(인증)</a></li>
                        <li><a href="/account/transferForm">이체하기(인증)</a></li>
                        <li><a href="/logout">로그아웃</a></li>
                    </c:when>
                    <%-- 인증 필요 없음 --%>
                    <c:otherwise>
                        <li><a href="/loginForm">로그인</a></li>
                        <li><a href="/joinForm">회원가입</a></li>
                        <li><a href="/account/withdrawForm">출금하기(미인증)</a></li>
                        <li><a href="/account/depositForm">입금하기(미인증)</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>