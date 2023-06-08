/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.SalesGroup;

import Assets.Custom_Item.OrderItem;
import DAO.Ban_DAO;
import DAO.ChiTietHoaDon_DAO;
import DAO.HoaDon_DAO;
import DAO.LoaiSanPham_DAO;
import DAO.SanPham_DAO;
import GUI.Fr_Login;
import POJO.Ban;
import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import POJO.LoaiSanPham;
import POJO.SanPham;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author huyng
 */
public class JF_GoiMonVaThanhToan extends javax.swing.JFrame {

    static String timKiemSanPham = "Nhập tên sản phẩm cần tìm...";
    private Ban ban = new Ban();
    public static HoaDon hd;
    public static ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();

    /**
     * Creates new form JF_GoiMonVaThanhToan
     */
    private void load_panelMenu(String tenSanPham, String maLoai) {
        ArrayList<SanPham> list = SanPham_DAO.timKiem(tenSanPham, maLoai);

        int rows = list.size() / 2,
                cols = 3,
                hgap = 12,
                vgap = 8;
        panel_Menu.removeAll();
        panel_Menu.setLayout(new GridLayout(rows, cols, hgap, vgap));

        ArrayList<Map<Integer, Integer>> soLuongSanPham = new ArrayList();
        listCTHD.forEach(e -> {
            Map<Integer, Integer> row = new HashMap<>();
            row.put(e.getMaSanPham(), e.getSoLuong());
            soLuongSanPham.add(row);
        });
        for (SanPham sanPham : list) {
            int soLuong = 0;
            OrderItem oI;
            for (Map<Integer, Integer> sp : soLuongSanPham) {
                if (sp.containsKey(sanPham.getMaSanPham())) {
                    soLuong = sp.get(sanPham.getMaSanPham());
                }
            }
            oI = new OrderItem(sanPham, soLuong);
            oI.setPreferredSize(new Dimension(150, 140));
            panel_Menu.add(oI);
        }
        panel_Menu.revalidate();
        panel_Menu.repaint();
    }

    private void load_cboLoaiSanPham() {
        Vector<LoaiSanPham> comboData = new Vector<>();
        comboData.add(new LoaiSanPham("All", "Loại sản phẩm"));
        for (LoaiSanPham loaiSanPham : LoaiSanPham_DAO.layDanhSach()) {
            comboData.add(loaiSanPham);
        }
        cbo_TimKiemSanPham.setModel(new DefaultComboBoxModel<>(comboData));
    }

    private void load_timKiem() {
        txt_TimKiemSanPham.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!timKiemSanPham.equals(txt_TimKiemSanPham.getText())) {
                    load_panelMenu(txt_TimKiemSanPham.getText(), ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!timKiemSanPham.equals(txt_TimKiemSanPham.getText())) {
                    load_panelMenu(txt_TimKiemSanPham.getText(), ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Handle change event
            }
        });
    }

    private void load_Form() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        load_timKiem();
        load_cboLoaiSanPham();

        if (ban.getTrangThai() && ban.getSoBan() != 0) {
            btn_TaoBan.setText("Hủy bàn");
            hd = HoaDon_DAO.layHoaDonCuaBanDangSuDung(ban.getSoBan());
            listCTHD = ChiTietHoaDon_DAO.layDanhSach(hd.getMaHoaDon());
        } else {
            hd = new HoaDon();
            listCTHD = new ArrayList<>();
        }
        load_panelMenu("", "All");
    }

    public JF_GoiMonVaThanhToan(Ban ban, ArrayList<ChiTietHoaDon> danhSachDat) {
        initComponents();
        this.ban = ban;
        load_Form();
        if (ban.getSoBan() != 0) {
            setTitle("Bàn số " + ban.getSoBan());
            panel_ThongTinBan1.thayDoiThongTinBan("Bàn số " + ban.getSoBan(),
                    ban.getKhuVuc(),
                    ban.getTrangThai() ? Fr_BanHang.TrangThaiBan.DangSuDung.toString() : Fr_BanHang.TrangThaiBan.Trong.toString(),
                    ban.getSoGhe() + " chỗ");
            panel_ThongTinBan1.getNr_SoKhach().setValue(hd.getSoKhach());

            listCTHD = danhSachDat;
            panel_ThongTinBan1.load_DanhSachDatMon(danhSachDat);
        } else {
            setTitle("Mang về");
            panel_ThongTinBan1.thayDoiThongTinBan("Mang về",
                    ban.getKhuVuc(),
                    "",
                    "");

            panel_ThongTinBan1.getNr_SoKhach().setValue(0);
            panel_ThongTinBan1.getNr_SoKhach().setEnabled(false);

            btn_TaoBan.setVisible(false);
//            Point center = new Point(jPanel2.getWidth() / 2, jPanel2.getHeight() / 2);
//            btn_ThanhToan.setLocation(new Point(center.x - (btn_ThanhToan.getWidth() / 2), center.y - (btn_ThanhToan.getHeight() / 2)));
            btn_ThanhToan.setPreferredSize(new Dimension((int) (btn_ThanhToan.getWidth() * 2.3), btn_ThanhToan.getHeight()));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel_Menu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_TimKiemSanPham = new javax.swing.JTextField();
        cbo_TimKiemSanPham = new javax.swing.JComboBox<>();
        panel_ThongTinBan1 = new GUI.SalesGroup.JP_ThongTinBan();
        jPanel2 = new javax.swing.JPanel();
        btn_TaoBan = new Assets.Custom_Item.ButtonRound();
        btn_ThanhToan = new Assets.Custom_Item.ButtonRound();

        panel_Menu.setBackground(new java.awt.Color(138, 91, 43));

        javax.swing.GroupLayout panel_MenuLayout = new javax.swing.GroupLayout(panel_Menu);
        panel_Menu.setLayout(panel_MenuLayout);
        panel_MenuLayout.setHorizontalGroup(
            panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        panel_MenuLayout.setVerticalGroup(
            panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel_Menu);

        jPanel1.setBackground(new java.awt.Color(222, 184, 135));

        txt_TimKiemSanPham.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt_TimKiemSanPham.setText("Nhập tên sản phẩm cần tìm...");
        txt_TimKiemSanPham.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txt_TimKiemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_TimKiemSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_TimKiemSanPhamMouseExited(evt);
            }
        });

        cbo_TimKiemSanPham.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cbo_TimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TimKiemSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_TimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(cbo_TimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_TimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(222, 184, 135));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(138, 91, 43)));

        btn_TaoBan.setForeground(new java.awt.Color(255, 255, 255));
        btn_TaoBan.setText("Tạo bàn");
        btn_TaoBan.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_TaoBan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_TaoBan.setRadius(20);
        btn_TaoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoBanActionPerformed(evt);
            }
        });

        btn_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThanhToan.setText("Thanh toán");
        btn_ThanhToan.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_ThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_ThanhToan.setRadius(20);
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btn_TaoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_TaoBan, btn_ThanhToan});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TaoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_ThongTinBan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_ThongTinBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_TimKiemSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemSanPhamMouseExited
        if (txt_TimKiemSanPham.getText().isEmpty())
            txt_TimKiemSanPham.setText(timKiemSanPham);
    }//GEN-LAST:event_txt_TimKiemSanPhamMouseExited

    private void cbo_TimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TimKiemSanPhamActionPerformed
        String tK = txt_TimKiemSanPham.getText();
        if (timKiemSanPham.equals(tK)) {
            load_panelMenu("", ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
        } else {
            load_panelMenu(tK, ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
        }
    }//GEN-LAST:event_cbo_TimKiemSanPhamActionPerformed

    private void txt_TimKiemSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemSanPhamMouseEntered
        if (txt_TimKiemSanPham.getText().equals(timKiemSanPham))
            txt_TimKiemSanPham.setText("");
    }//GEN-LAST:event_txt_TimKiemSanPhamMouseEntered

    private boolean taoHoaDon() {
        try {
            if (listCTHD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
            // Tạo hóa đơn
            hd = new HoaDon(0, Fr_Login.nhanVien.getMaNhanVien(),
                    ban.getSoBan(),
                    new Date(), null,
                    (int) panel_ThongTinBan1.getNr_SoKhach().getValue());
            HoaDon_DAO.them(hd);

            // Thêm chi tiết hóa đơn
            for (ChiTietHoaDon cthd : listCTHD) {
                cthd.setMaHoaDon(hd.getMaHoaDon());
                ChiTietHoaDon_DAO.them(cthd);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    private void btn_TaoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoBanActionPerformed
        if (ban.getTrangThai()) {
            if (JOptionPane.showConfirmDialog(this, "Xác nhận hủy bàn số " + ban.getSoBan(), "Xác nhận hủy bàn", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
                // Xóa cthd
                ChiTietHoaDon_DAO.xoaTheoHoaDon(hd.getMaHoaDon());
                // Xóa hd
                HoaDon_DAO.xoa(hd.getMaHoaDon());
                // Đổi trạng thái bàn
                Ban_DAO.thayDoiTrangThai(ban);
                //Load lại panel bàn
                Fr_BanHang.load_panelBan(Fr_BanHang.trangThaiBan);
                // Tắt form
                dispose();
            }
        } else {
            // Tạo bàn
            if (!taoHoaDon()) {
                return;
            }
            // Thay đổi trạng thái bàn và tạo thời gian
            Ban_DAO.thayDoiTrangThai(ban);

            JOptionPane.showMessageDialog(null, "Tạo bàn thành công!");
            Fr_BanHang.load_panelBan(-1);
            this.dispose();

        }

    }//GEN-LAST:event_btn_TaoBanActionPerformed

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        if (hd.getMaHoaDon() == 0) {
            if (!taoHoaDon()) {
                return;
            }
        }
        // Cập nhật lại hóa đơn để lấy thành tiền
        HoaDon_DAO.updateThanhTien();
        hd = HoaDon_DAO.layHoaDonCuaBanDangSuDung(ban.getSoBan());

        // Cập nhật ngày thanh toán cho hóa đơn
        hd.setThoiGianThanhToan(new Date());
        HoaDon_DAO.sua(hd);

        // Hiện bill
        new JF_Bill(hd).setVisible(true);

        // Thay đổi trạng thái bàn
        if (ban.getTrangThai()) {
            Ban_DAO.thayDoiTrangThai(ban);
        }
        // Load lại bàn
        Fr_BanHang.load_panelBan(Fr_BanHang.trangThaiBan);

        // Đóng form
        dispose();
    }//GEN-LAST:event_btn_ThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Assets.Custom_Item.ButtonRound btn_TaoBan;
    private Assets.Custom_Item.ButtonRound btn_ThanhToan;
    private javax.swing.JComboBox<LoaiSanPham> cbo_TimKiemSanPham;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_Menu;
    public static GUI.SalesGroup.JP_ThongTinBan panel_ThongTinBan1;
    private javax.swing.JTextField txt_TimKiemSanPham;
    // End of variables declaration//GEN-END:variables
}
