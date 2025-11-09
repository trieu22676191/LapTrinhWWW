<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Thêm điện thoại</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>Thêm điện thoại</h2>

<form action="/luu" method="post" enctype="multipart/form-data">
    <div><label>Mã ĐT:</label> <input name="maDT"></div>
    <div><label>Tên ĐT:</label> <input name="tenDT"></div>
    <div><label>Năm SX:</label> <input name="namSX" type="number"></div>
    <div><label>Cấu hình:</label> <input name="cauHinh"></div>
    <div>
        <label>Mã NCC:</label>
        <select name="maNCC">
            <c:forEach var="ncc" items="${listNCC}">
                <option value="${ncc.maNCC}" ${ncc.maNCC == dienThoai.nhaCungCap.maNCC ? 'selected' : ''}>
                        ${ncc.tenNCC} (${ncc.maNCC})
                </option>
            </c:forEach>
        </select>
    </div>
    <div><label>Hình ảnh:</label> <input type="file" name="hinhAnhFile"></div>

    <br>
    <a href="/quan-ly">Quay lại</a>
    <button type="submit">Thêm</button>
</form>
</body>
</html>