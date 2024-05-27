/*
	@ (#) KhachHangImpl.java		Apr 11, 2024
 */
package daoimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.server.UnicastRemoteObject;

/*
@author: Đào Thanh Phú
@date: Apr 11, 2024
 */
public class KhachHangImpl extends UnicastRemoteObject implements KhachHang_DAO {

    private EntityManager em;

    public KhachHangImpl() throws RemoteException {
        // TODO Auto-generated constructor stub
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public boolean create(KhachHang kh) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(kh);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<KhachHang> docTuBang() throws RemoteException {
        return (ArrayList<KhachHang>) em.createNamedQuery("KhachHang.docTuBang", KhachHang.class).getResultList();
    }

    @Override
    public boolean update(KhachHang newKhachHangInfo) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            KhachHang existingKhachHang = em.find(KhachHang.class, newKhachHangInfo.getMaKH()); // Tìm đối tượng KhachHang trong cơ sở dữ liệu bằng mã
            if (existingKhachHang != null) {
                // Cập nhật thông tin của đối tượng KhachHang đã tìm thấy
                existingKhachHang.setTenKH(newKhachHangInfo.getTenKH());
                existingKhachHang.setDiaChi(newKhachHangInfo.getDiaChi());
                existingKhachHang.setSoDienThoai(newKhachHangInfo.getSoDienThoai());
                existingKhachHang.setGioiTinh(newKhachHangInfo.isGioiTinh());
                em.merge(existingKhachHang); // Sử dụng merge để cập nhật thông tin đối tượng đã tồn tại trong cơ sở dữ liệu
                tx.commit();
                return true;
            }
//	        else {
//	            System.out.println("Không tìm thấy khách hàng với mã: " + newKhachHangInfo.getMaKH());
//	        }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maKH) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            KhachHang kh = em.find(KhachHang.class, maKH); // Tìm khách hàng dựa trên mã
            if (kh != null) {
                em.remove(kh); // Xóa khách hàng nếu tìm thấy
                tx.commit();
                return true;
            } else {
                System.out.println("Không tìm thấy khách hàng với mã " + maKH);
                //return false;
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<KhachHang> timKiemKhachHang(String maKH, String tenKH, String diaChi, String soDienThoai, String gioiTinh) throws RemoteException {
        String sql = "SELECT kh FROM KhachHang kh WHERE 1=1"
                + " AND (:maKH IS NULL OR kh.maKH LIKE CONCAT('%', :maKH, '%'))"
                + " AND (:tenKH IS NULL OR kh.tenKH LIKE CONCAT('%', :tenKH, '%'))"
                + " AND (:diaChi IS NULL OR kh.diaChi LIKE CONCAT('%', :diaChi, '%'))"
                + " AND (:soDienThoai IS NULL OR kh.soDienThoai LIKE CONCAT('%', :soDienThoai, '%'))";

        if (gioiTinh != null && !gioiTinh.isEmpty()) {
            sql += " AND kh.gioiTinh = :gioiTinh";
        }

        TypedQuery<KhachHang> query = em.createQuery(sql, KhachHang.class);

        // Đặt các tham số của truy vấn
        query.setParameter("maKH", maKH);
        query.setParameter("tenKH", tenKH);
        query.setParameter("diaChi", diaChi);
        query.setParameter("soDienThoai", soDienThoai);

        if (gioiTinh != null && !gioiTinh.isEmpty()) {
            boolean gt;
            if (gioiTinh.equals("Nam")) {
                gt = true;
            } else {
                gt = false;
            }

            query.setParameter("gioiTinh", gt);
        }

        // Thực hiện truy vấn và trả về kết quả
        return (ArrayList<KhachHang>) query.getResultList();
    }

    @Override
    public String layTenKhachHang(String maKhachHang) throws RemoteException {
//        TypedQuery<String> query = em.createNamedQuery("KhachHang.layTenKhachHang", String.class);
//        query.setParameter("maKH", maKhachHang);
//        //String khachHang = query.getSingleResult();
//        //query.setParameter("maKH", maKhachHang);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null; // Trả về null nếu không có kết quả
//        }

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            KhachHang kh = em.find(KhachHang.class, maKhachHang);
            if (kh != null) {
                tx.commit();
                return kh.getMaKH() + " - " + kh.getTenKH();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public static void main(String[] args) throws RemoteException {
//		KhachHang kh = new KhachHang("KH01", "Phú", "Hà Nội", "0123456789", true);
//		KhachHangImpl khImpl = new KhachHangImpl();
//		khImpl.create(kh);
//		System.out.println(khImpl.create(kh));

        KhachHangImpl khImpl = new KhachHangImpl();
//		ArrayList<KhachHang> list = khImpl.docTuBang();
//		list.forEach(x->System.out.println(x));

//		KhachHang kh = new KhachHang("KH200", "Hiếu lon", "Hà Nội", "0923456789", true);
//		System.out.println(khImpl.update(kh));
//		System.out.println(khImpl.delete("KH200"));
//		ArrayList<KhachHang> list = khImpl.timKiemKhachHang("000", null, "Hà Nội", null, null);
//		list.forEach(x -> System.out.println(x));
        System.out.println(khImpl.layTenKhachHang("KH01"));
    }

}
