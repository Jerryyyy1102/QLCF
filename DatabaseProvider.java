/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class DatabaseProvider {

    static Connection connection = null;

    static String tenServer = "LAPTOP-3HFC21GG",
            tenDatabase = "QuanLyCuaHangCafe",
            tenDangNhap = "",
            matKhau = "";

    public void open() throws ClassNotFoundException, SQLException {
//        tenServer = "Admin\\zerix";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driver);
        String connectionURL = "jdbc:sqlserver://" + tenServer
                + ":1433;databaseName=" + tenDatabase;
        if (!tenDangNhap.isEmpty()) {
            connectionURL += ";user=" + tenDangNhap
                    + ";password=" + matKhau;
        } else {
            connectionURL += "; IntegratedSecurity = true; ";
        }
        connection = DriverManager.getConnection(connectionURL);
//            if (connection != null) {
//                System.out.println("Connected");
//            } else {
//                System.out.println("Can't connected");
//            }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet result = null;
        try {
           
            Statement state = connection.createStatement();
            result = state.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }

        return result;
    }

    public ResultSet executeQuery(String sql, Object[] thamSo) {
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= thamSo.length; i++) {
                preparedStatement.setObject(i, thamSo[i - 1]);
            }
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return result;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return n;
    }

    public int executeUpdate(String sql, Object[] thamSo) {
        int n = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= thamSo.length; i++) {
                preparedStatement.setObject(i, thamSo[i - 1]);
            }
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return n;
    }
}
