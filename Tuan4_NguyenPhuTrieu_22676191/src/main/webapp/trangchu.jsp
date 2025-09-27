<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 27/09/2025
  Time: 8:17 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Quản lý tin tức</title>
    <style>
        body{font-family:Arial; margin:10px;}
        .menu{margin-bottom:10px;}
        .menu a{margin-right:10px;}
        .content{border:1px solid #ccc; padding:15px; min-height:300px;}
        .footer{text-align:center; margin-top:10px;}
        table{border-collapse:collapse;width:100%;}
        table, th, td{border:1px solid #ccc;padding:8px;}
    </style>
</head>
<body>
<div class="menu">
    <a href="DanhSachTinTucServlet">Danh sách tin tức</a>
    <a href="TinTucFormServlet">Thêm tin tức</a>
    <a href="QuanLyFormServlet">Quản lý</a>
</div>
<div class="content">
    <jsp:include page="${page}" />
</div>
<div class="footer">Họ tên sinh viên – Mã SV – Lớp</div>
</body>
</html>


