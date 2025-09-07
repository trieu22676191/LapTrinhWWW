<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 07/09/2025
  Time: 9:57 CH
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Multi Language</title>
</head>
<body>

<%-- Lấy ngôn ngữ được chọn từ radio button --%>
<c:set var="languageCode" value="${param.radLanguageCode}" />
<c:if test="${not empty languageCode}">
    <fmt:setLocale value="${languageCode}" scope="session" />
</c:if>

<fmt:setBundle basename="resource" scope="session"/>

<%-- Form chọn ngôn ngữ (submit về chính trang này) --%>
<form method="GET" action="login.jsp">
    <fieldset>
        <legend><fmt:message key="languageMessage"/></legend>
        <input type="radio" name="radLanguageCode" value="vi"
               <c:if test="${languageCode == 'vi'}">checked</c:if>> Tiếng Việt
        <input type="radio" name="radLanguageCode" value="en"
               <c:if test="${languageCode == 'en'}">checked</c:if>> English
        <input type="submit" value="<fmt:message key='choose'/>"/>
    </fieldset>
</form>

<hr/>

<%-- Form login --%>
<form action="login.jsp" method="POST">
    <table>
        <tr>
            <td><fmt:message key="userName"/></td>
            <td><input type="text" name="txtUserName"/></td>
        </tr>
        <tr>
            <td><fmt:message key="pass"/></td>
            <td><input type="password" name="txtPassword"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<fmt:message key='login'/>"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>



