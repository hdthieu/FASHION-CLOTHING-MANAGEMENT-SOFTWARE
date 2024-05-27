/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietHoaDonDao;
import dao.HoaDon_DAO;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;

import com.toedter.calendar.JCalendar;
import dao.ThongKe_DAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.poi.ss.util.CellRangeAddress;
import url.RmiConfig;

/**
 *
 * @author TriHieu
 */
public class GUI_ThongKe extends javax.swing.JPanel {

    private boolean isFiltered = false;
    private boolean isFilteredHD = false;
    private boolean isFilteredDSBH = false;
    private boolean isFilteredKH = false;
    private String patternCurrency = "###,###.###VND";
    private DecimalFormat dcfCurrency = new DecimalFormat(patternCurrency);
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DefaultTableModel model;
    private ChiTietHoaDonDao ctHDDao;
//    private List<ChiTietHoaDon> tKSPList = ctHDDao.tKsanPham();
    private List<ChiTietHoaDon> filteredData; // lọc create chart()
//    private List<ChiTietHoaDon> tKHDList = ctHDDao.tKHDlenBang();
    private List<ChiTietHoaDon> filteredDataHD; // lọc create chart()
//    private List<ChiTietHoaDon> tkKHList = ctHDDao.docDLieuTKeKH();
    private List<ChiTietHoaDon> filteredDataKH;
//    private List<ChiTietHoaDon> tKDSBHList = ctHDDao.tKDSBHlenBang();
    private List<ChiTietHoaDon> filterDataDSBH;
    private DefaultCategoryDataset datasetSP = new DefaultCategoryDataset();
    private DefaultCategoryDataset datasetHD = new DefaultCategoryDataset();
    private DefaultCategoryDataset datasetDSBH = new DefaultCategoryDataset();
    private DefaultCategoryDataset datasetKH = new DefaultCategoryDataset();
    DefaultTableModel model1 = new DefaultTableModel(); // model Của table Sản Phẩm
    DefaultTableModel model2 = new DefaultTableModel(); // model Của table Hóa Đơn
    DefaultTableModel model3 = new DefaultTableModel(); // model Của table Hóa Đơn
    DefaultTableModel model4 = new DefaultTableModel(); // model Của table Doanh Số Của Nhân Viên
    private int tM = 0;

    private static final String URL = RmiConfig.RMI_URL;

    HoaDon_DAO hdDao;
    ThongKe_DAO tkeDao;
    

    public GUI_ThongKe() throws java.rmi.RemoteException {

        try {
            ctHDDao = (ChiTietHoaDonDao) Naming.lookup(URL + "ChiTietHoaDon");
            hdDao = (HoaDon_DAO) Naming.lookup(URL + "HoaDon");
            tkeDao= (ThongKe_DAO) Naming.lookup(URL+"tkeDao");
            
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<ChiTietHoaDon> tKSPList = ctHDDao.tKsanPham();
//        List<ChiTietHoaDon> filteredData; // lọc create chart()
        List<ChiTietHoaDon> tKHDList = ctHDDao.tKHDlenBang();
//        List<ChiTietHoaDon> filteredDataHD; // lọc create chart()
        List<ChiTietHoaDon> tkKHList = ctHDDao.docDLieuTKeKH();
//        List<ChiTietHoaDon> filteredDataKH;
        List<ChiTietHoaDon> tKDSBHList = ctHDDao.tKDSBHlenBang();
        initComponents();
        enableTxt();

        // table của thống kê Sản Phẩm
        model1.addColumn("Mã Sản Phẩm");
        model1.addColumn("Tên Sản Phẩm");
        model1.addColumn("Tên Loại Sản Phẩm");
        model1.addColumn("Ngày Tạo");
        model1.addColumn("Số Lượng Bán");
        model1.addColumn("Doanh Thu");
        tblTKeSP.setModel(model1);

        // table của thống kê Hóa Đơn
        model2.addColumn("Mã Hóa Đơn");
        model2.addColumn("Tên Nhân Viên");
        model2.addColumn("Tên Khách Hàng");
        model2.addColumn("Ngày Tạo");
        model2.addColumn("Doanh Thu");
        tblTKHD.setModel(model2);

        // table của thống kê Khách Hàng
        model3.addColumn("Mã Khách Hàng");
        model3.addColumn("Tên Khách Hàng");
        model3.addColumn("Giới Tính");
        model3.addColumn("Lượt Mua");
        model3.addColumn("Tiền Đã Mua");
        model3.addColumn("Ngày Mua");
        tblTKKH.setModel(model3);

        // table của thống kê Doanh Số Bán Hàng
        model4.addColumn("Mã Nhân Viên");
        model4.addColumn("Tên Nhân Viên");
        model4.addColumn("Giới Tính");
        model4.addColumn("Số Lượng SP Bán");
        model4.addColumn("Doanh Số Bán Hàng");
        model4.addColumn("Ngày Bán");
        tblTKDSBH.setModel(model4);

        // Tabbed SPham
        updateTableSP(tKSPList);
        updateTxtDThu(0, model1.getRowCount(), txtTongDThu, tblTKeSP, 5);
        updateSPBanChay();

        // Tabbed Hóa Đơn
        updateTableHD(tKHDList);
        rdoSLHDTNgay.setSelected(true);
        createChartHD();
        updateTxtDThu(0, model2.getRowCount(), txtDThuHD, tblTKHD, 4);

        // Tabbed Khách Hàng
        updateTableKH(tkKHList);
        rdoSLSPKHMua.setSelected(true);
        createChartKH();
        updateTxtDThu(0, model3.getRowCount(), txtTTienKH, tblTKKH, 4);
        updateKHMuaNhieuNhat(model3.getRowCount());
        updateTxtSLMuaCuaKHvatxtSLuotBanDSBHNV(txtTongMua, tblTKKH, 3, model3.getRowCount());

        // Tabbed Doanh Số Bán Hàng Của Nhân Viên
        updateTableDSBHCuaNV(tKDSBHList);
        updateTxtDThu(0, model4.getRowCount(), txtDoanhSo, tblTKDSBH, 4);
        updateNVienCoDSBHNhieu();
//        updateTxtSLMuaCuaKHvatxtSLuotBanDSBHNV(txtTSLuotBan, tblTKDSBH, 3, model4.getRowCount());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnGroupDSBH = new javax.swing.ButtonGroup();
        btnGrHD = new javax.swing.ButtonGroup();
        btnGRKH = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnTKSP = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTKeSP = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblTKSP = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDateTruoc = new com.toedter.calendar.JDateChooser();
        txtDateSau = new com.toedter.calendar.JDateChooser();
        btnGuiBaoCao = new javax.swing.JButton();
        btnXuatFileSP = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTongSP = new javax.swing.JTextField();
        txtTongDThu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTKeSP = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSPBC = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnTKeHD = new javax.swing.JButton();
        btnXuatExHD = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDateHDTruoc = new com.toedter.calendar.JDateChooser();
        txtDateHDSau = new com.toedter.calendar.JDateChooser();
        btnGuiBCaoHD = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTKHD = new javax.swing.JTable();
        txtTongHD = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDThuHD = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        rdoSLHDTNgay = new javax.swing.JRadioButton();
        rdoDTTHD = new javax.swing.JRadioButton();
        pnlTKHD = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnExKH = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtDateKHSau = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtDateKHT = new com.toedter.calendar.JDateChooser();
        btnTKeKH = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        btnGuiBCaoKH = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        txtTongMua = new javax.swing.JTextField();
        txtTTienKH = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTKKH = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtKHMuaNhieu = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        pnlBDTKKH = new javax.swing.JPanel();
        rdoSLSPKHMua = new javax.swing.JRadioButton();
        rdoDTTuKH = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        btnXuatExDSBH = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtDateDSBHT = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        txtDateDSBHS = new com.toedter.calendar.JDateChooser();
        btnTKeDSBH = new javax.swing.JButton();
        btnGuiBCaoDSBH = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTKDSBH = new javax.swing.JTable();
        txtDoanhSo = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        lblNVBHNhieuNhat = new javax.swing.JLabel();
        txtDSBHNN = new javax.swing.JTextField();
        lblNVBHNhieuNhat1 = new javax.swing.JLabel();

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Gửi Báo Cáo Sơ Bộ");

        btnTKeSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKeSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKeSP.setText("Thống Kê");
        btnTKeSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeSPActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTKSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTKSP.setText("Thống Kê Sản Phẩm");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Khoảng Thời Gian");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Từ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Đến");

        btnGuiBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuiBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/send_report.png"))); // NOI18N
        btnGuiBaoCao.setText("Gửi Báo Cáo");
        btnGuiBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBaoCaoActionPerformed(evt);
            }
        });

        btnXuatFileSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatFileSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnXuatFileSP.setText("File Excel");
        btnXuatFileSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileSPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Xuất File Excel của Thống Kê");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(90, 90, 90)))
                .addComponent(btnTKeSP)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTKSP)
                        .addGap(446, 446, 446))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(177, 177, 177))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnXuatFileSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuiBaoCao)
                        .addGap(153, 153, 153))))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 598, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 597, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblTKSP)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatFileSP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuiBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnTKeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 99, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 114, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 114, Short.MAX_VALUE)))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setText("Sản Phẩm Bán Chạy Nhất");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Tổng Số Sản Phẩm");

        txtTongSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongSP.setFocusCycleRoot(true);
        txtTongSP.setFocusTraversalPolicyProvider(true);

        txtTongDThu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Tổng Doanh Thu");

        tblTKeSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Ngày Bán", "Số Lượng SP Đã Bán", "Doanh Thu"
            }
        ));
        jScrollPane2.setViewportView(tblTKeSP);

        txtSPBC.setColumns(20);
        txtSPBC.setRows(5);
        jScrollPane1.setViewportView(txtSPBC);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongDThu, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtTongSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel16)
                                .addGap(77, 77, 77))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTongDThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel14)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bảng Thống Kê", jPanel1);

        javax.swing.GroupLayout pnTKSPLayout = new javax.swing.GroupLayout(pnTKSP);
        pnTKSP.setLayout(pnTKSPLayout);
        pnTKSPLayout.setHorizontalGroup(
            pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        pnTKSPLayout.setVerticalGroup(
            pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", pnTKSP);

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Xuất File Excel của Thống Kê");

        btnTKeHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKeHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKeHD.setText("Thống Kê");
        btnTKeHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeHDActionPerformed(evt);
            }
        });

        btnXuatExHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatExHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnXuatExHD.setText("File Excel");
        btnXuatExHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExHDActionPerformed(evt);
            }
        });

        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Thống Kê Hóa Đơn");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Khoảng Thời Gian");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Từ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Đến");

        btnGuiBCaoHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuiBCaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/send_report.png"))); // NOI18N
        btnGuiBCaoHD.setText("Gửi Báo Cáo");
        btnGuiBCaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBCaoHDActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Gửi Báo Cáo Sơ Bộ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateHDTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateHDSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(btnTKeHD))
                    .addComponent(jLabel18))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(337, 337, 337))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(btnXuatExHD)
                                .addGap(23, 23, 23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel5))
                            .addComponent(btnGuiBCaoHD))
                        .addGap(96, 96, 96))))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 596, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 596, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(txtDateHDTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(txtDateHDSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatExHD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuiBCaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(273, 273, 273))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnTKeHD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(299, 299, 299))))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 215, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 215, Short.MAX_VALUE)))
        );

        tblTKHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tên Nhân Viên", "Tên Khách Hàng", "Ngày Tạo", "Doanh Thu"
            }
        ));
        jScrollPane3.setViewportView(tblTKHD);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Tổng Hóa Đơn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng Doanh Thu");

        txtDThuHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDThuHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel21))
                .addGap(65, 65, 65)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongHD, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtDThuHD))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDThuHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jTabbedPane4.addTab("Bảng Thống Kê", jPanel12);

        btnGrHD.add(rdoSLHDTNgay);
        rdoSLHDTNgay.setText("Biểu Đồ SL Hóa Đơn Theo Ngày");
        rdoSLHDTNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSLHDTNgayActionPerformed(evt);
            }
        });

        btnGrHD.add(rdoDTTHD);
        rdoDTTHD.setText("Biểu Đồ Doanh Thu Của Từng Hóa Đơn");
        rdoDTTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDTTHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTKHDLayout = new javax.swing.GroupLayout(pnlTKHD);
        pnlTKHD.setLayout(pnlTKHDLayout);
        pnlTKHDLayout.setHorizontalGroup(
            pnlTKHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTKHDLayout.setVerticalGroup(
            pnlTKHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(rdoSLHDTNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(rdoDTTHD)
                .addGap(312, 312, 312))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(pnlTKHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDTTHD)
                    .addComponent(rdoSLHDTNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTKHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Biểu Đồ", jPanel13);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel2);

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Xuất File Excel của Thống Kê");

        btnExKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnExKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnExKH.setText("File Excel");
        btnExKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExKHActionPerformed(evt);
            }
        });

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Thống Kê Khách Hàng");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setText("Từ");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel34.setText("Đến");

        btnTKeKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKeKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKeKH.setText("Thống Kê");
        btnTKeKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeKHActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel35.setText("Khoảng Thời Gian");

        btnGuiBCaoKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuiBCaoKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/send_report.png"))); // NOI18N
        btnGuiBCaoKH.setText("Gửi Báo Cáo");
        btnGuiBCaoKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBCaoKHActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Gửi Báo Cáo Sơ Bộ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(413, 413, 413))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(52, 52, 52)
                        .addComponent(txtDateKHSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel35)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(62, 62, 62)
                            .addComponent(txtDateKHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnTKeKH)
                        .addGap(169, 169, 169)
                        .addComponent(btnExKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(128, 128, 128)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11))
                    .addComponent(btnGuiBCaoKH))
                .addGap(96, 96, 96))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 599, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 599, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(txtDateKHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34))
                            .addComponent(txtDateKHSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 82, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnExKH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGuiBCaoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnTKeKH)
                                .addGap(96, 96, 96))))))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 119, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 118, Short.MAX_VALUE)))
        );

        jLabel51.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel51.setText("Tổng Lượt Mua");

        tblTKKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "GIới Tính", "Tổng Lượt Mua", "Tổng Tiền Đã Mua", "Ngày Mua"
            }
        ));
        jScrollPane4.setViewportView(tblTKKH);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel29.setText("Khách Hàng Mua Nhiều Nhất");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel28.setText("Tổng Tiền ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTongMua, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(138, 138, 138)
                                .addComponent(txtTTienKH))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(txtKHMuaNhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtTongMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtKHMuaNhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtTTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Bảng Thống Kê", jPanel16);

        javax.swing.GroupLayout pnlBDTKKHLayout = new javax.swing.GroupLayout(pnlBDTKKH);
        pnlBDTKKH.setLayout(pnlBDTKKHLayout);
        pnlBDTKKHLayout.setHorizontalGroup(
            pnlBDTKKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBDTKKHLayout.setVerticalGroup(
            pnlBDTKKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        btnGRKH.add(rdoSLSPKHMua);
        rdoSLSPKHMua.setText("Tổng Số Sản Phẩm Mua Của Khách Hàng");
        rdoSLSPKHMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSLSPKHMuaActionPerformed(evt);
            }
        });

        btnGRKH.add(rdoDTTuKH);
        rdoDTTuKH.setText("Tổng Tiền Khách Hàng Đã Chi ");
        rdoDTTuKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDTTuKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBDTKKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(rdoSLSPKHMua)
                .addGap(118, 118, 118)
                .addComponent(rdoDTTuKH, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoSLSPKHMua)
                    .addComponent(rdoDTTuKH))
                .addGap(18, 18, 18)
                .addComponent(pnlBDTKKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane6.addTab("Biểu Đồ", jPanel17);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane6)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Khách Hàng", jPanel3);

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setText("Xuất File Excel của Thống Kê");

        btnXuatExDSBH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatExDSBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnXuatExDSBH.setText("File Excel");
        btnXuatExDSBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExDSBHActionPerformed(evt);
            }
        });

        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Thống Kê Doanh Số Bán Hàng Của Nhân Viên");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Khoảng Thời Gian");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Từ");

        txtDateDSBHT.setDateFormatString("MMMMM d, yyyy\n");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText("Đến");

        btnTKeDSBH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKeDSBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKeDSBH.setText("Thống Kê");
        btnTKeDSBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeDSBHActionPerformed(evt);
            }
        });

        btnGuiBCaoDSBH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuiBCaoDSBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/send_report.png"))); // NOI18N
        btnGuiBCaoDSBH.setText("Gửi Báo Cáo");
        btnGuiBCaoDSBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBCaoDSBHActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Gửi Báo Cáo Sơ Bộ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(357, 357, 357))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateDSBHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateDSBHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(btnTKeDSBH)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnXuatExDSBH)))
                .addGap(110, 110, 110)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel24))
                    .addComponent(btnGuiBCaoDSBH))
                .addGap(117, 117, 117))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 604, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 605, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(43, 43, 43)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(txtDateDSBHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(txtDateDSBHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnTKeDSBH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addGap(18, 18, 18)
                            .addComponent(btnGuiBCaoDSBH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(18, 18, 18)
                            .addComponent(btnXuatExDSBH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 119, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 119, Short.MAX_VALUE)))
        );

        tblTKDSBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Số Lượng SP Bán", "Doanh Số Bán Hàng", "Ngày Bán"
            }
        ));
        jScrollPane5.setViewportView(tblTKDSBH);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel38.setText("Tổng Doanh Thu ");

        lblNVBHNhieuNhat.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblNVBHNhieuNhat.setText("Nhân Viên Có Doanh Thu");

        lblNVBHNhieuNhat1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblNVBHNhieuNhat1.setText("Bán Hàng Cao Nhất");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNVBHNhieuNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(lblNVBHNhieuNhat1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDSBHNN, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(lblNVBHNhieuNhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNVBHNhieuNhat1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(txtDSBHNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bảng Thống Kê", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Doanh Số Bán Hàng Của Nhân Viên", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enableTxt() {
        txtTongSP.setEditable(false);
        txtTongDThu.setEditable(false);
        txtSPBC.setEditable(false);
        txtTongMua.setEditable(false);
        txtTTienKH.setEditable(false);
        txtKHMuaNhieu.setEditable(false);
//        txtTSLuotBan.setEditable(false);
        txtDoanhSo.setEditable(false);
        txtTongHD.setEditable(false);
        txtDThuHD.setEditable(false);
        txtDSBHNN.setEditable(false);
    }

    private void btnGuiBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBaoCaoActionPerformed
        String spBanChay = txtSPBC.getText();
        String tongSP = txtTongSP.getText();
        String tongDoanhThu = txtTongDThu.getText();

        // Tạo nội dung email
        String emailContent = "<br>     Sản phẩm bán chạy: " + spBanChay + "<br>"
                + "<br>     Tổng số sản phẩm: " + tongSP + "<br>"
                + "<br> ============================ <br>";
        for (int i = 0; i < tblTKeSP.getRowCount(); i++) {
            String tenSP = tblTKeSP.getValueAt(i, 1).toString();
            String doanhThu = tblTKeSP.getValueAt(i, 5).toString();
            emailContent += "<br> Sản phẩm: " + tenSP
                    + ", Doanh thu: " + doanhThu + "<br>";
        }

        emailContent += "<br> ============================ <br>"
                + "<br>     Tổng doanh thu: " + tongDoanhThu;

        sendEmail(emailContent, "Gửi báo cáo sản phẩm");
    }//GEN-LAST:event_btnGuiBaoCaoActionPerformed

    private void btnTKeSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeSPActionPerformed

        try {
            Date selectedDateTruoc = txtDateTruoc.getDate();
            Date selectedDateSau = txtDateSau.getDate();
            java.sql.Date sqlDateTruoc = new java.sql.Date(selectedDateTruoc.getTime());
            java.sql.Date sqlDateSau = new java.sql.Date(selectedDateSau.getTime());
            // Tính tổng số lượng sản phẩm trong khoảng thời gian
            long tongSoLuong = ctHDDao.tinhTongSoLuongBanTrongKhoangThoiGian(sqlDateTruoc, sqlDateSau);
            txtTongSP.setText(String.valueOf(tongSoLuong));
            
            // Tính tổng doanh thu trong khoảng thời gian
            String tongDoanhThu = ctHDDao.tinhTongDoanhThuTrongKhoangThoiGian(sqlDateTruoc, sqlDateSau);
            txtTongDThu.setText(tongDoanhThu);
            // Tìm và hiển thị tên sản phẩm bán chạy nhất trong khoản thời gian
            List<String> tenSPBanChay = ctHDDao.findBestSellingProductNames(sqlDateTruoc, sqlDateSau);
            StringBuilder spBanChayBuilder = new StringBuilder();
            for (String tenSP : tenSPBanChay) {
                spBanChayBuilder.append(tenSP).append("\n");
            }
            txtSPBC.setText(spBanChayBuilder.toString());
            List<Object[]> spList = ctHDDao.getSPbyDate(sqlDateTruoc, sqlDateSau);
            // Đổ dữ liệu từ danh sách vào bảng
            model1 = (DefaultTableModel) tblTKeSP.getModel(); 
            model1.setRowCount(0);
            for (Object[] sp : spList) {
                model1.addRow(sp);
            }
            JOptionPane.showMessageDialog(null, "Thống kê thành công");
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTKeSPActionPerformed

    // btn thống kê Hóa Đơn
    private void btnTKeHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeHDActionPerformed
        try {
            Date selectedDateHDTruoc = txtDateHDTruoc.getDate();
            Date selectedDateHDSau = txtDateHDSau.getDate();
            java.sql.Date sqlDateHDTruoc = new java.sql.Date(selectedDateHDTruoc.getTime());
            java.sql.Date sqlDateHDSau = new java.sql.Date(selectedDateHDSau.getTime());
            List<ChiTietHoaDon> tKHD = ctHDDao.locDateTKHD(sqlDateHDTruoc, sqlDateHDSau);
            if (!tKHD.isEmpty()) {
                model2 = (DefaultTableModel) tblTKHD.getModel();
                model2.setRowCount(0);
                updateTableHD(tKHD);
                isFilteredHD = true;
                filteredDataHD = ctHDDao.locDateTKHD(sqlDateHDTruoc, sqlDateHDSau);
                createChartHD();
                updateTxtDThu(0, model2.getRowCount(), txtDThuHD, tblTKHD, 4);
                JOptionPane.showMessageDialog(null, "Thống Kê Thành Công");

            } else {
                JOptionPane.showMessageDialog(null, "Thống Kê Không Thành Công");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTKeHDActionPerformed

    private void btnTKeDSBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeDSBHActionPerformed
        try {
            Date selectedDateDSBHTruoc = txtDateDSBHT.getDate();
            Date selectedDateDSBHSau = txtDateDSBHS.getDate();
            java.sql.Date sqlDateDSBHTruoc = new java.sql.Date(selectedDateDSBHTruoc.getTime());
            java.sql.Date sqlDateDSBHSau = new java.sql.Date(selectedDateDSBHSau.getTime());
            List<ChiTietHoaDon> tKDSBH = ctHDDao.locDLieuTKeNhanVien(sqlDateDSBHTruoc, sqlDateDSBHSau);
            model4 = (DefaultTableModel) tblTKDSBH.getModel();
            model4.setRowCount(0);
            updateTableDSBHCuaNV(tKDSBH);
                   // đổ vô txt
          Map<String, Double> employeesWithMaxRevenue = tkeDao.findNhanVienWithMaxDoanhThuMap(sqlDateDSBHTruoc, sqlDateDSBHSau);
                StringBuilder tenNhanVien = new StringBuilder();
                Double maxDoanhThu = 0.0;
                for (Map.Entry<String, Double> entry : employeesWithMaxRevenue.entrySet()) {
                    String nhanVien = entry.getKey();
                    Double doanhThu = entry.getValue();
                    if (doanhThu > maxDoanhThu) {
                        maxDoanhThu = doanhThu;
                        tenNhanVien = new StringBuilder(nhanVien);
                    } else if (doanhThu.equals(maxDoanhThu)) {
                        tenNhanVien.append(", ").append(nhanVien);
                    }
                }       
                DecimalFormat df = new DecimalFormat("#,###.### VNĐ");
                String formattedDoanhThu = df.format(maxDoanhThu);
                txtDSBHNN.setText(tenNhanVien.toString());
                txtDoanhSo.setText(formattedDoanhThu);
             
            JOptionPane.showMessageDialog(null, "Thống Kê Thành Công");    
            
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTKeDSBHActionPerformed

    private void btnXuatExHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExHDActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String excelFilePath = fileToSave.getAbsolutePath();
            if (!excelFilePath.endsWith(".xlsx")) {
                excelFilePath += ".xlsx";
            } else {
                JOptionPane.showMessageDialog(null, "Phải là tệp excel (.xlsx)");
            }
            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(excelFilePath);
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Thống Kê Hóa Đơn");
                CellStyle titleStyle = fontText(sheet);
                CellStyle headerStyle = createStyleForHeader(sheet);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                Row rowTuaDe = sheet.createRow(1);
                Cell titleCell = rowTuaDe.createCell(0);
                titleCell.setCellValue("Doanh Sách Hóa Đơn");
                titleCell.setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, tblTKHD.getColumnCount() - 1));

                CellStyle itemStyle = createStyleForItem(sheet);
                Row rowTieuDe = sheet.createRow(3);
                for (int i = 0; i < tblTKHD.getColumnCount(); i++) {
                    Cell cell = rowTieuDe.createCell(i, CellType.STRING);
                    cell.setCellValue(tblTKHD.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }
                int doanhThuColumnIndex = 4;
                for (int rowtblHD = 0; rowtblHD < tblTKHD.getRowCount(); rowtblHD++) {
                    Row rowItem = sheet.createRow(4 + rowtblHD);
                    for (int coltblHD = 0; coltblHD < tblTKHD.getColumnCount(); coltblHD++) {
                        Cell cell = rowItem.createCell(coltblHD);
                        Object value = tblTKHD.getValueAt(rowtblHD, coltblHD);
                        if (coltblHD == doanhThuColumnIndex) {
                            String valueStr = String.valueOf(value).replaceAll("[,VND]", "").trim();
                            double valueDouble = Double.parseDouble(valueStr);
                            cell.setCellType(CellType.NUMERIC);
                            cell.setCellValue(valueDouble);
                        } else {
                            cell.setCellType(CellType.STRING);
                            cell.setCellValue(String.valueOf(value));
                        }
                        autosizeColumn(sheet, coltblHD);
                        cell.setCellStyle(itemStyle);
                    }
                }
                for (int i = 4; i < sheet.getLastRowNum(); i++) {       // Điều chỉnh kích thước của cột
                    int numberOfColumn = sheet.getRow(i).getPhysicalNumberOfCells();
                    autosizeColumn(sheet, numberOfColumn);
                }

                int lastRow = sheet.getLastRowNum() + 3;
                Row rowTongHD = sheet.createRow(lastRow++);
                Cell cellTongHD = rowTongHD.createCell(1, CellType.STRING);
                cellTongHD.setCellValue("Tổng Hóa Đơn:  ");
                double tongHDValue = Double.parseDouble(txtTongHD.getText().replaceAll(",", ""));
                Cell cellTongHDValue = rowTongHD.createCell(2, CellType.NUMERIC);
                cellTongHDValue.setCellValue(tongHDValue);

                Row rowTongDThu = sheet.createRow(lastRow++);
                Cell cellTongDthu = rowTongDThu.createCell(1, CellType.STRING);
                cellTongDthu.setCellValue("Tổng Doanh Thu:  ");
                double tongDThuValue = Double.parseDouble(txtDThuHD.getText().replaceAll(",", "").replace("VND", "").trim());
                Cell cellTongDThuValue = rowTongDThu.createCell(2, CellType.NUMERIC);
                cellTongDThuValue.setCellValue(tongDThuValue);

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                workbook.write(fileOut);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXuatExHDActionPerformed

    private void btnXuatExDSBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExDSBHActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String excelFilePath = fileToSave.getAbsolutePath();
            // Đảm bảo tệp có đuôi là .xlsx
            if (!excelFilePath.endsWith(".xlsx")) {
                excelFilePath += ".xlsx";
            } else {
                JOptionPane.showMessageDialog(null, "Phải là tệp excel (.xlsx)");
            }

            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(excelFilePath);
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Doanh số bán hàng");
                CellStyle titleStyle = fontText(sheet);
                CellStyle headerStyle = createStyleForHeader(sheet);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);

                Row rowTuaDe = sheet.createRow(1);
                Cell titleCell = rowTuaDe.createCell(0); // Start from the first cell
                titleCell.setCellValue("Doanh số bán hàng");
                titleCell.setCellStyle(titleStyle);

                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, tblTKDSBH.getColumnCount() - 1));

                CellStyle itemStyle = createStyleForItem(sheet);
                Row rowTieuDe = sheet.createRow(3);
                // Lấy tiêu đề 
                for (int i = 0; i < tblTKDSBH.getColumnCount(); i++) {
                    Cell cell = rowTieuDe.createCell(i, CellType.STRING);
                    cell.setCellValue(tblTKDSBH.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }
                // Lấy dữ liệu
                for (int rowtblSP = 0; rowtblSP < tblTKDSBH.getRowCount(); rowtblSP++) {
                    Row rowItem = sheet.createRow(4 + rowtblSP);
                    for (int coltblSP = 0; coltblSP < tblTKDSBH.getColumnCount(); coltblSP++) {
                        Cell cell1 = rowItem.createCell(coltblSP);
                        Object valueSP = tblTKDSBH.getValueAt(rowtblSP, coltblSP);
                        if (coltblSP == 4) {
                            String valueStr = String.valueOf(valueSP).replaceAll("[,VND]", "").trim();
                            double valueDouble = Double.parseDouble(valueStr);
                            cell1.setCellType(CellType.NUMERIC);
                            cell1.setCellValue(valueDouble);
                        } else if (coltblSP == 3) {
                            String valueStr = String.valueOf(valueSP).replaceAll("[^\\d.]", "").trim();
                            int valueSLBan = Integer.parseInt(valueStr);
                            cell1.setCellValue(valueSLBan);
                        } else {
                            cell1.setCellType(CellType.STRING);
                            cell1.setCellValue(String.valueOf(valueSP));
                        }
                        autosizeColumn(sheet, coltblSP);
                        cell1.setCellStyle(itemStyle);
                    }
                }
                for (int i = 4; i < sheet.getLastRowNum(); i++) {
                    int numberOfColumn = sheet.getRow(i).getPhysicalNumberOfCells();
                    autosizeColumn(sheet, numberOfColumn);
                }
                int lastRow = sheet.getLastRowNum() + 3;
                Row rowDoanhSo = sheet.createRow(lastRow++);
                Cell cellDSo = rowDoanhSo.createCell(1, CellType.STRING);
                cellDSo.setCellValue("Tổng Doanh Số ");
                Cell cellDSValue = rowDoanhSo.createCell(2, CellType.STRING);
                cellDSValue.setCellValue(txtDoanhSo.getText());
                Row rowTongLBan = sheet.createRow(lastRow++);
                Cell cellTongLBan = rowTongLBan.createCell(1, CellType.STRING);
                cellTongLBan.setCellValue("Tổng lượt Bán  ");
                Cell cellTongLBanValue = rowTongLBan.createCell(2, CellType.STRING);
                // In ra tổng số lượt bán
//                cellTongLBanValue.setCellValue(txtTSLuotBan.getText());
                Row rowTongNhanVien = sheet.createRow(lastRow++);
                Cell cellTongNV = rowTongNhanVien.createCell(1, CellType.STRING);
                cellTongNV.setCellValue("Tổng Nhân Viên:  ");
                Cell cellTongNVValue = rowTongNhanVien.createCell(2, CellType.STRING);
//                cellTongNVValue.setCellValue(txtTSLuotBan.getText());

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);

                workbook.write(fileOut);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXuatExDSBHActionPerformed

    private void btnXuatFileSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileSPActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String excelFilePath = fileToSave.getAbsolutePath();
            // Đảm bảo tệp có đuôi là .xlsx
            if (!excelFilePath.endsWith(".xlsx")) {
                excelFilePath += ".xlsx";
            } else {
                JOptionPane.showMessageDialog(null, "Phải là tệp excel (.xlsx)");
            }

            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(excelFilePath);
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Thống Kê Sản Phẩm");
                // Tạo style cho header
                CellStyle titleStyle = fontText(sheet);
                CellStyle headerStyle = createStyleForHeader(sheet);
                headerStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center
                // Tạo tựa đề và merge các ô vào cho vào giữa
                Row rowTuaDe = sheet.createRow(1);
                Cell titleCell = rowTuaDe.createCell(0); // Start from the first cell
                titleCell.setCellValue("Doanh Thu Sản Phẩm");
                titleCell.setCellStyle(titleStyle);

                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, tblTKeSP.getColumnCount() - 1));

                CellStyle itemStyle = createStyleForItem(sheet);
                Row rowTieuDe = sheet.createRow(3);
                for (int i = 0; i < tblTKeSP.getColumnCount(); i++) {
                    Cell cell = rowTieuDe.createCell(i, CellType.STRING);
                    cell.setCellValue(tblTKeSP.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }
                int doanhThuColumnIndexSP = 5;
                int soLgBanColumnIndexSP = 4;

                for (int rowtblSP = 0; rowtblSP < tblTKeSP.getRowCount(); rowtblSP++) {
                    Row rowItem = sheet.createRow(4 + rowtblSP);
                    for (int coltblSP = 0; coltblSP < tblTKeSP.getColumnCount(); coltblSP++) {
                        Cell cell1 = rowItem.createCell(coltblSP);
                        Object valueSP = tblTKeSP.getValueAt(rowtblSP, coltblSP);
                        if (coltblSP == doanhThuColumnIndexSP) {
                            String valueStr = String.valueOf(valueSP).replaceAll("[,VND]", "").trim();
                            double valueDouble = Double.parseDouble(valueStr);
                            cell1.setCellType(CellType.NUMERIC);

                            cell1.setCellValue(valueDouble);
                        } else if (coltblSP == soLgBanColumnIndexSP) {
                            int valueSLBan = Integer.parseInt(String.valueOf(valueSP));
                            cell1.setCellValue(valueSLBan);
                        } else {
                            cell1.setCellType(CellType.STRING);
                            cell1.setCellValue(String.valueOf(valueSP));
                        }
                        autosizeColumn(sheet, coltblSP);
                        cell1.setCellStyle(itemStyle);
                    }
                }
                for (int i = 4; i < sheet.getLastRowNum(); i++) {       // Điều chỉnh kích thước của cột
                    int numberOfColumn = sheet.getRow(i).getPhysicalNumberOfCells();
                    autosizeColumn(sheet, numberOfColumn);
                }
                System.out.println(sheet.getLastRowNum());
                int lastRow = sheet.getLastRowNum() + 3;
                System.out.println(lastRow);
                Row rowDoanhSo = sheet.createRow(lastRow++);
                Cell cellDSo = rowDoanhSo.createCell(1, CellType.STRING);
                cellDSo.setCellValue("Tổng Sản Phẩm ");
                Cell cellDSValue = rowDoanhSo.createCell(2, CellType.STRING);
                cellDSValue.setCellValue(txtTongSP.getText());

                Row rowTongLBan = sheet.createRow(lastRow++);
                Cell cellTongLBan = rowTongLBan.createCell(1, CellType.STRING);
                cellTongLBan.setCellValue("Sản Phẩm Bán Chạy Nhất  ");
                Cell cellTongLBanValue = rowTongLBan.createCell(2, CellType.STRING);
                cellTongLBanValue.setCellValue(txtSPBC.getText());

                Row rowTongNhanVien = sheet.createRow(lastRow++);
                Cell cellTongNV = rowTongNhanVien.createCell(1, CellType.STRING);
                cellTongNV.setCellValue("Tổng Doanh Thu:  ");
                Cell cellTongNVValue = rowTongNhanVien.createCell(2, CellType.STRING);
                cellTongNVValue.setCellValue(txtTongDThu.getText());

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);

                workbook.write(fileOut);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXuatFileSPActionPerformed

    private void txtDThuHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDThuHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDThuHDActionPerformed

    private void rdoSLHDTNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSLHDTNgayActionPerformed
        try {
            createChartHD();
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdoSLHDTNgayActionPerformed

    private void rdoDTTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDTTHDActionPerformed
        try {
            createChartHD();
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdoDTTHDActionPerformed

    private void rdoSLSPKHMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSLSPKHMuaActionPerformed
        try {
            createChartKH();
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdoSLSPKHMuaActionPerformed

    private void rdoDTTuKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDTTuKHActionPerformed
        try {
            createChartKH();
        } catch (RemoteException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdoDTTuKHActionPerformed

    private void btnExKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExKHActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String excelFilePath = fileToSave.getAbsolutePath();
            // Đảm bảo tệp có đuôi là .xlsx
            if (!excelFilePath.endsWith(".xlsx")) {
                excelFilePath += ".xlsx";
            } else {
                JOptionPane.showMessageDialog(null, "Phải là tệp excel (.xlsx)");
            }

            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(excelFilePath);
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Thống Kê Khách Hàng");
                CellStyle titleStyle = fontText(sheet);
                CellStyle headerStyle = createStyleForHeader(sheet);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);

                Row rowTuaDe = sheet.createRow(1);
                Cell titleCell = rowTuaDe.createCell(0); // Start from the first cell
                titleCell.setCellValue("Thống Kê Khách Hàng");
                titleCell.setCellStyle(titleStyle);

                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, tblTKDSBH.getColumnCount() - 1));

                CellStyle itemStyle = createStyleForItem(sheet);
                Row rowTieuDe = sheet.createRow(3);
                // Lấy tiêu đề 
                for (int i = 0; i < tblTKKH.getColumnCount(); i++) {
                    Cell cell = rowTieuDe.createCell(i, CellType.STRING);
                    cell.setCellValue(tblTKKH.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }
                // Lấy dữ liệu
                for (int rowtblKH = 0; rowtblKH < tblTKKH.getRowCount(); rowtblKH++) {
                    Row rowItem = sheet.createRow(4 + rowtblKH);
                    for (int coltblKH = 0; coltblKH < tblTKKH.getColumnCount(); coltblKH++) {
                        Cell cell1 = rowItem.createCell(coltblKH);
                        Object valueKH = tblTKKH.getValueAt(rowtblKH, coltblKH);
                        if (coltblKH == 4) {
                            String valueStr = String.valueOf(valueKH).replaceAll("[,VND]", "").trim();
                            double valueDouble = Double.parseDouble(valueStr);
                            cell1.setCellType(CellType.NUMERIC);
                            cell1.setCellValue(valueDouble);
                        } else if (coltblKH == 3) {
//                            String valueStr = String.valueOf(valueKH).replaceAll("[^\\d.]", "").trim();
                            int valueSLMua = Integer.parseInt(String.valueOf(valueKH));
                            cell1.setCellValue(valueSLMua);
                        } else {
                            cell1.setCellType(CellType.STRING);
                            cell1.setCellValue(String.valueOf(valueKH));
                        }
                        autosizeColumn(sheet, coltblKH);
                        cell1.setCellStyle(itemStyle);
                    }
                }
                for (int i = 4; i < sheet.getLastRowNum(); i++) {
                    int numberOfColumn = sheet.getRow(i).getPhysicalNumberOfCells();
                    autosizeColumn(sheet, numberOfColumn);
                }
                int lastRow = sheet.getLastRowNum() + 3;
                // tông lượt mua của tất cả các khách hàng
                Row rowTLMua = sheet.createRow(lastRow++);
                Cell cellTLMua = rowTLMua.createCell(1, CellType.STRING);
                cellTLMua.setCellValue("Tổng Lượt Mua ");
                Cell cellLMuaValue = rowTLMua.createCell(2, CellType.STRING);
                cellLMuaValue.setCellValue(txtTongMua.getText());
    
                // Tổng tiền của tất cả các khách hàng đã mua
                Row rowTongTien = sheet.createRow(lastRow++);
                Cell cellTongTien = rowTongTien.createCell(1, CellType.STRING);
                cellTongTien.setCellValue("Tổng Tiền:  ");
                Cell cellTongNVValue = rowTongTien.createCell(2, CellType.STRING);
                cellTongNVValue.setCellValue(txtTTienKH.getText());

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);

                workbook.write(fileOut);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnExKHActionPerformed

    private void btnTKeKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeKHActionPerformed
        try {
            Date selectedDateKHTruoc = txtDateKHT.getDate();
            Date selectedDateKHSau = txtDateKHSau.getDate();
            java.sql.Date sqlDateKHTruoc = new java.sql.Date(selectedDateKHTruoc.getTime());
            java.sql.Date sqlDateKHSau = new java.sql.Date(selectedDateKHSau.getTime());
            List<ChiTietHoaDon> tKKH = ctHDDao.locDLieuTKeKH(sqlDateKHTruoc, sqlDateKHSau);
            if (!tKKH.isEmpty()) {
                model3 = (DefaultTableModel) tblTKKH.getModel();
                model3.setRowCount(0);
                updateTableKH(tKKH);
                updateTxtSLMuaCuaKHvatxtSLuotBanDSBHNV(txtTongMua, tblTKKH, 3, model3.getRowCount());
                updateTxtDThu(0, model3.getRowCount(), txtTTienKH, tblTKKH, 4);
                isFilteredKH = true;
                filteredDataKH = ctHDDao.locDLieuTKeKH(sqlDateKHTruoc, sqlDateKHSau);
                createChartKH();
                updateKHMuaNhieuNhat(model3.getRowCount());
                JOptionPane.showMessageDialog(null, "Thống Kê Thành Công");
            } else {
                JOptionPane.showMessageDialog(null, "Thống Kê Không Thành Công");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTKeKHActionPerformed

    private void btnGuiBCaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBCaoHDActionPerformed
        String tongHD = txtTongHD.getText();
        String tongDoanhThu = txtDThuHD.getText();
        // Tạo nội dung email
        String emailContent = "<br>     Tổng Hóa Đơn: " + tongHD + "<br>"
                + "<br>     Tổng Doanh Thu: " + tongDoanhThu + "<br>"
                + "<br> ============================ <br>";
        for (int i = 0; i < tblTKHD.getRowCount(); i++) {
            String maHD = tblTKHD.getValueAt(i, 0).toString();
            String tenNV = tblTKHD.getValueAt(i, 1).toString();
            String tenKH = tblTKHD.getValueAt(i, 2).toString();
            String ngayTao = tblTKHD.getValueAt(i, 3).toString();
            String doanhThu = tblTKHD.getValueAt(i, 4).toString();
            emailContent += "<br> Mã Hóa Đơn: " + maHD
                    + ",   Tên Nhân Viên: " + tenNV + ",   Tên Khách Hàng: " + tenKH + ",   Ngày Tạo: " + ngayTao + ",   Doanh Thu: " + doanhThu + "<br>";
        }
        emailContent += "<br> ============================ <br>"
                + "<br>     Tổng doanh thu: " + tongDoanhThu;
        sendEmail(emailContent, "Gửi báo cáo hóa đơn");
    }//GEN-LAST:event_btnGuiBCaoHDActionPerformed

    private void btnGuiBCaoKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBCaoKHActionPerformed
        String tongMua = txtTongMua.getText();
        String kHMuaNhieu = txtKHMuaNhieu.getText();
        String tongTien = txtTTienKH.getText();
        // Tạo nội dung email
        String emailContent = "<br>     Tổng Mua: " + tongMua + "<br>"
                + "<br>     Khách Hàng Mua Nhiều: " + kHMuaNhieu + "<br>"
                + "<br> ============================ <br>";
        for (int i = 0; i < tblTKKH.getRowCount(); i++) {
            String maKH = tblTKKH.getValueAt(i, 0).toString();
            String tenKH = tblTKKH.getValueAt(i, 1).toString();
            String gioiTinh = tblTKKH.getValueAt(i, 2).toString();
            String tongLMua = tblTKKH.getValueAt(i, 3).toString();
            String tongTMua = tblTKKH.getValueAt(i, 4).toString();
            String ngayMua = tblTKKH.getValueAt(i, 5).toString();
            emailContent += "<br> Mã Hóa Đơn: " + maKH
                    + ",   Tên Nhân Viên: " + tenKH + ",   Giới Tính: " + gioiTinh + ",   Ngày Mua: " + ngayMua + ",   Tổng Lượt Mua: " + tongLMua + ",   Tổng Tiền Đã Mua: " + tongTMua + "<br>";
        }
        emailContent += "<br> ============================ <br>"
                + "<br>     Tổng Tiền Của Tất Cả Khách Hàng: " + tongTien;
        sendEmail(emailContent, "Gửi báo cáo các khách hàng đã mua");
    }//GEN-LAST:event_btnGuiBCaoKHActionPerformed

    private void btnGuiBCaoDSBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBCaoDSBHActionPerformed
//        String tongSLBan = txtTSLuotBan.getText();
        String dSo = txtDoanhSo.getText();
        // Tạo nội dung email
        String emailContent = "<br>     Tổng Lượt Bán: " //+ tongSLBan + "<br>"
                + "<br> ============================ <br>";
        for (int i = 0; i < tblTKDSBH.getRowCount(); i++) {
            String maNV = tblTKDSBH.getValueAt(i, 0).toString();
            String tenNV = tblTKDSBH.getValueAt(i, 1).toString();
            String gioiTinh = tblTKDSBH.getValueAt(i, 2).toString();
            String sLSPBan = tblTKDSBH.getValueAt(i, 3).toString();
            String dSBH = tblTKDSBH.getValueAt(i, 4).toString();
            String ngayBan = tblTKDSBH.getValueAt(i, 5).toString();
            emailContent += "<br> Mã Nhân Viên: " + maNV
                    + ",   Tên Nhân Viên: " + tenNV + ",   Giới Tính: " + gioiTinh + ",   Ngày Bán: " + ngayBan + ",   Số lượng Sản Phẩm Bán: " + sLSPBan + ",   Doanh Số: " + dSBH + "<br>";
        }
        emailContent += "<br> ============================ <br>"
                + "<br>     Tổng Tiền Của Tất Cả Khách Hàng: " + dSo;

        sendEmail(emailContent, "Gửi báo cáo doanh số bán hàng");
    }//GEN-LAST:event_btnGuiBCaoDSBHActionPerformed
    public void updateTxtDThu(int totalDThu, int rowCount, JTextField txtUpdate, JTable table, int column) {
        for (int i = 0; i < rowCount; i++) {
            String str = (String) table.getValueAt(i, column);
            int so = Integer.parseInt(replace(str));
            totalDThu += so;
        }
        txtUpdate.setText(dcfCurrency.format(totalDThu));
    }

    // Hàm  Tìm Sản Phẩm Bán Chạy Nhất và tìm Nhân Viên có Doanh Số Bán Hàng Nhiều Nhất 
    public void updateSPBanChay() {
        int max = (int) tblTKeSP.getValueAt(0, 4);
        String ten = new String(tblTKeSP.getValueAt(0, 1).toString());
        for (int i = 1; i < model1.getRowCount(); i++) {
            int slBan = (int) tblTKeSP.getValueAt(i, 4);
            if (slBan > max) {
                max = slBan;
                ten = (String) tblTKeSP.getValueAt(i, 1);
            }
        }
        txtSPBC.setText(ten);
    }

    // Hàm  Tìm nhân viên có doanh số bán hàng nhiều nhất 
    public void updateNVienCoDSBHNhieu() {
        String maxStr = (String) tblTKDSBH.getValueAt(0, 4);
        double max = Double.parseDouble(replace(maxStr));
        String ten = new String(tblTKeSP.getValueAt(0, 1).toString());
        for (int i = 1; i < model4.getRowCount(); i++) {
            String dSoBanStr = (String) tblTKDSBH.getValueAt(i, 4);
            double dSoBan = Double.parseDouble(replace(dSoBanStr));
            if (dSoBan > max) {
                max = dSoBan;
                ten = (String) tblTKDSBH.getValueAt(i, 1);
            }
        }
        txtDSBHNN.setText(ten);
    }

    // Hàm Tìm Khách Hàng Mua Nhiều Nhất 
    private void updateKHMuaNhieuNhat(int rowCount) {
        int max = (int) tblTKKH.getValueAt(0, 3);
        String tenKH = tblTKKH.getValueAt(0, 1).toString();
        for (int i = 0; i < rowCount; i++) {
            int slMua = (int) tblTKKH.getValueAt(i, 3);
            if (slMua > max) {
                max = slMua;
                tenKH = (String) tblTKKH.getValueAt(i, 1);
            }
        }
        txtKHMuaNhieu.setText(tenKH);
    }

    // Hàm Tìm Khách Hàng Có Số Lượng Mua Nhiều Nhất Và Số Lượt Bán Hàng Của Nhân Viên
    private void updateTxtSLMuaCuaKHvatxtSLuotBanDSBHNV(JTextField txt, JTable table, int column, int rowCount) {
        int soLuong = 0;
        for (int i = 0; i < rowCount; i++) {
            soLuong += (int) table.getValueAt(i, column);
        }
        txt.setText(soLuong + "");
    }

    // Hàm Update Bảng Sản Phẩm
    private void updateTableSP(List<ChiTietHoaDon> tkSPList) {
        model1 = (DefaultTableModel) tblTKeSP.getModel();
        model1.setRowCount(0);
        for (ChiTietHoaDon thongKeSP : tkSPList) {
            String maSP = thongKeSP.getMaSP().getMaSP();
            String tenSP = thongKeSP.getMaSP().getTenSP();
            String tenLoaiSP = thongKeSP.getMaSP().getLoaiSP().getTenLoai();
            LocalDate ngayTao = thongKeSP.getMaHD().getNgayTao();
            int sLBan = thongKeSP.getSoLuong();
            double dThu = thongKeSP.getDonGia();
            model1.addRow(new Object[]{maSP, tenSP, tenLoaiSP, ngayTao, sLBan, dcfCurrency.format(dThu)});
        }
        // update txtSoLuong SP
        int totalSP = model1.getRowCount();
        txtTongSP.setText(String.valueOf(totalSP));
    }

   

    // Hàm tạo chart general
    private void taoChart(String title, String label, String labelY, DefaultCategoryDataset datasetSP, JPanel pnBieuDo) {
        JFreeChart chart = ChartFactory.createBarChart(title, label, labelY, datasetSP,
                PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        pnBieuDo.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        pnBieuDo.removeAll();
        pnBieuDo.add(chartPanel, BorderLayout.CENTER);
        pnBieuDo.revalidate();
        pnBieuDo.repaint();
    }

    // Hàm Update Bảng Khách Hàng
    private void updateTableKH(List<ChiTietHoaDon> tKKH) {
        model3 = (DefaultTableModel) tblTKKH.getModel();
        model3.setRowCount(0);      //        System.out.println(tKKH);
        for (ChiTietHoaDon thongKeKH : tKKH) {
            String maKH = thongKeKH.getMaHD().getMaKh().getMaKH();
            String tenKH = thongKeKH.getMaHD().getMaKh().getTenKH();
            Boolean gioiTinh = thongKeKH.getMaHD().getMaKh().isGioiTinh();
            int sLMua = thongKeKH.getSoLuong();
            Double tTMua = thongKeKH.getDonGia();
            String gioiTinhStr = gioiTinh ? "Nam" : "Nữ";
            LocalDate ngayMua = thongKeKH.getMaHD().getNgayTao();
            model3.addRow(new Object[]{maKH, tenKH, gioiTinhStr, sLMua, dcfCurrency.format(tTMua), ngayMua});
        }
    }

    // Hàm tạo biểu đồ Khách Hàng
    private void createChartKH() throws RemoteException {
        Date selectedDateKHTruoc = txtDateKHT.getDate();
        Date selectedDateKHSau = txtDateKHSau.getDate();
        java.sql.Date sqlDateKHTruoc = new java.sql.Date(selectedDateKHTruoc.getTime());
        java.sql.Date sqlDateKHSau = new java.sql.Date(selectedDateKHSau.getTime());
        List<ChiTietHoaDon> dataToChartKH;
        List<ChiTietHoaDon> tkKHList = ctHDDao.docDLieuTKeKH();

        String title = "Thống Kê Khách Hàng";
        if (isFilteredKH && sqlDateKHTruoc != null && sqlDateKHSau != null) {
            title += sqlDateKHTruoc + " tới " + sqlDateKHSau;
            dataToChartKH = filteredDataKH;
        } else {
            title = "Thống kê Khách Hàng ";
            dataToChartKH = tkKHList;
        }
        datasetKH.clear();
        if (rdoSLSPKHMua.isSelected()) {
            HashMap<String, Integer> SLMuaCuaKHMap = new HashMap<>();
            for (ChiTietHoaDon dSKH : dataToChartKH) {
                int sL = dSKH.getSoLuong();
                String tenKH = dSKH.getMaHD().getMaKh().getTenKH();
                SLMuaCuaKHMap.merge(tenKH, sL, Integer::sum);
            }
            for (Map.Entry<String, Integer> entry : SLMuaCuaKHMap.entrySet()) {
                String tenKH = entry.getKey();
                int sL = entry.getValue();
                datasetKH.setValue(sL, "Số Lượng Sản Phẩm Đã Mua", tenKH);
            }
            taoChart(title, "Tên Khách Hàng", "Số Lượng Sản Phẩm Đã Mua", datasetKH, pnlBDTKKH);
        } else if (rdoDTTuKH.isSelected()) {
            HashMap<String, Double> doanhThuKHMap = new HashMap<>();
            for (ChiTietHoaDon dSKH : dataToChartKH) {
                String tenKH = dSKH.getMaHD().getMaKh().getTenKH();
                double doanhThu = dSKH.getDonGia();
                doanhThuKHMap.merge(tenKH, doanhThu, Double::sum);
            }
            for (Map.Entry<String, Double> entry : doanhThuKHMap.entrySet()) {
                double tongDoanhThu = entry.getValue();
                String maKH = entry.getKey();
                datasetKH.setValue(tongDoanhThu, "Tổng Tiền Đã Chi", maKH);
            }
            taoChart(title, "Tên Khách Hàng", "Tổng Tiền Đã Chi", datasetKH, pnlBDTKKH);
        }
    }

    // Hàm Update Bảng Hóa Đơn
    private void updateTableHD(List<ChiTietHoaDon> tkHDList) {
        model2 = (DefaultTableModel) tblTKHD.getModel();
        model2.setRowCount(0);
        for (ChiTietHoaDon hoaDon : tkHDList) {
            String maHD = hoaDon.getMaHD().getMaHD();
            String tenNhanVien = hoaDon.getMaHD().getMaNV().getTenNhanVien();
            String tenKhachHang = hoaDon.getMaHD().getMaKh().getTenKH();
            LocalDate ngayTao = hoaDon.getMaHD().getNgayTao();
            double doanhThu = hoaDon.getDonGia();
            model2.addRow(new Object[]{maHD, tenNhanVien, tenKhachHang, ngayTao, dcfCurrency.format(doanhThu)});
        }
        txtTongHD.setText(String.valueOf(model2.getRowCount()));
    }

    //Hàm tạo biểu đồ Hóa Đơn
    private void createChartHD() throws RemoteException {
        Date selectedDateHDTruoc = txtDateHDTruoc.getDate();
        Date selectedDateHDSau = txtDateHDSau.getDate();
        java.sql.Date sqlDateHDTruoc = new java.sql.Date(selectedDateHDTruoc.getTime());
        java.sql.Date sqlDateHDSau = new java.sql.Date(selectedDateHDSau.getTime());
        List<ChiTietHoaDon> dataToChartHD;

        List<ChiTietHoaDon> tKHDList = ctHDDao.tKHDlenBang();

        String title = "Thống kê Doanh Hóa Đơn từ ";
        if (isFilteredHD && sqlDateHDTruoc != null && sqlDateHDSau != null) {
            title += sqlDateHDTruoc + " tới " + sqlDateHDSau;
            dataToChartHD = filteredDataHD;
        } else {
            title = "Thống kê Hóa Đơn ";
            dataToChartHD = tKHDList;
        }
        datasetHD.clear();
        if (rdoSLHDTNgay.isSelected()) {
            for (ChiTietHoaDon chiTietHoaDon : dataToChartHD) {
                LocalDate ngayTao = chiTietHoaDon.getMaHD().getNgayTao();
                int soLuongHD = chiTietHoaDon.getSoLuong();
                datasetHD.setValue(soLuongHD, "Doanh Thu", ngayTao);
            }
            taoChart(title, "Ngày", "Số Lượng Hóa Đơn", datasetHD, pnlTKHD);
        } else if (rdoDTTHD.isSelected()) {
            HashMap<String, Double> doanhThuHDMap = new HashMap<>();
            for (ChiTietHoaDon dSHD : dataToChartHD) {
                String maHD = dSHD.getMaHD().getMaHD();
                double doanhThu = dSHD.getDonGia();
                doanhThuHDMap.merge(maHD, doanhThu, Double::sum);
            }
            for (Map.Entry<String, Double> entry : doanhThuHDMap.entrySet()) {
                double tongDoanhThu = entry.getValue();
                String maHD = entry.getKey();
                datasetHD.setValue(tongDoanhThu, "Doanh Thu Của Từng Hóa Đơn", maHD);
            }
            taoChart(title, "Mã Hóa Đơn", "Doanh Thu", datasetHD, pnlTKHD);
        }
    }

    // Hàm Cập nhật Table Doanh Số Bán Hàng Của Nhân Viên
    //update bảng NV
    private void updateTableDSBHCuaNV(List<ChiTietHoaDon> tKDSBHList) {
        model4 = (DefaultTableModel) tblTKDSBH.getModel();
        model4.setRowCount(0);
        for (ChiTietHoaDon thongKeDSBHNV : tKDSBHList) {
            String tenNV = thongKeDSBHNV.getMaHD().getMaNV().getTenNhanVien();
            String gioiTinh = thongKeDSBHNV.getMaHD().getMaNV().getGioiTinh();
            int sLSPBanDuoc = thongKeDSBHNV.getSoLuong();
            Double tTMua = thongKeDSBHNV.getDonGia();
            String maNV = thongKeDSBHNV.getMaHD().getMaNV().getMaNhanVien();
            LocalDate ngayTao = thongKeDSBHNV.getMaHD().getNgayTao();
            model4.addRow(new Object[]{maNV, tenNV, gioiTinh, sLSPBanDuoc, dcfCurrency.format(tTMua), ngayTao});
        }
//        txtTSLuotBan.setText(model4.getRowCount() + "");

    }

   

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        // Create font
        return cellStyle;

    }

    private static CellStyle fontText(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private static CellStyle createStyleForItem(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private void sendEmail(String emailContent, String subjectStr) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");

            String accountName = "hoductrihieu@gmail.com";
            String accountPassword = "cfeh zhly akov yune";

            Session s = Session.getInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountName, accountPassword);
                }
            });
            String from = accountName;
            String to = "hieu13200303@gmail.com"; // Địa chỉ email người nhận
            String subject = subjectStr;

            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(msg);
            JOptionPane.showMessageDialog(null, "Gửi email thành công");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Gửi email không thành công: " + e.getMessage());
        }
    }

    // Hàm Xóa các kí tự
    public String replace(String str) {
        String strNew = str.replaceAll("VND", "");
        String strReplace = strNew.replaceAll(",", "");
        return strReplace;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExKH;
    private javax.swing.ButtonGroup btnGRKH;
    private javax.swing.ButtonGroup btnGrHD;
    private javax.swing.ButtonGroup btnGroupDSBH;
    private javax.swing.JButton btnGuiBCaoDSBH;
    private javax.swing.JButton btnGuiBCaoHD;
    private javax.swing.JButton btnGuiBCaoKH;
    private javax.swing.JButton btnGuiBaoCao;
    private javax.swing.JButton btnTKeDSBH;
    private javax.swing.JButton btnTKeHD;
    private javax.swing.JButton btnTKeKH;
    private javax.swing.JButton btnTKeSP;
    private javax.swing.JButton btnXuatExDSBH;
    private javax.swing.JButton btnXuatExHD;
    private javax.swing.JButton btnXuatFileSP;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JLabel lblNVBHNhieuNhat;
    private javax.swing.JLabel lblNVBHNhieuNhat1;
    private javax.swing.JLabel lblTKSP;
    private javax.swing.JPanel pnTKSP;
    private javax.swing.JPanel pnlBDTKKH;
    private javax.swing.JPanel pnlTKHD;
    private javax.swing.JRadioButton rdoDTTHD;
    private javax.swing.JRadioButton rdoDTTuKH;
    private javax.swing.JRadioButton rdoSLHDTNgay;
    private javax.swing.JRadioButton rdoSLSPKHMua;
    private javax.swing.JTable tblTKDSBH;
    private javax.swing.JTable tblTKHD;
    private javax.swing.JTable tblTKKH;
    private javax.swing.JTable tblTKeSP;
    private javax.swing.JTextField txtDSBHNN;
    private javax.swing.JTextField txtDThuHD;
    private com.toedter.calendar.JDateChooser txtDateDSBHS;
    private com.toedter.calendar.JDateChooser txtDateDSBHT;
    private com.toedter.calendar.JDateChooser txtDateHDSau;
    private com.toedter.calendar.JDateChooser txtDateHDTruoc;
    private com.toedter.calendar.JDateChooser txtDateKHSau;
    private com.toedter.calendar.JDateChooser txtDateKHT;
    private com.toedter.calendar.JDateChooser txtDateSau;
    private com.toedter.calendar.JDateChooser txtDateTruoc;
    private javax.swing.JTextField txtDoanhSo;
    private javax.swing.JTextField txtKHMuaNhieu;
    private javax.swing.JTextArea txtSPBC;
    private javax.swing.JTextField txtTTienKH;
    private javax.swing.JTextField txtTongDThu;
    private javax.swing.JTextField txtTongHD;
    private javax.swing.JTextField txtTongMua;
    private javax.swing.JTextField txtTongSP;
    // End of variables declaration//GEN-END:variables
}
