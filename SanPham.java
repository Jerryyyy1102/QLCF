/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import BUS.SQLHandler;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class SanPham {

    private int maSanPham;
    private String tenSanPham;
    private int giaBan;
    private String hinhAnh;
    private String maLoai;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSanPham, String hinhAnh, String maLoai, int giaBan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.maLoai = maLoai;
        this.giaBan = giaBan;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

//</editor-fold>
    public String getTenLoai() throws SQLException {
        String tenLoai = null;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("LoaiSanPham", "MaLoai = '" + maLoai + "'");
        tenLoai = (String) ketQua.get("TenLoai");

        return tenLoai;
    }

    @Override
    public String toString() {
        return getTenSanPham();
    }
}
