<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 27/09/2025
  Time: 8:43 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.bai3_jsp.model.DanhMuc" %>

<h2>Thêm tin tức mới</h2>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<div style="color:red;"><%= error %></div>
<% } %>

<form id="frmAdd" method="post" action="TinTucFormServlet" onsubmit="return validateForm();">
    <table>
        <!-- MATT là auto-increment - nên không đưa vào form; nếu cần có thể thêm input để nhập thủ công -->
        <tr>
            <td>Tiêu đề:</td>
            <td><input type="text" name="tieude" id="tieude" required></td>
        </tr>
        <tr>
            <td>Nội dung (<=255 ký tự):</td>
            <td><textarea name="noidung" id="noidung" rows="4" cols="60" required></textarea></td>
        </tr>
        <tr>
            <td>Liên kết (bắt đầu bằng http://):</td>
            <td><input type="text" name="lienket" id="lienket" required></td>
        </tr>
        <tr>
            <td>Danh mục:</td>
            <td>
                <select name="madm" id="madm" required>
                    <option value="">--Chọn danh mục--</option>
                    <%
                        List<DanhMuc> ds = (List<DanhMuc>) request.getAttribute("dsDanhMuc");
                        if (ds != null) {
                            for (DanhMuc dm: ds) {
                    %>
                    <option value="<%=dm.getMaDM()%>"><%=dm.getTenDanhMuc()%></option>
                    <%  } } %>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Thêm"></td>
        </tr>
    </table>
</form>

<script>
    function validateForm() {
        var tieude = document.getElementById('tieude').value.trim();
        var noidung = document.getElementById('noidung').value.trim();
        var lienket = document.getElementById('lienket').value.trim();
        var madm = document.getElementById('madm').value;

        if (!tieude || !noidung || !lienket || !madm) {
            alert('Vui lòng nhập đầy đủ các trường bắt buộc.');
            return false;
        }

        // link phải bắt đầu http://
        var linkRegex = /^http:\/\/.+/;
        if (!linkRegex.test(lienket)) {
            alert('Liên kết phải bắt đầu bằng "http://".');
            return false;
        }

        // nội dung <= 255 ký tự
        if (noidung.length > 255) {
            alert('Nội dung không được vượt quá 255 ký tự.');
            return false;
        }

        return true;
    }
</script>
