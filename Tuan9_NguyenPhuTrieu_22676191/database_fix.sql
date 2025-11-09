-- Sửa lỗi: Tăng kích thước cột SODIENTHOAI

USE quanlydienthoai;

-- Xóa dữ liệu cũ nếu có lỗi
TRUNCATE TABLE dienthoai;
TRUNCATE TABLE nhacungcap;

-- Sửa kích thước cột SODIENTHOAI
ALTER TABLE nhacungcap MODIFY COLUMN SODIENTHOAI VARCHAR(20);

-- Thêm lại dữ liệu mẫu nhà cung cấp
INSERT INTO nhacungcap VALUES 
('NCC01', 'Apple Inc.', 'Cupertino, CA, USA', '1-800-692-7753'),
('NCC02', 'Samsung Electronics', 'Seoul, South Korea', '+82-2-2053-3000'),
('NCC03', 'Xiaomi Corporation', 'Beijing, China', '+86-10-6046-5566');

-- Thêm lại dữ liệu mẫu điện thoại
INSERT INTO dienthoai VALUES 
('DT01', 'iPhone 15', 2023, 'A16 Bionic, 6GB RAM, 128GB', 'iphone15.png', 'NCC01'),
('DT02', 'Samsung Galaxy S23', 2023, 'Snapdragon 8 Gen 2, 8GB RAM, 256GB', 's23.jpg', 'NCC02'),
('DT03', 'iPhone 16', 2024, 'A17 Pro, 8GB RAM, 256GB', 'ip16.jpg', 'NCC01'),
('DT04', 'Samsung Galaxy A55', 2024, 'Exynos 1480, 8GB RAM, 128GB', 'a55.png', 'NCC02');

