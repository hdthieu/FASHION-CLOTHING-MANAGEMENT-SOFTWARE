package dao;

import java.util.ArrayList;
import java.util.List;

import entity.DonDatHang;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DonDatHangDao extends Remote{
	 	ArrayList<DonDatHang> getListDonDatHang() throws RemoteException;
	    boolean themDDH(DonDatHang ddh) throws RemoteException;
	    double tinhTongTienTheoMaDonDatHang(String maDonDatHang) throws RemoteException;
	    String layTrangThaiDonHang(String maDonDatHang) throws RemoteException;
	    boolean capNhatTrangThaiDonHang(String maDonHang, String trangThaiMoi) throws RemoteException;
	    ArrayList<DonDatHang> timKiemDonDatHang(String ma, String ngayTu, String ngayDen, String maKh, String maNV, double tongTienTu, double tongTienDen, String soDienThoai) throws RemoteException;
}
