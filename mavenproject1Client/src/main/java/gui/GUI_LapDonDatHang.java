package gui;

import dao.ChiTietDonDatHangDao;
import dao.DonDatHangDao;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import url.RmiConfig;

/**
 *
 * @author dinhh
 */
public class GUI_LapDonDatHang extends javax.swing.JPanel {

    public static DefaultTableModel dataModel;
    private static final String URL = RmiConfig.RMI_URL;
    private DonDatHangDao ddh;
    private SanPham_DAO ds;
    private ChiTietDonDatHangDao dao;

    public GUI_LapDonDatHang(String maNV) throws RemoteException {
        try {
            ds = (SanPham_DAO) Naming.lookup(URL + "SanPham");
            ddh = (DonDatHangDao) Naming.lookup(URL + "DonDatHang");
            dao = (ChiTietDonDatHangDao) Naming.lookup(URL + "ChiTietDonDatHang");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();

        String[] CTDonDatHang = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        dataModel = new DefaultTableModel(CTDonDatHang, 0);
        tblChiTietDonDatHang.setModel(dataModel);
//        DonDatHangImpl ddh = new DonDatHangImpl();
        List<DonDatHang> list = ddh.getListDonDatHang();
        String ma = phatSinhMaDDH(list);
        txtMaDDH.setText(ma);
        txtMaNV.setText(maNV);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMaDDH = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        txtMaDDH = new javax.swing.JTextField();
        btnTaoDonDatHang = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblLapDDH = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        lblThongTinDDH = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        btnKH = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietDonDatHang = new javax.swing.JTable();
        pnHinhAnh = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        lblLoaiSP = new javax.swing.JLabel();
        txtLoaiSP = new javax.swing.JTextField();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        txtTenSP = new javax.swing.JTextField();
        btnSanPham = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaDDH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaDDH.setText("Mã Đơn Đặt Hàng");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNV.setText("Mã Nhân Viên");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblKhachHang.setText("Khách Hàng");

        txtMaDDH.setEditable(false);

        btnTaoDonDatHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTaoDonDatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add.png"))); // NOI18N
        btnTaoDonDatHang.setText("Tạo");
        btnTaoDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonDatHangActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLapDDH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLapDDH.setText("Lập Đơn Đặt Hàng");

        txtKhachHang.setEditable(false);

        lblThongTinDDH.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblThongTinDDH.setText("Thông Tin Đơn Đặt Hàng");

        txtMaNV.setEditable(false);

        btnKH.setText("...");
        btnKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKHActionPerformed(evt);
            }
        });

        tblChiTietDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietDonDatHang);

        pnHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaSP.setText("Mã Sản Phẩm");

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenSP.setText("Tên Sản Phẩm");

        txtMaSP.setEditable(false);
        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lblLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLoaiSP.setText("Loại Sản Phẩm");

        txtLoaiSP.setEditable(false);

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong.setText("Số Lượng");

        lblDonGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDonGia.setText("Đơn Giá");

        txtDonGia.setEditable(false);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTenSP.setEditable(false);

        btnSanPham.setText("...");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSP)
                            .addComponent(lblLoaiSP)
                            .addComponent(lblTenSP)
                            .addComponent(lblSoLuong)
                            .addComponent(lblDonGia)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(945, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pnHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSP)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanPham))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSP)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLoaiSP)
                            .addComponent(txtLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachHang)
                    .addComponent(lblMaDDH))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMaDDH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                        .addComponent(lblThongTinDDH)
                        .addGap(415, 415, 415))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(lblMaNV)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(640, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(btnTaoDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLapDDH)
                .addGap(510, 510, 510))
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
                .addComponent(lblLapDDH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaDDH)
                    .addComponent(txtMaDDH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNV)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKhachHang)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKH))
                .addGap(18, 18, 18)
                .addComponent(btnTaoDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblThongTinDDH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 158, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 158, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(492, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            String ma = txtMaSP.getText();
            if (ma.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String ten = txtTenSP.getText();
            String loai = txtLoaiSP.getText();

            String soLuongText = txtSoLuong.getText().trim();
            if (soLuongText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!soLuongText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Số lượng phải là một số nguyên dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int soLuong = Integer.parseInt(soLuongText);
            String donGia = txtDonGia.getText();
//            SanPhamImpl ds = new SanPhamImpl();
            SanPham sp = ds.getSanPhamTheoMa(ma);

            int soLuongConLai = sp.getSoLuong();

            if (soLuongConLai <= 0) {
                JOptionPane.showMessageDialog(null, "Sản phẩm hết hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (soLuong > soLuongConLai) {
                JOptionPane.showMessageDialog(null, "Nhập quá số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            xoaRong();
            donGia = donGia.replace(",", "");
            double donGiaSP = Double.parseDouble(donGia);

            for (int i = 0; i < tblChiTietDonDatHang.getRowCount(); i++) {
                String maTbl = tblChiTietDonDatHang.getValueAt(i, 0).toString();
                if (maTbl.equals(ma)) {
                    int soLuongAdd = Integer.parseInt(tblChiTietDonDatHang.getValueAt(i, 3).toString());
                    soLuongAdd += soLuong;

                    tblChiTietDonDatHang.setValueAt(soLuongAdd, i, 3);
                    tblChiTietDonDatHang.setValueAt(dcf.format(soLuongAdd * donGiaSP), i, 5);
                    ds.capNhatSoLuongSP(ma, -soLuong);

                    return;
                }
            }
            donGia = donGia.replace(",", "");
            Vector vec = new Vector();
            vec.add(ma);
            vec.add(ten);
            vec.add(loai);
            vec.add(soLuong);
            vec.add(donGiaSP);
            vec.add(dcf.format(soLuong * donGiaSP));

            ds.capNhatSoLuongSP(ma, -soLuong);
            dataModel.addRow(vec);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        GUI_SelectSP slSP = new GUI_SelectSP();
        slSP.setVisible(true);
        try {
            if (slSP.sanPhamTimDuoc != null) {
                txtMaSP.setText(slSP.sanPhamTimDuoc.getMaSP());
                txtTenSP.setText(slSP.sanPhamTimDuoc.getTenSP());
                txtLoaiSP.setText(slSP.sanPhamTimDuoc.getLoaiSP().getMaLoai());
                txtDonGia.setText(slSP.sanPhamTimDuoc.getGiaBan() + "");
//                SanPhamImpl ds = new SanPhamImpl();
                SanPham sp = ds.getSanPhamTheoMa(slSP.sanPhamTimDuoc.getMaSP());
                loadAnh("image/SanPham/" + sp.getHinhAnh());
                txtSoLuong.setText("");

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblChiTietDonDatHang.getSelectedRow();
        try {
            if (row > -1) {
                String ma = (String) tblChiTietDonDatHang.getValueAt(row, 0);
                int soLuong = Integer.parseInt(tblChiTietDonDatHang.getValueAt(row, 3).toString());
//                SanPhamImpl ds = new SanPhamImpl();
                ds.capNhatSoLuongSP(ma, soLuong);
                dataModel.removeRow(row);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTaoDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonDatHangActionPerformed
        try {
            int row = tblChiTietDonDatHang.getRowCount();
            if (row > 0) {
                if (!txtKhachHang.getText().isEmpty()) {
                    LocalDate ngayTao = LocalDate.now();
                    String maDDH = txtMaDDH.getText();
                    String maNV = txtMaNV.getText();
                    String maKh = txtKhachHang.getText();
                    String makh = maKh.substring(0, Math.min(maKh.length(), 5));
                    boolean success = luuDonDatHang(maDDH, ngayTao, makh, maNV);

                    System.out.println(success);
                    if (success) {
                        for (int i = 0; i < row; i++) {
                            String maSP = tblChiTietDonDatHang.getValueAt(i, 0).toString();
                            int soLuong = Integer.parseInt(tblChiTietDonDatHang.getValueAt(i, 3).toString());
                            double donGia = Double.parseDouble(tblChiTietDonDatHang.getValueAt(i, 4).toString().replace(",", ""));
                            luuCTDonDatHang(maDDH, maSP, soLuong, donGia);
                        }
//                        DonDatHangImpl ddh = new DonDatHangImpl();
                        List<DonDatHang> list = ddh.getListDonDatHang();
                        String ma = phatSinhMaDDH(list);
                        txtMaDDH.setText(ma);
                        txtKhachHang.setText("");
                        dataModel.setRowCount(0);
                        JOptionPane.showMessageDialog(null, "Đơn đặt hàng đã được tạo thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể tạo đơn đặt hàng", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần tạo đơn đặt hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào được thêm vào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTaoDonDatHangActionPerformed

    private void btnKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKHActionPerformed

        GUI_SelectKH kh = new GUI_SelectKH();
        kh.setVisible(true);
        if (kh.khachHangTimDuoc != null) {
            txtKhachHang.setText(kh.khachHangTimDuoc.getMaKH() + " - " + kh.khachHangTimDuoc.getTenKH());
        }
    }//GEN-LAST:event_btnKHActionPerformed
    File fileAnhSP;

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    private void loadAnh(String anh) {
        pnHinhAnh.setIcon(getAnhSP(anh));
    }
    DecimalFormat dcf = new DecimalFormat("###,###");

    public boolean luuDonDatHang(String maDDH, LocalDate ngayTao, String maKh, String maNV) throws java.rmi.RemoteException {
        DonDatHang ddh1 = new DonDatHang();
        ddh1.setMaDDH(maDDH);
        ddh1.setNgayTao(ngayTao);
        ddh1.setMaKh(new KhachHang(maKh));
        ddh1.setMaNV(new NhanVien(maNV));
        ddh1.setTinhTrang("Chưa Thanh Toán");
//         ddhd = new DonDatHangImpl();
        return ddh.themDDH(ddh1);
    }

    public void luuCTDonDatHang(String maDDH, String maSP, int soLuong, double donGia) throws java.rmi.RemoteException {
        ChiTietDonDatHang ctddh = new ChiTietDonDatHang();
        ctddh.setMaDDH(new DonDatHang(maDDH));
        ctddh.setMaSP(new SanPham(maSP));
        ctddh.setSoLuong(soLuong);
        ctddh.setDonGia(donGia);
//        ChiTietDonDatHangImpl dao = new ChiTietDonDatHangImpl();
        dao.themDDH(ctddh);
    }

    private String phatSinhMaDDH(List<DonDatHang> ddh) {
        int maxCode = 0;
        for (DonDatHang donDatHang : ddh) {
            String maDDH = donDatHang.getMaDDH();
            try {
                int code = Integer.parseInt(maDDH.substring(3));
                if (code > maxCode) {
                    maxCode = code;
                }
            } catch (NumberFormatException e) {

                e.printStackTrace();
            }
        }
        maxCode++;
        if (maxCode < 1000) {
            return "DDH" + String.format("%03d", maxCode);
        } else if (maxCode < 10000) {
            return "DDH" + maxCode;
        } else {
            JOptionPane.showMessageDialog(null, "Mã số đã đạt tối đa, cần cập nhật thêm", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void xoaRong() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtLoaiSP.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        loadAnh("image/SanPham/" + "default.png");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKH;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnTaoDonDatHang;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblLapDDH;
    private javax.swing.JLabel lblLoaiSP;
    private javax.swing.JLabel lblMaDDH;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThongTinDDH;
    private javax.swing.JLabel pnHinhAnh;
    public static javax.swing.JTable tblChiTietDonDatHang;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtMaDDH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

}
