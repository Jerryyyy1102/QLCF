/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
   private String tenDangNhap;
   private String matKhau;
private int maNhanVien;
    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, int maNhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getNhanVien() {
        return maNhanVien;
    }

    public void setNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
   
   
}
