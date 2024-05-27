package dao;

import java.util.List;
import java.sql.*;
import entity.KhuyenMai;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KhuyenMaiDao extends Remote{
	public boolean checkTrungMa(String maCanKiemTra) throws RemoteException;

	public void themKhuyenMai(KhuyenMai khuyenMai) throws RemoteException;

	public boolean delete(String maKM) throws RemoteException;

	public boolean capNhatKhuyenMai(String maKM, String tenKM, String moTa, java.util.Date ngayBatDau, java.util.Date ngayKetThuc, String trangThai) throws RemoteException;
}
