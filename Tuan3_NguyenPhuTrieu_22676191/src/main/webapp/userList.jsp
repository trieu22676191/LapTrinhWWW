<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 07/09/2025
  Time: 8:04 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="iuh.fit.se.bai2_jsp.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h2>User List</h2>
<table >
    <tr>
        <th>First Name</th><th>Last Name</th><th>Email</th><th>Gender</th><th>Birthday</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("userList");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getFirstName() %></td>
        <td><%= u.getLastName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getGender() %></td>
        <td><%= u.getBirthday() %></td>
    </tr>
    <%      }
    }
    %>
</table>
</body>
</html>

