package iuh.fit.se.controller;

import iuh.fit.se.entity.NhaCungCap;
import iuh.fit.se.repository.NhaCungCapRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nhacungcap")
@CrossOrigin(origins = "http://localhost:8080")
public class NhaCungCapRestController {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    // Lấy danh sách tất cả nhà cung cấp
    @GetMapping
    public ResponseEntity<List<NhaCungCap>> getAllNhaCungCap() {
        List<NhaCungCap> list = nhaCungCapRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // Lấy chi tiết một nhà cung cấp
    @GetMapping("/{id}")
    public ResponseEntity<NhaCungCap> getNhaCungCapById(@PathVariable String id) {
        return nhaCungCapRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Thêm mới nhà cung cấp
    @PostMapping
    public ResponseEntity<?> createNhaCungCap(@Valid @RequestBody NhaCungCap nhaCungCap) {
        try {
            // Kiểm tra xem mã nhà cung cấp đã tồn tại chưa
            if (nhaCungCapRepository.existsById(nhaCungCap.getMaNCC())) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Mã nhà cung cấp đã tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            NhaCungCap savedNCC = nhaCungCapRepository.save(nhaCungCap);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNCC);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi thêm nhà cung cấp: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Cập nhật nhà cung cấp
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNhaCungCap(@PathVariable String id, @Valid @RequestBody NhaCungCap nhaCungCap) {
        try {
            if (!nhaCungCapRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Không tìm thấy nhà cung cấp với mã: " + id);
                return ResponseEntity.notFound().build();
            }

            nhaCungCap.setMaNCC(id);
            NhaCungCap updatedNCC = nhaCungCapRepository.save(nhaCungCap);
            return ResponseEntity.ok(updatedNCC);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNhaCungCap(@PathVariable String id) {
        try {
            if (!nhaCungCapRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Không tìm thấy nhà cung cấp với mã: " + id);
                return ResponseEntity.notFound().build();
            }

            nhaCungCapRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Xóa nhà cung cấp thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lỗi khi xóa nhà cung cấp: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
