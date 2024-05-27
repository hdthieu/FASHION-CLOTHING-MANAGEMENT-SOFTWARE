package gui;

import gui.GUI_HomeMain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
//import Connection.ConnectSQL;
import dao.ChiTietHoaDonDao;
import dao.ChiTietKhuyenMaiDao;
import dao.DonDatHangDao;
import dao.KhachHang_DAO;
import dao.KhuyenMaiDao;
import dao.LoaiSanPham_DAO;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.SanPham_DAO;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;

/**
 *
 * @author TriHieu
 */
public class Login extends javax.swing.JFrame {

    //private static final String URL = "rmi://DESKTOP-R6U97GL:7878/";
    private TaiKhoanDao taiKhoanDao;

    // Constructor nhận tất cả các DAO
    public Login(TaiKhoanDao tkDao) {
        this.taiKhoanDao = tkDao;

        initComponents();
    }

    public String getUsername() {
        return txtUserName.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        lblTenDangNhap = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cua-hang-thoi-trang.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng Nhập");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        Panel.setBackground(new java.awt.Color(255, 255, 255));
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTenDangNhap.setBackground(new Color(80, 53, 26)
        );
        lblTenDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenDangNhap.setText("Tên đăng nhập");
        Panel.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        txtPass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        Panel.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 260, 30));

        lblMatKhau.setBackground(new Color(80, 53, 26)
        );
        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMatKhau.setText("Mật khẩu");
        Panel.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, -1, -1));

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setText("Đăng Nhập");
        btnLogin.setBorder(new javax.swing.border.MatteBorder(null));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        Panel.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 120, 40));

        txtUserName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 260, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Đăng nhập ");
        Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 170, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cua-hang-thoi-trang.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 0, 1100, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        String username = txtUserName.getText();
        String password = txtPass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và mật khẩu.");
            return;
        }

        try {
            TaiKhoan user = taiKhoanDao.checkLogin(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                GUI_HomeMain homeMain = new GUI_HomeMain(username);
                homeMain.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối: " + e.getMessage());
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

}
