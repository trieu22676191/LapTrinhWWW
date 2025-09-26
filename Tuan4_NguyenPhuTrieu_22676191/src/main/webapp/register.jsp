<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 26/09/2025
  Time: 9:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h1>User Registration Form</h1>
        <form action="registration" method="post">
            <div class="form-group">
                <div class="name-fields">
                    <input type="text" name="firstName" placeholder="First Name" required />
                    <input type="text" name="lastName" placeholder="Last Name" required />
                </div>
            </div>
            <br />
            <div class="form-group">
                    <input type="text" name="email" placeholder="Your Email" required />
            </div>
            <br />
            <div class="form-group">
                    <input type="text" name="reEmail" placeholder="Re-enter Email" required />
            </div>
            <br />
            <div class="form-group">
                    <input type="text" name="password" placeholder="New Password" required />
            </div>
            <br />
            <div class="form-group">
                <div class="birthday-fields">
                    <select name="month">
                        <option value="">Month</option>
                        <% for (int i = 1; i <= 12; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>
                    <select name="day">
                        <option value="">Day</option>
                        <% for (int i = 1; i <= 31; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>
                    <select name="year">
                        <option value="">Year</option>
                        <%
                            java.util.Calendar cal = java.util.Calendar.getInstance();
                            int currentYear = cal.get(java.util.Calendar.YEAR);
                            for (int i = currentYear; i >= 1900; i--) {
                        %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>
                </div>
            </div>
            <br />
            <div class="form-group">
                <label>Gender</label>
                <div class="gender-options">
                    <input type="radio" name="gender" value="Female"> Female
                    <input type="radio" name="gender" value="Male"> Male
                </div>
            </div>
            <br />
            <button type="submit" class="btn-signup">Sign Up</button>
        </form>
    </div>
</body>
</html>
