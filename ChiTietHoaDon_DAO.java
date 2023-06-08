/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.SQLHandler;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.ChiTietHoaDon;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaHoaDon", "MaSanPham", "SoLuong", "TongTien", "GhiChu"};
    private static final String tenBang = "ChiTietHoaDon";
    private static final String duLieu = "%d,%d,%d,%d,N'%s'";

    private static final String ganGiaTri = tenThuocTinh[1] + " = %d,"
            + tenThuocTinh[2] + " = %d, "
            + tenThuocTinh[3] + " = %d "
            + tenThuocTinh[4] + "= N'%s'";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    static Field[] fields = ChiTietHoaDon.class.getDeclaredFields();
    private static String tenCot = "";

    public ChiTietHoaDon_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<ChiTietHoaDon> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<ChiTietHoaDon> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(chiTietHoaDon, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(chiTietHoaDon, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(chiTietHoaDon, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(chiTietHoaDon);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<ChiTietHoaDon> layDanhSach(int MaHoaDon) {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang, "MaHoaDon = " + MaHoaDon);
            ArrayList<ChiTietHoaDon> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(chiTietHoaDon, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(chiTietHoaDon, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(chiTietHoaDon, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(chiTietHoaDon);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(ChiTietHoaDon chiTietHoaDon) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(chiTietHoaDon);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maChiTietHoaDon) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maChiTietHoaDon));
        return ketQua == 1;
    }

    public static boolean xoaTheoHoaDon(int maHoaDon) {
        int ketQua = SQLHandler.xoa(tenBang, "MaHoaDon = " + maHoaDon);
        return ketQua == 1;
    }

    public static boolean sua(ChiTietHoaDon chiTietHoaDon, int maChiTietHoaDon) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(chiTietHoaDon);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChiTietHoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maChiTietHoaDon));
        return ketQua == 1;
    }
}
