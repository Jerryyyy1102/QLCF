/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.SalesGroup;

import DAO.ChiTietHoaDon_DAO;
import GUI.Fr_Login;
import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JF_Bill extends javax.swing.JFrame {

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    private HoaDon hoaDon;

    /**
     * Creates new form JF_Bill
     */
    private void load_Form() {

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        load_HoaDon();
        load_ChiTietHoaDon();
    }

    public JF_Bill() {
        initComponents();
        load_Form();
    }

    private void load_HoaDon() {
        lb_MaHoaDon.setText(String.valueOf(hoaDon.getMaHoaDon()));
        lb_SoBan.setText(String.valueOf(hoaDon.getBan()));
        lb_TenNhanVien.setText(Fr_Login.nhanVien.getTenNhanVien());
        lb_ThanhTien.setText(String.valueOf(hoaDon.getThanhTien()));
        lb_ThoiGianLap.setText(sdf.format(hoaDon.getThoiGianLap()));
        lb_ThoiGianThanhToan.setText(sdf.format(hoaDon.getThoiGianThanhToan()));
    }

    private void load_ChiTietHoaDon() {
        String[] tieuDe = {"Tên sản phẩm", "Giá bán", "Số lượng", "Tổng tiền"};
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        for (ChiTietHoaDon e : ChiTietHoaDon_DAO.layDanhSach(hoaDon.getMaHoaDon())) {
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
        tbl_DanhSachDatMon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));

        tbl_DanhSachDatMon.setRowHeight(20);
        
        int width = tbl_DanhSachDatMon.getWidth();
        tbl_DanhSachDatMon.getColumnModel().getColumn(0).setPreferredWidth((int) (width * 0.35));
        tbl_DanhSachDatMon.getColumnModel().getColumn(1).setPreferredWidth((int) (width * 0.15));
        tbl_DanhSachDatMon.getColumnModel().getColumn(2).setPreferredWidth((int) (width * 0.15));
        tbl_DanhSachDatMon.getColumnModel().getColumn(3).setPreferredWidth((int) (width * 0.25));
    }

    public JF_Bill(HoaDon hoaDon) {
        initComponents();
        this.hoaDon = hoaDon;
        load_Form();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        lb_ThoiGianLap = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lb_TenNhanVien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_MaHoaDon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lb_ThoiGianThanhToan = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lb_SoBan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_In = new Assets.Custom_Item.ButtonRound();
        btn_Thoat = new Assets.Custom_Item.ButtonRound();
        lb_TienCanThanhToan = new javax.swing.JLabel();
        lb_ThanhTien = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachDatMon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setText("Thời gian lập:");

        lb_ThoiGianLap.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_ThoiGianLap.setText("hh:mm:ss dd/MM/yyyy");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Nhân viên:");

        lb_TenNhanVien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_TenNhanVien.setText("Nhân viên thực hiện");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Số Hóa Đơn:");

        lb_MaHoaDon.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_MaHoaDon.setText("Số hóa đơn");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html><b>HÓA ĐƠN THANH TOÁN<b>");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setText("Thời gian thanh toán:");

        lb_ThoiGianThanhToan.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_ThoiGianThanhToan.setText("hh:mm:ss dd/MM/yyyy");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setText("Vị trí bàn:");

        lb_SoBan.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_SoBan.setText("Số bàn");

        btn_In.setText("In");
        btn_In.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_In.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_In.setRadius(20);
        btn_In.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InActionPerformed(evt);
            }
        });

        btn_Thoat.setText("Thoát");
        btn_Thoat.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_Thoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Thoat.setRadius(20);
        btn_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatActionPerformed(evt);
            }
        });

        lb_TienCanThanhToan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lb_TienCanThanhToan.setText("<html><b><i>Tổng số tiền cần thanh toán:<i><b>");

        lb_ThanhTien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_ThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ThanhTien.setText("9999999999");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btn_In, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btn_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_TienCanThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lb_ThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_TienCanThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ThanhTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_In, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

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
        jScrollPane1.setViewportView(tbl_DanhSachDatMon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_ThoiGianLap, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_ThoiGianThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_MaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_SoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lb_MaHoaDon, lb_TenNhanVien, lb_ThoiGianLap, lb_ThoiGianThanhToan});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel12, jLabel18, jLabel19, jLabel20});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_MaHoaDon)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_TenNhanVien)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lb_ThoiGianLap, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lb_ThoiGianThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lb_SoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_InActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InActionPerformed
        // Tạo đối tượng Document
        SimpleDateFormat sdf_print = new SimpleDateFormat("hhmmss_ddMMyyyy");
        Document document = new Document();
        String tenFile = String.valueOf(hoaDon.getMaHoaDon()) + "_" + sdf_print.format(hoaDon.getThoiGianLap()) + ".pdf";

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Chọn thư mục");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Hiển thị hộp thoại chọn thư mục
        int result = chooser.showOpenDialog(this);
        File selectedFile = new File(tenFile);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            System.out.println("Selected Directory: " + selectedFile.getAbsolutePath());
        }
        else return;
        try {
            // Khởi tạo PdfWriter để viết tài liệu PDF vào file
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(selectedFile.getAbsolutePath() + "\\" + tenFile));

            // Mở tài liệu
            document.open();
            btn_In.setVisible(false);
            btn_Thoat.setVisible(false);
            lb_TienCanThanhToan.setText("Tổng số tiền đã thanh toán:");
            Graphics2D g2d = writer.getDirectContent().createGraphicsShapes(getWidth(), getHeight());

            // Vẽ JFrame lên Document bằng Graphics2D
            this.print(g2d);

            // Giải phóng đối tượng Graphics2D
            g2d.dispose();

            // Đóng tài liệu
            document.close();

            JOptionPane.showMessageDialog(this, "In hóa đơn thành công!");
            dispose();
        } catch (DocumentException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_InActionPerformed

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        int n = JOptionPane.showConfirmDialog(null, "Xác nhận thoát?", "Thông báo", JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }

    }//GEN-LAST:event_btn_ThoatActionPerformed

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
            java.util.logging.Logger.getLogger(JF_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Assets.Custom_Item.ButtonRound btn_In;
    private Assets.Custom_Item.ButtonRound btn_Thoat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_MaHoaDon;
    private javax.swing.JLabel lb_SoBan;
    private javax.swing.JLabel lb_TenNhanVien;
    private javax.swing.JLabel lb_ThanhTien;
    private javax.swing.JLabel lb_ThoiGianLap;
    private javax.swing.JLabel lb_ThoiGianThanhToan;
    private javax.swing.JLabel lb_TienCanThanhToan;
    private javax.swing.JTable tbl_DanhSachDatMon;
    // End of variables declaration//GEN-END:variables
}
