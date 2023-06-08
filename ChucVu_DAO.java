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
import POJO.ChucVu;

/**
 *
 * @author Admin
 */
public class ChucVu_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaChuVu", "TenChucVu"};
    private static final String tenBang = "ChucVu";
    private static final String duLieu = "'%s', N'%s'";

    private static final String ganGiaTri = tenThuocTinh[1] + " = N'%s'";
    private static final String dieuKien = tenThuocTinh[0] + " = N'%s'";

    static Field[] fields = ChucVu.class.getDeclaredFields();
    private static String tenCot = "";

    public ChucVu_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<ChucVu> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<ChucVu> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                ChucVu sanPham = new ChucVu();
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
            Logger.getLogger(ChucVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ChucVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(ChucVu sanPham) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(sanPham);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChucVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maChucVu) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maChucVu));
        return ketQua == 1;
    }

    public static boolean sua(ChucVu sanPham, int maChucVu) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(sanPham);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ChucVu_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maChucVu));
        return ketQua == 1;
    }
}
