/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.DatabaseProvider;
import BUS.SQLHandler;
import POJO.NhanVien;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TaiKhoan_DAO {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static NhanVien dangNhap(String tenDangNhap, String matKhau) {
        NhanVien nhanVien = null;
        try {
            String tenBang = "TaiKhoan tk, NhanVien nv",
                    dieuKien = "TenDangNhap = ? and MatKhau = ? and tk.MaNhanVien = nv.MaNhanVien";
            String[] thamSo = {tenDangNhap, matKhau};
            Map<String, Object> ketQua = SQLHandler.layMot(tenBang, dieuKien, thamSo);
            if (ketQua == null) {
                return null;
            }
            
            nhanVien = new NhanVien();
            nhanVien.setMaNhanVien((int) ketQua.get("MaNhanVien"));
            nhanVien.setTenNhanVien((String) ketQua.get("TenNhanVien"));
            nhanVien.setDiaChi((String) ketQua.get("DiaChi"));
            nhanVien.setGioiTinh((boolean) ketQua.get("GioiTinh"));
            nhanVien.setNgayVaoLam((Date) ketQua.get("NgayVaoLam"));
            nhanVien.setMaChucVu((String) ketQua.get("MaChucVu"));

        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nhanVien;
    }
}
