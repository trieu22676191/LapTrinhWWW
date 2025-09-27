<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 27/09/2025
  Time: 8:45 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.bai3_jsp.model.TinTuc" %>

<h2>Quản lý tin tức</h2>

<table>
    <tr><th>Mã</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th><th>Danh mục</th><th>Hành động</th></tr>
    <%
        List<TinTuc> list = (List<TinTuc>) request.getAttribute("listTin");
        if (list != null) {
            for (TinTuc t : list) {
    %>
    <tr>
        <td><%=t.getMaTT()%></td>
        <td><%=t.getTieuDe()%></td>
        <td><%=t.getNoiDungTT()%></td>
        <td><a href="<%=t.getLienKet()%>" target="_blank">Link</a></td>
        <td><%=t.getMaDM()%></td>
        <td>
            <a href="QuanLyFormServlet?action=delete&id=<%=t.getMaTT()%>" onclick="return confirm('Bạn chắc chắn muốn xóa?');">Xóa</a>
        </td>
    </tr>
    <%  } } %>
</table>

