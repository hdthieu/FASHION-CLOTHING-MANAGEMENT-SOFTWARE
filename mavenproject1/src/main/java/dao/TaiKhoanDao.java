package dao;

import java.util.ArrayList;
import java.util.List;

import entity.TaiKhoan;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaiKhoanDao extends Remote{
	public ArrayList<TaiKhoan> docTuBang() throws RemoteException;
	public TaiKhoan getTaiKhoanTheoTK(String tk) throws RemoteException;
	public boolean create(TaiKhoan taiKhoan) throws RemoteException;
	public boolean update(TaiKhoan taiKhoan) throws RemoteException;
	public boolean delete(String ten) throws RemoteException;
        public TaiKhoan checkLogin(String username, String password) throws RemoteException;
}
