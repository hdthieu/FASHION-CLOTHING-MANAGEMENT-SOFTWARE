package daoimpl;


import java.util.ArrayList;
import java.util.List;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NhaCungCapImpl extends UnicastRemoteObject implements NhaCungCapDao {
	private EntityManager em;

	public NhaCungCapImpl() throws RemoteException{
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }
	
	@Override
	public boolean create(NhaCungCap nhaCungCap) throws RemoteException{
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nhaCungCap);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
		
	}
	public int tinhSoLuong() throws RemoteException{
        String query = "SELECT COUNT(n) FROM NhaCungCap n";
        Long count = em.createQuery(query, Long.class).getSingleResult();
        return count.intValue();
    }
	@Override
	public boolean update(NhaCungCap nhaCungCap) {{
		    EntityTransaction tx = em.getTransaction();
		    try {
		        tx.begin();
		        NhaCungCap ncc = em.find(NhaCungCap.class, nhaCungCap.getMaNhaCungCap()); 
		        if (ncc != null) {
		            ncc.setTenNhaCungCap(nhaCungCap.getTenNhaCungCap());
		            ncc.setDiaChi(nhaCungCap.getDiaChi());
		            ncc.setSdt(nhaCungCap.getSdt());
		            em.merge(ncc); 
		            tx.commit();
		            return true;
		        } 
		    } catch (Exception e) {
		        tx.rollback();
		        e.printStackTrace();
		    }
		    return false;
		}
		
	}

	@Override
	public boolean delete(String maNhaCungCap) throws RemoteException{
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			NhaCungCap nhacungcap = em.find(NhaCungCap.class, maNhaCungCap);
			em.remove(nhacungcap);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
		
	}
	@Override
	public ArrayList<NhaCungCap> docTuBang() throws RemoteException{
		return (ArrayList<NhaCungCap>) em.createNamedQuery("NhaCungCap.docTuBang", NhaCungCap.class).getResultList();
	}
	@Override
	 public ArrayList<NhaCungCap> searchNhaCungCap(String maNCC, String tenNCC,  String soDienThoai,String diaChi) throws RemoteException{
        TypedQuery<NhaCungCap> query = em.createNamedQuery("NhaCungCap.searchNhaCungCap", NhaCungCap.class);
        query.setParameter("maNhaCungCap", maNCC);
        query.setParameter("tenNhaCungCap", tenNCC);
         query.setParameter("soDienThoai", soDienThoai);
        query.setParameter("diaChi", diaChi);
        return (ArrayList<NhaCungCap>) query.getResultList();
    }
	public static void main(String[] args) throws RemoteException{
		NhaCungCapImpl ncc = new NhaCungCapImpl();
		// test docTuBang
//		ArrayList<NhaCungCap> list = ncc.docTuBang();
//		for (NhaCungCap nhaCungCap : list) {
//			System.out.println(nhaCungCap);
//		}
		// test xóa 
//		boolean xoancc=ncc.delete("NCC001");
//		if(xoancc) {
//            System.out.println("Xóa thành công");
//            } else {
//            	System.out.println("Xóa thất bại");
//        }
//		test thêm		
//		NhaCungCap newncc = new NhaCungCap("NCC021", "Nha cung cap 1", "Dia chi 1", "0123456789");
//		boolean themncc=ncc.create(newncc);
//		if (themncc) {
//			System.out.println("Thêm thành công");
//		} else {
//			System.out.println("Thêm thất bại");
//		}
		// test update		
//		NhaCungCap newncc2 = new NhaCungCap("NCC020", "Adidas", "0369566285", "Hồ Chí Minh");
//		boolean updatencc=ncc.update(newncc2);
//		if (updatencc) {
//			System.out.println("Update thành công");
//		} else {
//			System.out.println("Update thất bại");
//		}
		// test search
		ArrayList<NhaCungCap> list = ncc.searchNhaCungCap("NCC020", "", "", "");
		for (NhaCungCap nhaCungCap : list) {
			System.out.println(nhaCungCap);
		}
	}

}
