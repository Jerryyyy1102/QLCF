/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Admin
 */
import BUS.SQLHandler;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.PhieuNhap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuNhap_DAO {

    static Field[] fields = PhieuNhap.class.getDeclaredFields();
    private static final String[] tenThuocTinh = new String[]{"MaNhanVien", "ThanhTien", "MaNhaCungCap", "NgayNhap", "MaPhieuNhap"};
    private static final String tenBang = "PhieuNhap";
    private static final String duLieu = "%d,%d,%d,'%s',%d";
    private static String tenCot = "";
    private static final String ganGiaTri = tenThuocTinh[1] + " = %d, "
            + tenThuocTinh[2] + " = %d,"
            + tenThuocTinh[3] + " = '%s', "
            + tenThuocTinh[4] + " = %d";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuNhap_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<PhieuNhap> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach;
            danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<PhieuNhap> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                PhieuNhap phieuNhap = new PhieuNhap();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(phieuNhap, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == Date.class) {
                        field.set(phieuNhap, (Date) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(phieuNhap, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(phieuNhap);
            }
            return list;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PhieuNhap_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<PhieuNhap> layDanhSach(int maNhaCungCap, int maNhanVien) {
        try {
            String sql = "";
            if (maNhaCungCap != 0) {
                sql += "MaNhaCungCap = " + maNhaCungCap;
                if (maNhanVien != 0) {
                    sql += " and MaNhanVien = " + maNhanVien;
                }
            } else if (maNhanVien != 0) {
                sql += " MaNhanVien = " + maNhanVien;
            }
            List<Map<String, Object>> danhSach;
            if (!sql.isEmpty()) {
                danhSach = SQLHandler.layDanhSach(tenBang, sql);
            } else {
                danhSach = SQLHandler.layDanhSach(tenBang);
            }
            ArrayList<PhieuNhap> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                PhieuNhap phieuNhap = new PhieuNhap();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(phieuNhap, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == Date.class) {
                        field.set(phieuNhap, (Date) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(phieuNhap, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(phieuNhap);
            }
            return list;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PhieuNhap_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(PhieuNhap phieuNhap) throws SQLException {
        phieuNhap.setMaPhieuNhap(SQLHandler.layMaTangTuDong(tenThuocTinh[4], tenBang));
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(phieuNhap);
                if (giaTri[i] instanceof Date) {
                    giaTri[i] = sdf.format(giaTri[i]);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(PhieuNhap_DAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maPhieuNhap) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maPhieuNhap));
        return ketQua == 1;
    }

    public static boolean sua(PhieuNhap phieuNhap, int maPhieuNhap) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(phieuNhap);

            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(PhieuNhap_DAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri[1], giaTri[2], giaTri[3], giaTri[4]),
                dieuKien.formatted(maPhieuNhap));
        return ketQua == 1;
    }

    public static ArrayList<PhieuNhap> timKiem(String tenPhieuNhap, String maLoai) {
        try {
            String sql = "TenPhieuNhap like '%' + N'" + tenPhieuNhap + "' + '%'";
            if (!"All".equals(maLoai)) {
                sql += " and PhieuNhap.MaLoai = '" + maLoai + "'";
            }
            List<Map<String, Object>> danhSach = SQLHandler.timKiem(tenBang, sql);

            ArrayList<PhieuNhap> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                PhieuNhap phieuNhap = new PhieuNhap();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);

                    if (field.getType() == int.class) {
                        field.setInt(phieuNhap,
                                (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == double.class) {
                        field.setDouble(phieuNhap,
                                (double) dong.get(tenThuocTinh[i]));
                    } else {
                        field.set(phieuNhap, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(phieuNhap);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhap_DAO.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PhieuNhap_DAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
