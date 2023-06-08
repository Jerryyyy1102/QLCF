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
import POJO.NhaCungCap;

/**
 *
 * @author Admin
 */
public class NhaCungCap_DAO {
     static Field[] fields = NhaCungCap.class.getDeclaredFields();
    private static final String[] tenThuocTinh = new String[]{"MaNhaCungCap", "TenNhaCungCap","SoDienThoai","Email","DiaChi"};
    private static final String tenBang = "NhaCungCap";
    private static final String duLieu = "%d,N'%s',N'%s',N'%s',N'%s'";
    private static String tenCot = "";
    private static final String ganGiaTri = tenThuocTinh[1] + " = N'%s'"+ tenThuocTinh[2] + " = N'%s'"+ tenThuocTinh[3] + " = N'%s'"+ tenThuocTinh[4] + " = N'%s'";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    public NhaCungCap_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<NhaCungCap> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<NhaCungCap> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                NhaCungCap loaiSanPham = new NhaCungCap();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(loaiSanPham, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(loaiSanPham, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(loaiSanPham, dong.get(tenThuocTinh[i]));
                    }
                }

                list.add(loaiSanPham);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NhaCungCap_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(NhaCungCap loaiSanPham) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(loaiSanPham);
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

    public static boolean sua(NhaCungCap loaiSanPham, String maLoai) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(loaiSanPham);
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
