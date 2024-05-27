/*
	@ (#) LoaiSanPham_DAO.java		Apr 20, 2024
*/
package dao;

import java.util.ArrayList;

import entity.LoaiSanPham;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
@author: Đào Thanh Phú
@date: Apr 20, 2024
*/
public interface LoaiSanPham_DAO extends Remote{

	public ArrayList<LoaiSanPham> docTuBang() throws RemoteException;
}
