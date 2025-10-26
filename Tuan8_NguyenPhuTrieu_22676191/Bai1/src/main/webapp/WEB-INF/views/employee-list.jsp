<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Employee List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<h2 class="text-center mb-4">EMPLOYEE LIST</h2>
<div class="container">
    <a href="/showNewEmployeeForm" class="btn btn-primary mb-3">Add Employee</a>
    <table class="table table-bordered text-center">
        <thead class="table-light">
        <tr>
            <th>First Name</th><th>Last Name</th><th>Gender</th>
            <th>Date of Birth</th><th>Email</th><th>Phone</th><th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${listEmployees}">
            <tr>
                <td>${emp.firstName}</td>
                <td>${emp.lastName}</td>
                <td>${emp.gender}</td>
                <td>${emp.dateOfBirth}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td>
                    <a href="/showFormForUpdate/${emp.id}" class="btn btn-sm btn-link">Update</a>
                    <a href="/deleteEmployee/${emp.id}" class="btn btn-sm btn-link text-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
