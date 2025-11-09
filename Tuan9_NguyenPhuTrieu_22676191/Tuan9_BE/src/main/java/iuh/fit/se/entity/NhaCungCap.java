package iuh.fit.se.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {

    @Id
    @Column(name = "MANCC")
    private String maNCC;

    @Column(name = "TENNHACC")
    private String tenNCC;

    @Column(name = "DIACHI")
    private String diaChi;

    @Column(name = "SODIENTHOAI")
    private String soDienThoai;

    @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DienThoai> dsDienThoai;

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public List<DienThoai> getDsDienThoai() {
        return dsDienThoai;
    }

    public void setDsDienThoai(List<DienThoai> dsDienThoai) {
        this.dsDienThoai = dsDienThoai;
    }
}
