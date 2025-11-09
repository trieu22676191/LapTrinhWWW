<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Registration Employee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<div class="container w-50">
    <h2 class="text-center mb-4 fw-bold">REGISTRATION EMPLOYEE</h2>
    <form action="/saveEmployee" method="post">
        <div class="mb-3"><label>First Name</label><input name="firstName" class="form-control"></div>
        <div class="mb-3"><label>Last Name</label><input name="lastName" class="form-control"></div>
        <div class="mb-3"><label>Email</label><input name="email" class="form-control"></div>
        <div class="mb-3"><label>Date of Birth</label><input name="dateOfBirth" type="date" class="form-control"></div>
        <div class="mb-3"><label>Phone number</label><input name="phone" class="form-control"></div>
        <div class="mb-3"><label>Gender</label><br>
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
        </div>
        <div class="mb-3"><label>Address</label><input name="address" class="form-control"></div>
        <a href="/" class="btn btn-secondary">Back</a>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
</body>
</html>
