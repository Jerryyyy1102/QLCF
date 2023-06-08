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
import POJO.ChiTietPhieuNhap;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaPhieuNhap", "MaNguyenLieu", "SoLuong", "DonGia", "GhiChu"};
    private static final String tenBang = "ChiTietPhieuNhap";
    private static final String duLieu = "%d,%d,%d,%d,N'%s'";

    private static final String ganGiaTri = tenThuocTinh[1] + " = %d," + tenThuocTinh[2]
            + " = %d, " + tenThuocTinh[3] + " = %d," + tenThuocTinh[4] + " = N'%s',";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    static Field[] fields = ChiTietPhieuNhap.class.getDeclaredFields();
    private static String tenCot = "";

    public ChiTietPhieuNhap_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<ChiTietPhieuNhap> layDanhSach(int maPhieuNhap) throws IllegalArgumentException, IllegalAccessException, SQLException {

        String sql = "";
        if (maPhieuNhap != 0) {
            sql += "maPhieuNhap = " + maPhieuNhap;
        }
        List<Map<String, Object>> danhSach;
        if (!sql.isEmpty()) {
            danhSach = SQLHandler.layDanhSach(tenBang, sql);
        } else {
            danhSach = SQLHandler.layDanhSach(tenBang);
        }
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();

        for (Map<String, Object> dong : danhSach) {
            ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (field.getType() == int.class) {
                    field.setInt(chiTietPhieuNhap, (int) dong.get(tenThuocTinh[i]));
                } else if (field.getType() == double.class) {
                    field.setDouble(chiTietPhieuNhap, (double) dong.get(tenThuocTinh[i]));
                } else {
                    field.set(chiTietPhieuNhap, String.valueOf(dong.get(tenThuocTinh[i])));
                }
            }

            list.add(chiTietPhieuNhap);
        }
        return list;
    }

    public static boolean them(ChiTietPhieuNhap chiTietPhieuNhap) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(chiTietPhieuNhap);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChiTietPhieuNhap_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maChiTietPhieuNhap) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maChiTietPhieuNhap));
        return ketQua == 1;
    }

    public static boolean sua(ChiTietPhieuNhap chiTietPhieuNhap, int maChiTietPhieuNhap) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(chiTietPhieuNhap);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChiTietPhieuNhap_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maChiTietPhieuNhap));
        return ketQua == 1;
    }
}
