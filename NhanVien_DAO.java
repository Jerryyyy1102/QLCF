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
import POJO.NhanVien;

/**
 *
 * @author Admin
 */
public class NhanVien_DAO {
    static Field[] fields = NhanVien.class.getDeclaredFields();
    private static final String[] tenThuocTinh = new String[]{"MaNhanVien", "TenNhanVien","NgaySinh","GioiTinh","DiaChi","NgayVaoLam","NgayNghi","MaChucVu"};
    private static final String tenBang = "NhanVien";
    private static final String duLieu = "%d,N'%s',N'%s',%d,N'%s',N'%s',N'%s',N'%s',N'%s'";
    private static String tenCot = "";
    private static final String ganGiaTri = tenThuocTinh[1] + " = N'%s'" + tenThuocTinh[2] + " = N'%s'," + tenThuocTinh[3] + " = %d," + tenThuocTinh[4] + " = N'%s'," + tenThuocTinh[5] + " = N'%s'," + tenThuocTinh[6] + " = N'%s',"+tenThuocTinh[7] + " = N'%s'" ;
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    public NhanVien_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<NhanVien> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<NhanVien> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                NhanVien nhanVien = new NhanVien();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(nhanVien, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(nhanVien, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(nhanVien, dong.get(tenThuocTinh[i]));
                    }
                }

                list.add(nhanVien);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(NhanVien nhanVien) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(nhanVien);
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

    public static boolean sua(NhanVien nhanVien, String maLoai) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(nhanVien);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri),
                dieuKien.formatted(maLoai));
        return ketQua == 1;
    }
    
    
    public static ArrayList<NhanVien> timKiem(String tenNhanVien, String maChucVu) {
        try {
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<NhanVien> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                NhanVien nhanVien = new NhanVien();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(nhanVien, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(nhanVien, (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(nhanVien, dong.get(tenThuocTinh[i]));
                    }
                }

                list.add(nhanVien);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
