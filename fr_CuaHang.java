/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt_ to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.ManagerGroup;

import Assets.Custom_Item.*;
import GUI.Fr_Login;
import POJO.*;
import DAO.*;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class fr_CuaHang extends javax.swing.JFrame {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static JTable tbl_SanPham_data = new JTable(),
            tbl_PhieuNhap_data = new JTable(),
            tbl_ChiTietPhieuNhap_data = new JTable(),
            tbl_NhanVien_data = new JTable(),
            tbl_NguyenLieu_data = new JTable();
    static String timKiemSanPham = "Nhập tên sản phẩm cần tìm...";

    /**
     * Creates new form fr_CuaHang
     */
    //<editor-fold defaultstate="collapsed" desc="Form cửa hàng">
    private void load_TimKiem() {
        txt_TimKiemSanPham.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!timKiemSanPham.equals(txt_TimKiemSanPham.getText())) {
                    //load_tblSanPham(txt_TimKiem.getText());
                    load_tblSanPham(txt_TimKiemSanPham.getText(), ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!timKiemSanPham.equals(txt_TimKiemSanPham.getText())) {
                    //load_tblSanPham(txt_TimKiem.getText());
                    load_tblSanPham(txt_TimKiemSanPham.getText(), ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Handle change event
            }
        });
    }

    private void load_XoaSuaSanPham(JTable table, int i) {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                try {
                    int maSanPham = (int) tbl_SanPham_data.getValueAt(row, 0);
                    String tenSanPham = (String) tbl_SanPham_data.getValueAt(row, 1);
                    int giaBan;
                    if (tbl_SanPham_data.getValueAt(row, 2) instanceof String string) {
                        giaBan = Integer.parseInt(string);
                    } else {
                        giaBan = (int) tbl_SanPham_data.getValueAt(row, 2);
                    }
                    String hinhAnh = (String) tbl_SanPham_data.getValueAt(row, 3);
                    String maLoai = (String) tbl_SanPham_data.getValueAt(row, 4);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, hinhAnh, maLoai, giaBan);
                    new JF_PhieuChiTietSP(sanPham).setVisible(true);
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại!");
                }
            }

            @Override
            public void onDelete(int row) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa " + (String) tbl_SanPham_data.getValueAt(row, 1), "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE)
                            == JOptionPane.YES_OPTION) {
                        if (tbl_SanPham.isEditing()) {
                            tbl_SanPham.getCellEditor().stopCellEditing();
                        }
                        int maSanPham = (int) tbl_SanPham_data.getValueAt(row, 0);
                        DefaultTableModel model = (DefaultTableModel) tbl_SanPham.getModel();
                        SanPham_DAO.xoa(maSanPham);

                        model.removeRow(row);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                }
            }
        };
        table.getColumnModel().getColumn(i).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(i).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(i).setPreferredWidth(20);
    }

    private void load_cboLoaiSanPham() {
        Vector<LoaiSanPham> comboData = new Vector<>();
        comboData.add(new LoaiSanPham("All", "Loại sản phẩm"));
        for (LoaiSanPham loaiSanPham : LoaiSanPham_DAO.layDanhSach()) {
            comboData.add(loaiSanPham);
        }
        cbo_TimKiemSanPham.setModel(new DefaultComboBoxModel<>(comboData));
    }

    private void load_tblSanPham(String tenSanPham, String maLoai) {
        // Định dạng table
        DefaultTableModel dtm = new DefaultTableModel();
        String[] tieuDe = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Tên file hình ảnh", "Mã loại", "Loại sản phẩm", ""};

        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        //Array<List>
        //Load dữ liệu
        for (SanPham sanPham : SanPham_DAO.timKiem(tenSanPham, maLoai)) {
            try {
                Vector<Object> v = new Stack<>();
                v.add(sanPham.getMaSanPham());
                v.add(sanPham.getTenSanPham());
                v.add(sanPham.getGiaBan());
                v.add(sanPham.getHinhAnh());
                v.add(sanPham.getMaLoai());
                v.add(sanPham.getTenLoai());
                dtm.addRow(v);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.toString());
            }
        }
        tbl_SanPham_data.setModel(dtm);

        tbl_SanPham.setModel(dtm);
        tbl_SanPham.removeColumn(tbl_SanPham.getColumn("Mã sản phẩm"));
        tbl_SanPham.removeColumn(tbl_SanPham.getColumn("Mã loại"));
        load_XoaSuaSanPham(tbl_SanPham, 4);

    }

    private void load_FormCuaHang() {
        txt_TenSanPham.setEditable(false);
        txt_GiaBan.setEditable(false);
        txt_HinhAnh.setEditable(false);
        txt_TenLoai.setEditable(false);
        load_TimKiem();
        load_cboLoaiSanPham();
        load_tblSanPham("", "All");

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Form nhập hàng (Kho hàng)">
    private void load_cboNhaCungCap() {
        Vector<NhaCungCap> comboData = new Vector<>();
        comboData.add(new NhaCungCap(0, "Nhà cung cấp"));
        for (NhaCungCap nhaCungCap : NhaCungCap_DAO.layDanhSach()) {
            comboData.add(nhaCungCap);
        }
        cbo_NhaCungCap.setModel(new DefaultComboBoxModel<>(comboData));
    }

    private void load_cboNhanVien() {
        Vector<NhanVien> comboData = new Vector<>();
        comboData.add(new NhanVien(0, "Nhân viên lập"));
        for (NhanVien nhanVien : NhanVien_DAO.layDanhSach()) {
            comboData.add(nhanVien);
        }
        cbo_NhanVien.setModel(new DefaultComboBoxModel<>(comboData));

    }

    static public void load_tblPhieuNhap(int maNhaCungCap, int maNhanVien) {
        // Định dạng table
        DefaultTableModel dtm = new DefaultTableModel();
        String[] tieuDe = new String[]{"Mã phiếu nhập", "Ngày nhập", "Thành tiền", "Mã nhà cung cấp", "Nhà cung cấp", "Mã nhân viên", "Nhân viên lập phiếu", ""};

        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        //Load dữ liệu
        for (PhieuNhap phieuNhap : PhieuNhap_DAO.layDanhSach(maNhaCungCap, maNhanVien)) {
            try {
                Vector<Object> v = new Stack<>();
                v.add(phieuNhap.getMaPhieuNhap());
                v.add(phieuNhap.getNgayNhap());
                v.add(phieuNhap.getThanhTien());
                v.add(phieuNhap.getMaNhaCungCap());
                v.add(phieuNhap.getTenNhaCungCap());
                v.add(phieuNhap.getMaNhanVien());
                v.add(phieuNhap.getTenNhanVien());
                dtm.addRow(v);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.toString());
            }
        }
        tbl_PhieuNhap_data.setModel(dtm);

        tbl_PhieuNhap.setModel(dtm);
        tbl_PhieuNhap.removeColumn(tbl_PhieuNhap.getColumn("Mã nhà cung cấp"));
        tbl_PhieuNhap.removeColumn(tbl_PhieuNhap.getColumn("Mã nhân viên"));

    }

    private void load_cboMaPhieuNhap() {
        Vector<PhieuNhap> comboData = new Vector<>();
        comboData.add(new PhieuNhap(0));
        for (PhieuNhap phieuNhap : PhieuNhap_DAO.layDanhSach()) {
            comboData.add(phieuNhap);
        }
        cbo_MaPhieuNhap.setModel(new DefaultComboBoxModel<>(comboData));
    }

    static public void load_cboNguyenLieu(int maPhieuNhap) {

        Vector<NguyenLieu> comboData = new Vector<>();
        for (NguyenLieu nguyenLieu : NguyenLieu_DAO.layDanhSach()) {
            comboData.add(nguyenLieu);
        }

        cbo_NguyenLieu.setModel(new DefaultComboBoxModel<>(comboData));
    }

    static public void load_tblChiTietPhieuNhap(int maPhieuNhap) {
        try {
            // Định dạng table
            DefaultTableModel dtm = new DefaultTableModel();
            String[] tieuDe = new String[]{"Mã phiếu nhập", "Mã nguyên liệu", "Nguyên liệu", "Đơn giá", "Số lượng", "Ghi chú"};

            dtm.setColumnIdentifiers(tieuDe);
            dtm.setRowCount(0);

            //Load dữ liệu
            for (ChiTietPhieuNhap chiTietPhieuNhap : ChiTietPhieuNhap_DAO.layDanhSach(maPhieuNhap)) {
                try {
                    Vector<Object> v = new Stack<>();
                    v.add(chiTietPhieuNhap.getMaPhieuNhap());
                    v.add(chiTietPhieuNhap.getMaNguyenLieu());
                    v.add(chiTietPhieuNhap.getTenNguyenLieu());
                    v.add(chiTietPhieuNhap.getDonGia());
                    v.add(chiTietPhieuNhap.getSoLuong());
                    dtm.addRow(v);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.toString());
                }
            }
            tbl_ChiTietPhieuNhap_data.setModel(dtm);

            tbl_ChiTietPhieuNhap.setModel(dtm);
            tbl_ChiTietPhieuNhap.removeColumn(tbl_ChiTietPhieuNhap.getColumn("Mã nguyên liệu"));
        } catch (IllegalArgumentException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(fr_CuaHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load_FormKhoHang() {
        txt_ThanhTien.setEditable(false);
        load_cboNhaCungCap();
        load_cboNhanVien();
        load_tblPhieuNhap(0, 0);
        load_cboMaPhieuNhap();
        load_cboNguyenLieu(0);
        load_tblChiTietPhieuNhap(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Form nhân viên">
    private void load_tblNhanVien(String tenNhanVien, String maChucVu) {
        // Định dạng table
        DefaultTableModel dtm = new DefaultTableModel();
        String[] tieuDe = new String[]{"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Ngày vào làm", "Ngày nghỉ", "Mã chức vụ", "Chức vụ", ""};

        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        //Array<List>
        //Load dữ liệu
        for (NhanVien nhanVien : NhanVien_DAO.timKiem(tenNhanVien, maChucVu)) {
            try {
                Vector<Object> v = new Stack<>();
                v.add(nhanVien.getMaNhanVien());
                v.add(nhanVien.getTenNhanVien());
                v.add(nhanVien.getNgaySinh());
                v.add(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
                v.add(nhanVien.getDiaChi());
                v.add(nhanVien.getNgayVaoLam());
                v.add(nhanVien.getNgayNghi());
                v.add(nhanVien.getMaChucVu());
                v.add(nhanVien.getTenChucVu());

                dtm.addRow(v);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.toString());
            }
        }
        tbl_NhanVien_data.setModel(dtm);

        tbl_NhanVien.setModel(dtm);
        tbl_NhanVien.removeColumn(tbl_NhanVien.getColumn("Mã nhân viên"));
        tbl_NhanVien.removeColumn(tbl_NhanVien.getColumn("Mã chức vụ"));
        //load_XoaSuaNhanVien(tbl_NhanVien, 4);
    }

    private void load_FormNhanVien() {
        load_tblNhanVien("", "");
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Form nguyên liệu">
    public static void load_tblNguyenLieu() {
        // Định dạng table
        DefaultTableModel dtm = new DefaultTableModel();
        String[] tieuDe = new String[]{"Mã nguyên liệu", "Tên nguyên liệu", "Xuất xứ", "Số lượng tồn", ""};

        dtm.setColumnIdentifiers(tieuDe);
        dtm.setRowCount(0);

        //Array<List>
        //Load dữ liệu
        for (NguyenLieu nguyenLieu : NguyenLieu_DAO.layDanhSach()) {
            Vector<Object> v = new Stack<>();
            v.add(nguyenLieu.getMaNguyenLieu());
            v.add(nguyenLieu.getTenNguyenLieu());
            v.add(nguyenLieu.getXuatXu());
            v.add(nguyenLieu.getSoLuongTon());

            dtm.addRow(v);
        }
        tbl_NguyenLieu_data.setModel(dtm);
        tbl_NguyenLieu.setModel(dtm);   
        tbl_NguyenLieu.removeColumn(tbl_NguyenLieu.getColumn("Mã nguyên liệu"));
    }

    private void load_FormNguyenLieu() {
        load_tblNguyenLieu();
    }

//</editor-fold>
    
    public fr_CuaHang() {
        FlatLightLaf.setup();
        initComponents();

        setLocationRelativeTo(null);
        setTitle("Trang chủ");

        if("K".equals(Fr_Login.nhanVien.getMaChucVu())){
            materialTabbed1.remove(Panel_NhanVien);
        }
        
        txt_TenNhanVien.setText(Fr_Login.nhanVien.getTenNhanVien());
        txt_TenNhanVien1.setText(Fr_Login.nhanVien.getTenNhanVien());
        txt_TenNhanVien2.setText(Fr_Login.nhanVien.getTenNhanVien());
        txt_TenNhanVien3.setText(Fr_Login.nhanVien.getTenNhanVien());

        
        
        load_FormCuaHang();
        load_FormKhoHang();
        load_FormNhanVien();
        load_FormNguyenLieu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_GioiTinh = new javax.swing.ButtonGroup();
        jPanel9 = new javax.swing.JPanel();
        materialTabbed1 = new Assets.Custom_Item.MaterialTabbed();
        Panel_TrangChu = new javax.swing.JPanel();
        Panel_SanPham = new javax.swing.JPanel();
        menu_Item1 = new Assets.Custom_Item.Menu_Item();
        Panel_ToolbarSP = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_TenNhanVien = new javax.swing.JLabel();
        panelRound1 = new Assets.Custom_Item.PanelRound();
        img_SP = new Assets.Custom_Item.ImagePanel();
        jLabel16 = new javax.swing.JLabel();
        txt_TenSanPham = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_HinhAnh = new javax.swing.JTextField();
        txt_TenLoai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        txt_TimKiemSanPham = new javax.swing.JTextField();
        cbo_TimKiemSanPham = new javax.swing.JComboBox<>();
        btn_ThemSanPham = new Assets.Custom_Item.ButtonRound();
        Panel_KhoHang = new javax.swing.JPanel();
        menu_Item2 = new Assets.Custom_Item.Menu_Item();
        Panel_ToolbarKH = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txt_TenNhanVien3 = new javax.swing.JLabel();
        pnl_PhieuNhap = new Assets.Custom_Item.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_PhieuNhap = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_NgayNhap = new javax.swing.JTextField();
        txt_MaPhieuNhap = new javax.swing.JTextField();
        cbo_NhanVien = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbo_NhaCungCap = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        btn_ThemPhieuNhap = new Assets.Custom_Item.ButtonRound();
        pnl_ChiTietPhieuNhap = new Assets.Custom_Item.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ChiTietPhieuNhap = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbo_MaPhieuNhap = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_DonGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_SoLuong = new javax.swing.JTextField();
        txt_TongTien = new javax.swing.JTextField();
        btn_ThemChiTietPhieuNhap = new Assets.Custom_Item.ButtonRound();
        cbo_NguyenLieu = new javax.swing.JComboBox<>();
        txtGhiChu = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btn_XoaChiTietPhieuNhap = new Assets.Custom_Item.ButtonRound();
        panelRound2 = new Assets.Custom_Item.PanelRound();
        jLabel13 = new javax.swing.JLabel();
        panelRound3 = new Assets.Custom_Item.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        Panel_NhanVien = new javax.swing.JPanel();
        menu_Item3 = new Assets.Custom_Item.Menu_Item();
        Panel_ToolbarSP1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txt_TenNhanVien1 = new javax.swing.JLabel();
        panelRound4 = new Assets.Custom_Item.PanelRound();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_NV_TenNhanVien = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_NgaySinh = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_NgayVaoLam = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_NgayNghi = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_DiaChi = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbo_ChucVu = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        rd_Nam = new javax.swing.JRadioButton();
        rd_Nu = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();
        txt_TimKiemNhanVien = new javax.swing.JTextField();
        cbo_TimKiemNhanVien = new javax.swing.JComboBox<>();
        btn_ThemNhanVien = new Assets.Custom_Item.ButtonRound();
        Panel_NguyenLieu = new javax.swing.JPanel();
        menu_Item4 = new Assets.Custom_Item.Menu_Item();
        Panel_ToolbarSP2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_TenNhanVien2 = new javax.swing.JLabel();
        panelRound5 = new Assets.Custom_Item.PanelRound();
        jLabel32 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_SoLuongTon = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_TenNguyenLieu = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txt_XuatXu = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        btn_ThemNguyenLieu = new Assets.Custom_Item.ButtonRound();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_NguyenLieu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(222, 184, 135));

        jPanel9.setBackground(new java.awt.Color(222, 184, 135));
        jPanel9.setPreferredSize(new java.awt.Dimension(1080, 1920));

        materialTabbed1.setBackground(new java.awt.Color(222, 184, 135));
        materialTabbed1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        materialTabbed1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        materialTabbed1.setOpaque(true);

        Panel_TrangChu.setBackground(new java.awt.Color(222, 184, 135));

        javax.swing.GroupLayout Panel_TrangChuLayout = new javax.swing.GroupLayout(Panel_TrangChu);
        Panel_TrangChu.setLayout(Panel_TrangChuLayout);
        Panel_TrangChuLayout.setHorizontalGroup(
            Panel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1436, Short.MAX_VALUE)
        );
        Panel_TrangChuLayout.setVerticalGroup(
            Panel_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1179, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Assets/img/Logo3.png")), Panel_TrangChu); // NOI18N

        Panel_SanPham.setBackground(new java.awt.Color(138, 91, 43));
        Panel_SanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_SanPham.add(menu_Item1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Panel_ToolbarSP.setBackground(new java.awt.Color(222, 184, 135));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-user-30.png"))); // NOI18N

        txt_TenNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_TenNhanVien.setText("Tên nhân viên");

        javax.swing.GroupLayout Panel_ToolbarSPLayout = new javax.swing.GroupLayout(Panel_ToolbarSP);
        Panel_ToolbarSP.setLayout(Panel_ToolbarSPLayout);
        Panel_ToolbarSPLayout.setHorizontalGroup(
            Panel_ToolbarSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ToolbarSPLayout.createSequentialGroup()
                .addContainerGap(994, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        Panel_ToolbarSPLayout.setVerticalGroup(
            Panel_ToolbarSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ToolbarSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ToolbarSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TenNhanVien)
                    .addComponent(jLabel20))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Panel_SanPham.add(Panel_ToolbarSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 60));

        panelRound1.setBackground(new java.awt.Color(222, 184, 135));
        panelRound1.setRoundBottomLeft(70);
        panelRound1.setRoundBottomRight(70);
        panelRound1.setRoundTopLeft(70);
        panelRound1.setRoundTopRight(70);

        img_SP.setBackground(new java.awt.Color(222, 184, 135));

        javax.swing.GroupLayout img_SPLayout = new javax.swing.GroupLayout(img_SP);
        img_SP.setLayout(img_SPLayout);
        img_SPLayout.setHorizontalGroup(
            img_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        img_SPLayout.setVerticalGroup(
            img_SPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-warning-30.png"))); // NOI18N
        jLabel16.setText("Thông tin chi tiết");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Tên sản phẩm");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Giá bán");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tên loại");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Hình ảnh");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(img_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(txt_TenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(txt_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel16)))
                .addGap(203, 203, 203))
        );

        panelRound1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel15, jLabel17, jLabel18, jLabel19});

        panelRound1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_GiaBan, txt_HinhAnh});

        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txt_TenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)))
                        .addGap(26, 26, 26))
                    .addComponent(img_SP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Panel_SanPham.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 66, 1180, 190));

        tbl_SanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "action"
            }
        ));
        tbl_SanPham.setRowHeight(40);
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPham);

        Panel_SanPham.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 339, 1180, 450));

        txt_TimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_TimKiemSanPham.setText("Nhập tên sản phẩm cần tìm...");
        txt_TimKiemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_TimKiemSanPhamMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_TimKiemSanPhamMouseExited(evt);
            }
        });
        Panel_SanPham.add(txt_TimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 550, 56));

        cbo_TimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_TimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TimKiemSanPhamActionPerformed(evt);
            }
        });
        Panel_SanPham.add(cbo_TimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 530, 56));

        btn_ThemSanPham.setText("+");
        btn_ThemSanPham.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemSanPham.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemSanPham.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemSanPham.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemSanPham.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemSanPham.setRadius(50);
        btn_ThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSanPhamActionPerformed(evt);
            }
        });
        Panel_SanPham.add(btn_ThemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 270, -1, -1));

        materialTabbed1.addTab("       Sản phẩm", new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icon_SanPham.png")), Panel_SanPham); // NOI18N

        Panel_KhoHang.setBackground(new java.awt.Color(138, 91, 43));
        Panel_KhoHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_KhoHang.add(menu_Item2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Panel_ToolbarKH.setBackground(new java.awt.Color(222, 184, 135));
        Panel_ToolbarKH.setPreferredSize(new java.awt.Dimension(1250, 60));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-user-30.png"))); // NOI18N

        txt_TenNhanVien3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_TenNhanVien3.setText("Tên nhân viên");

        javax.swing.GroupLayout Panel_ToolbarKHLayout = new javax.swing.GroupLayout(Panel_ToolbarKH);
        Panel_ToolbarKH.setLayout(Panel_ToolbarKHLayout);
        Panel_ToolbarKHLayout.setHorizontalGroup(
            Panel_ToolbarKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ToolbarKHLayout.createSequentialGroup()
                .addContainerGap(994, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenNhanVien3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        Panel_ToolbarKHLayout.setVerticalGroup(
            Panel_ToolbarKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ToolbarKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ToolbarKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TenNhanVien3)
                    .addComponent(jLabel36))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Panel_KhoHang.add(Panel_ToolbarKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 60));

        pnl_PhieuNhap.setBackground(new java.awt.Color(222, 184, 135));
        pnl_PhieuNhap.setPreferredSize(new java.awt.Dimension(1248, 500));
        pnl_PhieuNhap.setRoundBottomLeft(70);
        pnl_PhieuNhap.setRoundBottomRight(70);
        pnl_PhieuNhap.setRoundTopLeft(70);
        pnl_PhieuNhap.setRoundTopRight(70);
        pnl_PhieuNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-warning-30.png"))); // NOI18N
        jLabel1.setText("Thông tin chi tiết");
        pnl_PhieuNhap.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 13, -1, -1));

        tbl_PhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_PhieuNhap.setRowHeight(40);
        tbl_PhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_PhieuNhap);

        pnl_PhieuNhap.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(614, 13, 570, 258));

        jPanel1.setBackground(new java.awt.Color(222, 184, 135));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Mã Phiếu Nhập");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Ngày Nhập");

        txt_NgayNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_MaPhieuNhap.setEditable(false);
        txt_MaPhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_MaPhieuNhap.setPreferredSize(new java.awt.Dimension(64, 30));

        cbo_NhanVien.setBackground(new java.awt.Color(242, 242, 242));
        cbo_NhanVien.setEditable(true);
        cbo_NhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_NhanVienActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Nhà cung cấp");

        cbo_NhaCungCap.setBackground(new java.awt.Color(242, 242, 242));
        cbo_NhaCungCap.setEditable(true);
        cbo_NhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_NhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_NhaCungCapActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Nhân viên");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Tiền Nhập");

        txt_ThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btn_ThemPhieuNhap.setText("+");
        btn_ThemPhieuNhap.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemPhieuNhap.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemPhieuNhap.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemPhieuNhap.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemPhieuNhap.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemPhieuNhap.setRadius(50);
        btn_ThemPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(btn_ThemPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_MaPhieuNhap, txt_NgayNhap, txt_ThanhTien});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbo_NhaCungCap, cbo_NhanVien});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btn_ThemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel34, jLabel4, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbo_NhaCungCap, cbo_NhanVien, txt_MaPhieuNhap, txt_NgayNhap, txt_ThanhTien});

        pnl_PhieuNhap.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 650, 210));

        Panel_KhoHang.add(pnl_PhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1210, 281));

        pnl_ChiTietPhieuNhap.setBackground(new java.awt.Color(222, 184, 135));
        pnl_ChiTietPhieuNhap.setPreferredSize(new java.awt.Dimension(1248, 500));
        pnl_ChiTietPhieuNhap.setRoundBottomLeft(70);
        pnl_ChiTietPhieuNhap.setRoundBottomRight(70);
        pnl_ChiTietPhieuNhap.setRoundTopLeft(70);
        pnl_ChiTietPhieuNhap.setRoundTopRight(70);
        pnl_ChiTietPhieuNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-warning-30.png"))); // NOI18N
        jLabel12.setText("Thông tin chi tiết");
        pnl_ChiTietPhieuNhap.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 13, -1, -1));

        jScrollPane4.setBackground(new java.awt.Color(222, 184, 135));
        jScrollPane4.setBorder(null);

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
        tbl_ChiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_ChiTietPhieuNhap);

        pnl_ChiTietPhieuNhap.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 530, 257));

        jPanel3.setBackground(new java.awt.Color(222, 184, 135));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Mã Phiếu Nhập");

        cbo_MaPhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbo_MaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_MaPhieuNhapActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Đơn giá");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Nguyên Liệu");

        txt_DonGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Số Lượng");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tổng tiền");

        txt_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_TongTien.setEditable(false);
        txt_TongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btn_ThemChiTietPhieuNhap.setText("+");
        btn_ThemChiTietPhieuNhap.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemChiTietPhieuNhap.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemChiTietPhieuNhap.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemChiTietPhieuNhap.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemChiTietPhieuNhap.setRadius(50);
        btn_ThemChiTietPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemChiTietPhieuNhapActionPerformed(evt);
            }
        });

        cbo_NguyenLieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtGhiChu.setEditable(false);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Ghi chú");

        btn_XoaChiTietPhieuNhap.setText("-");
        btn_XoaChiTietPhieuNhap.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_XoaChiTietPhieuNhap.setColor(new java.awt.Color(223, 164, 88));
        btn_XoaChiTietPhieuNhap.setColorClick(new java.awt.Color(138, 91, 43));
        btn_XoaChiTietPhieuNhap.setColorOver(new java.awt.Color(223, 164, 88));
        btn_XoaChiTietPhieuNhap.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_XoaChiTietPhieuNhap.setRadius(50);
        btn_XoaChiTietPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaChiTietPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_NguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txt_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TongTien)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_ThemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_XoaChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel7, jLabel8, jLabel9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbo_MaPhieuNhap, cbo_NguyenLieu, txt_DonGia, txt_SoLuong});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_ThemChiTietPhieuNhap, btn_XoaChiTietPhieuNhap});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_XoaChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ThemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbo_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbo_NguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbo_MaPhieuNhap, cbo_NguyenLieu, txt_DonGia, txt_SoLuong, txt_TongTien});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, jLabel7, jLabel8, jLabel9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_ThemChiTietPhieuNhap, btn_XoaChiTietPhieuNhap});

        pnl_ChiTietPhieuNhap.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 620, 230));

        Panel_KhoHang.add(pnl_ChiTietPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 1210, 300));

        panelRound2.setBackground(new java.awt.Color(222, 184, 135));
        panelRound2.setRoundTopLeft(70);
        panelRound2.setRoundTopRight(70);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("PHIẾU NHẬP");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Panel_KhoHang.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 380, 70));

        panelRound3.setBackground(new java.awt.Color(222, 184, 135));
        panelRound3.setRoundTopLeft(70);
        panelRound3.setRoundTopRight(70);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("CHI TIẾT PHIẾU NHẬP");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
        );

        Panel_KhoHang.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 437, 380, 70));

        materialTabbed1.addTab("     Kho Hàng", new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-in-transit-50.png")), Panel_KhoHang); // NOI18N

        Panel_NhanVien.setBackground(new java.awt.Color(138, 91, 43));
        Panel_NhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_NhanVien.add(menu_Item3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Panel_ToolbarSP1.setBackground(new java.awt.Color(222, 184, 135));
        Panel_ToolbarSP1.setPreferredSize(new java.awt.Dimension(1250, 60));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-user-30.png"))); // NOI18N

        txt_TenNhanVien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_TenNhanVien1.setText("Tên nhân viên");

        javax.swing.GroupLayout Panel_ToolbarSP1Layout = new javax.swing.GroupLayout(Panel_ToolbarSP1);
        Panel_ToolbarSP1.setLayout(Panel_ToolbarSP1Layout);
        Panel_ToolbarSP1Layout.setHorizontalGroup(
            Panel_ToolbarSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ToolbarSP1Layout.createSequentialGroup()
                .addContainerGap(994, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        Panel_ToolbarSP1Layout.setVerticalGroup(
            Panel_ToolbarSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ToolbarSP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ToolbarSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TenNhanVien1)
                    .addComponent(jLabel21))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Panel_NhanVien.add(Panel_ToolbarSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 60));

        panelRound4.setBackground(new java.awt.Color(222, 184, 135));
        panelRound4.setRoundBottomLeft(70);
        panelRound4.setRoundBottomRight(70);
        panelRound4.setRoundTopLeft(70);
        panelRound4.setRoundTopRight(70);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-warning-30.png"))); // NOI18N
        jLabel23.setText("Thông tin chi tiết");
        panelRound4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(222, 184, 135));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Tên nhân viên");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Ngày sinh");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Ngày vào làm");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Ngày nghỉ");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Địa chỉ");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Chức vụ");

        jPanel5.setBackground(new java.awt.Color(222, 184, 135));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Giới tính", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnGroup_GioiTinh.add(rd_Nam);
        rd_Nam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rd_Nam.setText("Nam");

        btnGroup_GioiTinh.add(rd_Nu);
        rd_Nu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rd_Nu.setText("Nữ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rd_Nu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rd_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rd_Nam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rd_Nu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NV_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txt_NgayNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel25, jLabel26, jLabel27, jLabel29, jLabel30, jLabel31});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbo_ChucVu, txt_DiaChi, txt_NV_TenNhanVien, txt_NgayNghi, txt_NgaySinh, txt_NgayVaoLam});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(txt_NV_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(txt_NgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(txt_NgayNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(cbo_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel25, jLabel26, jLabel27, jLabel29, jLabel30, jLabel31});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbo_ChucVu, txt_DiaChi, txt_NV_TenNhanVien, txt_NgayNghi, txt_NgaySinh, txt_NgayVaoLam});

        panelRound4.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 1110, 117));

        Panel_NhanVien.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 66, 1180, 190));

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "action"
            }
        ));
        tbl_NhanVien.setRowHeight(40);
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_NhanVien);

        Panel_NhanVien.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 339, 1180, 450));

        txt_TimKiemNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_TimKiemNhanVien.setText("Nhập tên nhân viên cần tìm...");
        txt_TimKiemNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_TimKiemNhanVienMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_TimKiemNhanVienMouseExited(evt);
            }
        });
        Panel_NhanVien.add(txt_TimKiemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 550, 56));

        cbo_TimKiemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TimKiemNhanVienActionPerformed(evt);
            }
        });
        Panel_NhanVien.add(cbo_TimKiemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 530, 56));

        btn_ThemNhanVien.setText("+");
        btn_ThemNhanVien.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemNhanVien.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemNhanVien.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemNhanVien.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemNhanVien.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemNhanVien.setRadius(50);
        btn_ThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanVienActionPerformed(evt);
            }
        });
        Panel_NhanVien.add(btn_ThemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 270, -1, -1));

        materialTabbed1.addTab("    Nhân viên", new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-employees-50.png")), Panel_NhanVien); // NOI18N

        Panel_NguyenLieu.setBackground(new java.awt.Color(138, 91, 43));
        Panel_NguyenLieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_NguyenLieu.add(menu_Item4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Panel_ToolbarSP2.setBackground(new java.awt.Color(222, 184, 135));
        Panel_ToolbarSP2.setPreferredSize(new java.awt.Dimension(1250, 60));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-user-30.png"))); // NOI18N

        txt_TenNhanVien2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_TenNhanVien2.setText("Tên nhân viên");

        javax.swing.GroupLayout Panel_ToolbarSP2Layout = new javax.swing.GroupLayout(Panel_ToolbarSP2);
        Panel_ToolbarSP2.setLayout(Panel_ToolbarSP2Layout);
        Panel_ToolbarSP2Layout.setHorizontalGroup(
            Panel_ToolbarSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ToolbarSP2Layout.createSequentialGroup()
                .addContainerGap(994, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenNhanVien2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        Panel_ToolbarSP2Layout.setVerticalGroup(
            Panel_ToolbarSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ToolbarSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ToolbarSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TenNhanVien2)
                    .addComponent(jLabel22))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Panel_NguyenLieu.add(Panel_ToolbarSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 60));

        panelRound5.setBackground(new java.awt.Color(222, 184, 135));
        panelRound5.setRoundBottomLeft(70);
        panelRound5.setRoundBottomRight(70);
        panelRound5.setRoundTopLeft(70);
        panelRound5.setRoundTopRight(70);
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-warning-30.png"))); // NOI18N
        jLabel32.setText("Thông tin chi tiết");
        panelRound5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(222, 184, 135));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Số lượng tồn");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Tên nguyên liệu");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Xuất xứ");

        btn_ThemNguyenLieu.setText("+");
        btn_ThemNguyenLieu.setBorderColor(new java.awt.Color(222, 184, 135));
        btn_ThemNguyenLieu.setColor(new java.awt.Color(223, 164, 88));
        btn_ThemNguyenLieu.setColorClick(new java.awt.Color(138, 91, 43));
        btn_ThemNguyenLieu.setColorOver(new java.awt.Color(223, 164, 88));
        btn_ThemNguyenLieu.setFont(new java.awt.Font("Varela Round", 1, 36)); // NOI18N
        btn_ThemNguyenLieu.setRadius(50);
        btn_ThemNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNguyenLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_ThemNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txt_XuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txt_SoLuongTon, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addGap(632, 632, 632))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel35, jLabel40, jLabel41});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_SoLuongTon, txt_TenNguyenLieu, txt_XuatXu});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addComponent(btn_ThemNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel35, jLabel40, jLabel41});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_SoLuongTon, txt_TenNguyenLieu, txt_XuatXu});

        panelRound5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 450, 230));

        tbl_NguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "action"
            }
        ));
        tbl_NguyenLieu.setRowHeight(40);
        tbl_NguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_NguyenLieu);

        panelRound5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 690, 440));

        Panel_NguyenLieu.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1200, 590));

        materialTabbed1.addTab("    Nguyên liệu", new javax.swing.ImageIcon(getClass().getResource("/Assets/img/icons8-ingredients-50.png")), Panel_NguyenLieu); // NOI18N

        materialTabbed1.setSelectedComponent(Panel_SanPham);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1749, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemChiTietPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemChiTietPhieuNhapActionPerformed
        int maPhieuNhap = ((PhieuNhap) cbo_MaPhieuNhap.getSelectedItem()).getMaPhieuNhap();
        int maNguyenLieu = ((NguyenLieu) cbo_NguyenLieu.getSelectedItem()).getMaNguyenLieu();
        int soLuong = Integer.parseInt(txt_SoLuong.getText());
        int donGia = Integer.parseInt(txt_DonGia.getText());
        String ghiChu = txtGhiChu.getText();

        ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(maPhieuNhap, maNguyenLieu, soLuong, donGia, ghiChu);

        ChiTietPhieuNhap_DAO.them(chiTietPhieuNhap);
        load_tblChiTietPhieuNhap(maPhieuNhap);
    }//GEN-LAST:event_btn_ThemChiTietPhieuNhapActionPerformed

    private void btn_ThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSanPhamActionPerformed
        // TODO add your handling code here:
        JF_PhieuChiTietSP p = new JF_PhieuChiTietSP();
        p.setVisible(true);
    }//GEN-LAST:event_btn_ThemSanPhamActionPerformed

    private void cbo_TimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TimKiemSanPhamActionPerformed
        String tK = txt_TimKiemSanPham.getText();
        if (timKiemSanPham.equals(tK)) {
            load_tblSanPham("", ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
        } else {
            load_tblSanPham(tK, ((LoaiSanPham) cbo_TimKiemSanPham.getSelectedItem()).getMaLoai());
        }
        System.out.println(cbo_TimKiemSanPham.getSelectedItem().toString());

    }//GEN-LAST:event_cbo_TimKiemSanPhamActionPerformed

    private void txt_TimKiemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemSanPhamMouseClicked
        if (txt_TimKiemSanPham.getText().equals(timKiemSanPham))
            txt_TimKiemSanPham.setText("");
    }//GEN-LAST:event_txt_TimKiemSanPhamMouseClicked

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        int row = tbl_SanPham.getSelectedRow();
        if (row != -1) {
            int i = 0;
            int maSanPham = (int) tbl_SanPham_data.getValueAt(row, i++);
            String tenSanPham = (String) tbl_SanPham_data.getValueAt(row, i++);
            int giaBan = 0;
            if (tbl_SanPham_data.getValueAt(row, i) instanceof Integer) {
                giaBan = (int) tbl_SanPham_data.getValueAt(row, i++);
            } else {
                giaBan = Integer.parseInt(((String) tbl_SanPham.getValueAt(row, i++)).isEmpty() ? "0" : (String) tbl_SanPham.getValueAt(row, i++));

            }
            String hinhAnh = (String) tbl_SanPham_data.getValueAt(row, i++);
            String maLoai = (String) tbl_SanPham_data.getValueAt(row, i++);
            String tenLoai = (String) tbl_SanPham_data.getValueAt(row, i++);

            // Hiển thị
            txt_TenSanPham.setText(tenSanPham);
            txt_GiaBan.setText(String.valueOf(giaBan));
            txt_HinhAnh.setText(hinhAnh);
            try {
                img_SP.setImage(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/SanPham/" + hinhAnh)));
            } catch (Exception e) {
                img_SP.setImage(null);
            }
            txt_TenLoai.setText(tenLoai);
        }
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
//        int row = tbl_NhanVien.getSelectedRow();
//        if (row != -1) {
//            int i = 0;
//            int maNhanVien = (int) tbl_NhanVien_data.getValueAt(row, i++);
//            String tenNhanVien = (String) tbl_NhanVien_data.getValueAt(row, i++);
//            Date ngaySinh = (Date) tbl_NhanVien_data.getValueAt(row, i++);
//            Date ngayVaoLam = (Date) tbl_NhanVien_data.getValueAt(row, i++);
//            Date ngayNghi = (Date) tbl_NhanVien_data.getValueAt(row, i++);
//            String diaChi = (String) tbl_NhanVien_data.getValueAt(row, i++);
//
//            // Hiển thị
//            txt_TenNhanVien.setText(tenNhanVien);
//            txt_GiaBan.setText(String.valueOf(giaBan));
//            txt_HinhAnh.setText(hinhAnh);
//            try {
//                img_SP.setImage(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/NhanVien/" + hinhAnh)));
//            } catch (Exception e) {
//                img_SP.setImage(null);
//            }
//            txt_TenLoai.setText(tenLoai);
//        }
    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void txt_TimKiemNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemNhanVienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemNhanVienMouseClicked

    private void cbo_TimKiemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TimKiemNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_TimKiemNhanVienActionPerformed

    private void tbl_NguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NguyenLieuMouseClicked
        int row = tbl_SanPham.getSelectedRow();
        if (row != -1) {
            int maSanPham = (int) tbl_SanPham_data.getValueAt(row, 0);
            String tenSanPham = (String) tbl_SanPham_data.getValueAt(row, 1);
            int giaBan = (int) tbl_SanPham_data.getValueAt(row, 2);
            String hinhAnh = (String) tbl_SanPham_data.getValueAt(row, 3);
            String maLoai = (String) tbl_SanPham_data.getValueAt(row, 4);
            String tenLoai = (String) tbl_SanPham_data.getValueAt(row, 5);

            // Hiển thị
            txt_TenSanPham.setText(tenSanPham);
            txt_GiaBan.setText(String.valueOf(giaBan));
            txt_HinhAnh.setText(hinhAnh);
            try {
                img_SP.setImage(new javax.swing.ImageIcon(getClass().getResource("/Assets/img/SanPham/" + hinhAnh)));
            } catch (Exception e) {
                img_SP.setImage(null);
            }
            txt_TenLoai.setText(tenLoai);
        }    }//GEN-LAST:event_tbl_NguyenLieuMouseClicked

    private void btn_ThemNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNguyenLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemNguyenLieuActionPerformed

    private void txt_TimKiemSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemSanPhamMouseExited
        if (txt_TimKiemSanPham.getText().isEmpty())
            txt_TimKiemSanPham.setText(timKiemSanPham);
    }//GEN-LAST:event_txt_TimKiemSanPhamMouseExited

    private void btn_ThemPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemPhieuNhapActionPerformed
        new JF_ThemKho().setVisible(true);
    }//GEN-LAST:event_btn_ThemPhieuNhapActionPerformed

    private void tbl_PhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuNhapMouseClicked
        int row = tbl_PhieuNhap.getSelectedRow();
        if (row != -1) {
            int i = 0;
            int maPhieuNhap = (int) tbl_PhieuNhap_data.getValueAt(row, i++);
            Date ngayNhap = (Date) tbl_PhieuNhap_data.getValueAt(row, i++);
            int thanhTien = (int) tbl_PhieuNhap_data.getValueAt(row, i++);
            int maNhaCungCap = (int) tbl_PhieuNhap_data.getValueAt(row, i++);
            String tenNhaCungCap = (String) tbl_PhieuNhap_data.getValueAt(row, i++);
            int maNhanVien = (int) tbl_PhieuNhap_data.getValueAt(row, i++);
            String tenNhanVien = (String) tbl_PhieuNhap_data.getValueAt(row, i++);

            // Hiển thị
            txt_MaPhieuNhap.setText(String.valueOf(maPhieuNhap));
            txt_NgayNhap.setText(sdf.format(ngayNhap));
            txt_ThanhTien.setText(String.valueOf(thanhTien));
            cbo_NhaCungCap.setSelectedItem(tenNhaCungCap);
            cbo_NhanVien.setSelectedItem(tenNhanVien);
        }
    }//GEN-LAST:event_tbl_PhieuNhapMouseClicked

    private void tbl_ChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietPhieuNhapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_ChiTietPhieuNhapMouseClicked

    private void cbo_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_NhanVienActionPerformed
        if (!(cbo_NhanVien.getSelectedItem() instanceof String) && !(cbo_NhaCungCap.getSelectedItem() instanceof String)) {
            int maNhaCungCap = ((NhaCungCap) cbo_NhaCungCap.getSelectedItem()).getMaNhaCungCap();
            int maNhanVien = ((NhanVien) cbo_NhanVien.getSelectedItem()).getMaNhanVien();
            load_tblPhieuNhap(maNhaCungCap, maNhanVien);
        }
    }//GEN-LAST:event_cbo_NhanVienActionPerformed

    private void cbo_NhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_NhaCungCapActionPerformed
        if (!(cbo_NhaCungCap.getSelectedItem() instanceof String) && !(cbo_NhanVien.getSelectedItem() instanceof String)) {
            int maNhaCungCap = ((NhaCungCap) cbo_NhaCungCap.getSelectedItem()).getMaNhaCungCap();
            int maNhanVien = ((NhanVien) cbo_NhanVien.getSelectedItem()).getMaNhanVien();
            load_tblPhieuNhap(maNhaCungCap, maNhanVien);
        }
    }//GEN-LAST:event_cbo_NhaCungCapActionPerformed

    private void btn_ThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemNhanVienActionPerformed

    private void txt_TimKiemNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TimKiemNhanVienMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemNhanVienMouseExited

    private void cbo_MaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_MaPhieuNhapActionPerformed

        load_tblChiTietPhieuNhap(Integer.parseInt(cbo_MaPhieuNhap.getSelectedItem().toString()));
    }//GEN-LAST:event_cbo_MaPhieuNhapActionPerformed

    private void btn_XoaChiTietPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaChiTietPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XoaChiTietPhieuNhapActionPerformed

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
            java.util.logging.Logger.getLogger(fr_CuaHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fr_CuaHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fr_CuaHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fr_CuaHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fr_CuaHang().setVisible(true);
            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc="Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_KhoHang;
    private javax.swing.JPanel Panel_NguyenLieu;
    private javax.swing.JPanel Panel_NhanVien;
    private javax.swing.JPanel Panel_SanPham;
    private javax.swing.JPanel Panel_ToolbarKH;
    private javax.swing.JPanel Panel_ToolbarSP;
    private javax.swing.JPanel Panel_ToolbarSP1;
    private javax.swing.JPanel Panel_ToolbarSP2;
    private javax.swing.JPanel Panel_TrangChu;
    private javax.swing.ButtonGroup btnGroup_GioiTinh;
    private Assets.Custom_Item.ButtonRound btn_ThemChiTietPhieuNhap;
    private Assets.Custom_Item.ButtonRound btn_ThemNguyenLieu;
    private Assets.Custom_Item.ButtonRound btn_ThemNhanVien;
    private Assets.Custom_Item.ButtonRound btn_ThemPhieuNhap;
    private Assets.Custom_Item.ButtonRound btn_ThemSanPham;
    private Assets.Custom_Item.ButtonRound btn_XoaChiTietPhieuNhap;
    private javax.swing.JComboBox<ChucVu> cbo_ChucVu;
    private static javax.swing.JComboBox<PhieuNhap> cbo_MaPhieuNhap;
    private static javax.swing.JComboBox<NguyenLieu> cbo_NguyenLieu;
    private javax.swing.JComboBox<NhaCungCap> cbo_NhaCungCap;
    private javax.swing.JComboBox<NhanVien> cbo_NhanVien;
    private javax.swing.JComboBox<LoaiSanPham> cbo_TimKiemNhanVien;
    private javax.swing.JComboBox<LoaiSanPham> cbo_TimKiemSanPham;
    private Assets.Custom_Item.ImagePanel img_SP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private Assets.Custom_Item.MaterialTabbed materialTabbed1;
    private Assets.Custom_Item.Menu_Item menu_Item1;
    private Assets.Custom_Item.Menu_Item menu_Item2;
    private Assets.Custom_Item.Menu_Item menu_Item3;
    private Assets.Custom_Item.Menu_Item menu_Item4;
    private Assets.Custom_Item.PanelRound panelRound1;
    private Assets.Custom_Item.PanelRound panelRound2;
    private Assets.Custom_Item.PanelRound panelRound3;
    private Assets.Custom_Item.PanelRound panelRound4;
    private Assets.Custom_Item.PanelRound panelRound5;
    private Assets.Custom_Item.PanelRound pnl_ChiTietPhieuNhap;
    private Assets.Custom_Item.PanelRound pnl_PhieuNhap;
    private javax.swing.JRadioButton rd_Nam;
    private javax.swing.JRadioButton rd_Nu;
    private static javax.swing.JTable tbl_ChiTietPhieuNhap;
    private static javax.swing.JTable tbl_NguyenLieu;
    private javax.swing.JTable tbl_NhanVien;
    private static javax.swing.JTable tbl_PhieuNhap;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_DonGia;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_HinhAnh;
    private javax.swing.JTextField txt_MaPhieuNhap;
    private javax.swing.JTextField txt_NV_TenNhanVien;
    private javax.swing.JTextField txt_NgayNghi;
    private javax.swing.JTextField txt_NgayNhap;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_NgayVaoLam;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_SoLuongTon;
    private javax.swing.JTextField txt_TenLoai;
    private javax.swing.JTextField txt_TenNguyenLieu;
    private javax.swing.JLabel txt_TenNhanVien;
    private javax.swing.JLabel txt_TenNhanVien1;
    private javax.swing.JLabel txt_TenNhanVien2;
    private javax.swing.JLabel txt_TenNhanVien3;
    private javax.swing.JTextField txt_TenSanPham;
    private javax.swing.JTextField txt_ThanhTien;
    private javax.swing.JTextField txt_TimKiemNhanVien;
    private javax.swing.JTextField txt_TimKiemSanPham;
    private javax.swing.JTextField txt_TongTien;
    private javax.swing.JTextField txt_XuatXu;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
