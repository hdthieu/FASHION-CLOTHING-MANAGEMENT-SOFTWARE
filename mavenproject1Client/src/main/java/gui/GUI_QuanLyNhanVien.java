
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

//import Connection.ConnectSQL;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import entity.NhaCungCap;
import entity.NhanVien;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import url.RmiConfig;

/**
 *
 * @author dinhh
 */
public class GUI_QuanLyNhanVien extends javax.swing.JPanel {

    /**
     * Creates new form NhanVien
     */
//    String filename = null;
//    byte[] person_image = null;
    File fileAnhNV;
//    NhanVienImpl nhanVienDAO = new NhanVienImpl();
    NhanVienDao nhanVienDAO;
    private DefaultTableModel dataModel;
    private static final String URL = RmiConfig.RMI_URL;

    public GUI_QuanLyNhanVien() throws java.rmi.RemoteException {
        try {
            nhanVienDAO = (NhanVienDao) Naming.lookup(URL + "NhanVien");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();
        dataModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Ngày Sinh", "Số Điện Thoại", "Email", "Địa Chỉ", "Chức Vụ", "Hình Ảnh"
                }
        );
        tblDSNhanVien.setModel(dataModel);
//        ConnectSQL.getInstance().connect();
        updateEmployeeTableData();
    }

    private void updateEmployeeTableData() throws java.rmi.RemoteException {
//        NhanVienImpl employeeDAO = new NhanVienImpl();
        List<NhanVien> employees = nhanVienDAO.docTuBang();

        dataModel.setRowCount(0);

        for (NhanVien employee : employees) {
            String[] rowData = {
                employee.getMaNhanVien(),
                employee.getTenNhanVien(),
                employee.getGioiTinh(),
                employee.getNgaySinh(),
                employee.getSDT(),
                employee.getEmail(),
                employee.getDiaChi(),
                employee.getChucVu(),
                employee.getHinhAnh()
            };
            dataModel.addRow(rowData);
        }
        tblDSNhanVien.setModel(dataModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnThemNhanVien = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnXuatExcel = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tblDSNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Ngày Sinh", "Số Điện Thoại", "Email", "Địa Chỉ", "Chức Vụ", "Hình Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSNhanVien);

        jScrollPane3.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 409, 1200, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(546, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(447, 447, 447))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 9, 1170, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Mã Nhân Viên");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 48, 102, -1));
        jPanel3.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 45, 220, -1));

        jLabel3.setText("Tên Nhân Viên");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 102, -1));
        jPanel3.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 45, 214, -1));

        lblEmail.setText("Email");
        jPanel3.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 102, -1));

        jLabel5.setText("Giới Tính");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 104, 102, -1));
        jPanel3.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 220, -1));

        jLabel6.setText("Địa Chỉ");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 102, -1));
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 217, -1));

        jLabel7.setText("Ngày Sinh");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 102, -1));

        jLabel8.setText("Chức Vụ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, -1, -1));

        jLabel9.setText("Chức Năng");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 309, -1, -1));

        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });
        jPanel3.add(btnThemNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, 30));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_refesh.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 120, 30));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_change.png"))); // NOI18N
        btnSua.setText("Cập Nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, -1, 30));

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_remove.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 90, 30));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 210, 230));

        btnChonAnh.setText("Chọn Ảnh");
        btnChonAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonAnhMouseClicked(evt);
            }
        });
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });
        jPanel3.add(btnChonAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 290, -1, -1));

        jLabel10.setText("Hình Ảnh Nhân Viên");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, -1, -1));

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên Bán Hàng", "Quản Lý" }));
        jPanel3.add(cboChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 217, -1));

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel3.add(cboGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 210, -1));

        jLabel11.setText("Số Điện Thoại");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 177, 102, -1));
        jPanel3.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 217, -1));

        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnXuatExcel.setText("Xuất");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel3.add(btnXuatExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, -1, 30));

        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnNhapExcel.setText("Nhập");
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });
        jPanel3.add(btnNhapExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, 30));
        jPanel3.add(dateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 220, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1190, 340));
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int row = tblDSNhanVien.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào để chỉnh sửa!");
            } else {
                NhanVien employee = revertNVFormTextFile();
                if (employee != null) {
                    if (nhanVienDAO.update(employee)) {
                        tblDSNhanVien.setValueAt(employee.getMaNhanVien(), row, 0);
                        tblDSNhanVien.setValueAt(employee.getTenNhanVien(), row, 1);
                        tblDSNhanVien.setValueAt(employee.getGioiTinh(), row, 2);
                        tblDSNhanVien.setValueAt(employee.getNgaySinh(), row, 3);
                        tblDSNhanVien.setValueAt(employee.getSDT(), row, 4);
                        tblDSNhanVien.setValueAt(employee.getEmail(), row, 5);
                        tblDSNhanVien.setValueAt(employee.getDiaChi(), row, 6);
                        tblDSNhanVien.setValueAt(employee.getChucVu(), row, 7);
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
                    }
                }
                updateEmployeeTableData();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed
    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/NhanVien/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhNV = new File(src);
        } catch (IOException e) {
            fileAnhNV = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    private void loadAnh(String anh) {
        lblAnh.setIcon(getAnhSP(anh));
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhNV.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/NhanVien/" + fileAnhNV.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int row = tblDSNhanVien.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào để xóa!");
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    String maNhanVien = tblDSNhanVien.getValueAt(row, 0).toString();

                    if (nhanVienDAO.delete(maNhanVien)) {
                        DefaultTableModel dataModel = (DefaultTableModel) tblDSNhanVien.getModel();
                        dataModel.removeRow(row);
                    }
                }

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnChonAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonAnhMouseClicked

    }//GEN-LAST:event_btnChonAnhMouseClicked

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        try {
            try {
                NhanVien employee = revertNVFormTextFile();
//                NhanVienImpl nhanVienImpl = new NhanVienImpl();
                if (nhanVienDAO.create(employee)) {
                    Object[] rowData = {
                        employee.getMaNhanVien(),
                        employee.getTenNhanVien(),
                        employee.getGioiTinh(),
                        employee.getNgaySinh(),
                        employee.getSDT(),
                        employee.getEmail(),
                        employee.getDiaChi(),
                        employee.getChucVu(),
                        employee.getHinhAnh()
                    };
                    dataModel.addRow(rowData);
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearEmployeeTextFields();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblDSNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhanVienMouseClicked

        int row = tblDSNhanVien.getSelectedRow();
        if (row >= 0) {
            txtMaNhanVien.setText(tblDSNhanVien.getValueAt(row, 0).toString());
            txtTenNhanVien.setText(tblDSNhanVien.getValueAt(row, 1).toString());
            String gioiTinhStr = tblDSNhanVien.getValueAt(row, 2).toString();
            cboGioiTinh.setSelectedItem(gioiTinhStr);
            String ngaySinhStr = tblDSNhanVien.getValueAt(row, 3).toString();
            try {
                Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr);
                dateNgaySinh.setDate(ngaySinh);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            txtSDT.setText(tblDSNhanVien.getValueAt(row, 4).toString());
            txtEmail.setText(tblDSNhanVien.getValueAt(row, 5).toString());
            txtDiaChi.setText(tblDSNhanVien.getValueAt(row, 6).toString());
            cboChucVu.setSelectedItem(tblDSNhanVien.getValueAt(row, 7).toString());
            loadAnh("image/NhanVien/" + tblDSNhanVien.getValueAt(row, 8).toString());

    }//GEN-LAST:event_tblDSNhanVienMouseClicked
    }
    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed

        JFileChooser fileChooser = new MyFileChooser("image/NhanVien/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhNV = fileChooser.getSelectedFile();
            lblAnh.setIcon(getAnhSP(fileAnhNV.getPath()));
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser("./excel");
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("NhanVien");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblDSNhanVien.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblDSNhanVien.getColumnName(i));
                }
                for (int j = 0; j < tblDSNhanVien.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblDSNhanVien.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblDSNhanVien.getValueAt(j, k) != null) {
                            cell.setCellValue(tblDSNhanVien.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        try {
            File excelFile;
            SimpleDateFormat inputFormat = new SimpleDateFormat("M/d/yyyy");

            FileInputStream excelFIS = null;
            BufferedInputStream excelBIS = null;
            XSSFWorkbook excelJTableImport = null;
            ArrayList<NhanVien> listAccExcel = new ArrayList<NhanVien>();
            JFileChooser jf = new JFileChooser("./excel");
            int result = jf.showOpenDialog(null);
            jf.setDialogTitle("Open file");
            Workbook workbook = null;
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    excelFile = jf.getSelectedFile();
                    excelFIS = new FileInputStream(excelFile);
                    excelBIS = new BufferedInputStream(excelFIS);
                    excelJTableImport = new XSSFWorkbook(excelBIS);
                    XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                    for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                        Row excelRow = excelSheet.getRow(row);

                        String maNhanVien = excelRow.getCell(0).getStringCellValue();
                        String tenNhanVien = excelRow.getCell(1).getStringCellValue();
                        String gioiTinh = excelRow.getCell(2).getStringCellValue();

                        Cell ngaySinhCell = excelRow.getCell(3);
                        String ngaySinhStr = "";
                        if (ngaySinhCell.getCellType() == CellType.NUMERIC) {
                            Date ngaySinhDate = ngaySinhCell.getDateCellValue();
                            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            ngaySinhStr = outputFormat.format(ngaySinhDate);
                        } else if (ngaySinhCell.getCellType() == CellType.STRING) {
                            ngaySinhStr = ngaySinhCell.getStringCellValue();
                        }

                        String soDienThoai = excelRow.getCell(4).getStringCellValue();
                        String email = excelRow.getCell(5).getStringCellValue();
                        String diaChi = excelRow.getCell(6).getStringCellValue();
                        String chucVu = excelRow.getCell(7).getStringCellValue();
                        String hinhAnh = excelRow.getCell(8).getStringCellValue();

                        NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinhStr, soDienThoai, email, diaChi, chucVu, hinhAnh);
                        listAccExcel.add(nv);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (excelBIS != null) {
                            excelBIS.close();
                        }
                        if (excelFIS != null) {
                            excelFIS.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                int successCount = 0;
                for (NhanVien nhanVien : listAccExcel) {
                    boolean isSuccess = nhanVienDAO.create(nhanVien);
                    if (!isSuccess) {
                        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        successCount++;
                    }
                }

                if (successCount > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công !");
                    updateEmployeeTableData();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNhapExcelActionPerformed
    private NhanVien revertNVFormTextFile() {
        String maNhanVien = txtMaNhanVien.getText();
        if (!maNhanVien.matches("^(NV|QL)\\d{3}$")) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ phải bắt đầu bằng NV hoặc QL theo sau là 3 chữ số !!!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String tenNhanVien = txtTenNhanVien.getText();

        if (tenNhanVien.isEmpty() || !tenNhanVien.matches("^[A-ZÀ-Ỹa-ỹ].{9,}$")) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ (phải bắt đầu bằng chữ hoa, có ít nhất 10 ký tự và chứa ký tự tiếng Việt)", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String gioiTinh = cboGioiTinh.getSelectedItem().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateNgaySinh.getDate();

        if (date != null) {
            String ngaySinh = sdf.format(date);

        } else {
            System.out.println("Date is null");
        }

        String ngaySinh = sdf.format(date);

        String sdt = txtSDT.getText().trim();

        if (!sdt.matches("^0[0-9]{9}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0 và có 10 chữ số", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String email = txtEmail.getText();
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String diaChi = txtDiaChi.getText();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String chucVu = cboChucVu.getSelectedItem().toString();
        String anh = (fileAnhNV != null) ? fileAnhNV.getName() : "defaultFileName";

        if (fileAnhNV == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hình ảnh", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        LocalDate birthDate = LocalDate.parse(ngaySinh);
        LocalDate currentDate = LocalDate.now();
        if (ChronoUnit.YEARS.between(birthDate, currentDate) < 18) {
            JOptionPane.showMessageDialog(null, "Nhân viên phải đủ 18 tuổi", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, sdt, email, diaChi, chucVu, anh);
    }

    private void clearEmployeeTextFields() {
        txtMaNhanVien.setText("");
        txtTenNhanVien.setText("");
        cboChucVu.setSelectedIndex(0);
        cboGioiTinh.setSelectedIndex(0);
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        ImageIcon defaultImage = new ImageIcon("default.png");
        lblAnh.setIcon(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTable tblDSNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables

}
