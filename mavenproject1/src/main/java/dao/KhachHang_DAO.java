/*
	@ (#) KhachHang_DAO.java		Apr 11, 2024
*/
package dao;

import java.util.ArrayList;

import entity.KhachHang;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
@author: Đào Thanh Phú
@date: Apr 11, 2024
*/
public interface KhachHang_DAO extends Remote{
	 public boolean create(KhachHang khachHang) throws RemoteException;
	 public ArrayList<KhachHang> docTuBang() throws RemoteException;
	 public boolean update(KhachHang khachHang) throws RemoteException;
	 public boolean delete(String maKH) throws RemoteException;
	 public ArrayList<KhachHang> timKiemKhachHang(String maKH, String tenKH, String diaChi, String soDienThoai, String gioiTinh) throws RemoteException;
	 public String layTenKhachHang(String maKH) throws RemoteException;
}
