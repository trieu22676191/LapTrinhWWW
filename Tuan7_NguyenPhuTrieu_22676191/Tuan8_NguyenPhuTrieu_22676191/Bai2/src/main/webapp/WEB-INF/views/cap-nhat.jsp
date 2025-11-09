<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cập nhật điện thoại</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>Cập nhật điện thoại</h2>

<form action="/luu" method="post" enctype="multipart/form-data">
    <div><label>Mã ĐT:</label> <input name="maDT" value="${dienThoai.maDT}" readonly></div>
    <div><label>Tên ĐT:</label> <input name="tenDT" value="${dienThoai.tenDT}"></div>
    <div><label>Năm SX:</label> <input name="namSX" type="number" value="${dienThoai.namSX}"></div>
    <div><label>Cấu hình:</label> <input name="cauHinh" value="${dienThoai.cauHinh}"></div>
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
    <div>
        <label>Hình ảnh hiện tại:</label><br>
        <c:if test="${not empty dienThoai.hinhAnh}">
            <img src="/${uploadDir}/${dienThoai.hinhAnh}" alt="${dienThoai.tenDT}" width="150">
        </c:if>
    </div>
    <div><label>Chọn ảnh mới (nếu muốn thay đổi):</label> <input type="file" name="hinhAnhFile"></div>

    <br>
    <a href="/quan-ly">Quay lại</a>
    <button type="submit">Cập nhật</button>
</form>
</body>
</html>