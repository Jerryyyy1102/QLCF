/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.DatabaseProvider;
import BUS.SQLHandler;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.NguyenLieu;

/**
 *
 * @author Admin
 */
public class NguyenLieu_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaNguyenLieu", "TenNguyenLieu", "XuatXu", "SoLuongTon"};
    private static final String tenBang = "NguyenLieu";
    private static final String duLieu = "%d,N'%s',N'%s',%d";

    private static final String ganGiaTri = tenThuocTinh[1] + " = N'%s'," + tenThuocTinh[2]
            + " = N'%s'," + tenThuocTinh[3] + " = %d";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    static Field[] fields = NguyenLieu.class.getDeclaredFields();
    private static String tenCot = "";

    public NguyenLieu_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<NguyenLieu> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<NguyenLieu> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                NguyenLieu nguyenLieu = new NguyenLieu();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(nguyenLieu, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(nguyenLieu, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(nguyenLieu, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(nguyenLieu);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<NguyenLieu> layDanhSach(int maNhaCungCap) {
        try {
            String lenh = "select distinct nl.MaNguyenLieu, TenNguyenLieu, XuatXu, SoLuongTon from NguyenLieu nl, ChiTietPhieuNhap ctpn, PhieuNhap pn "
                    + "where nl.MaNguyenLieu = ctpn.MaNguyenLieu and ctpn.MaPhieuNhap = pn.MaPhieuNhap and MaNhaCungCap = " + maNhaCungCap;
            List<Map<String, Object>> danhSach = SQLHandler.thucThiLenh(lenh);
            ArrayList<NguyenLieu> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                NguyenLieu nguyenLieu = new NguyenLieu();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(nguyenLieu, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(nguyenLieu, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(nguyenLieu, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(nguyenLieu);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(NguyenLieu nguyenLieu) throws SQLException {
        nguyenLieu.setMaNguyenLieu(SQLHandler.layMaTangTuDong(tenThuocTinh[0], tenBang));
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(nguyenLieu);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maNguyenLieu) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maNguyenLieu));
        return ketQua == 1;
    }

    public static boolean sua(NguyenLieu nguyenLieu, int maNguyenLieu) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(nguyenLieu);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(NguyenLieu_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maNguyenLieu));
        return ketQua == 1;
    }

}
