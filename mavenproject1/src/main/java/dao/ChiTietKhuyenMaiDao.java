package dao;

import java.util.Date;
import java.util.List;

import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChiTietKhuyenMaiDao extends Remote {
	public List<ChiTietKhuyenMai> docTuBang() throws RemoteException;

	public void themChiTietKhuyenMai(String maKhuyenMai, String maSP, float giaGiam) throws RemoteException; // // test lại

	public String kiemTraTrangThaiKhuyenMaiCuaSanPham(String maSP)throws RemoteException;

	public boolean delete(String maKM)throws RemoteException;

	public boolean capNhatCTKhuyenMai(String maKM, float giaGiam)throws RemoteException; // test 

	public boolean loaiBoSanPhamTuKhuyenMai(String maSP)throws RemoteException;

	// ==================== tìm kiếm khuyến mãi ======================
	public List<ChiTietKhuyenMai> getListKMTheoMaKM(String maKM)throws RemoteException;

	public List<KhuyenMai> timKiemKhuyenMai(String maKM, Double giaMin, Double giaMax, Date ngayBD, Date ngayKT,
			String tenKM) throws RemoteException;

	public double layGiaGiamKhuyenMai(String maSP) throws RemoteException;

}
