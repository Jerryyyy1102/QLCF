/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SQLHandler {

    public static DatabaseProvider provider = new DatabaseProvider();

    public static List<Map<String, Object>> layDanhSach(String tenBang) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        String lenh = "SELECT * FROM " + tenBang;
        ResultSet resultSet = provider.executeQuery(lenh);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
            rows.add(row);
        }

        return rows;
    }

    public static List<Map<String, Object>> layDanhSach(String tenBang, String dieuKien) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        String lenh = "SELECT * FROM " + tenBang + " WHERE " + dieuKien;
        ResultSet resultSet = provider.executeQuery(lenh);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
            rows.add(row);
        }

        return rows;
    }

    public static int layMaTangTuDong(String tenMa, String tenBang) throws SQLException {

        int ma = 1;
        String sql = "SELECT MAX(%s) FROM %s";
        ResultSet rs = SQLHandler.provider.executeQuery(sql.formatted(tenMa, tenBang));
        if (rs.next()) {
            int maCuoi = rs.getInt(1);
            ma = maCuoi + 1;
        }
        return ma;
    }

    //<editor-fold defaultstate="collapsed" desc="Statement">
    public static int them(String tenBang, String tenCot, String duLieu) {
        try {
            String lenh = "INSERT INTO " + tenBang + " (" + tenCot + ") VALUES (" + duLieu + ")";

            provider.executeUpdate(lenh);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int xoa(String tenBang, String dieuKien) {
        String lenh = "DELETE FROM " + tenBang + " WHERE " + dieuKien;
        provider.executeUpdate(lenh);

        return 1;
    }

    public static int sua(String tenBang, String ganGiaTri, String dieuKien) {
        String lenh = "UPDATE " + tenBang + " SET " + ganGiaTri + " WHERE " + dieuKien;

        provider.executeUpdate(lenh);

        return 1;
    }

    public static List<Map<String, Object>> timKiem(String tenBang, String dieuKien) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        String lenh = "SELECT * FROM " + tenBang + " WHERE " + dieuKien;
        ResultSet resultSet = provider.executeQuery(lenh);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
            rows.add(row);
        }

        return rows;
    }

    public static Map<String, Object> layMot(String tenBang, String dieuKien) throws SQLException {
        Map<String, Object> row = null;

        String lenh = "SELECT * FROM " + tenBang + " WHERE " + dieuKien;
        ResultSet resultSet = provider.executeQuery(lenh);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        if (resultSet.next()) {
            row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
        }

        return row;
    }
    //</editor-fold>

    public static List<Map<String, Object>> thucThiLenh(String lenh) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        ResultSet resultSet = provider.executeQuery(lenh);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
            rows.add(row);
        }

        return rows;
    }
    
    //<editor-fold defaultstate="collapsed" desc="PreparedStatement">  
    public static int them(String tenBang, String tenCot, String duLieu, Object[] thamSo) {

        try {
            String lenh = "INSERT INTO " + tenBang + " (" + tenCot + ") VALUES (" + duLieu + ")";
            provider.executeUpdate(lenh, thamSo);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int xoa(String tenBang, String dieuKien, Object[] thamSo) {
        String lenh = "DELETE FROM " + tenBang + " WHERE " + dieuKien;
        DatabaseProvider provider = new DatabaseProvider();

        provider.executeUpdate(lenh, thamSo);

        return 1;
    }

    public static int sua(String tenBang, String ganGiaTri, String dieuKien, Object[] thamSo) {
        String lenh = "UPDATE " + tenBang + " SET " + ganGiaTri + " WHERE " + dieuKien;

        provider.executeUpdate(lenh, thamSo);

        return 1;
    }

    public static List<Map<String, Object>> timKiem(String tenBang, String dieuKien, Object[] thamSo) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        String lenh = "SELECT * FROM " + tenBang + " WHERE " + dieuKien;
        ResultSet resultSet = provider.executeQuery(lenh, thamSo);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
            rows.add(row);
        }

        return rows;
    }

    public static Map<String, Object> layMot(String tenBang, String dieuKien, Object[] thamSo) throws SQLException {
        Map<String, Object> row = null;

        String lenh = "SELECT * FROM " + tenBang + " WHERE " + dieuKien;
        ResultSet resultSet = provider.executeQuery(lenh, thamSo);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int soCot = metaData.getColumnCount();

        if (resultSet.next()) {
            row = new HashMap<>();
            for (int i = 1; i <= soCot; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                row.put(columnName, columnValue);
            }
        }

        return row;
    }
    //</editor-fold>
}
