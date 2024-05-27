package daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ChiTietKhuyenMaiDao;
import entity.ChiTietKhuyenMai;
import entity.KhuyenMai;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChiTietKhuyenMaiImpl extends UnicastRemoteObject implements ChiTietKhuyenMaiDao {

    private EntityManager em;

    public ChiTietKhuyenMaiImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    public List<ChiTietKhuyenMai> getChiTietByKhuyenMai(KhuyenMai khuyenMai) throws RemoteException {
        return em.createQuery("SELECT ckm FROM ChiTietKhuyenMai ckm WHERE ckm.khuyenMai = :khuyenMai",
                ChiTietKhuyenMai.class).setParameter("khuyenMai", khuyenMai).getResultList();
    }

    @Override
    public List<ChiTietKhuyenMai> docTuBang() throws RemoteException {
        String jpql = "SELECT NEW entity.ChiTietKhuyenMai("
                + "NEW entity.KhuyenMai(km.maKhuyenMai, km.tenKhuyenMai, km.moTa, km.ngayBatDau, km.ngayKetThuc, km.trangThai), NEW entity.SanPham(NEW entity.LoaiSanPham(lsp.maLoai, lsp.tenLoai)), ctkm.giaGiam) "
                + "FROM ChiTietKhuyenMai ctkm "
                + "JOIN ctkm.khuyenMai km "
                + "JOIN ctkm.sanPham sp "
                + "JOIN sp.loaiSP lsp "
                + "GROUP BY km.maKhuyenMai, km.tenKhuyenMai, km.moTa, ctkm.giaGiam, km.ngayBatDau, km.ngayKetThuc, km.trangThai, lsp.tenLoai, lsp.maLoai";
        return em.createQuery(jpql, ChiTietKhuyenMai.class).getResultList();
    }

    @Override
    public void themChiTietKhuyenMai(String maKhuyenMai, String maSP, float giaGiam) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai();
            KhuyenMai km = em.find(KhuyenMai.class, maKhuyenMai);
            SanPham sp = em.find(SanPham.class, maSP);
            ChiTietKhuyenMai cTKM = new ChiTietKhuyenMai(km, sp, giaGiam);
            em.persist(cTKM);
            tx.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(String maKM) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Delete all ChiTietKhuyenMai entries for a specific KhuyenMai
            int deletedCount = em
                    .createQuery("DELETE FROM ChiTietKhuyenMai c WHERE c.khuyenMai.maKhuyenMai = :maKhuyenMai")
                    .setParameter("maKhuyenMai", maKM).executeUpdate();
            tx.commit();
            return deletedCount > 0; // return true if any rows were deleted
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean capNhatCTKhuyenMai(String maKM, float giaGiam) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "UPDATE ChiTietKhuyenMai c SET c.giaGiam = :giaGiam WHERE c.khuyenMai.maKhuyenMai = :maKhuyenMai";
            Query query = em.createQuery(jpql);
            query.setParameter("giaGiam", giaGiam);
            query.setParameter("maKhuyenMai", maKM);
            int result = query.executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KhuyenMai> timKiemKhuyenMai(String maKM, Double giaMin, Double giaMax, Date ngayBD, Date ngayKT,
            String tenKM) throws RemoteException {
        List<KhuyenMai> ketQua = null;
        try {
            String jpql = "SELECT DISTINCT km FROM KhuyenMai km "
                    + "JOIN km.setChiTietKhuyenMai ctkm "
                    + " WHERE 1=1 "
                    + "AND (:maKM IS NULL OR km.maKhuyenMai LIKE CONCAT('%', :maKM, '%')) "
                    + "AND (:tenKM IS NULL OR km.tenKhuyenMai LIKE CONCAT('%', :tenKM, '%'))";

            if (giaMin != null && giaMax != null) {
                jpql += " AND ctkm.giaGiam BETWEEN :giaMin AND :giaMax";
            } else if (giaMin != null) {
                jpql += " AND ctkm.giaGiam >= :giaMin";
            } else if (giaMax != null) {
                jpql += " AND ctkm.giaGiam <= :giaMax";
            }

            if (ngayBD != null && ngayKT != null) {
                jpql += " AND km.ngayBatDau BETWEEN :ngayBD AND :ngayKT";
            } else if (ngayBD != null) {
                jpql += " AND km.ngayBatDau >= :ngayBD";
            } else if (ngayKT != null) {
                jpql += " AND km.ngayKetThuc <= :ngayKT";
            }

            TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);

            // Đặt các tham số của truy vấn
            query.setParameter("maKM", maKM);
            query.setParameter("tenKM", tenKM);
            // Đặt giá trị cho giá giảm
            if (giaMin != null && giaMax != null) {
                query.setParameter("giaMin", giaMin);
                query.setParameter("giaMax", giaMax);
            } else if (giaMin != null) {
                query.setParameter("giaMin", giaMin);
            } else if (giaMax != null) {
                query.setParameter("giaMax", giaMax);
            }

            // Đặt giá trị cho ngày
            if (ngayBD != null && ngayKT != null) {
                query.setParameter("ngayBD", ngayBD);
                query.setParameter("ngayKT", ngayKT);
            } else if (ngayBD != null) {
                query.setParameter("ngayBD", ngayBD);
            } else if (ngayKT != null) {
                query.setParameter("ngayKT", ngayKT);
            }

            // Lấy kết quả
            ketQua = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public List<ChiTietKhuyenMai> getListKMTheoMaKM(String maKM) throws RemoteException {
        List<ChiTietKhuyenMai> dsCTKhuyenMai = null;
        try {
            // String jpql = "SELECT c FROM ChiTietKhuyenMai c JOIN c.sanPham s WHERE c.khuyenMai.maKhuyenMai = :maKM";
            String jpql = "SELECT c FROM ChiTietKhuyenMai c JOIN c.sanPham s WHERE c.khuyenMai.maKhuyenMai = :maKM";

            Query query = em.createQuery(jpql);
            query.setParameter("maKM", maKM);
            dsCTKhuyenMai = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTKhuyenMai;
    }

    @Override
    public double layGiaGiamKhuyenMai(String maSP) throws RemoteException {
        double giaGiam = 0.0;
        try {
            String jql = "SELECT ctkm.giaGiam FROM ChiTietKhuyenMai ctkm " + " JOIN ctkm.khuyenMai km "
                    + " WHERE ctkm.sanPham.maSP = :maSP AND km.trangThai = :trangThai";

            TypedQuery<Double> query = em.createQuery(jql, Double.class);
            query.setParameter("maSP", maSP);
            query.setParameter("trangThai", "active");
            List<Double> results = query.getResultList();

            if (!results.isEmpty()) {
                giaGiam = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giaGiam;
    }

    @Override
    public boolean loaiBoSanPhamTuKhuyenMai(String maSP) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int deletedCount = em.createQuery("DELETE FROM ChiTietKhuyenMai ctkm WHERE ctkm.sanPham.maSP = :maSP")
                    .setParameter("maSP", maSP).executeUpdate();
            tx.commit();
            return deletedCount > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String kiemTraTrangThaiKhuyenMaiCuaSanPham(String maSP) throws RemoteException {
        try {
            String jpql = "SELECT km.trangThai FROM ChiTietKhuyenMai ctkm " + "JOIN ctkm.khuyenMai km "
                    + "WHERE ctkm.sanPham.maSP = :maSP AND (km.trangThai = :trangThai1 OR km.trangThai = :trangThai2)";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maSP", maSP);
            query.setParameter("trangThai1", "active");
            query.setParameter("trangThai2", "upcoming");
            List<String> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws RemoteException {
        ChiTietKhuyenMaiImpl ctkmImpl = new ChiTietKhuyenMaiImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		List<ChiTietKhuyenMai> list = ctkmImpl.docTuBang();
//		list.forEach(x -> System.out.println(x));

//		String maKM, Double giaMin, Double giaMax, Date ngayBD, Date ngayKT,
        try {

            Date ngayBatDau = sdf.parse("2021-11-15"); // Ví dụ ngày bắt đầu
            Date ngayKetThuc = sdf.parse("2020-12-15"); // Ví dụ ngày kết thúc

            List<KhuyenMai> ketQuaTimKiem = ctkmImpl.timKiemKhuyenMai("KM009", null, null, null, null,
                    null);
            System.out.println("Ket qua tim kiem khuyen mai:");
            for (KhuyenMai ctkm : ketQuaTimKiem) {
                System.out.println(ctkm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//		 String maSP = "SP010";
//		    // Remove the product from all promotions
//		    boolean isRemoved = ctkmImpl.loaiBoSanPhamTuKhuyenMai(maSP);
//		    if (isRemoved) {
//		        System.out.println("Product with ID " + maSP + " was successfully removed from all promotions.");
//		    } else {
//		        System.out.println("Failed to remove product with ID " + maSP + " from promotions.");
//		    }	
//		String trangThai = ctkmImpl.kiemTraTrangThaiKhuyenMaiCuaSanPham("SP012");
//		if (trangThai != null) {
//			System.out.println("Trạng thái khuyến mãi của sản phẩm " + "SP012" + ": " + trangThai);
//		} else {
//			System.out.println("Không tìm thấy trạng thái khuyến mãi cho sản phẩm " + "SP012");
//		}
//        List<ChiTietKhuyenMai> listKM = ctkmImpl.getListKMTheoMaKM("KM001");
//        System.out.println(listKM.size());
//		double giaGiamSP010 = ctkmImpl.layGiaGiamKhuyenMai("SP010");
//		System.out.println("Gia giam cho SP010: " + giaGiamSP010);
//		double giaGiamSP012 = ctkmImpl.layGiaGiamKhuyenMai("SP012");
//		System.out.println("Gia giam cho SP012: " + giaGiamSP012);
    }

}

//}
