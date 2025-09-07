<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 07/09/2025
  Time: 10:24 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="iuh.fit.se.bai1_jsp.model.Student" %>
<%
    Student sv = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b3e5fc; /* đồng bộ 1 màu nền */
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #0d47a1;
        }
        table {
            width: 70%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 3px 6px rgba(0,0,0,0.2);
        }
        th, td {
            padding: 10px;
            border: 1px solid #0288d1;
            text-align: left;
        }
        th {
            width: 200px;
            background-color: #81d4fa;
            color: #000;
        }
    </style>
</head>
<body>
<h2>Registration Details</h2>
<table>
    <tr>
        <th>First Name</th>
        <td><%= sv.getFirstName() %></td>
    </tr>
    <tr>
        <th>Last Name</th>
        <td><%= sv.getLastName() %></td>
    </tr>
    <tr>
        <th>Date of Birth</th>
        <td><%= sv.getDateOfBirth() %></td>
    </tr>
    <tr>
        <th>Email</th>
        <td><%= sv.getEmail() %></td>
    </tr>
    <tr>
        <th>Mobile Number</th>
        <td><%= sv.getMobileNumber() %></td>
    </tr>
    <tr>
        <th>Gender</th>
        <td><%= sv.getGender() %></td>
    </tr>
    <tr>
        <th>Address</th>
        <td><%= sv.getAddress() %></td>
    </tr>
    <tr>
        <th>City</th>
        <td><%= sv.getCity() %></td>
    </tr>
    <tr>
        <th>Pin Code</th>
        <td><%= sv.getPinCode() %></td>
    </tr>
    <tr>
        <th>State</th>
        <td><%= sv.getState() %></td>
    </tr>
    <tr>
        <th>Country</th>
        <td><%= sv.getCountry() %></td>
    </tr>
    <tr>
        <th>Hobbies</th>
        <td><%= sv.getHobbies() %></td>
    </tr>
    <tr>
        <th>Course Applied</th>
        <td><%= sv.getCourseApplied() %></td>
    </tr>
</table>
</body>
</html>

