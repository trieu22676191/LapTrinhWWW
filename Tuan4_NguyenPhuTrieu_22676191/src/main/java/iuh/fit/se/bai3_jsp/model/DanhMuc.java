package iuh.fit.se.bai3_jsp.model;

public class DanhMuc {
    private int maDM;
    private String tenDanhMuc;

    public DanhMuc() {}
    public DanhMuc(int maDM, String tenDanhMuc) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
    }
    public int getMaDM() { return maDM; }
    public void setMaDM(int maDM) { this.maDM = maDM; }
    public String getTenDanhMuc() { return tenDanhMuc; }
    public void setTenDanhMuc(String tenDanhMuc) { this.tenDanhMuc = tenDanhMuc; }
}
