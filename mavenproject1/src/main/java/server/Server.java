/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

//import Connection.ConnectSQL;
import daoimpl.ChiTietDonDatHangImpl;
import daoimpl.ChiTietHoaDonImpl;
import daoimpl.ChiTietKhuyenMaiImpl;
import daoimpl.DonDatHangImpl;
import daoimpl.HoaDonImpl;
import daoimpl.KhachHangImpl;
import daoimpl.KhuyenMaiImpl;
import daoimpl.LoaiSanPhamImpl;
import daoimpl.NhaCungCapImpl;
import daoimpl.NhanVienImpl;
import daoimpl.SanPhamImpl;
import daoimpl.TaiKhoanImpl;
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
import dao.ThongKe_DAO;
import daoimpl.ThongKeImp;
import entity.ChiTietDonDatHang;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import url.RmiConfig;

/**
 *
 * @author TriHieu
 */
public class Server {

//    private static final String URL = "rmi://DESKTOP-FDOTM33:7878/";
    private static final String URL = RmiConfig.RMI_URL;

    public static void main(String[] args) throws RemoteException, NamingException {

        Context context = new InitialContext();
        ChiTietDonDatHangDao cTDDHDao = new ChiTietDonDatHangImpl();
        ChiTietHoaDonDao ctHDDao = new ChiTietHoaDonImpl();
        ChiTietKhuyenMaiDao ctkmDao = new ChiTietKhuyenMaiImpl();
        DonDatHangDao dDHDao = new DonDatHangImpl();
        HoaDon_DAO hDDao = new HoaDonImpl();
        KhachHang_DAO kHDao = new KhachHangImpl();
        KhuyenMaiDao kmDao = new KhuyenMaiImpl();
        LoaiSanPham_DAO lsPDao = new LoaiSanPhamImpl();
        NhaCungCapDao nccDao = new NhaCungCapImpl();
        NhanVienDao nvDao = new NhanVienImpl();
        SanPham_DAO spDao = new SanPhamImpl();
        TaiKhoanDao tkDao = new TaiKhoanImpl();
        ThongKe_DAO tkeDao= new ThongKeImp();
        LocateRegistry.createRegistry(7878);
//		CourseDAO courseDAO = new CourseImpl(); // Java Remote Object
//		StudentDAO studentDAO = new StudentImpl(); // Java Remote Object
//		DepartmentDAO departmentDAO = new DepartmentImpl(); // Java Remote Object
//
//		
//      
        context.bind(URL + "ChiTietDonDatHang", cTDDHDao);
        context.bind(URL + "ChiTietHoaDon", ctHDDao);
        context.bind(URL + "ChiTietKhuyenMai", ctkmDao);
        context.bind(URL + "DonDatHang", dDHDao);
        context.bind(URL + "HoaDon", hDDao);
        context.bind(URL + "KhachHang", kHDao);
        context.bind(URL + "KhuyenMai", kmDao);
        context.bind(URL + "LoaiSanPham", lsPDao);
        context.bind(URL + "NhaCungCap", nccDao);
        context.bind(URL + "NhanVien", nvDao);
        context.bind(URL + "SanPham", spDao);
        context.bind(URL + "TaiKhoan", tkDao);
        context.bind(URL+"tkeDao",tkeDao);
        System.out.println("Server is running...");
    }
}
