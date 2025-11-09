<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h2>📱 Danh Sách Điện Thoại</h2>

<div class="filter-section">
    <div class="form-group">
        <label for="nccFilter">Lọc theo Nhà Cung Cấp:</label>
        <select id="nccFilter" onchange="filterByNCC()">
            <option value="">-- Tất Cả --</option>
        </select>
    </div>
</div>

<div id="loadingDiv" class="loading">Đang tải dữ liệu...</div>
<div id="errorDiv" class="error" style="display: none;"></div>

<table id="phoneTable" style="display: none;">
    <thead>
        <tr>
            <th>Hình Ảnh</th>
            <th>Mã ĐT</th>
            <th>Tên Điện Thoại</th>
            <th>Năm SX</th>
            <th>Cấu Hình</th>
            <th>Nhà Cung Cấp</th>
        </tr>
    </thead>
    <tbody id="phoneTableBody">
    </tbody>
</table>

<script>
    const API_URL = 'http://localhost:8081';
    console.log('=== Danh Sách Page ===');
    console.log('API_URL:', API_URL);
    
    // Load danh sách nhà cung cấp
    async function loadNhaCungCap() {
        console.log('Loading nhà cung cấp...');
        try {
            const url = API_URL + '/api/nhacungcap';
            console.log('Fetching:', url);
            
            const response = await fetch(url);
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                throw new Error('HTTP ' + response.status);
            }
            
            const data = await response.json();
            console.log('Nhà cung cấp data:', data);
            
            const select = document.getElementById('nccFilter');
            data.forEach(ncc => {
                const option = document.createElement('option');
                option.value = ncc.maNCC;
                option.textContent = ncc.tenNCC;
                select.appendChild(option);
            });
            
            console.log('✅ Load nhà cung cấp thành công!');
        } catch (error) {
            console.error('❌ Lỗi load nhà cung cấp:', error);
        }
    }
    
    // Load danh sách điện thoại
    async function loadDienThoai(maNCC = '') {
        console.log('Loading điện thoại, maNCC:', maNCC);
        
        const loadingDiv = document.getElementById('loadingDiv');
        const errorDiv = document.getElementById('errorDiv');
        const table = document.getElementById('phoneTable');
        const tbody = document.getElementById('phoneTableBody');
        
        loadingDiv.style.display = 'block';
        errorDiv.style.display = 'none';
        table.style.display = 'none';
        
        try {
            let url = API_URL + '/api/dienthoai';
            if (maNCC) {
                url += '/ncc/' + maNCC;
            }
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
                tbody.innerHTML = '<tr><td colspan="6" style="text-align: center;">Không có dữ liệu</td></tr>';
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
                    
                    tbody.appendChild(row);
                });
            }
            
            loadingDiv.style.display = 'none';
            table.style.display = 'table';
            console.log('✅ Load điện thoại thành công!');
        } catch (error) {
            console.error('❌ Lỗi load điện thoại:', error);
            loadingDiv.style.display = 'none';
            errorDiv.textContent = 'Lỗi: ' + error.message;
            errorDiv.style.display = 'block';
        }
    }
    
    // Lọc theo nhà cung cấp
    function filterByNCC() {
        const maNCC = document.getElementById('nccFilter').value;
        console.log('Filter by NCC:', maNCC);
        loadDienThoai(maNCC);
    }
    
    // Load dữ liệu khi trang được tải
    window.onload = function() {
        console.log('Window loaded!');
        loadNhaCungCap();
        loadDienThoai();
    };
    
    console.log('Script loaded!');
</script>

<%@ include file="footer.jsp" %>
