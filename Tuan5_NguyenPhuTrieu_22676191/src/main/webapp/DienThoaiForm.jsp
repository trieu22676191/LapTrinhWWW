<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 03/10/2025
  Time: 11:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Thêm điện thoại</title></head>
<body>
<h2>Thêm điện thoại</h2>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>
<form action="${pageContext.request.contextPath}/DienThoaiFormServlet" method="post" enctype="multipart/form-data">
    Mã ĐT: <input type="text" name="maDT" required/><br/>
    Tên ĐT: <input type="text" name="tenDT" required/><br/>
    Năm SX: <input type="text" name="namSX" pattern="\d{4}" title="4 digits" required/><br/>
    Cấu hình: <input type="text" name="cauHinh" maxlength="255" required/><br/>
    Mã NCC: <input type="text" name="maNCC" required/><br/>
    Hình ảnh: <input type="file" name="hinhAnh" accept=".png,.jpg,.jpeg" required/><br/>
    <button type="submit">Thêm</button>
</form>
</body>
</html>

