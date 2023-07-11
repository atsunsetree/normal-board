<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@include file="../admin/header.jsp" %>

        <h1>관리자 메인</h1>
        <hr />
        <table border="1">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>메일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </body>

        </html>