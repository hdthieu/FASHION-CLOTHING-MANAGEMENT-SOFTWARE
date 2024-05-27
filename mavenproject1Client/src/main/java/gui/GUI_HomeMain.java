package gui;

//import Connection.ConnectSQL;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import gui.GUI_QuanLySP;
import gui.GUI_TimKiemSP;
import gui.GUI_LapHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.management.timer.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import url.RmiConfig;

public class GUI_HomeMain extends javax.swing.JFrame {

    private static final String URL = RmiConfig.RMI_URL;
    // Hello
    Connection conn;
    ResultSet rs;
//    private ConnectSQL cn = new ConnectSQL();
    public String username;
    private TaiKhoanDao ds;

    public GUI_HomeMain(String username) throws java.rmi.RemoteException {
        this.username = username;
        try {
            ds = (TaiKhoanDao) Naming.lookup(URL + "TaiKhoan");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initComponents();
        setLocationRelativeTo(null);
        this.setSize(new Dimension(1275, 870));
        this.setLocationRelativeTo(null);
        clock();
        setMenusBasedOnRole(menuSP, menuKH, menuHD, menuDDH, menuNV, menuTK, menuKM);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try {
                    GUI_LapHoaDon.xuLyThoat();
                } catch (RemoteException ex) {
                    Logger.getLogger(GUI_HomeMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        setUsername(username);

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    private String getPermissionForCurrentUser() {
//        try (Connection conn = cn.getConnection(); PreparedStatement st = conn.prepareStatement("SELECT quyenTruyCap FROM TaiKhoan WHERE tenTaiKhoan=?")) {
//            st.setString(1, getUsername());
//            try (ResultSet rs = st.executeQuery()) {
//                return rs.next() ? rs.getString("quyenTruyCap") : "Không xác định";
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return "Không xác định";
//        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mssql");
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t.quyenTruyCap FROM TaiKhoan t WHERE t.tenTaiKhoan = :tenTaiKhoan";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("tenTaiKhoan", getUsername());
            return query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Không xác định";
        }
    }

    public void clock() {
        javax.swing.Timer Time;

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(d);

        Time = new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss a");
                String time = st.format(dt);

                lblClock.setText("Ngày: " + date + "    Giờ: " + time);
            }
        });
        Time.start();
    }

    private void addMenu(JMenuBar menuBar, JMenu... menus) {
        for (JMenu menu : menus) {
            menuBar.add(menu);
            addMenuRecursive(menuBar, menu);
        }

        menuBar.revalidate();
        menuBar.repaint();
    }

    private void addMenuRecursive(JMenuBar menuBar, JMenu menu) {
        Component[] components = menu.getMenuComponents();
        for (Component component : components) {
            if (component instanceof JMenu) {
                addMenu(menuBar, (JMenu) component);
            }
        }
    }

    private void setMenusBasedOnRole(JMenu menuSP, JMenu menuKH, JMenu menuHD, JMenu menuDDH, JMenu menuNV, JMenu menuTK, JMenu menuKM) {
        String quyen = getPermissionForCurrentUser();
        JMenuBar menuBar = getJMenuBar();
        menuBar.removeAll();
        if ("Nhân Viên Bán Hàng".equals(quyen)) {
            addMenu(menuBar, menuSP, menuKH, menuHD, menuDDH, menuKM);
        } else if ("Quản Lý".equals(quyen)) {
            addMenu(menuBar, menuSP, menuKH, menuHD, menuDDH, menuNV, menuTK, menuKM);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        pnlBody = new javax.swing.JPanel();
        bgHome = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuSP = new javax.swing.JMenu();
        menuQLSP = new javax.swing.JMenuItem();
        menuTKSP = new javax.swing.JMenuItem();
        menuQLNCC = new javax.swing.JMenuItem();
        menuTKNCC = new javax.swing.JMenuItem();
        menuKH = new javax.swing.JMenu();
        menuQLKH = new javax.swing.JMenuItem();
        menuTKKH = new javax.swing.JMenuItem();
        menuHD = new javax.swing.JMenu();
        menuLHD = new javax.swing.JMenuItem();
        menuTKHD = new javax.swing.JMenuItem();
        menuDDH = new javax.swing.JMenu();
        menuLDDH = new javax.swing.JMenuItem();
        menuTKDDH = new javax.swing.JMenuItem();
        menuNV = new javax.swing.JMenu();
        menuQLNV = new javax.swing.JMenuItem();
        menuTKNV = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuTK = new javax.swing.JMenu();
        menuQLTK = new javax.swing.JMenuItem();
        menuKM = new javax.swing.JMenu();
        miniTaoKM = new javax.swing.JMenuItem();
        miniDSKM = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fashion_Phần Mềm Quản Lý Bán Hàng Quần Áo Thời Trang");
        setBackground(new java.awt.Color(153, 153, 153));

        jPanel1.setBackground(new java.awt.Color(12, 104, 180));

        lblClock.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setText("Ngày giờ hiển thị");

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("Tên tài khoản");

        btnDangXuat.setBackground(new java.awt.Color(204, 204, 204));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(0, 0, 0));
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(lblClock)
                .addGap(181, 181, 181)
                .addComponent(lblUser)
                .addGap(91, 91, 91)
                .addComponent(btnDangXuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblClock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addComponent(btnDangXuat))
        );

        pnlBody.setBackground(new java.awt.Color(102, 102, 102));
        pnlBody.setLayout(new java.awt.BorderLayout());

        bgHome.setBackground(new java.awt.Color(255, 102, 102));
        bgHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/background1.png"))); // NOI18N
        pnlBody.add(bgHome, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(12, 104, 180));

        menuSP.setBackground(new java.awt.Color(51, 255, 255));
        menuSP.setForeground(new java.awt.Color(236, 240, 241));
        menuSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_product.png"))); // NOI18N
        menuSP.setText("Sản Phẩm");
        menuSP.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLSP.setText("Quản Lý Sản Phẩm");
        menuQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLSPActionPerformed(evt);
            }
        });
        menuSP.add(menuQLSP);

        menuTKSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKSP.setText("Tìm Kiếm Sản Phẩm");
        menuTKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKSPActionPerformed(evt);
            }
        });
        menuSP.add(menuTKSP);

        menuQLNCC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLNCC.setText("Quản Lý Nhà Cung Cấp");
        menuQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLNCCActionPerformed(evt);
            }
        });
        menuSP.add(menuQLNCC);

        menuTKNCC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKNCC.setText("Tìm Kiếm Nhà Cung Cấp");
        menuTKNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKNCCActionPerformed(evt);
            }
        });
        menuSP.add(menuTKNCC);

        jMenuBar1.add(menuSP);

        menuKH.setForeground(new java.awt.Color(236, 240, 241));
        menuKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_KH.png"))); // NOI18N
        menuKH.setText("Khách Hàng");
        menuKH.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLKH.setText("Quản Lý Khách Hàng ");
        menuQLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLKHActionPerformed(evt);
            }
        });
        menuKH.add(menuQLKH);

        menuTKKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKKH.setText("Tìm Kiếm Khách Hàng");
        menuTKKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKKHActionPerformed(evt);
            }
        });
        menuKH.add(menuTKKH);

        jMenuBar1.add(menuKH);

        menuHD.setForeground(new java.awt.Color(236, 240, 241));
        menuHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_hoaDon.png"))); // NOI18N
        menuHD.setText("Hóa Đơn");
        menuHD.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuLHD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuLHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuLHD.setText("Lập Hóa Đơn");
        menuLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLHDActionPerformed(evt);
            }
        });
        menuHD.add(menuLHD);

        menuTKHD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKHD.setText("Tìm Kiếm Hóa Đơn");
        menuTKHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKHDActionPerformed(evt);
            }
        });
        menuHD.add(menuTKHD);

        jMenuBar1.add(menuHD);

        menuDDH.setForeground(new java.awt.Color(236, 240, 241));
        menuDDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_hoaDon.png"))); // NOI18N
        menuDDH.setText("Đơn Đặt Hàng");
        menuDDH.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuLDDH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuLDDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuLDDH.setText("Lập Đơn Đặt Hàng");
        menuLDDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLDDHActionPerformed(evt);
            }
        });
        menuDDH.add(menuLDDH);

        menuTKDDH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKDDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKDDH.setText("Tìm Kiếm Đơn Đặt Hàng");
        menuTKDDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKDDHActionPerformed(evt);
            }
        });
        menuDDH.add(menuTKDDH);

        jMenuBar1.add(menuDDH);

        menuNV.setForeground(new java.awt.Color(236, 240, 241));
        menuNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_staff.png"))); // NOI18N
        menuNV.setText("Nhân Viên");
        menuNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLNV.setText("Quản Lý Nhân VIên");
        menuQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLNVActionPerformed(evt);
            }
        });
        menuNV.add(menuQLNV);

        menuTKNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKNV.setText("Tìm Kiếm Nhân Viên");
        menuTKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKNVActionPerformed(evt);
            }
        });
        menuNV.add(menuTKNV);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        jMenuItem1.setText("Thống Kê");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuNV.add(jMenuItem1);

        jMenuBar1.add(menuNV);

        menuTK.setForeground(new java.awt.Color(236, 240, 241));
        menuTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_account.png"))); // NOI18N
        menuTK.setText("Tài Khoản");
        menuTK.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuQLTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLTK.setText("Quản Lý Tài Khoản");
        menuQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLTKActionPerformed(evt);
            }
        });
        menuTK.add(menuQLTK);

        jMenuBar1.add(menuTK);

        menuKM.setForeground(new java.awt.Color(236, 240, 241));
        menuKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/promotion.png"))); // NOI18N
        menuKM.setText("Khuyến Mãi");
        menuKM.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        miniTaoKM.setText("Tạo Khuyến Mãi");
        miniTaoKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miniTaoKMActionPerformed(evt);
            }
        });
        menuKM.add(miniTaoKM);

        miniDSKM.setText("Danh Sách Khuyến Mãi");
        miniDSKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miniDSKMActionPerformed(evt);
            }
        });
        menuKM.add(miniDSKM);

        jMenuBar1.add(menuKM);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1264, 857));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLNCCActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_NhaCungCap());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuQLNCCActionPerformed

    private void menuQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLSPActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_QuanLySP());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuQLSPActionPerformed

    private void menuTKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKSPActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_TimKiemSP());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKSPActionPerformed

    private void menuQLKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLKHActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_QuanLyKhachHang());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuQLKHActionPerformed

    private void menuTKKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKKHActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_TimKiemKH());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKKHActionPerformed

    private void menuLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLHDActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();

            pnlBody.add(new GUI_LapHoaDon(username));
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuLHDActionPerformed

    private void menuTKHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKHDActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_TimKiemHD());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKHDActionPerformed

    private void menuQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLNVActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_QuanLyNhanVien());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuQLNVActionPerformed

    private void menuTKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKNVActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_TimKiemNhanVien());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKNVActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_ThongKe());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLTKActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
            pnlBody.add(new GUI_TaiKhoan());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuQLTKActionPerformed

    private void miniTaoKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniTaoKMActionPerformed
        try {
            pnlBody.removeAll();
            pnlBody.add(new GUI_TaoKM());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_miniTaoKMActionPerformed

    private void miniDSKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniDSKMActionPerformed
        try {
            pnlBody.removeAll();
            pnlBody.add(new GUI_DanhSachKhuyenMai());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_miniDSKMActionPerformed

    private void menuTKNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKNCCActionPerformed
        try {
            pnlBody.removeAll();
            pnlBody.add(new GUI_TimKiemNhaCungCap());
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKNCCActionPerformed

    private void menuLDDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLDDHActionPerformed
        try {
            GUI_LapHoaDon.xuLyThoat();
            pnlBody.removeAll();
//            Tai ds = new TaiKhoanImpl();
            TaiKhoan tk = ds.getTaiKhoanTheoTK(username);
            pnlBody.add(new GUI_LapDonDatHang(tk.getMaNhanVien().getMaNhanVien()));
            pnlBody.repaint();
            pnlBody.revalidate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuLDDHActionPerformed

    private void menuTKDDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKDDHActionPerformed
        try {
            pnlBody.removeAll();
            pnlBody.add(new GUI_DanhSachDonDatHang());
            pnlBody.repaint();
            pnlBody.revalidate();}catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuTKDDHActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

        Login lg = new Login(ds);

        // Hiển thị màn hình đăng nhập
        lg.setVisible(true);

        // Đóng cửa sổ hiện tại
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgHome;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenu menuDDH;
    private javax.swing.JMenu menuHD;
    private javax.swing.JMenu menuKH;
    private javax.swing.JMenu menuKM;
    private javax.swing.JMenuItem menuLDDH;
    private javax.swing.JMenuItem menuLHD;
    private javax.swing.JMenu menuNV;
    private javax.swing.JMenuItem menuQLKH;
    private javax.swing.JMenuItem menuQLNCC;
    private javax.swing.JMenuItem menuQLNV;
    private javax.swing.JMenuItem menuQLSP;
    private javax.swing.JMenuItem menuQLTK;
    private javax.swing.JMenu menuSP;
    private javax.swing.JMenu menuTK;
    private javax.swing.JMenuItem menuTKDDH;
    private javax.swing.JMenuItem menuTKHD;
    private javax.swing.JMenuItem menuTKKH;
    private javax.swing.JMenuItem menuTKNCC;
    private javax.swing.JMenuItem menuTKNV;
    private javax.swing.JMenuItem menuTKSP;
    private javax.swing.JMenuItem miniDSKM;
    private javax.swing.JMenuItem miniTaoKM;
    private javax.swing.JPanel pnlBody;
    // End of variables declaration//GEN-END:variables
}
