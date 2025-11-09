<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h2>⚙️ Quản Lý Điện Thoại</h2>

<div id="loadingDiv" class="loading">Đang tải dữ liệu...</div>
<div id="errorDiv" class="error" style="display: none;"></div>
<div id="successDiv" class="success" style="display: none;"></div>

<table id="phoneTable" style="display: none;">
    <thead>
        <tr>
            <th>Hình Ảnh</th>
            <th>Mã ĐT</th>
            <th>Tên Điện Thoại</th>
            <th>Năm SX</th>
            <th>Cấu Hình</th>
            <th>Nhà Cung Cấp</th>
            <th>Thao Tác</th>
        </tr>
    </thead>
    <tbody id="phoneTableBody">
    </tbody>
</table>

<script>
    const API_URL = 'http://localhost:8081';
    console.log('=== Quản Lý Page ===');
    console.log('API_URL:', API_URL);
    
    // Load danh sách điện thoại
    async function loadDienThoai() {
        console.log('Loading điện thoại...');
        
        const loadingDiv = document.getElementById('loadingDiv');
        const errorDiv = document.getElementById('errorDiv');
        const table = document.getElementById('phoneTable');
        const tbody = document.getElementById('phoneTableBody');
        
        loadingDiv.style.display = 'block';
        errorDiv.style.display = 'none';
        table.style.display = 'none';
        
        try {
            const url = API_URL + '/api/dienthoai';
            console.log('Fetching:', url);
            
            const response = await fetch(url);
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                throw new Error('Không thể tải dữ liệu');
            }
            
            const data = await response.json();
            console.log('Điện thoại data:', data);
            
            tbody.innerHTML = '';
            
            if (data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="7" style="text-align: center;">Không có dữ liệu</td></tr>';
            } else {
                data.forEach(dt => {
                    const row = document.createElement('tr');
                    
                    // Tạo cell cho hình ảnh
                    const tdImage = document.createElement('td');
                    if (dt.hinhAnh) {
                        const img = document.createElement('img');
                        img.src = API_URL + '/uploads/' + dt.hinhAnh;
                        img.className = 'phone-image';
                        img.alt = dt.tenDT;
                        tdImage.appendChild(img);
                    } else {
                        tdImage.textContent = 'Không có hình';
                    }
                    row.appendChild(tdImage);
                    
                    // Các cells khác
                    const tdMaDT = document.createElement('td');
                    tdMaDT.textContent = dt.maDT;
                    row.appendChild(tdMaDT);
                    
                    const tdTenDT = document.createElement('td');
                    const strong = document.createElement('strong');
                    strong.textContent = dt.tenDT;
                    tdTenDT.appendChild(strong);
                    row.appendChild(tdTenDT);
                    
                    const tdNamSX = document.createElement('td');
                    tdNamSX.textContent = dt.namSX;
                    row.appendChild(tdNamSX);
                    
                    const tdCauHinh = document.createElement('td');
                    tdCauHinh.textContent = dt.cauHinh || 'N/A';
                    row.appendChild(tdCauHinh);
                    
                    const tdNCC = document.createElement('td');
                    tdNCC.textContent = dt.nhaCungCap ? dt.nhaCungCap.tenNCC : 'N/A';
                    row.appendChild(tdNCC);
                    
                    // Cell thao tác
                    const tdActions = document.createElement('td');
                    
                    const btnUpdate = document.createElement('a');
                    btnUpdate.href = '/cap-nhat?id=' + dt.maDT;
                    btnUpdate.className = 'btn btn-warning';
                    btnUpdate.style.padding = '8px 15px';
                    btnUpdate.style.marginRight = '5px';
                    btnUpdate.textContent = 'Cập nhật';
                    tdActions.appendChild(btnUpdate);
                    
                    const btnDelete = document.createElement('button');
                    btnDelete.className = 'btn btn-danger';
                    btnDelete.style.padding = '8px 15px';
                    btnDelete.textContent = 'Xóa';
                    btnDelete.onclick = function() { deleteDienThoai(dt.maDT); };
                    tdActions.appendChild(btnDelete);
                    
                    row.appendChild(tdActions);
                    tbody.appendChild(row);
                });
            }
            
            loadingDiv.style.display = 'none';
            table.style.display = 'table';
            console.log('✅ Load điện thoại thành công!');
        } catch (error) {
            console.error('❌ Lỗi:', error);
            loadingDiv.style.display = 'none';
            errorDiv.textContent = 'Lỗi: ' + error.message;
            errorDiv.style.display = 'block';
        }
    }
    
    // Xóa điện thoại
    async function deleteDienThoai(maDT) {
        console.log('Deleting:', maDT);
        
        if (!confirm('Bạn có chắc chắn muốn xóa điện thoại này?')) {
            return;
        }
        
        try {
            const url = API_URL + '/api/dienthoai/' + maDT;
            console.log('Deleting:', url);
            
            const response = await fetch(url, {
                method: 'DELETE'
            });
            
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                throw new Error('Không thể xóa điện thoại');
            }
            
            const successDiv = document.getElementById('successDiv');
            successDiv.textContent = 'Xóa điện thoại thành công!';
            successDiv.style.display = 'block';
            console.log('✅ Xóa thành công!');
            
            setTimeout(() => {
                successDiv.style.display = 'none';
            }, 3000);
            
            loadDienThoai();
        } catch (error) {
            console.error('❌ Lỗi:', error);
            const errorDiv = document.getElementById('errorDiv');
            errorDiv.textContent = 'Lỗi: ' + error.message;
            errorDiv.style.display = 'block';
        }
    }
    
    // Load dữ liệu khi trang được tải
    window.onload = function() {
        console.log('Window loaded!');
        loadDienThoai();
    };
    
    console.log('Script loaded!');
</script>

<%@ include file="footer.jsp" %>
