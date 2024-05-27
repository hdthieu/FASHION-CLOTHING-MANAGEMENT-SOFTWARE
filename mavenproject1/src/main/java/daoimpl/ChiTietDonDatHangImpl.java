package daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import dao.ChiTietDonDatHangDao;
import entity.ChiTietDonDatHang;
import entity.DonDatHang;
import entity.SanPham;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChiTietDonDatHangImpl extends UnicastRemoteObject implements ChiTietDonDatHangDao {

    private EntityManager em;
    private EntityTransaction et;

    public ChiTietDonDatHangImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<ChiTietDonDatHang> getListDonDatHang() throws RemoteException {
        TypedQuery<ChiTietDonDatHang> query = em.createQuery("SELECT d FROM ChiTietDonDatHang d", ChiTietDonDatHang.class);
        return (ArrayList<ChiTietDonDatHang>) query.getResultList();
    }

    @Override
    public ArrayList<ChiTietDonDatHang> getListDonDatHangTheoMaDDH(String maDDH) throws RemoteException {
        TypedQuery<ChiTietDonDatHang> query = em.createQuery(
                "SELECT c FROM ChiTietDonDatHang c WHERE c.maDDH.maDDH = :maDDH", ChiTietDonDatHang.class);
        query.setParameter("maDDH", maDDH);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public boolean themDDH(ChiTietDonDatHang ddh) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ddh);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]) throws RemoteException {
        ChiTietDonDatHangDao ctddh = new ChiTietDonDatHangImpl();
        // Test getListDonDatHang
        ArrayList<ChiTietDonDatHang> list = ctddh.getListDonDatHang();
        for (ChiTietDonDatHang chiTiet : list) {
            System.out.println(chiTiet);
        }
        // Test	getListDonDatHangTheoMaDDH
//		ArrayList<ChiTietDonDatHang> list1 = (ArrayList<ChiTietDonDatHang>) ctddh.getListDonDatHangTheoMaDDH("DDH001");
//		 for (ChiTietDonDatHang chiTiet : list1) {
//	            System.out.println(chiTiet);
//	        }
        // Test themDDH
//		ChiTietDonDatHang ctddhnew=new ChiTietDonDatHang(new DonDatHang("DDH012"),new SanPham("SP001"),10,(double) 50000);
//		boolean result=ctddh.themDDH(ctddhnew);
//		if(result) {
//			System.out.println("Them thanh cong");
//		}
//		else {
//			System.out.println("Them that bai");
//		}
    }
}
