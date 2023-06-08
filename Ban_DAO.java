/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.SQLHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.Ban;

/**
 *
 * @author Admin
 */
public class Ban_DAO {

    private static final String tenBang = "Ban";
    private static final String duLieu = "%d, %d, N'%s', %d";
    private static final String tenCot = "SoBan, SoGhe, KhuVuc, TrangThai";
    private static final String ganGiaTri = "SoGhe = %d, KhuVuc = N'%s', TrangThai = %d";
    private static final String dieuKien = "SoBan = %d";
    private static final String[] tenThuocTinh = new String[]{"SoBan", "SoGhe", "KhuVuc", "TrangThai"};

    public static ArrayList<Ban> layDanhSach() {
        try {
            List<Map<String, Object>> danhSach;
            danhSach = SQLHandler.layDanhSach(tenBang);
            ArrayList<Ban> list = new ArrayList<>();
            for (Map<String, Object> dong : danhSach) {
                Ban ban = new Ban();
                ban.setSoBan((int) dong.get(tenThuocTinh[0]));
                ban.setSoGhe((int) dong.get(tenThuocTinh[1]));
                ban.setKhuVuc((String) dong.get(tenThuocTinh[2]));
                ban.setTrangThai((boolean) dong.get(tenThuocTinh[3]));
                list.add(ban);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(Ban_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Ban> layDanhSach(int trangThai) {
        try {
            List<Map<String, Object>> danhSach;
            if (trangThai == 1 || trangThai == 0) {
                danhSach = SQLHandler.layDanhSach(tenBang, tenThuocTinh[3] + " = " + trangThai);
            } else {
                danhSach = SQLHandler.layDanhSach(tenBang);
            }
            ArrayList<Ban> list = new ArrayList<>();
            for (Map<String, Object> dong : danhSach) {
                Ban ban = new Ban();
                ban.setSoBan((int) dong.get(tenThuocTinh[0]));
                ban.setSoGhe((int) dong.get(tenThuocTinh[1]));
                ban.setKhuVuc((String) dong.get(tenThuocTinh[2]));
                ban.setTrangThai((boolean) dong.get(tenThuocTinh[3]));
                list.add(ban);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(Ban_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean them(Ban ban) {
        int n = SQLHandler.them(tenBang, tenCot,
                duLieu.formatted(
                        ban.getSoBan(),
                        ban.getSoGhe(),
                        ban.getKhuVuc(),
                        ban.getTrangThai()));
        return n == 1;
    }

    public static boolean xoa(int soBan) {
        int n = SQLHandler.xoa(tenBang, dieuKien.formatted(soBan));
        return n == 1;
    }

    public static boolean sua(Ban ban, int soBan) {
        int n = SQLHandler.sua(tenBang,
                ganGiaTri.formatted(
                        ban.getSoGhe(),
                        ban.getKhuVuc(),
                        ban.getTrangThai()),
                dieuKien.formatted(soBan));
        return n == 1;
    }

    public static void thayDoiTrangThai(Ban ban) {
        ban.setTrangThai(!ban.getTrangThai());
        SQLHandler.sua(tenBang,
                ganGiaTri.formatted(
                        ban.getSoGhe(),
                        ban.getKhuVuc(),
                        ban.getTrangThai() ? 1 : 0),
                dieuKien.formatted(ban.getSoBan()));
    }
}
