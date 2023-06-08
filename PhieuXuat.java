/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuXuat {
    private int maPhieuXuat;
    private int maNguyenLieu;
    private int soLuong;
    private Date ngayxuat;
    private int maNhanVien;

    public PhieuXuat(int maNguyenLieu, int soLuong, Date ngayxuat, int maNhanVien) {
        this.maPhieuXuat = 0;
        this.maNguyenLieu = maNguyenLieu;
        this.soLuong = soLuong;
        this.ngayxuat = ngayxuat;
        this.maNhanVien = maNhanVien;
    }

    public PhieuXuat() {
    }

    

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(int maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayxuat() {
        return ngayxuat;
    }

    public void setNgayxuat(Date ngayxuat) {
        this.ngayxuat = ngayxuat;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }
    
    
    
}
