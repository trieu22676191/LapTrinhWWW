<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Điện Thoại</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        
        .container {
            max-width: 1400px;
            margin: 0 auto;
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            overflow: hidden;
        }
        
        header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }
        
        header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
        }
        
        nav {
            background: #2d3748;
            padding: 15px 30px;
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        
        nav a {
            color: white;
            text-decoration: none;
            padding: 12px 30px;
            background: #4a5568;
            border-radius: 8px;
            transition: all 0.3s ease;
            font-weight: 600;
        }
        
        nav a:hover {
            background: #667eea;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .content {
            padding: 40px;
        }
        
        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        
        .btn-primary {
            background: #667eea;
            color: white;
        }
        
        .btn-primary:hover {
            background: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        
        .btn-success {
            background: #48bb78;
            color: white;
        }
        
        .btn-success:hover {
            background: #38a169;
        }
        
        .btn-danger {
            background: #f56565;
            color: white;
        }
        
        .btn-danger:hover {
            background: #e53e3e;
        }
        
        .btn-warning {
            background: #ed8936;
            color: white;
        }
        
        .btn-warning:hover {
            background: #dd6b20;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        th {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px;
            text-align: left;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 14px;
        }
        
        td {
            padding: 15px;
            border-bottom: 1px solid #e2e8f0;
        }
        
        tr:hover {
            background: #f7fafc;
        }
        
        tr:last-child td {
            border-bottom: none;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #2d3748;
            font-weight: 600;
        }
        
        input[type="text"],
        input[type="number"],
        input[type="file"],
        select,
        textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s ease;
        }
        
        input:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        
        .phone-image {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.2);
        }
        
        .error {
            color: #f56565;
            background: #fff5f5;
            padding: 12px;
            border-radius: 8px;
            margin: 15px 0;
            border-left: 4px solid #f56565;
        }
        
        .success {
            color: #48bb78;
            background: #f0fff4;
            padding: 12px;
            border-radius: 8px;
            margin: 15px 0;
            border-left: 4px solid #48bb78;
        }
        
        .loading {
            text-align: center;
            padding: 40px;
            font-size: 18px;
            color: #667eea;
        }
        
        .card {
            background: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .filter-section {
            background: #f7fafc;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>🏪 Quản Lý Điện Thoại</h1>
            <p>Hệ Thống Quản Lý Cửa Hàng Điện Thoại</p>
        </header>
        
        <nav>
            <a href="/">📱 Danh Sách</a>
            <a href="/quan-ly">⚙️ Quản Lý</a>
            <a href="/them-moi">➕ Thêm Mới</a>
        </nav>
        
        <div class="content">

