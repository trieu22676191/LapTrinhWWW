<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Danh sách điện thoại</title>
    <style> table, th, td { border: 1px solid black; border-collapse: collapse; padding: 8px; } </style>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>Danh sách điện thoại</h2>

<form action="/" method="GET" style="margin-bottom: 15px;">
    Tìm theo NCC:
    <input type="text" name="maNCC" placeholder="nhập MANCC (ví dụ NCC01)" value="${param.maNCC}">
    <button type="submit">Lọc</button>
    <a href="/">Xóa lọc</a>
</form>

<table>
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th>Năm</th>
        <th>Cấu hình</th>
        <th>Ảnh</th>
        <th>NCC</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dt" items="${listDienThoai}">
        <tr>
            <td>${dt.maDT}</td>
            <td>${dt.tenDT}</td>
            <td>${dt.namSX}</td>
            <td>${dt.cauHinh}</td>
            <td>
                <c:if test="${not empty dt.hinhAnh}">
                    <img src="/${uploadDir}/${dt.hinhAnh}" alt="${dt.tenDT}" width="100">
                </c:if>
            </td>
            <td>${dt.nhaCungCap.tenNCC}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>