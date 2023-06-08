/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.SalesGroup;

import Assets.Custom_Item.TableItem;
import DAO.Ban_DAO;
import DAO.ChiTietHoaDon_DAO;
import DAO.HoaDon_DAO;
import GUI.Fr_Login;
import POJO.Ban;
import POJO.ChiTietHoaDon;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Admin
 */
public class Fr_BanHang extends javax.swing.JFrame {

    public static int trangThaiBan = -1;

    public enum TrangThaiBan {
        Khong("Trạng thái"),
        Trong("Trống"),
        DangSuDung("Đang sử dụng");

        private String trangThai;

        private TrangThaiBan(String trangThai) {
            this.trangThai = trangThai;
        }

        @Override
        public String toString() {

            return trangThai;
        }

    }

    public static void load_panelBan(int trangThai) {
        ArrayList<Ban> list = Ban_DAO.layDanhSach(trangThai);
        int n = list.size();
        if (trangThai == 1 || trangThai == -1) {
            n = list.size() - 1;
        }
        int du = n % 4;

        int rows = n / 4 + (du > 0 ? 1 : 0),
                cols = 4,
                hgap = 8,
                vgap = 5;

        panel_Ban.removeAll();
        panel_Ban.setLayout(new GridLayout(rows, cols, hgap, vgap));
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 0);
        panel_Ban.setBorder(emptyBorder);
        for (Ban ban : list) {
            if (ban.getSoBan() == 0) {
                continue;
            }
            TableItem tI = new TableItem(ban);

            tI.addMouseListener(new MouseAdapter() {
                ArrayList<ChiTietHoaDon> danhSachDat = new ArrayList<>();

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (ban.getTrangThai()) {
                        danhSachDat = ChiTietHoaDon_DAO.layDanhSach(HoaDon_DAO.layHoaDonCuaBanDangSuDung(ban.getSoBan()).getMaHoaDon());
                    }
                    if(e.getClickCount() == 1){
                        TrangThaiBan trangThaiBan = ban.getTrangThai() ? TrangThaiBan.DangSuDung : TrangThaiBan.Trong;
                    panel_ThongTinBan.thayDoiThongTinBan("Bàn số " + ban.getSoBan(), ban.getKhuVuc(), trangThaiBan.toString(), ban.getSoGhe() + " chỗ");
                    if (trangThaiBan == TrangThaiBan.DangSuDung) {
                        panel_ThongTinBan.getNr_SoKhach().setValue(HoaDon_DAO.layHoaDonCuaBanDangSuDung(ban.getSoBan()).getSoKhach());
                    }
                    panel_ThongTinBan.load_DanhSachDatMon(danhSachDat);
                    }
                    else if (e.getClickCount() == 2) {
                        JF_GoiMonVaThanhToan jF_GoiMonVaThanhToan = new JF_GoiMonVaThanhToan(ban, danhSachDat);
                        jF_GoiMonVaThanhToan.setVisible(true);
                    }
                }

            });
            panel_Ban.add(tI);
            panel_Ban.revalidate();
            panel_Ban.repaint();
        }
        panel_Ban.revalidate();
        panel_Ban.repaint();
    }

    private void load_cboLocTrangThai() {
        Vector<TrangThaiBan> comboData = new Vector<>();
        comboData.add(TrangThaiBan.Khong);
        comboData.add(TrangThaiBan.DangSuDung);
        comboData.add(TrangThaiBan.Trong);
        cbo_LocTrangThai.setModel(new DefaultComboBoxModel<>(comboData));

        cbo_LocTrangThai.addActionListener((ActionEvent event) -> {
            // Cập nhật thông tin đã chọn khi mục thay đổi
            TrangThaiBan ttb = (TrangThaiBan) cbo_LocTrangThai.getSelectedItem();
            if (null == ttb) {
                load_panelBan(Fr_BanHang.trangThaiBan);
            } else {
                switch (ttb) {
                    case DangSuDung ->
                        trangThaiBan = 1;

                    case Trong ->
                        trangThaiBan = 0;

                    default ->
                        trangThaiBan = -1;
                }
            }
            System.out.println(trangThaiBan);
            load_panelBan(trangThaiBan);
        });
    }

    private void load_Form() {

        setLocationRelativeTo(null);
        setTitle("Trang chủ");
        load_cboLocTrangThai();
        load_panelBan(Fr_BanHang.trangThaiBan);
        txt_TenNhanVien.setText(Fr_Login.nhanVien.getTenNhanVien());
    }

    /**
     * Creates new form Fr_CuaHang
     */
    public Fr_BanHang() {
        initComponents();
        load_Form();
    }

    //<editor-fold defaultstate="collapsed" desc="Các method xử lý">
    //</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Tab_TenNV = new javax.swing.JPanel();
        txt_TenNhanVien = new javax.swing.JLabel();
        Tab_LichSu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Tab_DangXuat = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Tab_PhieuXuat = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbo_LocTrangThai = new javax.swing.JComboBox<>();
        btn_MangVe = new javax.swing.JButton();
        panel_ThongTinBan = new GUI.SalesGroup.JP_ThongTinBan();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_Ban = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1073, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(222, 184, 135));

        jPanel9.setBackground(new java.awt.Color(222, 184, 135));
        jPanel9.setPreferredSize(new java.awt.Dimension(1080, 1920));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(222, 184, 135));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/Logo3.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jPanel6.setBackground(new java.awt.Color(138, 91, 43));

        Tab_TenNV.setBackground(new java.awt.Color(222, 184, 135));

        txt_TenNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_TenNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-user-30.png"))); // NOI18N
        txt_TenNhanVien.setText("Tên NV");

        javax.swing.GroupLayout Tab_TenNVLayout = new javax.swing.GroupLayout(Tab_TenNV);
        Tab_TenNV.setLayout(Tab_TenNVLayout);
        Tab_TenNVLayout.setHorizontalGroup(
            Tab_TenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_TenNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Tab_TenNVLayout.setVerticalGroup(
            Tab_TenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_TenNVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_TenNhanVien)
                .addContainerGap())
        );

        Tab_LichSu.setBackground(new java.awt.Color(222, 184, 135));
        Tab_LichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_LichSuMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-clock-30.png"))); // NOI18N
        jLabel5.setText("Lịch sử");

        javax.swing.GroupLayout Tab_LichSuLayout = new javax.swing.GroupLayout(Tab_LichSu);
        Tab_LichSu.setLayout(Tab_LichSuLayout);
        Tab_LichSuLayout.setHorizontalGroup(
            Tab_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_LichSuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );
        Tab_LichSuLayout.setVerticalGroup(
            Tab_LichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_LichSuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        Tab_DangXuat.setBackground(new java.awt.Color(222, 184, 135));
        Tab_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_DangXuatMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-logout-30.png"))); // NOI18N
        jLabel7.setText("Đăng xuất");

        javax.swing.GroupLayout Tab_DangXuatLayout = new javax.swing.GroupLayout(Tab_DangXuat);
        Tab_DangXuat.setLayout(Tab_DangXuatLayout);
        Tab_DangXuatLayout.setHorizontalGroup(
            Tab_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_DangXuatLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Tab_DangXuatLayout.setVerticalGroup(
            Tab_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_DangXuatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Tab_PhieuXuat.setBackground(new java.awt.Color(222, 184, 135));
        Tab_PhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tab_PhieuXuatMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-setting-30.png"))); // NOI18N
        jLabel6.setText("Xuất nguyên liệu");

        javax.swing.GroupLayout Tab_PhieuXuatLayout = new javax.swing.GroupLayout(Tab_PhieuXuat);
        Tab_PhieuXuat.setLayout(Tab_PhieuXuatLayout);
        Tab_PhieuXuatLayout.setHorizontalGroup(
            Tab_PhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_PhieuXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        Tab_PhieuXuatLayout.setVerticalGroup(
            Tab_PhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_PhieuXuatLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab_TenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Tab_PhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tab_LichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(Tab_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Tab_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Tab_LichSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(Tab_PhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Tab_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(749, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        jPanel4.setBackground(new java.awt.Color(222, 184, 135));

        jLabel8.setFont(new java.awt.Font("Poor Richard", 1, 36)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("MONSTER COFFEE");

        cbo_LocTrangThai.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        btn_MangVe.setBackground(new java.awt.Color(138, 91, 43));
        btn_MangVe.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_MangVe.setForeground(new java.awt.Color(255, 255, 255));
        btn_MangVe.setText("Mang về");
        btn_MangVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MangVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btn_MangVe, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_LocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_MangVe)))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_MangVe, cbo_LocTrangThai});

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 0, 1030, 120));
        jPanel9.add(panel_ThongTinBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 390, 620));

        panel_Ban.setBackground(new java.awt.Color(138, 91, 43));

        javax.swing.GroupLayout panel_BanLayout = new javax.swing.GroupLayout(panel_Ban);
        panel_Ban.setLayout(panel_BanLayout);
        panel_BanLayout.setHorizontalGroup(
            panel_BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        panel_BanLayout.setVerticalGroup(
            panel_BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel_Ban);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 740, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tab_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_DangXuatMouseClicked
        // TODO add your handling code here:
        Fr_Login f = new Fr_Login();
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất ?", JOptionPane.ICON_PROPERTY, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            f.setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_Tab_DangXuatMouseClicked

    private void Tab_PhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_PhieuXuatMouseClicked
        // TODO add your handling code here:
        JF_PhieuXuatNguyenLieu f = new JF_PhieuXuatNguyenLieu();
        f.setVisible(true);
    }//GEN-LAST:event_Tab_PhieuXuatMouseClicked

    private void btn_MangVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MangVeActionPerformed
        // TODO add your handling code here:
        new JF_GoiMonVaThanhToan(new Ban(0, 0, "Mang về", false), new ArrayList<>()).setVisible(true);

    }//GEN-LAST:event_btn_MangVeActionPerformed

    private void Tab_LichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tab_LichSuMouseClicked
        // TODO add your handling code here:
        new JF_LichSu().setVisible(true);
    }//GEN-LAST:event_Tab_LichSuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fr_BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fr_BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fr_BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fr_BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Fr_BanHang().setVisible(true);
            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc="Form Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tab_DangXuat;
    private javax.swing.JPanel Tab_LichSu;
    private javax.swing.JPanel Tab_PhieuXuat;
    private javax.swing.JPanel Tab_TenNV;
    private javax.swing.JButton btn_MangVe;
    private javax.swing.JComboBox<TrangThaiBan> cbo_LocTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JPanel panel_Ban;
    public static GUI.SalesGroup.JP_ThongTinBan panel_ThongTinBan;
    private javax.swing.JLabel txt_TenNhanVien;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
