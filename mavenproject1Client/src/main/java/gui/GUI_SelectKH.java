package gui;

import dao.KhachHang_DAO;
import entity.KhachHang;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import url.RmiConfig;

public class GUI_SelectKH extends JDialog {

    public static KhachHang khachHangTimDuoc = null;
    private static final String URL = RmiConfig.RMI_URL;
    private KhachHang_DAO khDao;

    public GUI_SelectKH() {
        
        try {
            khDao = (KhachHang_DAO) Naming.lookup(URL + "KhachHang");
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        addControls();
        addEvents();

        this.setSize(650, 450);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblKhachHang;
    private DefaultTableModel dtmKhachHang;
    private JButton btnChon, btnThemKhach;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã KH");
        dtmKhachHang.addColumn("Họ Tên");
        dtmKhachHang.addColumn("Số Điện Thoại");
        dtmKhachHang.addColumn("Địa Chỉ");
        dtmKhachHang.addColumn("Giới Tính");
        tblKhachHang = new JTable(dtmKhachHang);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        pnTable.add(scrKhachHang, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
//        btnThemKhach = new JButton("Thêm khách");
        btnChon.setFont(font);
//        btnThemKhach.setFont(font);
        pnButton.add(btnChon);
//        pnButton.add(btnThemKhach);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));
//        btnThemKhach.setPreferredSize(new Dimension(130, 40));

        try {
            loadDataLenTable();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addEvents() {
        txtTuKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonKhachHang();
            }
        });

//        btnThemKhach.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                xuLyThemKhach();
//            }
//        });
    }

    private void xuLyChonKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            String ma = tblKhachHang.getValueAt(row, 0) + "";
            String ten = tblKhachHang.getValueAt(row, 1) + "";
            String SDT = tblKhachHang.getValueAt(row, 2) + "";
            String diaChi = tblKhachHang.getValueAt(row, 3) + "";
            String gioiTinh = tblKhachHang.getValueAt(row, 4) + "";
            boolean gt;
            if (gioiTinh.equals("Nam")) {
                gt = true;
            } else {
                gt = false;
            }

            khachHangTimDuoc = new KhachHang(ma, ten, diaChi, SDT, gt);
        }
        this.dispose();
    }

    private void xuLyThemKhach() {

    }

    private void loadDataLenTable() throws java.rmi.RemoteException {
        //KhachHangImpl ds = new KhachHangImpl();
        List<KhachHang> list = khDao.docTuBang();
        String gt;
        for (KhachHang khachHang : list) {
            if (khachHang.isGioiTinh()) {
                gt = "Nam";
            } else {
                gt = "Nữ";
            }

            String[] rowData = {khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getSoDienThoai() + "", khachHang.getDiaChi(), gt + ""};
            dtmKhachHang.addRow(rowData);
        }
        tblKhachHang.setModel(dtmKhachHang);
    }

    private void loadDataLenTable(String tuKhoa) {

    }

}
