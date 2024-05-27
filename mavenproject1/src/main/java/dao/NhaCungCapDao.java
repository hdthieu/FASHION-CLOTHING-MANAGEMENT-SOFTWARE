package dao;
import entity.NhaCungCap;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
public interface NhaCungCapDao extends Remote{
	public ArrayList<NhaCungCap> docTuBang() throws RemoteException;
	public boolean create(NhaCungCap nhaCungCap) throws RemoteException;
	public boolean update(NhaCungCap nhaCungCap) throws RemoteException;
	public boolean delete(String maNhaCungCap) throws RemoteException;
	public int tinhSoLuong() throws RemoteException;
	public ArrayList<NhaCungCap> searchNhaCungCap(String maNCC, String tenNCC,String soDienThoai ,String diaChi ) throws RemoteException;
}
