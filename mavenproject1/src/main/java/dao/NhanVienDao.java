package dao;

import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NhanVienDao extends Remote{

    public ArrayList<NhanVien> docTuBang() throws RemoteException ;

    public NhanVien getNhanVienTheoMa(String maNV) throws RemoteException;

    public boolean create(NhanVien nhanVien) throws RemoteException;

    public boolean update(NhanVien nhanVien) throws RemoteException;

    public boolean delete(String maNV)throws RemoteException;

    public ArrayList<NhanVien> searchEmployees(String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh, String SDT, String email, String diaChi, String chucVu) throws RemoteException;
}
