<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h2>➕ Thêm Mới Điện Thoại</h2>

<div id="errorDiv" class="error" style="display: none;"></div>
<div id="successDiv" class="success" style="display: none;"></div>

<div class="card">
    <form id="addPhoneForm" onsubmit="handleSubmit(event)">
        <div class="form-group">
            <label for="maDT">Mã Điện Thoại: <span style="color: red;">*</span></label>
            <input type="text" id="maDT" name="maDT" required>
        </div>
        
        <div class="form-group">
            <label for="tenDT">Tên Điện Thoại: <span style="color: red;">*</span></label>
            <input type="text" id="tenDT" name="tenDT" required>
        </div>
        
        <div class="form-group">
            <label for="namSX">Năm Sản Xuất:</label>
            <input type="number" id="namSX" name="namSX" min="2000" max="2030" value="2024">
        </div>
        
        <div class="form-group">
            <label for="cauHinh">Cấu Hình:</label>
            <textarea id="cauHinh" name="cauHinh" rows="3"></textarea>
        </div>
        
        <div class="form-group">
            <label for="maNCC">Nhà Cung Cấp: <span style="color: red;">*</span></label>
            <select id="maNCC" name="maNCC" required>
                <option value="">-- Chọn Nhà Cung Cấp --</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="hinhAnh">Hình Ảnh:</label>
            <input type="file" id="hinhAnh" name="hinhAnh" accept="image/*">
        </div>
        
        <div style="margin-top: 30px;">
            <button type="submit" class="btn btn-success">Thêm Mới</button>
            <a href="/quan-ly" class="btn btn-primary" style="margin-left: 10px;">Quay Lại</a>
        </div>
    </form>
</div>

<script>
    const API_URL = 'http://localhost:8081';
    console.log('=== Thêm Mới Page ===');
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
            
            const select = document.getElementById('maNCC');
            data.forEach(ncc => {
                const option = document.createElement('option');
                option.value = ncc.maNCC;
                option.textContent = ncc.tenNCC + ' (' + ncc.maNCC + ')';
                select.appendChild(option);
                console.log('Added option:', ncc.tenNCC);
            });
            
            console.log('✅ Load nhà cung cấp thành công! Tổng:', data.length);
        } catch (error) {
            console.error('❌ Lỗi load nhà cung cấp:', error);
            alert('Lỗi khi load nhà cung cấp: ' + error.message);
        }
    }
    
    // Xử lý submit form
    async function handleSubmit(event) {
        event.preventDefault();
        console.log('Submitting form...');
        
        const errorDiv = document.getElementById('errorDiv');
        const successDiv = document.getElementById('successDiv');
        errorDiv.style.display = 'none';
        successDiv.style.display = 'none';
        
        const formData = new FormData();
        formData.append('maDT', document.getElementById('maDT').value);
        formData.append('tenDT', document.getElementById('tenDT').value);
        formData.append('namSX', document.getElementById('namSX').value);
        formData.append('cauHinh', document.getElementById('cauHinh').value);
        formData.append('maNCC', document.getElementById('maNCC').value);
        
        const fileInput = document.getElementById('hinhAnh');
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]);
            console.log('File:', fileInput.files[0].name);
        }
        
        try {
            const url = API_URL + '/api/dienthoai/with-image';
            console.log('Posting to:', url);
            
            const response = await fetch(url, {
                method: 'POST',
                body: formData
            });
            
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Không thể thêm điện thoại');
            }
            
            successDiv.textContent = 'Thêm điện thoại thành công!';
            successDiv.style.display = 'block';
            console.log('✅ Thêm thành công!');
            
            // Reset form
            document.getElementById('addPhoneForm').reset();
            
            // Redirect sau 2 giây
            setTimeout(() => {
                window.location.href = '/quan-ly';
            }, 2000);
        } catch (error) {
            console.error('❌ Lỗi:', error);
            errorDiv.textContent = 'Lỗi: ' + error.message;
            errorDiv.style.display = 'block';
        }
    }
    
    // Load dữ liệu khi trang được tải
    window.onload = function() {
        console.log('Window loaded!');
        loadNhaCungCap();
    };
    
    console.log('Script loaded!');
</script>

<%@ include file="footer.jsp" %>
