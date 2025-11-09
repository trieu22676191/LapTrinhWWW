package iuh.fit.se.repository;

import iuh.fit.se.entity.DienThoai;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DienThoaiRepository extends JpaRepository<DienThoai, String> {
    List<DienThoai> findByNhaCungCap_MaNCC(String maNCC);
}
