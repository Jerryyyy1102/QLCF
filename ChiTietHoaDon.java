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
public class ChiTietHoaDon {

    private int maHoaDon;
    private int maSanPham;
    private int soLuong;
    private int tongTien;
    private String ghiChu;
//<editor-fold defaultstate="collapsed" desc="Constructor">

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maHoaDon, int maSanPham, int soLuong, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public ChiTietHoaDon(int maHoaDon, int maSanPham, int soLuong, int tongTien, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public ChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        this.maHoaDon = chiTietHoaDon.maHoaDon;
        this.maSanPham = chiTietHoaDon.maSanPham;
        this.soLuong = chiTietHoaDon.soLuong;
        this.tongTien = chiTietHoaDon.tongTien;
        this.ghiChu = chiTietHoaDon.ghiChu;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Get/Set">
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

//</editor-fold>
    public String getTenSanPham() throws SQLException {
        String tenSanPham;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("SanPham", "MaSanPham = '" + maSanPham + "'");
        tenSanPham = (String) ketQua.get("TenSanPham");

        return tenSanPham;
    }

    public int getGiaSanPham() throws SQLException {
        int giaSanPham;
        Map<String, Object> ketQua;
        ketQua = SQLHandler.layMot("SanPham", "MaSanPham = '" + maSanPham + "'");
        giaSanPham = (int) ketQua.get("GiaBan");

        return giaSanPham;
    }

    @Override
    public String toString() {
        return getMaHoaDon() + " " + getMaSanPham() + " " + soLuong;
    }

}
