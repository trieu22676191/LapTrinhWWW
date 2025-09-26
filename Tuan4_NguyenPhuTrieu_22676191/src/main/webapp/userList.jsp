<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 26/09/2025
  Time: 10:04 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>Danh sách tài khoản đã đăng ký</h1>
    <table>
        <thead>
        <tr>Họ và tên</tr>
        <tr>Email</tr>
        <tr>Giới tính</tr>
        <tr>Ngày sinh</tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.gender == true}">Nam</c:when>
                        <c:when test="${user.gender == false}">Nữ</c:when>
                    </c:choose>
                </td>
                <td>${user.birthday}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
