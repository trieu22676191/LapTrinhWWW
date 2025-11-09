package iuh.fit.se.controller;

import iuh.fit.se.entity.DienThoai;
import iuh.fit.se.entity.NhaCungCap;
import iuh.fit.se.repository.DienThoaiRepository;
import iuh.fit.se.repository.NhaCungCapRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dienthoai")
@CrossOrigin(origins = "http://localhost:8080")
public class DienThoaiRestController {

    @Autowired
    private DienThoaiRepository dienThoaiRepository;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Lấy danh sách tất cả điện thoại
    @GetMapping
    public ResponseEntity<List<DienThoai>> getAllDienThoai() {
        List<DienThoai> list = dienThoaiRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // Lấy danh sách điện thoại theo nhà cung cấp
    @GetMapping("/ncc/{maNCC}")
    public ResponseEntity<List<DienThoai>> getDienThoaiByNCC(@PathVariable String maNCC) {
        List<DienThoai> list = dienThoaiRepository.findByNhaCungCap_MaNCC(maNCC);
        return ResponseEntity.ok(list);
    }

    // Lấy chi tiết một điện thoại
    @GetMapping("/{id}")
    public ResponseEntity<DienThoai> getDienThoaiById(@PathVariable String id) {
        return dienThoaiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Thêm mới điện thoại (không có file upload)
    @PostMapping
    public ResponseEntity<?> createDienThoai(@Valid @RequestBody DienThoai dienThoai) {
        try {
            // Kiểm tra xem mã điện thoại đã tồn tại chưa
            if (dienThoaiRepository.existsById(dienThoai.getMaDT())) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Mã điện thoại đã tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            DienThoai savedDT = dienThoaiRepository.save(dienThoai);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDT);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi thêm điện thoại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Cập nhật điện thoại (không có file upload)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDienThoai(@PathVariable String id, @Valid @RequestBody DienThoai dienThoai) {
        try {
            if (!dienThoaiRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Không tìm thấy điện thoại với mã: " + id);
                return ResponseEntity.notFound().build();
            }

            dienThoai.setMaDT(id);
            DienThoai updatedDT = dienThoaiRepository.save(dienThoai);
            return ResponseEntity.ok(updatedDT);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi cập nhật điện thoại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Xóa điện thoại
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDienThoai(@PathVariable String id) {
        try {
            if (!dienThoaiRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Không tìm thấy điện thoại với mã: " + id);
                return ResponseEntity.notFound().build();
            }

            dienThoaiRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa điện thoại thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi xóa điện thoại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Upload hình ảnh
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "File rỗng");
                return ResponseEntity.badRequest().body(error);
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            Map<String, String> response = new HashMap<>();
            response.put("fileName", fileName);
            response.put("message", "Upload thành công");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi upload file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Thêm mới điện thoại kèm file upload
    @PostMapping("/with-image")
    public ResponseEntity<?> createDienThoaiWithImage(
            @RequestParam("maDT") String maDT,
            @RequestParam("tenDT") String tenDT,
            @RequestParam("namSX") int namSX,
            @RequestParam("cauHinh") String cauHinh,
            @RequestParam("maNCC") String maNCC,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            // Kiểm tra xem mã điện thoại đã tồn tại chưa
            if (dienThoaiRepository.existsById(maDT)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Mã điện thoại đã tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            // Tìm nhà cung cấp
            NhaCungCap ncc = nhaCungCapRepository.findById(maNCC)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhà cung cấp: " + maNCC));

            DienThoai dienThoai = new DienThoai();
            dienThoai.setMaDT(maDT);
            dienThoai.setTenDT(tenDT);
            dienThoai.setNamSX(namSX);
            dienThoai.setCauHinh(cauHinh);
            dienThoai.setNhaCungCap(ncc);

            // Xử lý upload file nếu có
            if (file != null && !file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                dienThoai.setHinhAnh(fileName);

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            DienThoai savedDT = dienThoaiRepository.save(dienThoai);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDT);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi thêm điện thoại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Cập nhật điện thoại kèm file upload
    @PutMapping("/{id}/with-image")
    public ResponseEntity<?> updateDienThoaiWithImage(
            @PathVariable String id,
            @RequestParam("tenDT") String tenDT,
            @RequestParam("namSX") int namSX,
            @RequestParam("cauHinh") String cauHinh,
            @RequestParam("maNCC") String maNCC,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            DienThoai dienThoai = dienThoaiRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy điện thoại: " + id));

            // Tìm nhà cung cấp
            NhaCungCap ncc = nhaCungCapRepository.findById(maNCC)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhà cung cấp: " + maNCC));

            dienThoai.setTenDT(tenDT);
            dienThoai.setNamSX(namSX);
            dienThoai.setCauHinh(cauHinh);
            dienThoai.setNhaCungCap(ncc);

            // Xử lý upload file nếu có
            if (file != null && !file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                dienThoai.setHinhAnh(fileName);

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            DienThoai updatedDT = dienThoaiRepository.save(dienThoai);
            return ResponseEntity.ok(updatedDT);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi cập nhật điện thoại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
