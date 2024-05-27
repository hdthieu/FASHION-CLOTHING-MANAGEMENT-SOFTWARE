package daoimpl;

import java.util.ArrayList;
import java.util.List;

import dao.TaiKhoanDao;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaiKhoanImpl extends UnicastRemoteObject implements TaiKhoanDao {

    private EntityManager em;

    public TaiKhoanImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public TaiKhoan checkLogin(String username, String password) throws RemoteException {
        try {
            // Sử dụng JPQL để kiểm tra thông tin đăng nhập
            String qlString = "SELECT t FROM TaiKhoan t WHERE t.tenTaiKhoan = :username AND t.matKhau = :password";
            TypedQuery<TaiKhoan> query = em.createQuery(qlString, TaiKhoan.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            // Thực hiện truy vấn và trả về kết quả
            TaiKhoan taiKhoan = query.getSingleResult();

            // Không cần kiểm tra mật khẩu ở đây nữa vì truy vấn đã kiểm tra
            return taiKhoan; // Nếu tài khoản tồn tại và mật khẩu khớp, đối tượng TaiKhoan sẽ được trả về
        } catch (NoResultException e) {
            // Không tìm thấy tài khoản hoặc mật khẩu không đúng, trả về null
        }
        return null;
    }

    @Override
    public ArrayList<TaiKhoan> docTuBang() throws RemoteException {
        return (ArrayList<TaiKhoan>) em.createNamedQuery("TaiKhoan.docTuBang", TaiKhoan.class).getResultList();
    }

    @Override
    public TaiKhoan getTaiKhoanTheoTK(String tk) throws RemoteException {
        return em.createNamedQuery("TaiKhoan.getTaiKhoanTheoTK", TaiKhoan.class)
                .setParameter(1, tk)
                .getSingleResult();
    }

    @Override
    public boolean create(TaiKhoan taiKhoan) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(taiKhoan);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(TaiKhoan taiKhoan) throws RemoteException {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            TaiKhoan tk = em.find(TaiKhoan.class, taiKhoan.getTenTaiKhoan());
            if (tk != null) {
                tk.setMatKhau(taiKhoan.getMatKhau());
                tk.setQuyenTruyCap(taiKhoan.getQuyenTruyCap());
                tk.setMaNhanVien(taiKhoan.getMaNhanVien());
                em.merge(tk);
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
    public boolean delete(String tenTaiKhoan) throws RemoteException {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            TaiKhoan nhanvien = em.find(TaiKhoan.class, tenTaiKhoan);
            if (nhanvien != null) {
                em.remove(nhanvien);
                tx.commit();
                return true;
            } 
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) throws RemoteException {
        TaiKhoanImpl tk = new TaiKhoanImpl();
        //test docTuBang
//		ArrayList<TaiKhoan> list = tk.docTuBang();
//		for (TaiKhoan taiKhoan : list) {
//			System.out.println(taiKhoan.getTenTaiKhoan());
//		}
        //test delete
//		tk.delete("thanhluu");
        //test create
//		tk.create(new TaiKhoan(new NhanVien("NV001"), "thanhluu1", "123", "Nhân Viên Bán Hàng"));
        //test update
//		TaiKhoan taiKhoan = new TaiKhoan(new NhanVien("NV001"), "thanhluu2", "123456", "Nhân Viên Bán Hàng");
//		tk.update(taiKhoan);
        //test getTaiKhoanByTen
        System.out.println(tk.getTaiKhoanTheoTK("doanvanhau"));
    }

}
