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
import POJO.SanPham;

/**
 *
 * @author Admin
 */
public class SanPham_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaSanPham", "TenSanPham", "GiaBan", "HinhAnh", "MaLoai"};
    private static final String tenBang = "SanPham";
    private static final String duLieu = "%d, N'%s', %d, '%s', '%s'";

    private static final String ganGiaTri = tenThuocTinh[1] + " = N'%s'," + tenThuocTinh[2]
            + " = %d, " + tenThuocTinh[3] + " = '%s', " + tenThuocTinh[4] + "='%s'";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";


    static Field[] fields = SanPham.class.getDeclaredFields();
    private static String tenCot = "";

    public SanPham_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<SanPham> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<SanPham> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                SanPham sanPham = new SanPham();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(sanPham, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(sanPham, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(sanPham, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(sanPham);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(LoaiSanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(SanPham sanPham) throws SQLException {
        sanPham.setMaSanPham(SQLHandler.layMaTangTuDong(tenThuocTinh[0], tenBang));
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(sanPham);

            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(SanPham_DAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maSanPham) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maSanPham));
        return ketQua == 1;
    }

    public static boolean sua(SanPham sanPham, int maSanPham) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(sanPham);

            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(SanPham_DAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri[1], giaTri[2], giaTri[3], giaTri[4]),
                dieuKien.formatted(maSanPham));
        return ketQua == 1;
    }

    public static ArrayList<SanPham> timKiem(String tenSanPham, String maLoai) {
        try {
            String sql = "TenSanPham like '%' + N'" + tenSanPham + "' + '%'";
            if (!"All".equals(maLoai)) {
                sql += " and SanPham.MaLoai = '" + maLoai + "'";
            }
            List<Map<String, Object>> danhSach = SQLHandler.timKiem(tenBang, sql);

            ArrayList<SanPham> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                SanPham sanPham = new SanPham();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);

                    if (field.getType() == int.class) {
                        field.setInt(sanPham,
                                (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(sanPham,
                                (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(sanPham, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(sanPham);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(LoaiSanPham_DAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
