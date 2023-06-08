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
public class ChiTietPhieuNhap {

    private int maPhieuNhap;
    private int maNguyenLieu;
    private int soLuong;
    private int donGia;
    private String ghiChu;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(int maPhieuNhap, int maNguyenLieu, int soLuong, int donGia, String ghiChu) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNguyenLieu = maNguyenLieu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ghiChu = ghiChu;
    }

    public ChiTietPhieuNhap(int maPhieuNhap, int maNguyenLieu) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNguyenLieu = maNguyenLieu;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return getMaPhieuNhap() + " " + getMaNguyenLieu();
    }

    public String getTenNguyenLieu() throws SQLException {
        String tenNguyenLieu = null;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("NguyenLieu", "MaNguyenLieu = " + maNguyenLieu);
        tenNguyenLieu = (String) ketQua.get("TenNguyenLieu");

        return tenNguyenLieu;
    }
}
