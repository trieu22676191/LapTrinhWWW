<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<h2>📝 Cập Nhật Điện Thoại</h2>

<div id="loadingDiv" class="loading">Đang tải dữ liệu...</div>
<div id="errorDiv" class="error" style="display: none;"></div>
<div id="successDiv" class="success" style="display: none;"></div>

<div class="card" id="formCard" style="display: none;">
    <form id="updatePhoneForm" onsubmit="handleSubmit(event)">
        <div class="form-group">
            <label for="maDT">Mã Điện Thoại:</label>
            <input type="text" id="maDT" name="maDT" readonly style="background-color: #e2e8f0;">
        </div>
        
        <div class="form-group">
            <label for="tenDT">Tên Điện Thoại: <span style="color: red;">*</span></label>
            <input type="text" id="tenDT" name="tenDT" required>
        </div>
        
        <div class="form-group">
            <label for="namSX">Năm Sản Xuất:</label>
            <input type="number" id="namSX" name="namSX" min="2000" max="2030">
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
            <label>Hình Ảnh Hiện Tại:</label>
            <div id="currentImage"></div>
        </div>
        
        <div class="form-group">
            <label for="hinhAnh">Chọn Hình Ảnh Mới (nếu muốn thay đổi):</label>
            <input type="file" id="hinhAnh" name="hinhAnh" accept="image/*">
        </div>
        
        <div style="margin-top: 30px;">
            <button type="submit" class="btn btn-success">Cập Nhật</button>
            <a href="/quan-ly" class="btn btn-primary" style="margin-left: 10px;">Quay Lại</a>
        </div>
    </form>
</div>

<script>
    const API_URL = 'http://localhost:8081';
    let currentPhone = null;
    console.log('=== Cập Nhật Page ===');
    console.log('API_URL:', API_URL);
    
    // Lấy ID từ URL
    function getPhoneIdFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        console.log('Phone ID from URL:', id);
        return id;
    }
    
    // Load thông tin điện thoại
    async function loadDienThoai() {
        const maDT = getPhoneIdFromURL();
        if (!maDT) {
            showError('Không tìm thấy mã điện thoại');
            return;
        }
        
        const loadingDiv = document.getElementById('loadingDiv');
        const formCard = document.getElementById('formCard');
        
        loadingDiv.style.display = 'block';
        formCard.style.display = 'none';
        
        try {
            const url = API_URL + '/api/dienthoai/' + maDT;
            console.log('Fetching:', url);
            
            const response = await fetch(url);
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                throw new Error('Không tìm thấy điện thoại');
            }
            
            currentPhone = await response.json();
            console.log('Phone data:', currentPhone);
            
            // Điền dữ liệu vào form
            document.getElementById('maDT').value = currentPhone.maDT;
            document.getElementById('tenDT').value = currentPhone.tenDT;
            document.getElementById('namSX').value = currentPhone.namSX;
            document.getElementById('cauHinh').value = currentPhone.cauHinh || '';
            
            // Hiển thị hình ảnh hiện tại
            const currentImageDiv = document.getElementById('currentImage');
            if (currentPhone.hinhAnh) {
                currentImageDiv.innerHTML = '<img src="' + API_URL + '/uploads/' + currentPhone.hinhAnh + 
                    '" class="phone-image" alt="' + currentPhone.tenDT + '" style="width: 150px; height: 150px;">';
            } else {
                currentImageDiv.innerHTML = '<span>Chưa có hình ảnh</span>';
            }
            
            // Load nhà cung cấp
            await loadNhaCungCap();
            
            // Chọn nhà cung cấp hiện tại
            if (currentPhone.nhaCungCap) {
                document.getElementById('maNCC').value = currentPhone.nhaCungCap.maNCC;
                console.log('Selected NCC:', currentPhone.nhaCungCap.maNCC);
            }
            
            loadingDiv.style.display = 'none';
            formCard.style.display = 'block';
            console.log('✅ Load điện thoại thành công!');
        } catch (error) {
            console.error('❌ Lỗi:', error);
            loadingDiv.style.display = 'none';
            showError(error.message);
        }
    }
    
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
            });
            
            console.log('✅ Load nhà cung cấp thành công!');
        } catch (error) {
            console.error('❌ Lỗi load nhà cung cấp:', error);
        }
    }
    
    // Xử lý submit form
    async function handleSubmit(event) {
        event.preventDefault();
        console.log('Updating phone...');
        
        const errorDiv = document.getElementById('errorDiv');
        const successDiv = document.getElementById('successDiv');
        errorDiv.style.display = 'none';
        successDiv.style.display = 'none';
        
        const formData = new FormData();
        formData.append('tenDT', document.getElementById('tenDT').value);
        formData.append('namSX', document.getElementById('namSX').value);
        formData.append('cauHinh', document.getElementById('cauHinh').value);
        formData.append('maNCC', document.getElementById('maNCC').value);
        
        const fileInput = document.getElementById('hinhAnh');
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]);
            console.log('New file:', fileInput.files[0].name);
        }
        
        const maDT = document.getElementById('maDT').value;
        
        try {
            const url = API_URL + '/api/dienthoai/' + maDT + '/with-image';
            console.log('Putting to:', url);
            
            const response = await fetch(url, {
                method: 'PUT',
                body: formData
            });
            
            console.log('Response status:', response.status);
            
            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Không thể cập nhật điện thoại');
            }
            
            successDiv.textContent = 'Cập nhật điện thoại thành công!';
            successDiv.style.display = 'block';
            console.log('✅ Cập nhật thành công!');
            
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
    
    // Hiển thị lỗi
    function showError(message) {
        const errorDiv = document.getElementById('errorDiv');
        errorDiv.textContent = 'Lỗi: ' + message;
        errorDiv.style.display = 'block';
    }
    
    // Load dữ liệu khi trang được tải
    window.onload = function() {
        console.log('Window loaded!');
        loadDienThoai();
    };
    
    console.log('Script loaded!');
</script>

<%@ include file="footer.jsp" %>
