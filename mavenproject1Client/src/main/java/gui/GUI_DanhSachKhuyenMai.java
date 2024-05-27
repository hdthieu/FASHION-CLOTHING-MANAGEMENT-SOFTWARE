/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietKhuyenMaiDao;
import dao.KhuyenMaiDao;
import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import url.RmiConfig;

/**
 *
 * @author TriHieu
 */
public class GUI_DanhSachKhuyenMai extends javax.swing.JPanel {

    private static final String URL = RmiConfig.RMI_URL;
    private String patternCurrency = "###,###.###VND";
    private DecimalFormat dcfCurrency = new DecimalFormat(patternCurrency);
    private DefaultTableModel dataModelSP;
    private DefaultTableModel dataModel;
    private ChiTietKhuyenMaiDao cTKMDao;
    private KhuyenMaiDao km;
//    private ChiTietKhuyenMai_DAO cTKMDao = new ChiTietKhuyenMai_DAO();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form GUI_DanhSachKhuyenMai
     */
    public GUI_DanhSachKhuyenMai() throws java.rmi.RemoteException {
        try {
            cTKMDao = (ChiTietKhuyenMaiDao) Naming.lookup(URL + "ChiTietKhuyenMai");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();
        String[] sanPham = {"Mã SP", "Tên SP", "Đơn Giá", "Giá Giảm", "Thành Tiền"};
        String[] kMai = {"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Mô Tả", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"};
        dataModel = new DefaultTableModel(kMai, 0);
        dataModelSP = new DefaultTableModel(sanPham, 0);
        tblKM.setModel(dataModel);
        tblSanPham.setModel(dataModelSP);
        try {
            updateTableKM();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtngayBD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaDen = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnlamMoi = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnlKM = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKM = new javax.swing.JTable();
        pnlSPham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Tìm Kiếm Khuyến Mãi ");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã Khuyến Mãi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên Khuyến Mãi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Bắt Đầu");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày Kết Thúc");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Khoảng Thời Gian");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Khoảng Giá Giảm");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Từ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Đến");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_search.png"))); // NOI18N
        btnTimKiem.setText("TÌm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnlamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnlamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_change.png"))); // NOI18N
        btnlamMoi.setText("Làm Mới");
        btnlamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlamMoiActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kMaiPro.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addComponent(txtngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(138, 138, 138))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(42, 42, 42)
                                .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(42, 42, 42)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnlamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnlamMoi))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlKM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblKM.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKM);

        javax.swing.GroupLayout pnlKMLayout = new javax.swing.GroupLayout(pnlKM);
        pnlKM.setLayout(pnlKMLayout);
        pnlKMLayout.setHorizontalGroup(
            pnlKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlKMLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlKMLayout.setVerticalGroup(
            pnlKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
            .addGroup(pnlKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlKMLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlSPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout pnlSPhamLayout = new javax.swing.GroupLayout(pnlSPham);
        pnlSPham.setLayout(pnlSPhamLayout);
        pnlSPhamLayout.setHorizontalGroup(
            pnlSPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlSPhamLayout.setVerticalGroup(
            pnlSPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(640, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(442, 442, 442))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(pnlSPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKMMouseClicked
        try {
            int row = tblKM.getSelectedRow();
            String ma = tblKM.getValueAt(row, 0).toString();

            tableChiTietKMai(ma);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblKMMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            timKiemKhuyenMai();
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_DanhSachKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnlamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiActionPerformed
        txtGiaDen.setText("");
        txtGiaTu.setText("");
        txtMa.setText("");
        txtNgayKT.setText("");
        txtTenKM.setText("");
        txtngayBD.setText("");
        dataModelSP.setRowCount(0);
        try {
            updateTableKM();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnlamMoiActionPerformed
    private void updateTableKM() throws RemoteException {
        List<ChiTietKhuyenMai> listKhuyenMai = cTKMDao.docTuBang();
        dataModel.setRowCount(0);
        // Thêm dữ liệu mới
        for (ChiTietKhuyenMai km : listKhuyenMai) {
            String ngayBatDau = dateFormat.format(km.getKhuyenMai().getNgayBatDau());
            String ngayKetThuc = dateFormat.format(km.getKhuyenMai().getNgayKetThuc());
            String[] rowData = {
                km.getKhuyenMai().getMaKhuyenMai(),
                km.getKhuyenMai().getTenKhuyenMai(),
                km.getKhuyenMai().getMoTa(),
                ngayBatDau,
                ngayKetThuc,
                km.getKhuyenMai().getTrangThai()
            };
            dataModel.addRow(rowData);
        }
        tblKM.setModel(dataModel);
    }

    private void tableChiTietKMai(String ma) throws RemoteException {
        List<ChiTietKhuyenMai> listCTKM = cTKMDao.getListKMTheoMaKM(ma);
        dataModelSP.setRowCount(0);
        for (ChiTietKhuyenMai cTietKMai : listCTKM) {
            double thanhTien = cTietKMai.getSanPham().getGiaBan() - cTietKMai.getGiaGiam();
            String[] rowData = {cTietKMai.getSanPham().getMaSP(), cTietKMai.getSanPham().getTenSP(), dcfCurrency.format(cTietKMai.getSanPham().getGiaBan()), dcfCurrency.format(cTietKMai.getGiaGiam()), dcfCurrency.format(thanhTien)};
            dataModelSP.addRow(rowData);
        }
        tblSanPham.setModel(dataModelSP);
    }

    private void timKiemKhuyenMai() throws RemoteException {
        String maKM = txtMa.getText().trim();
        String giaGiamTu = txtGiaTu.getText().trim();
        String giaGiamDen = txtGiaDen.getText().trim();
        String tenKM = txtTenKM.getText().trim();
        Double giaGiamTud = null;
        Double giaGiamDend = null;
        String ngayBDString = txtngayBD.getText().toString();
        String ngayKTString = txtNgayKT.getText().toString();
        Date ngayBD = null;
        Date ngayKThuc = null;
        java.sql.Date sqlDatengayBD = null;
        java.sql.Date sqlDatengayKThuc = null;
        // điều kiện rỗng của tất cả
        if (maKM.isEmpty() && giaGiamTu.isEmpty() && giaGiamDen.isEmpty() && ngayBDString.isEmpty() && ngayKTString.isEmpty() && tenKM.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ít nhất 1 thông tin để tìm");
            return;
        }
        // điều kiện của giá giảm
        if (!giaGiamTu.isEmpty()) {
            try {
                if (giaGiamTu.matches("\\d+")) {
                    giaGiamTud = Double.parseDouble(giaGiamTu);
                } else {
                    JOptionPane.showMessageDialog(null, "Giá giảm phải là số dương");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá giảm phải là số");
                return;
            }
        }
        if (!giaGiamDen.isEmpty()) {
            try {
                if (giaGiamDen.matches("\\d+")) {
                    giaGiamDend = Double.parseDouble(giaGiamDen);
                } else {
                    JOptionPane.showMessageDialog(null, "Giá giảm phải là số dương");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá giảm phải là số");
                return;
            }
        }
        if (giaGiamTud != null && giaGiamDend != null && giaGiamTud > giaGiamDend) {
            JOptionPane.showMessageDialog(null, "Giá giảm tại ô bắt đầu phải nhỏ hơn giá giảm tại ô đến");
            return;
        }
        // điều kiện của ngày tháng năm
        try {
            if (!ngayBDString.isEmpty()) {
                if (ngayBDString.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    ngayBD = dateFormat.parse(ngayBDString);
                    sqlDatengayBD = new java.sql.Date(ngayBD.getTime());
                } else {
                    JOptionPane.showMessageDialog(null, "Ngày Bắt Đầu phải có định dạng (dd-MM-yyyy)");
                    return;
                }

            }
            if (!ngayKTString.isEmpty()) {
                if (ngayKTString.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    ngayKThuc = dateFormat.parse(ngayKTString);
                    sqlDatengayKThuc = new java.sql.Date(ngayKThuc.getTime());
                } else {
                    JOptionPane.showMessageDialog(null, "Ngày kết thúc phải có định dạng (dd-MM-yyyy)");
                    return;
                }
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Định dạng đúng là dd-MM-yyyy");
            return;
        }
        try {
            List<KhuyenMai> listKM = cTKMDao.timKiemKhuyenMai(maKM, giaGiamTud, giaGiamDend, sqlDatengayBD, sqlDatengayKThuc, tenKM);
            if (listKM != null && !listKM.isEmpty()) {
                dataModel.setRowCount(0);
                for (KhuyenMai km : listKM) {
                    String ngayBatDau = dateFormat.format(km.getNgayBatDau());
                    String ngayKetThuc = dateFormat.format(km.getNgayKetThuc());
                    String[] rowData = {
                        km.getMaKhuyenMai(),
                        km.getTenKhuyenMai(),
                        km.getMoTa(),
                        ngayBatDau,
                        ngayKetThuc,
                        km.getTrangThai()};
                    dataModel.addRow(rowData);
                }
                tblKM.setModel(dataModel);
                JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Tìm kiếm không thành công");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlKM;
    private javax.swing.JPanel pnlSPham;
    private javax.swing.JTable tblKM;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaDen;
    private javax.swing.JTextField txtGiaTu;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgayKT;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtngayBD;
    // End of variables declaration//GEN-END:variables

}
