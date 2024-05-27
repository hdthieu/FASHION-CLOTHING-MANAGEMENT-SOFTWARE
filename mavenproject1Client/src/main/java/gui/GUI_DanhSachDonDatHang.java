package gui;

//import Connection.ConnectSQL;
import dao.ChiTietDonDatHangDao;
import dao.DonDatHangDao;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import entity.HoaDon;
import entity.SanPham;
import static gui.GUI_LapHoaDon.tblChiTietHD;
import com.sun.jdi.connect.spi.Connection;
import dao.ChiTietKhuyenMaiDao;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import url.RmiConfig;

/**
 *
 * @author dinhh
 */
public class GUI_DanhSachDonDatHang extends javax.swing.JPanel {

    DefaultTableModel dataModelDonDatHang;
    DefaultTableModel dataModelCTDonDatHang;
    DecimalFormat dcf = new DecimalFormat("###,###");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String URL = RmiConfig.RMI_URL;
    private static ChiTietDonDatHangDao ds;
    private DonDatHangDao dDHDao;
    private HoaDon_DAO hd;
    private KhachHang_DAO kh;
    private DonDatHangDao donDatHangDAO;
    private SanPham_DAO sanPhamDAO;
    private ChiTietKhuyenMaiDao ctkmDao;
    public GUI_DanhSachDonDatHang() throws java.rmi.RemoteException {
        try {
            ctkmDao = (ChiTietKhuyenMaiDao) Naming.lookup(URL + "ChiTietKhuyenMai");
            ds = (ChiTietDonDatHangDao) Naming.lookup(URL + "ChiTietDonDatHang");
            dDHDao = (DonDatHangDao) Naming.lookup(URL + "DonDatHang");
            hd = (HoaDon_DAO) Naming.lookup(URL + "HoaDon");
            kh = (KhachHang_DAO) Naming.lookup(URL + "KhachHang");
            donDatHangDAO = (DonDatHangDao) Naming.lookup(URL + "DonDatHang");
            sanPhamDAO = (SanPham_DAO) Naming.lookup(URL + "SanPham");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();
        String[] TBLDonDatHang = {"Mã Đơn Đặt Hàng", "Ngày Tạo", "Khách Hàng", "Mã Nhân Viên", "Tổng Tiền", "Tình Trạng"};
        dataModelDonDatHang = new DefaultTableModel(TBLDonDatHang, 0);
        tblDonDatHang.setModel(dataModelDonDatHang);

        String[] TBLCTDonDatHang = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        dataModelCTDonDatHang = new DefaultTableModel(TBLCTDonDatHang, 0);
        tblChiTietDonDatHang.setModel(dataModelCTDonDatHang);
//        Connection.ConnectSQL.getInstance().connect();
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        lblTuNgay = new javax.swing.JLabel();
        txtMaDDH = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        lblGiaTu = new javax.swing.JLabel();
        txtGiaTu = new javax.swing.JTextField();
        lblGiaDen = new javax.swing.JLabel();
        txtGiaDen = new javax.swing.JTextField();
        txtTuNgay = new javax.swing.JTextField();
        lblMaHD1 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblMaHD4 = new javax.swing.JLabel();
        lblNgayDen = new javax.swing.JLabel();
        txtNgayDen = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnHuyDon = new javax.swing.JButton();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblMaHD2 = new javax.swing.JLabel();
        lblMaHD3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonDatHang = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietDonDatHang = new javax.swing.JTable();
        lblMaHD6 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHD.setText("Mã Đơn Đặt Hàng");

        lblTuNgay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTuNgay.setText("Từ Ngày");

        txtMaDDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDDHActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_refesh.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Danh Sách Đơn Đặt Hàng");

        lblMaKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaKH.setText("Mã Khách Hàng");

        lblGiaTu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGiaTu.setText("Từ");

        lblGiaDen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGiaDen.setText("Đến");

        lblMaHD1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHD1.setText("Tìm Kiếm Theo Khoảng Tổng Tiền");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNV.setText("Mã Nhân Viên");

        lblMaHD4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHD4.setText("Tìm Kiếm Theo Khoảng Ngày");

        lblNgayDen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayDen.setText("Đến Ngày");

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-thanh-toan.png"))); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuyDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuyDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_remove.png"))); // NOI18N
        btnHuyDon.setText("Hủy Đơn");
        btnHuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonActionPerformed(evt);
            }
        });

        lblSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSDT.setText("Số điện thoại KH");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaHD)
                            .addComponent(lblTuNgay)
                            .addComponent(lblNgayDen))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaDDH, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMaHD4))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGiaTu)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblMaKH)
                                .addGap(18, 18, 18)
                                .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblGiaDen)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMaHD1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblMaNV)
                                .addGap(38, 38, 38)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblSDT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(566, 566, 566)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(txtMaDDH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaKH)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNV)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD1)
                    .addComponent(lblMaHD4)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTuNgay)
                    .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiaTu)
                    .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgayDen)
                    .addComponent(lblGiaDen)
                    .addComponent(txtGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaHD2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMaHD2.setText("Đơn Đặt Hàng");
        jPanel4.add(lblMaHD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 13, -1, -1));

        lblMaHD3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMaHD3.setText("Chi Tiết Đơn Đặt Hàng");
        jPanel4.add(lblMaHD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1477, 13, -1, -1));

        tblDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Đơn Đặt Hàng", "Ngày Tạo", "Mã Khách Hàng", "Mã Nhân Viên", "Tổng Tiền", "Tình Trạng"
            }
        ));
        tblDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonDatHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDonDatHang);

        jScrollPane1.setViewportView(jScrollPane2);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 67, 628, 337));

        tblChiTietDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane3.setViewportView(tblChiTietDonDatHang);

        jScrollPane4.setViewportView(jScrollPane3);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 67, 600, 344));

        lblMaHD6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMaHD6.setText("Chi Tiết Đơn Đặt Hàng");
        jPanel4.add(lblMaHD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(867, 13, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1277, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        String maDDH = txtMaDDH.getText().toString();
        String maKH = txtKhachHang.getText().toString();
        String maNV = txtMaNV.getText().toString();
        String ngayDau = txtTuNgay.getText().toString();
        String ngayCuoi = txtNgayDen.getText().toString();
        String tongDau = txtGiaTu.getText();
        String tongCuoi = txtGiaDen.getText();
        String sdt = txtSDT.getText();
        double tongTu = -1;
        double tongDen = -1;
        if (maDDH.isEmpty() && maKH.isEmpty() && maNV.isEmpty() && ngayDau.isEmpty() && ngayCuoi.isEmpty() && tongDau.isEmpty() && tongCuoi.isEmpty() && sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ít nhất 1 thông tin để tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!tongDau.isEmpty() && tongDau.matches("\\d+")) {
            tongTu = Double.parseDouble(tongDau);
            System.out.println(tongTu);
        } else if (!tongDau.isEmpty() && !tongDau.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Giá phải là số dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!tongCuoi.isEmpty() && tongCuoi.matches("\\d+")) {
            tongDen = Double.parseDouble(tongCuoi);
            System.out.println(tongDen);
        } else if (!tongCuoi.isEmpty() && !tongCuoi.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Giá phải là số dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (tongDen >= 0 && tongTu >= 0 && (tongTu > tongDen)) {
            JOptionPane.showMessageDialog(this, "Tổng từ phải nhỏ hơn hoặc bằng tổng đến", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!ngayDau.isEmpty() && ngayDau.matches("\\d{2}-\\d{2}-\\d{4}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayTao = LocalDate.parse(ngayDau, formatter);
            if (ngayTao.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "Ngày Từ không vượt quá hiện tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } else if (!ngayDau.isEmpty() && !ngayDau.matches("\\d{2}-\\d{2}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Ngày có định dạng dd-MM-yyyy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!ngayCuoi.isEmpty() && !ngayCuoi.matches("\\d{2}-\\d{2}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Ngày có định dạng dd-MM-yyyy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!ngayDau.isEmpty() && !ngayCuoi.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            if (LocalDate.parse(ngayDau, formatter).isAfter(LocalDate.parse(ngayCuoi, formatter))) {
                JOptionPane.showMessageDialog(this, "Ngày Từ không vượt quá ngày Đến", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        if (!sdt.isEmpty()) {
            if (!sdt.matches("0\\d{9}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
//        DonDatHangDao ds = new DonDatHangDao();
        try {
            List<DonDatHang> list = dDHDao.timKiemDonDatHang(maDDH, ngayDau, ngayCuoi, maKH, maNV, tongTu, tongDen, sdt);
            int sl = list.size();
            if (!list.isEmpty()) {
                dataModelDonDatHang.getDataVector().removeAllElements();
                for (DonDatHang dh : list) {
                    double tongTien = dDHDao.tinhTongTienTheoMaDonDatHang(dh.getMaDDH());
                    String[] rowData = {dh.getMaDDH(), dtf.format(dh.getNgayTao()), dh.getMaKh().getMaKH(), dh.getMaNV().getMaNhanVien(), dcf.format(tongTien), dh.getTinhTrang()};
                    dataModelDonDatHang.addRow(rowData);
                }
                tblDonDatHang.setModel(dataModelDonDatHang);
            } else {
                dataModelDonDatHang.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Không tìm thấy đơn đặt hàng nào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {

//        DonDatHangDao donDatHang = new DonDatHangDao() {};
        try {
            List<DonDatHang> list = dDHDao.getListDonDatHang();

            for (DonDatHang ddh : list) {
//            DonDatHangDao hdon = new DonDatHangDao();
                double tongTien = dDHDao.tinhTongTienTheoMaDonDatHang(ddh.getMaDDH());
                String[] rowData = {ddh.getMaDDH(), dtf.format(ddh.getNgayTao()), ddh.getMaKh().getMaKH(), ddh.getMaNV().getMaNhanVien(), dcf.format(tongTien), ddh.getTinhTrang()};
                dataModelDonDatHang.addRow(rowData);
            }
            tblDonDatHang.setModel(dataModelDonDatHang);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed
    public void lamMoi() {
        txtMaDDH.setText("");
        txtKhachHang.setText("");
        txtMaNV.setText("");
        txtGiaDen.setText("");
        txtGiaTu.setText("");
        txtNgayDen.setText("");
        txtTuNgay.setText("");
        txtSDT.setText("");
        dataModelDonDatHang.getDataVector().removeAllElements();
        dataModelCTDonDatHang.setRowCount(0);
        updateTable();
    }
    private void tblDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonDatHangMouseClicked

        int row = tblDonDatHang.getSelectedRow();
        if (row >= 0) {
            Object maDDHValue = tblDonDatHang.getValueAt(row, 0);
            Object khachHangValue = tblDonDatHang.getValueAt(row, 2);
            Object maNVValue = tblDonDatHang.getValueAt(row, 3);

            if (maDDHValue != null && khachHangValue != null && maNVValue != null) {
                txtMaDDH.setText(maDDHValue.toString());
                txtKhachHang.setText(khachHangValue.toString());
                txtMaNV.setText(maNVValue.toString());
                String ma = maDDHValue.toString();
                try {
                    tableChiTietDonDatHang(ma);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {

            }
        }
    }//GEN-LAST:event_tblDonDatHangMouseClicked

    private void txtMaDDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDDHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDDHActionPerformed
    private void xuLyXuatHoaDonBanHang() throws RemoteException {
        ArrayList<Vector> dsGioHang = new ArrayList<>();
        int row = tblChiTietDonDatHang.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(null, "Không có mục hàng để tạo hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
//        HoaDon_DAO hd = new HoaDon_DAO();
        List<HoaDon> list = hd.getListHoaDon();
        String ma = phatSinhMaHD(list);
        int row2 = tblDonDatHang.getSelectedRow();
        String maDDHValue = (String) tblDonDatHang.getValueAt(row2, 0);
        String khachHangValue = (String) tblDonDatHang.getValueAt(row2, 2);
        String maNVValue = (String) tblDonDatHang.getValueAt(row2, 3);

        double tongTien = 0;
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tblChiTietDonDatHang.getValueAt(i, 0));
            vec.add(tblChiTietDonDatHang.getValueAt(i, 1));
            vec.add(tblChiTietDonDatHang.getValueAt(i, 2));
            vec.add(tblChiTietDonDatHang.getValueAt(i, 3));
            vec.add(tblChiTietDonDatHang.getValueAt(i, 4));
            vec.add(tblChiTietDonDatHang.getValueAt(i, 5));
            tongTien += Double.parseDouble((tblChiTietDonDatHang.getValueAt(i, 5) + "").replace(",", ""));
            dsGioHang.add(vec);
        }

        // Kiểm tra trạng thái đơn hàng trước khi tạo hóa đơn
//        DonDatHangDao donDatHangDAO = new DonDatHangDao();
        String trangThaiDon = donDatHangDAO.layTrangThaiDonHang(maDDHValue);

        if (trangThaiDon.equals("Đã Thanh Toán")) {
            JOptionPane.showMessageDialog(null, "Đơn hàng đã thanh toán, không thể tạo hóa đơn mới.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (trangThaiDon.equals("Đã Hủy")) {
            JOptionPane.showMessageDialog(null, "Đơn hàng đã hủy, không thể tạo hóa đơn mới.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //KhachHang_DAO kh2 = (KhachHang_DAO) Naming.lookup(URL + "KhachHang");
        String tenKhachHang = kh.layTenKhachHang(khachHangValue);
        GUI_XuatHoaDon xd = new GUI_XuatHoaDon(dsGioHang, tongTien, maNVValue, tenKhachHang, ma);
        xd.setVisible(true);
        if (xd.checkBanHang) {
            boolean capNhatThanhCong = donDatHangDAO.capNhatTrangThaiDonHang(maDDHValue, "Đã Thanh Toán");
            lamMoi();
        }
    }

    private String phatSinhMaHD(List<HoaDon> listHoaDon) {
        if (listHoaDon.isEmpty()) {
            return "HD001";
        } else {
            HoaDon hoaDonCuoi = listHoaDon.get(listHoaDon.size() - 1);
            String maCuoi = hoaDonCuoi.getMaHD();
            String soHienTaiStr = maCuoi.substring(2);
            int soHienTai = Integer.parseInt(soHienTaiStr);
            int soTiepTheo = soHienTai + 1;
            String maMoi = "HD" + String.format("%03d", soTiepTheo);
            return maMoi;
        }
    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        int row = tblChiTietDonDatHang.getRowCount();
        if (row > 0) {
            try {
                xuLyXuatHoaDonBanHang();
                // Cập nhật trạng thái đơn hàng nếu quá trình tạo hóa đơn thành công
//            DonDatHangDao donDatHangDAO = new DonDatHangDao();
                // boolean capNhatThanhCong = donDatHangDAO.capNhatTrangThaiDonHang(txtMaDDH.getText(), "Đã Thanh Toán");

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {

            JOptionPane.showMessageDialog(null, "Chưa chọn đơn đặt hàng nào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonActionPerformed

        int selectedRow = tblDonDatHang.getSelectedRow();
//        DonDatHangDao donDatHangDAO = new DonDatHangDao();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn đơn đặt hàng nào!");
        } else {
            DefaultTableModel model = (DefaultTableModel) tblDonDatHang.getModel();
            String maDonHang = txtMaDDH.getText();
            try {
                String trangThai = donDatHangDAO.layTrangThaiDonHang(txtMaDDH.getText());

                if (trangThai.equals("Đã Hủy")) {
                    JOptionPane.showMessageDialog(null, "Đơn đặt hàng này đã được hủy trước đó!");
                } else if (trangThai.equals("Đã Thanh Toán")) {
                    JOptionPane.showMessageDialog(null, "Đơn đặt hàng đã thanh toán. Không thể hủy!");
                } else {
                    int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn hủy đơn đặt hàng này không?", "Xác nhận hủy đơn đặt hàng", JOptionPane.YES_NO_OPTION);

                    if (xacNhan == JOptionPane.YES_OPTION) {
                        boolean capNhatThanhCong = donDatHangDAO.capNhatTrangThaiDonHang(maDonHang, "Đã Hủy");

                        if (capNhatThanhCong) {
                            JOptionPane.showMessageDialog(null, "Hủy đơn đặt hàng thành công!");
                            lamMoi();
                        } else {
                            JOptionPane.showMessageDialog(null, "Hủy không thành công!");
                        }
                    } else {

                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHuyDonActionPerformed

    private void tableChiTietDonDatHang(String ma) throws RemoteException {
//        ChiTietDonDatHangDao donDatHang = new ChiTietDonDatHangDao();
        List<ChiTietDonDatHang> list = ds.getListDonDatHangTheoMaDDH(ma);
        dataModelCTDonDatHang.setRowCount(0);

        SanPham sanPham;
        String tenSP;
        String tenLoai;

//        SanPham_DAO sanPhamDAO = new SanPham_DAO();
        for (ChiTietDonDatHang ddh : list) {
            sanPham = ddh.getMaSP();
            tenSP = sanPhamDAO.layTenSanPhamTheoMa(sanPham.getMaSP());
            tenLoai = sanPhamDAO.layTenLoaiSanPhamTheoMa(sanPham.getMaSP());
            String[] rowData = {
                sanPham.getMaSP(),
                tenSP,
                tenLoai,
                String.valueOf(ddh.getSoLuong()),
                String.valueOf(ddh.getDonGia()),
                dcf.format(ddh.getSoLuong() * ddh.getDonGia())
            };

            dataModelCTDonDatHang.addRow(rowData);
        }

        tblChiTietDonDatHang.setModel(dataModelCTDonDatHang);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblGiaDen;
    private javax.swing.JLabel lblGiaTu;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaHD1;
    private javax.swing.JLabel lblMaHD2;
    private javax.swing.JLabel lblMaHD3;
    private javax.swing.JLabel lblMaHD4;
    private javax.swing.JLabel lblMaHD6;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgayDen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JTable tblChiTietDonDatHang;
    private javax.swing.JTable tblDonDatHang;
    private javax.swing.JTextField txtGiaDen;
    private javax.swing.JTextField txtGiaTu;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaDDH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayDen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
