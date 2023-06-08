/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import BUS.SQLHandler;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class NhanVien {

    private int maNhanVien;
    private String tenNhanVien;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private Date ngayVaoLam;
    private Date ngayNghi;
    private String maChucVu;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String tenNhanVien, Date ngaySinh, boolean gioiTinh, String diaChi, Date ngayVaoLam, Date ngayNghi, String maChucVu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.ngayNghi = ngayNghi;
        this.maChucVu = maChucVu;
    }

    public NhanVien(int maNhanVien, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public Date getNgayNghi() {
        return ngayNghi;
    }

    public void setNgayNghi(Date ngayNghi) {
        this.ngayNghi = ngayNghi;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }
//</editor-fold>

    @Override
    public String toString() {
        return getTenNhanVien();
    }

    public Object getTenChucVu() throws SQLException {
        String tenChucVu = null;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("ChucVu", "MaChucVu = '" + maChucVu + "'");
        tenChucVu = (String) ketQua.get("TenChucVu");

        return tenChucVu;
    }

    public String getTenNhanVien(int ma) throws SQLException {
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("NhanVien", "MaNhanVien = " + ma );
        tenNhanVien = (String) ketQua.get("TenNhanVien");

        return tenNhanVien;
    }
    
}
