package daoimpl;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import dao.KhuyenMaiDao;
import entity.KhuyenMai;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KhuyenMaiImpl extends UnicastRemoteObject implements KhuyenMaiDao {

    private EntityManager em;

    public KhuyenMaiImpl() throws RemoteException {
        // TODO Auto-generated constructor stub
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    public List<KhuyenMai> getAllKhuyenMai() throws RemoteException {
        return em.createQuery("SELECT km FROM KhuyenMai km", KhuyenMai.class).getResultList();
    }

    @Override
    public void themKhuyenMai(KhuyenMai khuyenMai) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(khuyenMai);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(String maKM) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            KhuyenMai km = em.find(KhuyenMai.class, maKM);
            if (km != null) {
                em.remove(km);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean checkTrungMa(String maCanKiemTra) throws RemoteException {
        try {
            String jpql = "SELECT COUNT(km) FROM KhuyenMai km WHERE km.maKhuyenMai = :maKhuyenMai";
            Long count = em.createQuery(jpql, Long.class).setParameter("maKhuyenMai", maCanKiemTra).getSingleResult();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatKhuyenMai(String maKM, String tenKM, String moTa, Date ngayBatDau, Date ngayKetThuc, String trangThai) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            KhuyenMai khuyenMai = em.find(KhuyenMai.class, maKM);
            if (khuyenMai == null) {
                return false;
            }
            khuyenMai.setTenKhuyenMai(tenKM);
            khuyenMai.setMoTa(moTa);
            khuyenMai.setNgayBatDau(ngayBatDau);
            khuyenMai.setNgayKetThuc(ngayKetThuc);
            khuyenMai.setTrangThai(trangThai);
            em.merge(khuyenMai);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws RemoteException {
//		KhuyenMaiImpl km = new KhuyenMaiImpl();
//		boolean trungMa = km.checkTrungMa("KM105");
//		 if (trungMa) {
//		        System.out.println("Mã " + "KM105" + " đã tồn tại trong cơ sở dữ liệu.");
//		    } else {
//		        System.out.println("Mã " + "KM105" + " không tồn tại trong cơ sở dữ liệu.");
//		    }
    }

}
