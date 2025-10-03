<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 04/10/2025
  Time: 12:24 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.model.DienThoai" %>
<html>
<head><title>Quản lý</title></head>
<body>
<h2>Quản lý (Danh sách có nút xóa)</h2>
<%
    List<DienThoai> list = iuh.fit.se.dao.DienThoaiDAO.getAll();
%>
<table border="1" cellpadding="6">
    <tr><th>Mã</th><th>Tên</th><th>Ảnh</th><th>Xóa</th></tr>
    <% for (DienThoai d : list) { %>
    <tr>
        <td><%=d.getMaDT()%></td>
        <td><%=d.getTenDT()%></td>
        <td><img src="<%=request.getContextPath()%>/images/<%=d.getHinhAnh()%>" width="100"/></td>
        <td><a href="<%=request.getContextPath()%>/QuanLyFormServlet?action=delete&maDT=<%=d.getMaDT()%>" onclick="return confirm('Xóa?')">Xóa</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>

