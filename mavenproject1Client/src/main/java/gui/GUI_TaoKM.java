package gui;

import dao.ChiTietKhuyenMaiDao;
import dao.KhuyenMaiDao;
import dao.LoaiSanPham_DAO;
import dao.NhaCungCapDao;
import dao.SanPham_DAO;
import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import url.RmiConfig;

/**
 *
 * @author TriHieu
 */
public class GUI_TaoKM extends javax.swing.JPanel {

    DefaultTableModel dataModelSP;
    DefaultTableModel dataModel;
    public Boolean isUpdatingComboBox;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    private static final String URL = RmiConfig.RMI_URL;
    private SanPham_DAO spDao;
    private KhuyenMaiDao kmDao;
    private ChiTietKhuyenMaiDao ctkmDao;

    public GUI_TaoKM() throws java.rmi.RemoteException {

        try {
            spDao = (SanPham_DAO) Naming.lookup(URL + "SanPham");
            kmDao = (KhuyenMaiDao) Naming.lookup(URL + "KhuyenMai");
            ctkmDao = (ChiTietKhuyenMaiDao) Naming.lookup(URL + "ChiTietKhuyenMai");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();
        String[] sanPham = {"Mã SP", "Tên SP", "Loại SP", "Đơn Giá", "Chọn", "Trạng Thái"};
        String[] kMai = {"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Mô Tả", "Loại", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá", "Trạng Thái"};
        dataModel = new DefaultTableModel(kMai, 0);
        dataModelSP = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Cột cuối cùng là CheckBox
                return columnIndex == 4 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cột CheckBox là có thể chỉnh sửa
                return column == 4;
            }
        };

        dataModelSP.setColumnIdentifiers(sanPham);
        tblKM.setModel(dataModel);
        tblSanPham.setModel(dataModelSP);
        //Connection.ConnectSQL.getInstance().connect();
        taiSanPhamVaDanhDauKhuyenMai("Quần");
        updateTableKM();
    }

    private void updateTableKM() throws java.rmi.RemoteException {
        List<ChiTietKhuyenMai> listKhuyenMai = ctkmDao.docTuBang();
        dataModel.setRowCount(0);
        // Thêm dữ liệu mới
        for (ChiTietKhuyenMai km : listKhuyenMai) {
            String ngayBatDau = dateFormatter.format(km.getKhuyenMai().getNgayBatDau());
            String ngayKetThuc = dateFormatter.format(km.getKhuyenMai().getNgayKetThuc());
            String[] rowData = {
                km.getKhuyenMai().getMaKhuyenMai(),
                km.getKhuyenMai().getTenKhuyenMai(),
                km.getKhuyenMai().getMoTa(),
                km.getSanPham().getLoaiSP().getTenLoai(),
                ngayBatDau,
                ngayKetThuc,
                String.valueOf(km.getGiaGiam()),
                km.getKhuyenMai().getTrangThai()
            };
            dataModel.addRow(rowData);
        }
        tblKM.setModel(dataModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlNorth = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblQuanLySanPham = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKM = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnSuaKM = new javax.swing.JButton();
        btnThem10 = new javax.swing.JButton();
        txtDateSau = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDateTruoc = new com.toedter.calendar.JDateChooser();
        txtMaKM = new javax.swing.JTextField();
        lblDiaChiNCC10 = new javax.swing.JLabel();
        lblTenNCC10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTextArea = new javax.swing.JTextArea();
        txtMucGiam = new javax.swing.JTextField();
        lblSdtNCC12 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        lblDiaChiNCC11 = new javax.swing.JLabel();
        btnLamMoi1 = new javax.swing.JButton();
        btnXoaKM = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        pnlCenter = new javax.swing.JPanel();

        pnlNorth.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblQuanLySanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblQuanLySanPham.setText("Tạo Khuyến Mãi");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Danh Sách Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        tblKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Nhà Cung Cấp", "Hình Ảnh"
            }
        ));
        tblKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel19.setText("Chức Năng");

        btnSuaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_refesh.png"))); // NOI18N
        btnSuaKM.setText("Sửa Khuyến Mãi");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnThem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add.png"))); // NOI18N
        btnThem10.setText("Thêm");
        btnThem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem10ActionPerformed(evt);
            }
        });

        txtDateSau.setDateFormatString("yyyy-MM-dd\n");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Khoảng Thời Gian");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Đến");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Từ");

        txtDateTruoc.setDateFormatString("yyyy-MM-dd\n");

        txtMaKM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        lblDiaChiNCC10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDiaChiNCC10.setText("Mã Khuyến Mãi");

        lblTenNCC10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTenNCC10.setText("Mô tả");

        txtTextArea.setColumns(20);
        txtTextArea.setRows(5);
        jScrollPane1.setViewportView(txtTextArea);

        lblSdtNCC12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblSdtNCC12.setText("Giá Giảm:");

        txtTenKM.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        lblDiaChiNCC11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDiaChiNCC11.setText("Tên Khuyến Mãi");

        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_refesh.png"))); // NOI18N
        btnLamMoi1.setText("Làm Mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        btnXoaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_remove.png"))); // NOI18N
        btnXoaKM.setText("Xóa KM");
        btnXoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTenNCC10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDiaChiNCC11)
                        .addGap(36, 36, 36)
                        .addComponent(txtTenKM)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblSdtNCC12)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addGap(113, 113, 113))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel16)
                                                .addComponent(jLabel17))
                                            .addGap(38, 38, 38)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDiaChiNCC10)
                        .addGap(36, 36, 36)
                        .addComponent(txtMaKM)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(btnThem10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChiNCC10)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChiNCC11)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTenNCC10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(65, 65, 65))
                    .addComponent(jScrollPane1))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSdtNCC12)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem10)
                    .addComponent(btnLamMoi1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaKM)
                    .addComponent(btnXoaKM))
                .addGap(103, 103, 103))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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
        jScrollPane3.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Loại Sản Phẩm");

        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quần", "Áo", "Giày", "Túi Xách", " " }));
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNorthLayout = new javax.swing.GroupLayout(pnlNorth);
        pnlNorth.setLayout(pnlNorthLayout);
        pnlNorthLayout.setHorizontalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblQuanLySanPham)
                .addGap(541, 541, 541))
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNorthLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 590, Short.MAX_VALUE)))
        );
        pnlNorthLayout.setVerticalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuanLySanPham)
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(338, Short.MAX_VALUE))
            .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNorthLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 212, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKMMouseClicked
        int selectedRow = tblKM.getSelectedRow();
        if (selectedRow >= 0) {
            String maKhuyenMai = (String) tblKM.getValueAt(selectedRow, 0);
            String moTa = (String) tblKM.getValueAt(selectedRow, 2);
            String tenKhuyenMai = (String) tblKM.getValueAt(selectedRow, 1);
            try {
                String ngayBatDauStr = (String) tblKM.getValueAt(selectedRow, 4);
                String ngayKetThucStr = (String) tblKM.getValueAt(selectedRow, 5);
                Date ngayBatDau = dateFormatter.parse(ngayBatDauStr);
                Date ngayKetThuc = dateFormatter.parse(ngayKetThucStr);
                txtDateTruoc.setDate(ngayBatDau);
                txtDateSau.setDate(ngayKetThuc);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtMaKM.setText(maKhuyenMai);
            txtTextArea.setText(moTa);
            txtTenKM.setText(tenKhuyenMai);
            txtMucGiam.setText((String) tblKM.getValueAt(selectedRow, 6));
            String loaiSP = (String) tblKM.getValueAt(selectedRow, 3);
            // Cập nhật comboBox
            isUpdatingComboBox = true;
            cboLoaiSP.setSelectedItem(loaiSP);
            isUpdatingComboBox = false;
        }
    }//GEN-LAST:event_tblKMMouseClicked

    private void btnThem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem10ActionPerformed
        try {

            String maKM = txtMaKM.getText();
            if (kmDao.checkTrungMa(maKM)) {
                JOptionPane.showMessageDialog(null, "Mã khuyến mãi này đã tồn tại. Vui lòng sử dụng mã khác.");
                return;
            }
            if (!validateInput()) {
                return;
            }
            String tenKM = txtTenKM.getText();
            String moTa = txtTextArea.getText();
            Date ngayBatDau = txtDateTruoc.getDate();
            Date ngayKetThuc = txtDateSau.getDate();
            double mucGiam = Double.parseDouble(txtMucGiam.getText());
            String trangThai;
            Date ngayHienTai = new Date();
            // Xử lý để làm điều kiện 
            String strNgayBatDau = dateFormatter.format(ngayBatDau);
            String strNgayHienTai = dateFormatter.format(ngayHienTai);
            try {
                // Chuyển đổi String trở lại thành Date để so sánh
                ngayBatDau = dateFormatter.parse(strNgayBatDau);
                ngayHienTai = dateFormatter.parse(strNgayHienTai);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (ngayBatDau.before(ngayHienTai)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được trước ngày hiện tại");
                return;
            }

            if (ngayHienTai.before(ngayBatDau)) {
                trangThai = "upcoming";
            } else if (!ngayHienTai.before(ngayBatDau) && !ngayHienTai.after(ngayKetThuc)) {
                trangThai = "active";
            } else {
                trangThai = "off";
            }
            // Kiểm tra xem có sản phẩm nào được chọn và hợp lệ 
            boolean coSanPhamDuocChon = false;
            List<String> sanPhamHopLe = new ArrayList<>();

            for (int i = 0; i < dataModelSP.getRowCount(); i++) {
                Boolean isChecked = (Boolean) dataModelSP.getValueAt(i, 4);
                if (isChecked != null && isChecked) {
                    String maSanPham = dataModelSP.getValueAt(i, 0).toString();
                    if (!spDao.laSanPhamCuaKhuyenMaiKhac(maSanPham)) {
                        coSanPhamDuocChon = true;
                        sanPhamHopLe.add(maSanPham); // Thêm vào danh sách nếu sản phẩm hợp lệ
                    } else {
                        JOptionPane.showMessageDialog(null, "Sản phẩm " + maSanPham + " đã có trong một khuyến mãi khác.");
                        return;
                    }
                }
            }
            if (!coSanPhamDuocChon) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm cho khuyến mãi.");
                return;
            }
            // Nếu tất cả các sản phẩm được chọn đều hợp lệ, thêm khuyến mãi
            kmDao.themKhuyenMai(new KhuyenMai(maKM, tenKM, moTa, ngayBatDau, ngayKetThuc, trangThai));
            // Thêm chi tiết khuyến mãi cho mỗi sản phẩm hợp lệ
            for (String maSanPham : sanPhamHopLe) {
                ctkmDao.themChiTietKhuyenMai(maKM, maSanPham, (float) mucGiam);
            }
            taiSanPhamVaDanhDauKhuyenMai("Quần");
            updateTableKM();
            xoaRong();
            JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!!");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
       
    }//GEN-LAST:event_btnThem10ActionPerformed

    private void btnXoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKMActionPerformed
        try {
            int selectedRow = tblKM.getSelectedRow();
            if (selectedRow >= 0) {
                String maKM = tblKM.getModel().getValueAt(selectedRow, 0).toString();
                boolean isDeletedCTKM = ctkmDao.delete(maKM);
                boolean isDeleted = kmDao.delete(maKM);
                if (isDeletedCTKM && isDeleted) {
                    taiSanPhamVaDanhDauKhuyenMai("Quần");
                    updateTableKM();
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    xoaRong();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa không thành công. Vui lòng thử lại.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        try {
            if (!validateInput()) {
                return;
            }
            String trangThai = null;
            String maKM = txtMaKM.getText();
            String tenKM = txtTenKM.getText();
            String moTa = txtTextArea.getText();
            float giaGiam = Float.parseFloat(txtMucGiam.getText());
            Date ngayBatDau = txtDateTruoc.getDate();
            Date ngayKetThuc = txtDateSau.getDate();
            Date ngayHienTai = new Date();
            // Xử lý để làm điều kiện 
            String strNgayBatDau = dateFormatter.format(ngayBatDau);
            String strNgayHienTai = dateFormatter.format(ngayHienTai);
            try {
                // Chuyển đổi String trở lại thành Date để so sánh
                ngayBatDau = dateFormatter.parse(strNgayBatDau);
                ngayHienTai = dateFormatter.parse(strNgayHienTai);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (ngayBatDau.before(ngayHienTai)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được trước ngày hiện tại");
                return;
            }
            if (ngayHienTai.before(ngayBatDau)) {
                trangThai = "upcoming";
            } else if (!ngayHienTai.before(ngayBatDau) && !ngayHienTai.after(ngayKetThuc)) {
                trangThai = "active";
            } else {
                trangThai = "off";
            }
            // Thêm sản phẩm cho khuyến mãi
            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                Boolean isChecked = (Boolean) tblSanPham.getValueAt(i, 4); // Cột "Chọn"
                String maSP = tblSanPham.getValueAt(i, 0).toString();
                // Kiểm tra nếu sản phẩm đang được chọn
                if (isChecked != null && isChecked) {
                    // Kiểm tra nếu sản phẩm không thuộc về khuyến mãi khác
                    if (!spDao.laSanPhamCuaKhuyenMaiKhac(maSP)) {
                        if (!spDao.kTraSPTonTaiTrongKM(maSP, maKM)) {
                            ctkmDao.themChiTietKhuyenMai(maKM, maSP, giaGiam);
                            tblSanPham.setValueAt("active", i, 5);
                            dataModelSP.setValueAt(false, i, 4);
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm cho khuyến mãi thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Sản phẩm " + maSP + " đã nằm trong khuyến mãi");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sản phẩm " + maSP + " đã nằm trong khuyến mãi khác");
                        return;
                    }
                }
            }
            // Cập nhật thông tin khuyến mãi
            boolean capNhatKM = kmDao.capNhatKhuyenMai(maKM, tenKM, moTa, ngayBatDau, ngayKetThuc, trangThai);
            boolean capNhatCTKM = ctkmDao.capNhatCTKhuyenMai(maKM, giaGiam);
            if (!capNhatKM && !capNhatCTKM) {
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin khuyến mãi thất bại.");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Thông tin khuyến mãi đã được cập nhật.");
                updateTableKM();
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        try {
            String selectedLoai = cboLoaiSP.getSelectedItem().toString();
            taiSanPhamVaDanhDauKhuyenMai(selectedLoai);
        } catch (RemoteException ex) {
            // Xử lý ngoại lệ ở đây, ví dụ:
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        xoaRong();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    public void taiSanPhamVaDanhDauKhuyenMai(String loaiSPFilter) throws java.rmi.RemoteException {
        List<SanPham> danhSachSanPham = spDao.docTuBangSPKM();
        dataModelSP.setRowCount(0);
        for (SanPham sp : danhSachSanPham) {
            String loaiSPStr = sp.getLoaiSP().getTenLoai();
            if (loaiSPStr.equalsIgnoreCase(loaiSPFilter)) {
                String trangThaiKM = ctkmDao.kiemTraTrangThaiKhuyenMaiCuaSanPham(sp.getMaSP());
                Boolean daCoKhuyenMai = trangThaiKM != null;
                Boolean isChecked = false;
                String hienThiTrangThai = daCoKhuyenMai ? trangThaiKM : "Không có khuyến mãi";
                dataModelSP.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP().getTenLoai(), sp.getGiaBan(), isChecked, hienThiTrangThai});
            }
        }
    }

    private void xoaRong() {
        txtMaKM.setText("");
        txtTextArea.setText("");
        txtMucGiam.setText("");
        txtTenKM.setText("");
    }

    private boolean validateInput() {
        // Kiểm tra txtMaKM và txtTextArea không được rỗng
        if (txtMaKM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã khuyến mãi không được rỗng.");
            return false;
        }
        if (!txtMaKM.getText().matches("KM\\d+")) {
            JOptionPane.showMessageDialog(null, "Mã khuyến mãi phải có định dạng 'KM' theo sau là số.");
            return false;
        }
        if (txtTenKM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên khuyến mãi không được để rỗng");
            return false;
        }
        if (txtTextArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mô tả không được rỗng.");
            return false;
        }

        if (txtMucGiam.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mức giảm không được rỗng.");
            return false;
        }

        try {
            double mucGiam = Double.parseDouble(txtMucGiam.getText().trim());
            if (mucGiam < 0) {
                JOptionPane.showMessageDialog(null, "Mức giảm phải là số không âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Mức giảm phải là số.");
            return false;
        }

        // Kiểm tra txtDateTruoc phải trước txtDateSau
        if (txtDateTruoc.getDate() == null || txtDateSau.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu và ngày kết thúc không được rỗng.");
            return false;
        }

        // Kiểm tra xem ngày bắt đầu có trước ngày kết thúc không
        if (!txtDateTruoc.getDate().before(txtDateSau.getDate())) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước ngày kết thúc.");
            return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnThem10;
    private javax.swing.JButton btnXoaKM;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChiNCC10;
    private javax.swing.JLabel lblDiaChiNCC11;
    private javax.swing.JLabel lblQuanLySanPham;
    private javax.swing.JLabel lblSdtNCC12;
    private javax.swing.JLabel lblTenNCC10;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JTable tblKM;
    private javax.swing.JTable tblSanPham;
    private com.toedter.calendar.JDateChooser txtDateSau;
    private com.toedter.calendar.JDateChooser txtDateTruoc;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMucGiam;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextArea txtTextArea;
    // End of variables declaration//GEN-END:variables
}
