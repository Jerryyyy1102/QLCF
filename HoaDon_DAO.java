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
import POJO.HoaDon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class HoaDon_DAO {

    private static final String[] tenThuocTinh = new String[]{"MaHoaDon", "MaNhanVien", "SoBan", "ThanhTien", "ThoiGianLap", "ThoiGianThanhToan", "SoKhach"};
    private static final String tenBang = "HoaDon";
    private static final String duLieu = "%d,%d,%d,%s,%s,%s,%d";

    private static final String ganGiaTri = tenThuocTinh[1] + " = %d,"
            + tenThuocTinh[2] + " = %d,"
            + tenThuocTinh[3] + " = %s,"
            + tenThuocTinh[4] + " = %s,"
            + tenThuocTinh[5] + " = %s,"
            + tenThuocTinh[6] + " = %d";
    private static final String dieuKien = tenThuocTinh[0] + " = %d";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    static Field[] fields = HoaDon.class.getDeclaredFields();
    private static String tenCot = "";

    public HoaDon_DAO() {
        for (int i = 0; i < tenThuocTinh.length - 1; i++) {
            tenCot += tenThuocTinh[i] + ",";
        }
        tenCot += tenThuocTinh[tenThuocTinh.length - 1];
    }

    public static ArrayList<HoaDon> layDanhSach() throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            List<Map<String, Object>> danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<HoaDon> list = new ArrayList<>();

            for (Map<String, Object> dong : danhSach) {
                HoaDon hoaDon = new HoaDon();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == int.class) {
                        field.setInt(hoaDon, (int) dong.get(tenThuocTinh[i]));
                    } else if (field.getType() == Date.class) {
                        if(dong.get(tenThuocTinh[i]) == null)
                            field.set(hoaDon, null);
                        else field.set(hoaDon, sdf.parse(((Timestamp) dong.get(tenThuocTinh[i])).toString()));
                    } else {
                        field.set(hoaDon, String.valueOf(dong.get(tenThuocTinh[i])));
                    }
                }

                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void updateThanhTien() {
        String sql = "update HoaDon set ThanhTien = (Select sum(cthd.TongTien) from ChiTietHoaDon cthd "
                + "where HoaDon.MaHoaDon = cthd.MaHoaDon)";
        SQLHandler.provider.executeUpdate(sql);
    }

    public static HoaDon layHoaDonCuaBanDangSuDung(int soBan) {
        try {
            String dieuKien = "SoBan = %d and ThoiGianLap = (select max(ThoiGianLap) from HoaDon hd where SoBan = %d and ThoiGianThanhToan is null)";
            dieuKien = dieuKien.formatted(soBan, soBan);
            Map<String, Object> ketQua = SQLHandler.layMot(tenBang, dieuKien);
            HoaDon hoaDon = new HoaDon();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (field.getType() == int.class) {
                    field.setInt(hoaDon, ketQua.get(tenThuocTinh[i]) == null ? 0 : (int) ketQua.get(tenThuocTinh[i]));
                } else if (field.getType() == Date.class) {
                    field.set(hoaDon, (Date) ketQua.get(tenThuocTinh[i]));
                } else {
                    field.set(hoaDon, String.valueOf(ketQua.get(tenThuocTinh[i])));
                }
            }

            return hoaDon;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean them(HoaDon hoaDon) throws SQLException {
        hoaDon.setMaHoaDon(SQLHandler.layMaTangTuDong(tenThuocTinh[0], tenBang));
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(hoaDon) == null ? "null" : fields[i].get(hoaDon);
                if (giaTri[i] instanceof Date) {
                    giaTri[i] = "'" + sdf.format(giaTri[i]) + "'";
                }

            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        giaTri[3] = "null";

        int ketQua = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(giaTri));
        return ketQua == 1;
    }

    public static boolean xoa(int maHoaDon) {
        int ketQua = SQLHandler.xoa(tenBang, dieuKien.formatted(maHoaDon));
        return ketQua == 1;
    }

    public static boolean sua(HoaDon hoaDon) {
        Object[] giaTri = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                giaTri[i] = fields[i].get(hoaDon);
                if (giaTri[i] instanceof Date) {
                    giaTri[i] = "'" + sdf.format(giaTri[i]) + "'";
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int i = 1;
        int ketQua = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(giaTri[i++], giaTri[i++], giaTri[i++], giaTri[i++], giaTri[i++], giaTri[i++]),
                dieuKien.formatted(hoaDon.getMaHoaDon()));
        return ketQua == 1;
    }
}
