/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.SalesGroup;

import POJO.ChiTietHoaDon;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JP_ThongTinBan extends javax.swing.JPanel {

    public JSpinner getNr_SoKhach() {
        return nr_SoKhach;
    }

    public void setNr_SoKhach(JSpinner nr_SoKhach) {
        this.nr_SoKhach = nr_SoKhach;
    }

    public JLabel getLb_ThoiGianNgoi() {
        return lb_SoChoNgoi;
    }

    public void setLb_ThoiGianNgoi(JLabel lb_ThoiGianNgoi) {
        this.lb_SoChoNgoi = lb_ThoiGianNgoi;
    }

    public JLabel getLb_KhuVuc() {
        return lb_KhuVuc;
    }

    public void setLb_KhuVuc(JLabel lb_KhuVuc) {
        this.lb_KhuVuc = lb_KhuVuc;
    }

    public JLabel getLb_SoBan() {
        return lb_SoBan;
    }

    public void setLb_SoBan(JLabel lb_SoBan) {
        this.lb_SoBan = lb_SoBan;
    }

    public JLabel getLb_TrangThai() {
        return lb_TrangThai;
    }

    public void setLb_TrangThai(JLabel lb_TrangThai) {
        this.lb_TrangThai = lb_TrangThai;
    }

    public JTable getTbl_DanhSachDatMon() {
        return tbl_DanhSachDatMon;
    }

    public void setTbl_DanhSachDatMon(JTable tbl_DanhSachDatMon) {
        this.tbl_DanhSachDatMon = tbl_DanhSachDatMon;
    }

    //<editor-fold defaultstate="collapsed" desc="Xu ly">
    public void thayDoiThongTinBan(String soBan, String khuVuc, String trangThai, String soChoNgoi) {
        lb_SoBan.setText(soBan);
        lb_KhuVuc.setText(khuVuc);
        lb_TrangThai.setText(trangThai);
        lb_SoChoNgoi.setText(soChoNgoi);
    }

    public void load_DanhSachDatMon(ArrayList<ChiTietHoaDon> list) {
        String[] tieuDe = {"Tên sản phẩm", "Giá bán", "Số lượng", "Tổng tiền"};
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        for (ChiTietHoaDon e : list) {
            try {
                Vector v = new Vector();
                v.add(e.getTenSanPham());
                v.add(e.getGiaSanPham());
                v.add(e.getSoLuong());
                v.add(e.getTongTien());
                dtm.addRow(v);
            } catch (SQLException ex) {
                Logger.getLogger(JP_ThongTinBan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tbl_DanhSachDatMon.setModel(dtm);

        tbl_DanhSachDatMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tbl_DanhSachDatMon.setRowHeight(25);
        tbl_DanhSachDatMon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        int width = tbl_DanhSachDatMon.getWidth();
        tbl_DanhSachDatMon.getColumnModel().getColumn(0).setPreferredWidth((int) (width*0.35));
    }

    //</editor-fold>
    /**
     * Creates new form JP_QuanLyBan
     */
    public JP_ThongTinBan() {
        initComponents();
        load_DanhSachDatMon(JF_GoiMonVaThanhToan.listCTHD);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lb_SoBan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_KhuVuc = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_TrangThai = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_SoChoNgoi = new javax.swing.JLabel();
        nr_SoKhach = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_DanhSachDatMon = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(222, 184, 135));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(138, 91, 43)));

        jPanel1.setBackground(new java.awt.Color(138, 91, 43));

        lb_SoBan.setBackground(new java.awt.Color(138, 91, 43));
        lb_SoBan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lb_SoBan.setForeground(new java.awt.Color(255, 255, 102));
        lb_SoBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_SoBan.setText("Bàn số ...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_SoBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_SoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(222, 184, 135));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Khu vực: ");

        lb_KhuVuc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_KhuVuc.setText("Khu vực");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Trạng thái:");

        lb_TrangThai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_TrangThai.setText("Trạng thái");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Số ghế:");

        lb_SoChoNgoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_SoChoNgoi.setText("Số ghế");

        nr_SoKhach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nr_SoKhach.setValue(1);
        nr_SoKhach.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nr_SoKhachStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Số khách:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_KhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_TrangThai))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nr_SoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_SoChoNgoi))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lb_KhuVuc, lb_SoChoNgoi, lb_TrangThai});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel3, jLabel5, jLabel6});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_KhuVuc)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lb_TrangThai))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lb_SoChoNgoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nr_SoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel3, jLabel5, jLabel6, lb_KhuVuc, lb_SoChoNgoi, lb_TrangThai});

        tbl_DanhSachDatMon.setBackground(new java.awt.Color(222, 184, 135));
        tbl_DanhSachDatMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_DanhSachDatMon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nr_SoKhachStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nr_SoKhachStateChanged
        if ((int) nr_SoKhach.getValue() < 1) {
            nr_SoKhach.setValue(1);
        }
    }//GEN-LAST:event_nr_SoKhachStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_KhuVuc;
    private javax.swing.JLabel lb_SoBan;
    private javax.swing.JLabel lb_SoChoNgoi;
    private javax.swing.JLabel lb_TrangThai;
    private javax.swing.JSpinner nr_SoKhach;
    public javax.swing.JTable tbl_DanhSachDatMon;
    // End of variables declaration//GEN-END:variables
}
