/*
	@ (#) SanPham_DAO.java		Apr 12, 2024
*/
package dao;
/*
@author: Đào Thanh Phú
@date: Apr 12, 2024
*/

import java.util.ArrayList;
import java.util.List;

import entity.SanPham;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SanPham_DAO extends Remote {

	
	public ArrayList<SanPham> docTuBang() throws RemoteException;
	
	public boolean create(SanPham sanPham) throws RemoteException;
	
	public boolean update(SanPham sanPham) throws RemoteException;
	
	public boolean delete(String maSP) throws RemoteException;
	
	public SanPham getSanPhamTheoMa(String maSP) throws RemoteException;
	
	public void capNhatSoLuongSP(String ma, int soLuongMat) throws RemoteException;
	public ArrayList<SanPham> timKiemSanPham(String maSP, String tenSP, String loai, String nhaCC, double giaTu, double giaDen, int soLuongTu, int soLuongDen) throws RemoteException;
	
	// Đơn đặt hàng
	public String layTenSanPhamTheoMa(String maSanPham) throws RemoteException;
	public String layTenLoaiSanPhamTheoMa(String maSanPham) throws RemoteException;
	
	// khuyenMai
	public List<SanPham> docTuBangSPKM() throws RemoteException;
	public boolean laSanPhamCuaKhuyenMaiKhac(String maSP) throws RemoteException;
	public boolean kTraSPTonTaiTrongKM(String maSP, String maKhuyenMaiHienTai) throws RemoteException;
	
	

	

}
