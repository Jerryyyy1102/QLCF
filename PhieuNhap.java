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
public class PhieuNhap {

    private int maNhanVien;
    private int thanhTien;
    private int maNhaCungCap;
    private Date ngayNhap;
    private int maPhieuNhap;

    public PhieuNhap() {
    }

    public PhieuNhap(int maPhieuNhap, Date ngayNhap, int thanhTien, int nhaCungCap, int nhanVien) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.thanhTien = thanhTien;
        this.maNhaCungCap = nhaCungCap;
        this.maNhanVien = nhanVien;
    }

    
    public PhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhaCungCap() throws SQLException {
        String TenNhaCungCap = null;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("NhaCungCap", "MaNhaCungCap = " + maNhaCungCap );
        TenNhaCungCap = (String) ketQua.get("TenNhaCungCap");
        return TenNhaCungCap;
    }

    public String getTenNhanVien() throws SQLException {
        String tenNhanVien = null;
        Map<String, Object> ketQua;
            ketQua = SQLHandler.layMot("NhanVien", "MaNhanVien = " + maNhanVien);
            tenNhanVien = (String) ketQua.get("TenNhanVien");
        return tenNhanVien;
    }

    @Override
    public String toString() {
        return String.valueOf(getMaPhieuNhap());
    }
}
