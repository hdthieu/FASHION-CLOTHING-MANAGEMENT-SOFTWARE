/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import gui.Login;
import dao.ChiTietDonDatHangDao;
import dao.ChiTietHoaDonDao;
import dao.ChiTietKhuyenMaiDao;
import dao.DonDatHangDao;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KhuyenMaiDao;
import dao.LoaiSanPham_DAO;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.SanPham_DAO;
import dao.TaiKhoanDao;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import url.RmiConfig;

public class Client {

//    private static final String URL = "rmi://DESKTOP-FDOTM33:7878/";
    private static final String URL = RmiConfig.RMI_URL;

    public static void main(String[] args) {
        try {
            // Kết nối tới RMI Registry và lấy đối tượng TaiKhoanDao
            TaiKhoanDao tkDao = (TaiKhoanDao) Naming.lookup(URL + "TaiKhoan");

            // Tạo và hiển thị form đăng nhập
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login(tkDao).setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
