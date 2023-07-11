<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@include file="header.jsp" %>
        <h1>관리자 로그인</h1>
        <hr />
        <form action="/login" method="post">
            <input type="text" name="username" placeholder="Enter username" /><br />
            <input type="password" name="password" placeholder="Enter password" /><br />
            <button>로그인</button>
        </form>
        </body>

        </html>