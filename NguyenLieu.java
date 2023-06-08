/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class NguyenLieu {
    private int maNguyenLieu;
    private String tenNguyenLieu;
    private String xuatXu;
    private int soLuongTon;

    public NguyenLieu() {
    }

    public NguyenLieu(int maNguyenLieu, String tenNguyenLieu, String xuatXu, int soLuongTon) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.xuatXu = xuatXu;
        this.soLuongTon = soLuongTon;
    }

    public NguyenLieu(int maNguyenLieu, String tenNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public int getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(int maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
        @Override
    public String toString() {
        return getTenNguyenLieu();
    }
    
}
