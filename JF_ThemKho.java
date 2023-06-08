/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.ManagerGroup;

import BUS.Supporter;
import DAO.ChiTietPhieuNhap_DAO;
import DAO.NguyenLieu_DAO;
import DAO.NhaCungCap_DAO;
import DAO.PhieuNhap_DAO;
import GUI.Fr_Login;
import GUI.SalesGroup.JP_ThongTinBan;
import POJO.ChiTietHoaDon;
import POJO.ChiTietPhieuNhap;
import POJO.NguyenLieu;
import POJO.NhaCungCap;
import POJO.PhieuNhap;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huyng
 */
public class JF_ThemKho extends javax.swing.JFrame {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean changed = false;
    ArrayList<NguyenLieu> listNguyenLieu = new ArrayList<>();
    ArrayList<ChiTietPhieuNhap> listChiTietPhieuNhap = new ArrayList<>();
    int n = 1;

    private void load_Form() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txt_ThemNguyenLieuMoi.setVisible(false);

        listNguyenLieu = NguyenLieu_DAO.layDanhSach();
        n = listNguyenLieu.size();

        setTitle("Tạo phiếu nhập mới");
        load_cboNhaCungCap();
        NhaCungCap ncc = (NhaCungCap) cbo_NhaCungCap.getItemAt(0);
        System.out.println(ncc.getMaNhaCungCap());
        load_cboNguyenLieu(ncc.getMaNhaCungCap());
        txt_NgayNhap.setText(sdf.format(new Date()));
        Supporter.ganPhim(KeyEvent.VK_ESCAPE, "Huy", btn_Huy, () -> btn_HuyActionPerformed(null));
        Supporter.ganPhim(KeyEvent.VK_ENTER, "XacNhan", btn_XacNhan, () -> btn_XacNhanActionPerformed(null));

        load_tblChiTietPhieuNhap();
    }

    private void load_cboNhaCungCap() {
        Vector<NhaCungCap> comboData = new Vector<>();
        for (NhaCungCap nhaCungCap : NhaCungCap_DAO.layDanhSach()) {
            comboData.add(nhaCungCap);
        }
        cbo_NhaCungCap.setModel(new DefaultComboBoxModel<>(comboData));
    }

    private void load_cboNguyenLieu(int maNhaCungCap) {
        Vector<NguyenLieu> comboData = new Vector<>();
        for (NguyenLieu nguyenLieu : NguyenLieu_DAO.layDanhSach(maNhaCungCap)) {
            comboData.add(nguyenLieu);
        }
        cbo_NguyenLieu.setModel(new DefaultComboBoxModel<>(comboData));
    }

    private void load_tblChiTietPhieuNhap() {

        String[] tieuDe = {"Tên nguyên liệu", "Đơn giá", "Số lượng", "Tổng tiền"};
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        for (ChiTietPhieuNhap ctpn : listChiTietPhieuNhap) {
            NguyenLieu nguyenLieu = listNguyenLieu.get(ctpn.getMaNguyenLieu() - 1);
            System.out.println(nguyenLieu);
            Vector v = new Vector();
            v.add(nguyenLieu.getTenNguyenLieu());
            v.add(ctpn.getDonGia());
            v.add(ctpn.getSoLuong());
            v.add(ctpn.getDonGia() * ctpn.getSoLuong());
            dtm.addRow(v);
        }
        tbl_ChiTietPhieuNhap.setModel(dtm);

        tbl_ChiTietPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tbl_ChiTietPhieuNhap.setRowHeight(25);
        tbl_ChiTietPhieuNhap.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
        int i = 0;
        int width = tbl_ChiTietPhieuNhap.getWidth();
        tbl_ChiTietPhieuNhap.getColumnModel().getColumn(i++).setPreferredWidth((int) (width * 0.4));
        tbl_ChiTietPhieuNhap.getColumnModel().getColumn(i++).setPreferredWidth((int) (width * 0.2));
        tbl_ChiTietPhieuNhap.getColumnModel().getColumn(i++).setPreferredWidth((int) (width * 0.15));
        tbl_ChiTietPhieuNhap.getColumnModel().getColumn(i++).setPreferredWidth((int) (width * 0.25));
    }

    /**
     * Creates new form JF_PhieuThemSP
     */
    public JF_ThemKho() {
        initComponents();
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

        pnl_ThemSanPham = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbo_NhaCungCap = new javax.swing.JComboBox<>();
        txt_NgayNhap = new Assets.Custom_Item.TextFieldR_Login();
        jPanel3 = new javax.swing.JPanel();
        btn_XacNhan = new Assets.Custom_Item.ButtonRound();
        btn_Huy = new Assets.Custom_Item.ButtonRound();
        panel_ChiTietHoaDon = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbo_NguyenLieu = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_DonGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_ThemChiTietPhieuNhap1 = new Assets.Custom_Item.ButtonRound();
        btn_ThemChiTietPhieuNhap2 = new Assets.Custom_Item.ButtonRound();
        lb_GhiChu = new javax.swing.JLabel();
        txt_GhiChu = new javax.swing.JTextField();
        nr_SoLuong = new javax.swing.JSpinner();
        cb_NguyenLieuMoi = new javax.swing.JCheckBox();
        txt_ThemNguyenLieuMoi = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ChiTietPhieuNhap = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_ThemSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThemSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jPanel12.setBackground(new java.awt.Color(255, 153, 0));
        jPanel12.setPreferredSize(new java.awt.Dimension(282, 112));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Nhập Hàng");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Ngày Nhập");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Nhà Cung Cấp");

        cbo_NhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbo_NhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_NhaCungCapActionPerformed(evt);
            }
        });

        txt_NgayNhap.setEditable(false);
        txt_NgayNhap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_NgayNhap.setLabelText("Ngày/Tháng/Năm");

        btn_XacNhan.setBackground(new java.awt.Color(255, 153, 0));
        btn_XacNhan.setText("Xác Nhận");
        btn_XacNhan.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_XacNhan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_XacNhan.setRadius(20);
        btn_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanActionPerformed(evt);
            }
        });

        btn_Huy.setBackground(new java.awt.Color(255, 153, 0));
        btn_Huy.setText("Hủy");
        btn_Huy.setBorderColor(new java.awt.Color(255, 255, 255));
        btn_Huy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Huy.setRadius(20);
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_ThemSanPhamLayout = new javax.swing.GroupLayout(pnl_ThemSanPham);
        pnl_ThemSanPham.setLayout(pnl_ThemSanPhamLayout);
        pnl_ThemSanPhamLayout.setHorizontalGroup(
            pnl_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_ThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl_ThemSanPhamLayout.createSequentialGroup()
                        .addGroup(pnl_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnl_ThemSanPhamLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel15, jLabel17});

        pnl_ThemSanPhamLayout.setVerticalGroup(
            pnl_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemSanPhamLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl_ThemSanPhamLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel15, jLabel17});

        panel_ChiTietHoaDon.setBackground(new java.awt.Color(222, 184, 135));
        panel_ChiTietHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Nguyên Liệu");
        panel_ChiTietHoaDon.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 17, -1, -1));

        cbo_NguyenLieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_NguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_NguyenLieuActionPerformed(evt);
            }
        });
        panel_ChiTietHoaDon.add(cbo_NguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 160, 35));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Đơn giá");
        panel_ChiTietHoaDon.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 70, 108, -1));

        txt_DonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panel_ChiTietHoaDon.add(txt_DonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 66, 160, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Số Lượng");
        panel_ChiTietHoaDon.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 119, 108, -1));

        btn_ThemChiTietPhieuNhap1.setText("+");
        btn_ThemChiTietPhieuNhap1.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemChiTietPhieuNhap1.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap1.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemChiTietPhieuNhap1.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap1.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemChiTietPhieuNhap1.setRadius(50);
        btn_ThemChiTietPhieuNhap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemChiTietPhieuNhap1ActionPerformed(evt);
            }
        });
        panel_ChiTietHoaDon.add(btn_ThemChiTietPhieuNhap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 270, 119, -1));

        btn_ThemChiTietPhieuNhap2.setText("-");
        btn_ThemChiTietPhieuNhap2.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemChiTietPhieuNhap2.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap2.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemChiTietPhieuNhap2.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap2.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemChiTietPhieuNhap2.setRadius(50);
        btn_ThemChiTietPhieuNhap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemChiTietPhieuNhap2ActionPerformed(evt);
            }
        });
        panel_ChiTietHoaDon.add(btn_ThemChiTietPhieuNhap2, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 270, 119, -1));

        lb_GhiChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_GhiChu.setText("Ghi chú");
        panel_ChiTietHoaDon.add(lb_GhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 176, 108, -1));

        txt_GhiChu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panel_ChiTietHoaDon.add(txt_GhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 172, 160, 35));

        nr_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panel_ChiTietHoaDon.add(nr_SoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 119, 160, 35));

        cb_NguyenLieuMoi.setBackground(new java.awt.Color(222, 184, 135));
        cb_NguyenLieuMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cb_NguyenLieuMoi.setText("Nguyên liệu mới");
        cb_NguyenLieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_NguyenLieuMoiActionPerformed(evt);
            }
        });
        panel_ChiTietHoaDon.add(cb_NguyenLieuMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 225, -1, -1));

        txt_ThemNguyenLieuMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panel_ChiTietHoaDon.add(txt_ThemNguyenLieuMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 160, 35));

        jPanel1.setBackground(new java.awt.Color(222, 184, 135));

        jScrollPane4.setBackground(new java.awt.Color(222, 184, 135));
        jScrollPane4.setBorder(null);

        tbl_ChiTietPhieuNhap.setBackground(new java.awt.Color(222, 184, 135));
        tbl_ChiTietPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_ChiTietPhieuNhap.setRowHeight(40);
        tbl_ChiTietPhieuNhap.setShowGrid(true);
        tbl_ChiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_ChiTietPhieuNhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(138, 91, 43));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chi tiết phiếu nhập");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_ThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_ChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnl_ThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_ChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void taoNguyenLieuMoi() {
        for (int i = NguyenLieu_DAO.layDanhSach().size(); i < n; i++) {
            try {
                NguyenLieu_DAO.them(listNguyenLieu.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(JF_ThemKho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void themChiTietPhieuNhap(int maPhieuNhap) {
        for (ChiTietPhieuNhap ctpn : listChiTietPhieuNhap) {
            ctpn.setMaPhieuNhap(maPhieuNhap);
            ChiTietPhieuNhap_DAO.them(ctpn);
        }
    }

    private void btn_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanActionPerformed
        
        NhaCungCap nhaCungCap = (NhaCungCap) cbo_NhaCungCap.getSelectedItem();
        try {
            PhieuNhap pn = new PhieuNhap(1, sdf.parse(txt_NgayNhap.getText()), 0, nhaCungCap.getMaNhaCungCap(), Fr_Login.nhanVien.getMaNhanVien());
            PhieuNhap_DAO.them(pn);
            taoNguyenLieuMoi();
            themChiTietPhieuNhap(pn.getMaPhieuNhap());
            JOptionPane.showMessageDialog(null, "Thêm thành công");

            dispose();
            fr_CuaHang.load_tblNguyenLieu();
            fr_CuaHang.load_cboNguyenLieu(0);
            fr_CuaHang.load_tblNguyenLieu();
            fr_CuaHang.load_tblPhieuNhap(0, 0);
        } catch (HeadlessException | SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại, lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_XacNhanActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void tbl_ChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietPhieuNhapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_ChiTietPhieuNhapMouseClicked

    private void btn_ThemChiTietPhieuNhap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemChiTietPhieuNhap1ActionPerformed
        ChiTietPhieuNhap chiTietPhieuNhap;

        int donGia = Integer.parseInt(txt_DonGia.getText());
        int soLuong = (int) nr_SoLuong.getValue();
        if (cb_NguyenLieuMoi.isSelected()) {
            String tenNguyenLieu = txt_ThemNguyenLieuMoi.getText();
            String xuatXu = txt_GhiChu.getText();
            // Tao nguyen lieu moi
            NguyenLieu nguyenLieu = new NguyenLieu(++n, tenNguyenLieu, xuatXu, soLuong);

            listNguyenLieu.add(nguyenLieu);
            chiTietPhieuNhap = new ChiTietPhieuNhap(n, nguyenLieu.getMaNguyenLieu(), soLuong, donGia, "");
        } else {
            NguyenLieu nguyenLieu = (NguyenLieu) cbo_NguyenLieu.getSelectedItem();
            String ghiChu = txt_GhiChu.getText();

            chiTietPhieuNhap = new ChiTietPhieuNhap(0, nguyenLieu.getMaNguyenLieu(), soLuong, donGia, ghiChu);
        }
        listChiTietPhieuNhap.add(chiTietPhieuNhap);
        
        load_tblChiTietPhieuNhap();
    }//GEN-LAST:event_btn_ThemChiTietPhieuNhap1ActionPerformed

    private void btn_ThemChiTietPhieuNhap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemChiTietPhieuNhap2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemChiTietPhieuNhap2ActionPerformed

    private void cbo_NhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_NhaCungCapActionPerformed
        NhaCungCap ncc = (NhaCungCap) cbo_NhaCungCap.getSelectedItem();
        load_cboNguyenLieu(ncc.getMaNhaCungCap());
    }//GEN-LAST:event_cbo_NhaCungCapActionPerformed

    private void cb_NguyenLieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_NguyenLieuMoiActionPerformed
        if (cb_NguyenLieuMoi.isSelected()) {
            //Thay combobox Nguyên liệu -> Text
            cbo_NguyenLieu.setVisible(false);
            txt_ThemNguyenLieuMoi.setVisible(true);
            lb_GhiChu.setText("Xuất xứ");

        } else {
            //Thay Text Nguyên liệu -> Combobox
            cbo_NguyenLieu.setVisible(true);
            txt_ThemNguyenLieuMoi.setVisible(false);
            lb_GhiChu.setText("Ghi chú");
        }
        panel_ChiTietHoaDon.revalidate();
        panel_ChiTietHoaDon.repaint();
    }//GEN-LAST:event_cb_NguyenLieuMoiActionPerformed

    private void cbo_NguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_NguyenLieuActionPerformed

    }//GEN-LAST:event_cbo_NguyenLieuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Assets.Custom_Item.ButtonRound btn_Huy;
    private Assets.Custom_Item.ButtonRound btn_ThemChiTietPhieuNhap1;
    private Assets.Custom_Item.ButtonRound btn_ThemChiTietPhieuNhap2;
    private Assets.Custom_Item.ButtonRound btn_XacNhan;
    private javax.swing.JCheckBox cb_NguyenLieuMoi;
    private javax.swing.JComboBox<NguyenLieu> cbo_NguyenLieu;
    private javax.swing.JComboBox<NhaCungCap> cbo_NhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lb_GhiChu;
    private javax.swing.JSpinner nr_SoLuong;
    private javax.swing.JPanel panel_ChiTietHoaDon;
    private javax.swing.JPanel pnl_ThemSanPham;
    private javax.swing.JTable tbl_ChiTietPhieuNhap;
    private javax.swing.JTextField txt_DonGia;
    private javax.swing.JTextField txt_GhiChu;
    private Assets.Custom_Item.TextFieldR_Login txt_NgayNhap;
    private javax.swing.JTextField txt_ThemNguyenLieuMoi;
    // End of variables declaration//GEN-END:variables
}
