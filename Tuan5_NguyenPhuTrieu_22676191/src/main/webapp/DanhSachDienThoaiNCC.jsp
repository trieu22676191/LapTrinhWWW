<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 03/10/2025
  Time: 11:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.model.DienThoai" %>
<html>
<head><title>Danh sách điện thoại</title></head>
<body>
<h2>Danh sách điện thoại</h2>
<form method="get" action="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet">
    Tìm theo NCC: <input type="text" name="mancc" placeholder="nhập MANCC (ví dụ NCC01)"/>
    <button type="submit">Lọc</button>
</form>
<table border="1" cellpadding="6">
    <tr><th>Mã</th><th>Tên</th><th>Năm</th><th>Cấu hình</th><th>Ảnh</th><th>NCC</th></tr>
    <%
        List<DienThoai> list = (List<DienThoai>) request.getAttribute("listDT");
        if (list != null && !list.isEmpty()) {
            for (DienThoai dt : list) {
    %>
    <tr>
        <td><%=dt.getMaDT()%></td>
        <td><%=dt.getTenDT()%></td>
        <td><%=dt.getNamSX()%></td>
        <td><%=dt.getCauHinh()%></td>
        <td>
            <img src="<%=request.getContextPath()%>/images/<%=dt.getHinhAnh()%>" width="120" alt="<%=dt.getTenDT()%>"/>
        </td>
        <td><%=dt.getMaNCC()%></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="7">Không có dữ liệu</td></tr>
    <%
        }
    %>
</table>
</body>
</html>


