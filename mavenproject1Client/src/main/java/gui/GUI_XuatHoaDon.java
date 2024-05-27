package gui;

import dao.ChiTietHoaDonDao;
import dao.ChiTietKhuyenMaiDao;
import dao.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.ChiTietKhuyenMai;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.SanPham;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import url.RmiConfig;

public class GUI_XuatHoaDon extends JDialog {

    private ArrayList<Vector> dsGioHang;
    private double tongTien;
    private String nhanVien;
    private String khachHang;
    private String hoaDon;
    
   private static final String URL = RmiConfig.RMI_URL;
    
    private ChiTietKhuyenMaiDao ctkmDao ;
    private ChiTietHoaDonDao ctHDDao ;
    private HoaDon_DAO hdDao;
     public GUI_XuatHoaDon() throws java.rmi.RemoteException {

         try {
             ctkmDao = (ChiTietKhuyenMaiDao) Naming.lookup(URL + "ChiTietKhuyenMai");
             ctHDDao = (ChiTietHoaDonDao) Naming.lookup(URL + "ChiTietHoaDon");
             hdDao = (HoaDon_DAO) Naming.lookup(URL + "HoaDon");
         } catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        checkBanHang = false;

    }
    public GUI_XuatHoaDon(ArrayList<Vector> dsGioHang, double tongTien, Object nhanVien, Object khachHang, String hoaDon) throws java.rmi.RemoteException {
        this();
        this.tongTien = tongTien;
        this.dsGioHang = dsGioHang;
        this.nhanVien = (String) nhanVien;
        this.khachHang = (String) khachHang;
        this.hoaDon = hoaDon;
        DecimalFormat dcf = new DecimalFormat("###,###");
        txtTongTien.setText(dcf.format(tongTien));
        txtTenKhach.setText((String) khachHang);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHoaDon = new javax.swing.JEditorPane();
        txtTenKhach = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        jLabel5.setText("jLabel5");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("Chi tiết hoá đơn");
        jPanel1.add(jLabel1);

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setPreferredSize(new java.awt.Dimension(128, 45));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(btnThanhToan);

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInHoaDon.setText("In hoá đơn");
        btnInHoaDon.setEnabled(false);
        btnInHoaDon.setPreferredSize(new java.awt.Dimension(128, 45));
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel2.add(btnInHoaDon);

        txtHoaDon.setEditable(false);
        jScrollPane1.setViewportView(txtHoaDon);

        txtTenKhach.setEditable(false);
        txtTenKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Khách hàng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(txtTenKhach))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static boolean checkBanHang = false;

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            checkBanHang = false;
            xuLyHienThiHoaDon();
            btnInHoaDon.setEnabled(true);
            String[] parts = khachHang.split("-");
            String kh = "";
            if (parts.length > 0) {
                kh = parts[0].trim(); // Lấy phần tử đầu tiên và loại bỏ khoảng trắng
            }
            luuHoaDon(hoaDon, LocalDate.now(), kh, nhanVien);
            for (Vector vec : dsGioHang) {
                String maSP = vec.get(0) + "";
                int sl = Integer.parseInt(vec.get(3) + "");
                double dg = Double.parseDouble(vec.get(4) + "");
                luuCTHoaDon(hoaDon, maSP, sl, dg);
            }
            btnThanhToan.setEnabled(false);
            checkBanHang = true;
        } catch (RemoteException ex) {
            // Xử lý ngoại lệ ở đây, ví dụ:
            ex.printStackTrace();
        }
    }

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!txtHoaDon.getText().equals("")) {
                txtHoaDon.print();
                this.dispose();
            }
        } catch (PrinterException ex) {
        }
    }

    private void btnTimKhachActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void xuLyHienThiHoaDon() throws java.rmi.RemoteException {
        txtHoaDon.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double tongTien = 0;
        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h3 style='text-align:center; '> Cửa Hàng Quàn Áo Thời Trang Fashion Shop</h3>";
        hd += "<h3 style='text-align:center; margin-top: -20px;'>Địa Chỉ: Nguyễn Văn Bảo, Quận Gò Vấp, TP.HCM</h3>";
        hd += "<br>";
        hd += "<h1 style='text-align:center;'>HOÁ ĐƠN THANH TOÁN</h1>";

        hd += "Mã hóa đơn: " + hoaDon + "<br/>";
        hd += "Nhân viên: " + nhanVien + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Khách hàng: " + txtTenKhach.getText() + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Khuyến Mãi</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (Vector vec : dsGioHang) {
            String maSP = vec.get(0).toString();
            double giaBanGoc = Double.parseDouble(vec.get(4).toString());
            int soLg = Integer.parseInt(vec.get(3).toString());

            double giaGiamKM = ctkmDao.layGiaGiamKhuyenMai(maSP);
//            System.out.println(giaGiamKM);
            double giaSauKM = giaBanGoc - giaGiamKM;

            double thanhTien = giaSauKM * soLg;
            tongTien += thanhTien;
            // Cập nhật tổng tiền và thêm thông tin sản phẩm vào hóa đơn...
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + maSP + "</td>";
            hd += "<td style='text-align:left;'>" + vec.get(1) + "</td>";
            hd += "<td style='text-align:center;'>" + soLg + "</td>";
            hd += "<td style='text-align:center;'>" + dcf.format(giaBanGoc) + "</td>";  // Use 'price' variable here
            hd += "<td style='text-align:center;'>" + dcf.format(giaGiamKM * soLg) + "</td>";  // Use 'discountAmount' variable here

            hd += "<td style='text-align:center;'>" + dcf.format(thanhTien) + "</td>";  // Use 'lineTotal' variable here
            hd += "</tr>";
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng Tiền Phải Trả</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        txtHoaDon.setText(hd);
        txtTongTien.setText(dcf.format(tongTien));
    }

    public void luuHoaDon(String maHD, LocalDate ngayTao, String maKh, String maNV) throws java.rmi.RemoteException{
        HoaDon hd = new HoaDon();
        hd.setMaHD(maHD);
        hd.setNgayTao(ngayTao);
        hd.setMaKh(new KhachHang(maKh));
        hd.setMaNV(new NhanVien(nhanVien));
        //HoaDonImpl hdd = new HoaDonImpl();
        hdDao.themHD(hd);
    }

//    public void luuCTHoaDon(String maHD, String maSP, int soLuong, double donGia) {
//        ChiTietHoaDon cthd = new ChiTietHoaDon();
//        cthd.setMaHD(new HoaDon(maHD));
//        cthd.setMaSP(new SanPham(maSP));
//        cthd.setSoLuong(soLuong);
//        cthd.setDonGia(donGia);
//        ChiTietHoaDon_DAO dao = new ChiTietHoaDon_DAO();
//        dao.themHD(cthd);
//    }
    public void luuCTHoaDon(String maHD, String maSP, int soLuong, double donGia) throws java.rmi.RemoteException {
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        double giaGiam = ctkmDao.layGiaGiamKhuyenMai(maSP);
        double giaSauGiam = donGia - giaGiam;
        if (giaSauGiam < 0) {
            giaSauGiam = 0;
        }
        cthd.setMaHD(new HoaDon(maHD));
        cthd.setMaSP(new SanPham(maSP));
        cthd.setSoLuong(soLuong);
        cthd.setDonGia(giaSauGiam);
        //ChiTietHoaDonImpl dao = new ChiTietHoaDonImpl();
        ctHDDao.themHD(cthd);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane txtHoaDon;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
