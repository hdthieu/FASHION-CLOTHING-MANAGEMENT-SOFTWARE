/*
	@ (#) SanPhamImpl.java		Apr 12, 2024
 */
package daoimpl;

import java.util.ArrayList;
import java.util.List;

import dao.SanPham_DAO;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
@author: Đào Thanh Phú
@date: Apr 12, 2024
 */
public class SanPhamImpl extends UnicastRemoteObject implements SanPham_DAO {

    private EntityManager em;

    public SanPhamImpl() throws RemoteException {
        // TODO Auto-generated constructor stub
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<SanPham> docTuBang() throws RemoteException {
        // TODO Auto-generated method stub
        TypedQuery<SanPham> sp = em.createNamedQuery("SanPham.docTuBang", SanPham.class);
        return (ArrayList<SanPham>) sp.getResultList();

        //return null;
    }

    @Override
    public boolean create(SanPham sanPham) throws RemoteException {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sanPham);
            tx.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(SanPham sanPham) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SanPham existingSanPham = em.find(SanPham.class, sanPham.getMaSP());
            if (existingSanPham != null) {
                em.merge(sanPham);
                tx.commit();
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(String maSP) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SanPham sp = em.find(SanPham.class, maSP); // Tìm khách hàng dựa trên mã
            if (sp != null) {
                em.remove(sp); // Xóa khách hàng nếu tìm thấy
                tx.commit();
                return true;
            }
//	        else {
//	            System.out.println("Không tìm thấy khách hàng với mã " + maSP);
//	            //return false;
//	        }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SanPham getSanPhamTheoMa(String maSP) throws RemoteException {
        // TODO Auto-generated method stub
        return em.find(SanPham.class, maSP);
    }

    @Override
    public void capNhatSoLuongSP(String ma, int soLuongMat) throws RemoteException {
        // TODO Auto-generated method stub
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SanPham sp = em.find(SanPham.class, ma);
            if (sp != null) {
                int soLuong = sp.getSoLuong();
                sp.setSoLuong(soLuong + soLuongMat);
                em.merge(sp);
                tx.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            tx.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<SanPham> timKiemSanPham(String maSP, String tenSP, String loai, String nhaCC, double giaTu,
            double giaDen, int soLuongTu, int soLuongDen) throws RemoteException {
        String sql = "SELECT sp FROM SanPham sp WHERE 1=1"
                + " AND (:maSP IS NULL OR sp.maSP LIKE CONCAT('%', :maSP, '%'))"
                + " AND (:tenSP IS NULL OR sp.tenSP LIKE CONCAT('%', :tenSP, '%'))"
                + " AND (:loai IS NULL OR sp.loaiSP.maLoai LIKE CONCAT('%', :loai, '%'))"
                + " AND (:nhaCC IS NULL OR sp.nhaCungCap.maNhaCungCap LIKE CONCAT('%', :nhaCC, '%'))";

        // Xử lý giá
        if (giaTu >= 0.0 && giaDen >= 0.0) {
            sql += " AND sp.giaBan BETWEEN :giaTu AND :giaDen";
        } else if (giaTu >= 0.0) {
            sql += " AND sp.giaBan >= :giaTu";
        } else if (giaDen >= 0.0) {
            sql += " AND sp.giaBan <= :giaDen";
        }

        // Xử lý số lượng
        if (soLuongTu >= 0 && soLuongDen >= 0) {
            sql += " AND sp.soLuong BETWEEN :soLuongTu AND :soLuongDen";
        } else if (soLuongTu >= 0) {
            sql += " AND sp.soLuong >= :soLuongTu";
        } else if (soLuongDen >= 0) {
            sql += " AND sp.soLuong <= :soLuongDen";
        }

        TypedQuery<SanPham> query = em.createQuery(sql, SanPham.class);

        // Đặt các tham số của truy vấn
        query.setParameter("maSP", maSP);
        query.setParameter("tenSP", tenSP);
        query.setParameter("loai", loai);
        query.setParameter("nhaCC", nhaCC);
        // Đặt giá trị cho giá
        if (giaTu >= 0.0 && giaDen >= 0.0) {
            query.setParameter("giaTu", giaTu);
            query.setParameter("giaDen", giaDen);
        } else if (giaTu >= 0.0) {
            query.setParameter("giaTu", giaTu);
        } else if (giaDen >= 0.0) {
            query.setParameter("giaDen", giaDen);
        }

        // Đặt giá trị cho số lượng
        if (soLuongTu >= 0 && soLuongDen >= 0) {
            query.setParameter("soLuongTu", soLuongTu);
            query.setParameter("soLuongDen", soLuongDen);
        } else if (soLuongTu >= 0) {
            query.setParameter("soLuongTu", soLuongTu);
        } else if (soLuongDen >= 0) {
            query.setParameter("soLuongDen", soLuongDen);
        }
        
        return (ArrayList<SanPham>) query.getResultList();

    }

    // Khuyến mãi
    @Override
    public List<SanPham> docTuBangSPKM() throws RemoteException {
        String jpql = "SELECT sp FROM SanPham sp JOIN FETCH sp.loaiSP";
        TypedQuery<SanPham> query = em.createQuery(jpql, SanPham.class);
        return query.getResultList();
    }

    @Override
    public boolean laSanPhamCuaKhuyenMaiKhac(String maSP ) throws RemoteException {

        String jpql = "SELECT COUNT(ctkm) FROM ChiTietKhuyenMai ctkm "
                + "JOIN ctkm.khuyenMai km "
                + "JOIN ctkm.sanPham sp "
                + "WHERE sp.maSP = :maSP AND km.trangThai IN ('active', 'upcoming') ";

        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("maSP", maSP);
        Long count = query.getSingleResult();

        return count > 0;

    }

    @Override
    public boolean kTraSPTonTaiTrongKM(String maSP1, String maKhuyenMaiHienTai) throws RemoteException {
        String jpql = "SELECT COUNT(ctkm) FROM ChiTietKhuyenMai ctkm "
                + "WHERE ctkm.sanPham.maSP = :maSP AND ctkm.khuyenMai.maKhuyenMai = :maKhuyenMaiHienTai";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("maSP", maSP1);
        query.setParameter("maKhuyenMaiHienTai", maKhuyenMaiHienTai);

        Long count = query.getSingleResult();

        return count > 0;
    }

    // Đơn đặt hàng
    @Override
    public String layTenSanPhamTheoMa(String maSanPham) throws RemoteException {
        Query query = em.createQuery("select s.tenSP from SanPham s where s.maSP = :maSP");
        query.setParameter("maSP", maSanPham);
        return (String) query.getSingleResult();
    }

    @Override
    public String layTenLoaiSanPhamTheoMa(String maSanPham) throws RemoteException {
        Query query = em.createQuery("select s.loaiSP.tenLoai from SanPham s where s.maSP = :maSP");
        query.setParameter("maSP", maSanPham);
        return (String) query.getSingleResult();
    }

    public static void main(String[] args) throws RemoteException {
        SanPhamImpl sp = new SanPhamImpl();
//        System.out.println(sp.docTuBangSPKM());
        System.err.println(sp.laSanPhamCuaKhuyenMaiKhac("SP014"));

//		ArrayList<SanPham> list = sp.docTuBang();
//		list.forEach(x->System.out.println(x));
//		SanPham sanPham = new SanPham("SP001", null, new LoaiSanPham("L002"), 500, 500, 500, new NhaCungCap("NCC006"), "abc.jpg");
//		sp.create(sanPham);
//		System.out.println("Thêm thành công");
        // sanPham.setGiaBan(1000);
        // sp.update(sanPham);
//		System.out.println(sp.update(sanPham));
        // System.out.println(sp.delete("SP1000"));
        // System.out.println(sp.getSanPhamTheoMa("SP001"));
//		sp.capNhatSoLuongSP("SP001", -10);
//		System.out.println(sp.getSanPhamTheoMa("SP001"));
//		ArrayList<SanPham> list = sp.timKiemSanPham("", null, null, null, 1500000, -1, 480, 500);
//		list.forEach(x->System.out.println(x));
        // System.out.println(sp.layTenSanPhamTheoMa("SP001"));
        // System.out.println(sp.layTenLoaiSanPhamTheoMa("SP001"));
//		List<SanPham> list = sp.docTuBangSPKM();
//		list.forEach(x->System.out.println(x));
        // System.out.println(sp.kTraSPTonTaiTrongKM("SP001", "KM001"));
    }

}
