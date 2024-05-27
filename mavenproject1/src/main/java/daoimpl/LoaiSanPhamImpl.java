/*
	@ (#) LoaiSanPhamImpl.java		Apr 20, 2024
*/
package daoimpl;

import java.util.ArrayList;

import dao.LoaiSanPham_DAO;
import entity.LoaiSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
@author: Đào Thanh Phú
@date: Apr 20, 2024
*/
public class LoaiSanPhamImpl extends UnicastRemoteObject implements LoaiSanPham_DAO{

	private EntityManager em;
	
	public LoaiSanPhamImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}
	@Override
	public ArrayList<LoaiSanPham> docTuBang() throws RemoteException{
		String jpql = "SELECT lsp FROM LoaiSanPham lsp";
		return (ArrayList<LoaiSanPham>) em.createQuery(jpql, LoaiSanPham.class).getResultList();
	}

//	public static void main(String[] args) {
//		LoaiSanPhamImpl lsp = new LoaiSanPhamImpl();
//		System.out.println(lsp.docTuBang());
//	}
}
