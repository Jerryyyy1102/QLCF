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
public class HoaDon {
    private int maHoaDon;
    private int maNhanVien;
    private int soBan;
    private int thanhTien;
    private Date thoiGianLap;
    private Date thoiGianThanhToan;
    private int soKhach;

    public HoaDon() {
    }

    
    public HoaDon(int maHoaDon, int maNhanVien, int soBan, Date thoiGianLap, Date thoiGianThanhToan, int soKhach) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.soBan = soBan;
        this.thoiGianLap = thoiGianLap;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.soKhach = soKhach;
    }

    public HoaDon(HoaDon hoaDon) {
        this.maHoaDon = hoaDon.getMaHoaDon();
        this.maNhanVien = hoaDon.getMaNhanVien();
        this.soBan = hoaDon.getBan();
        this.soKhach = hoaDon.getSoKhach();
        this.thoiGianLap = hoaDon.getThoiGianLap();
        this.thoiGianThanhToan = hoaDon.getThoiGianThanhToan();
        this.thanhTien = hoaDon.getThanhTien();
    }
    
    

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getBan() {
        return soBan;
    }

    public void setBan(int soBan) {
        this.soBan = soBan;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(Date thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public Date getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(Date thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public int getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(int soKhach) {
        this.soKhach = soKhach;
    }

    @Override
    public String toString() {
        return String.valueOf(getMaHoaDon());
    }
    
    
    
}
