<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 27/09/2025
  Time: 8:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Kết quả</h2>

<% String msg = (String) request.getAttribute("message"); %>
<% if (msg != null) { %>
<div style="color:green;"><%= msg %></div>
<% } %>

<p><a href="DanhSachTinTucServlet">Quay về danh sách tin</a></p>

