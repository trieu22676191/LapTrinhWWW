<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 27/09/2025
  Time: 8:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.bai3_jsp.model.TinTuc, iuh.fit.se.bai3_jsp.model.DanhMuc" %>

<h2>Danh sách tin tức</h2>

<form method="get" action="DanhSachTinTucServlet">
    <label>Chọn danh mục:</label>
    <select name="madm" onchange="this.form.submit()">
        <option value="">--Tất cả--</option>
        <%
            List<DanhMuc> dsDanhMuc = (List<DanhMuc>) request.getAttribute("dsDanhMuc");
            Integer selected = (Integer) request.getAttribute("selectedDM");
            if (dsDanhMuc != null) {
                for (DanhMuc dm : dsDanhMuc) {
        %>
        <option value="<%=dm.getMaDM()%>" <%= (selected!=null && selected==dm.getMaDM())? "selected":"" %>><%=dm.getTenDanhMuc()%></option>
        <%
                }
            }
        %>
    </select>
</form>

<table>
    <tr><th>Mã</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th><th>Danh mục</th></tr>
    <%
        List<TinTuc> listTin = (List<TinTuc>) request.getAttribute("listTin");
        if (listTin != null) {
            for (TinTuc t: listTin) {
    %>
    <tr>
        <td><%=t.getMaTT()%></td>
        <td><%=t.getTieuDe()%></td>
        <td><%=t.getNoiDungTT()%></td>
        <td><a target="_blank" href="<%=t.getLienKet()%>">Link</a></td>
        <td><%=t.getMaDM()%></td>
    </tr>
    <%
            }
        }
    %>
</table>


