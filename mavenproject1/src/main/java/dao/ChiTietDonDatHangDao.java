package dao;

import java.util.ArrayList;
import java.util.List;

import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface  ChiTietDonDatHangDao extends Remote{
	 ArrayList<ChiTietDonDatHang> getListDonDatHang()  throws RemoteException;
     ArrayList<ChiTietDonDatHang> getListDonDatHangTheoMaDDH(String maDDH)  throws RemoteException;
     boolean themDDH(ChiTietDonDatHang ddh)  throws RemoteException;
	
}
