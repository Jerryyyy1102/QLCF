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
import POJO.PhieuXuat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuXuat_DAO {

    static Field[] fields = PhieuXuat.class.getDeclaredFields();
    private static final String[] tenThuocTinh = new String[]{"MaPhieuXuat", "MaNguyenLieu", "SoLuong", "NgayXuat", "MaNhanVien"};
    private static final String tenBang = "PhieuXuat";
    private static final String duLieu = "%d,%d,%d,'%s', %d";
    private static String tenCot = "";
    private static final String ganGiaTri = tenThuocTinh[1] + " = %d," + tenThuocTinh[2] + " = %d," + tenThuocTinh[3] + " = '%s'" + tenThuocTinh[4] +" = %d";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuXuat_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<PhieuXuat> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<PhieuXuat> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                PhieuXuat phieuXuat = new PhieuXuat();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if (field.getType() == int.class) {
                        field.setInt(phieuXuat, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(phieuXuat, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(phieuXuat, dong.get(tenThuocTinh[i]));
                    }
                }

                list.add(phieuXuat);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PhieuXuat_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(PhieuXuat phieuXuat) throws SQLException {
        phieuXuat.setMaPhieuXuat(SQLHandler.layMaTangTuDong(tenThuocTinh[0], tenBang));
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                if (fields[i].get(phieuXuat) instanceof Date) {
                    giaTri[i] = sdf.format((Date) fields[i].get(phieuXuat));
                } else {
                    giaTri[i] = fields[i].get(phieuXuat);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(String maLoai) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maLoai));
        return ketQua == 1;
    }

    public static boolean sua(PhieuXuat phieuXuat, String maLoai) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(phieuXuat);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maLoai));
        return ketQua == 1;
    }
}
