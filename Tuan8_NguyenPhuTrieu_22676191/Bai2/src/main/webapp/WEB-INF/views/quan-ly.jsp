<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Quản lý</title>
    <style> table, th, td { border: 1px solid black; border-collapse: collapse; padding: 8px; text-align: center; } </style>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>Quản lý (Danh sách có nút xóa/cập nhật)</h2>

<table>
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th>Ảnh</th>
        <th>Cập nhật</th>
        <th>Xóa</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dt" items="${listDienThoai}">
        <tr>
            <td>${dt.maDT}</td>
            <td>${dt.tenDT}</td>
            <td>
                <c:if test="${not empty dt.hinhAnh}">
                    <img src="/${uploadDir}/${dt.hinhAnh}" alt="${dt.tenDT}" width="100">
                </c:if>
            </td>
            <td>
                <a href="/cap-nhat/${dt.maDT}">Cập nhật</a>
            </td>
            <td>
                <a href="/xoa/${dt.maDT}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>