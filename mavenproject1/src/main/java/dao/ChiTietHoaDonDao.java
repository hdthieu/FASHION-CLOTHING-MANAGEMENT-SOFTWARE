package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChiTietHoaDonDao extends Remote{
	

	public ArrayList<ChiTietHoaDon> getListHoaDon()  throws RemoteException;

	public ArrayList<ChiTietHoaDon> getListHoaDonTheoMaHD(String maHD) throws RemoteException;

	public boolean themHD(ChiTietHoaDon hd) throws RemoteException;

	// Thong ke
//	public ArrayList<ChiTietHoaDon> docTuBang();
	
	public List<ChiTietHoaDon> tKsanPham() throws RemoteException; // Đúng

	public List<ChiTietHoaDon> locDateTKSP(Date fromDate, Date toDate) throws RemoteException;

	// hoa don
	public List<ChiTietHoaDon> tKHDlenBang() throws RemoteException; // Đúng

	public List<ChiTietHoaDon> locDateTKHD(Date fromDate, Date toDate) throws RemoteException; 

	// khach hang
	
	public List<ChiTietHoaDon> docDLieuTKeKH() throws RemoteException;

	public List<ChiTietHoaDon> locDLieuTKeKH(Date fromDate, Date toDate) throws RemoteException;

	public List<ChiTietHoaDon> tKDSBHlenBang() throws RemoteException; // Đúng

	public List<ChiTietHoaDon> locDateDSBHNV(Date fromDate, Date toDate) throws RemoteException;
        // thong ke nhan vien
          public List<ChiTietHoaDon> locDLieuTKeNhanVien(Date fromDate, Date toDate) throws RemoteException;

        // thong ke san pham
         public long tinhTongSoLuongBanTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc)throws RemoteException;
        public String tinhTongDoanhThuTrongKhoangThoiGian(Date ngayBatDau,Date ngayKetThuc)throws RemoteException;
        public List<String> findBestSellingProductNames(Date ngayBatDau, Date ngayKetThuc) throws RemoteException;
        public List<Object[]> getSPbyDate(Date ngayBatDau, Date ngayKetThuc)throws RemoteException;
}
