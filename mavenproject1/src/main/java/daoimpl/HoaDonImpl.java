/*
	@ (#) HoaDonImpl.java		Apr 12, 2024
 */
package daoimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.HoaDon_DAO;

import entity.HoaDon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
@author: Đào Thanh Phú
@date: Apr 12, 2024
 */
public class HoaDonImpl extends UnicastRemoteObject implements HoaDon_DAO {

    private EntityManager em;

    public HoaDonImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<HoaDon> getListHoaDon() throws RemoteException {
        return (ArrayList<HoaDon>) em.createQuery("select hd from HoaDon hd", HoaDon.class).getResultList();
        // return null;
    }

    @Override
    public boolean themHD(HoaDon hd) throws RemoteException {
        // TODO Auto-generated method stub
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hd);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double tinhTongTienTheoMaHoaDon(String maHoaDon) throws RemoteException {
//		String sql = "SELECT SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGiaBan) AS TongThanhTien FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon WHERE HoaDon.maHoaDon = ?";

        String jpql = "select SUM (cthd.soLuong * cthd.donGia) from HoaDon hd inner join hd.setChiTietHoaDon cthd where hd.maHD = :maHD ";

        return em.createQuery(jpql, Double.class).setParameter("maHD", maHoaDon).getSingleResult();

    }

    @Override
    public ArrayList<HoaDon> timKiemHoaDon(String ma, String ngayTu, String ngayDen, String maKh, String maNV,
            double tongTienTu, double tongTienDen) throws RemoteException {
        String sql = "SELECT hd FROM HoaDon hd " + "INNER JOIN hd.setChiTietHoaDon cthd WHERE 1=1";

        if (ma != null && !ma.isEmpty()) {
            sql += " AND hd.maHD LIKE CONCAT('%', :maHD, '%')";
        }

        if (ngayTu != null && !ngayTu.isEmpty() && ngayDen != null && !ngayDen.isEmpty()) {
            // Thêm điều kiện ngày từ ngày đến ngày
            sql += " AND hd.ngayTao BETWEEN :ngayTu AND :ngayDen ";
        } else if (ngayTu != null && !ngayTu.isEmpty()) {
            // Thêm điều kiện ngày từ ngày
            sql += " AND hd.ngayTao >= :ngayTu ";
        } else if (ngayDen != null && !ngayDen.isEmpty()) {
            // Thêm điều kiện ngày đến ngày
            sql += " AND hd.ngayTao <= :ngayDen ";
        }

        if (maKh != null && !maKh.isEmpty()) {
            sql += " AND hd.maKh.maKH LIKE CONCAT('%', :maKh, '%') ";
        }

        if (maNV != null && !maNV.isEmpty()) {
            sql += " AND hd.maNV.maNhanVien LIKE CONCAT('%', :maNV, '%') ";
        }
        sql += " GROUP BY hd.maHD, hd.ngayTao, hd.maKh.maKH, hd.maNV.maNhanVien";

        // Xử lý tổng tiền
        if (tongTienTu >= 0 && tongTienDen >= 0) {
            sql += " HAVING (SUM(cthd.soLuong * cthd.donGia) BETWEEN :tongTienTu AND :tongTienDen)";
        } else if (tongTienTu >= 0) {
            sql += " HAVING (SUM(cthd.soLuong * cthd.donGiaBan) >= :tongTienTu)";
        } else if (tongTienDen >= 0) {
            sql += " HAVING (SUM(cthd.soLuong * cthd.donGiaBan) <= tongTienDen)";
        }

        TypedQuery<HoaDon> query = em.createQuery(sql, HoaDon.class);

        if (ma != null && !ma.isEmpty()) {
            query.setParameter("maHD", ma);
        }

        if (ngayTu != null && !ngayTu.isEmpty() && ngayDen != null && !ngayDen.isEmpty()) {
            // Đặt giá trị cho điều kiện ngày từ ngày đến ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayTaoTu = LocalDate.parse(ngayTu, formatter);
            LocalDate ngayTaoDen = LocalDate.parse(ngayDen, formatter);
            query.setParameter("ngayTu", ngayTaoTu);
            query.setParameter("ngayDen", ngayTaoDen);
        } else if (ngayTu != null && !ngayTu.isEmpty()) {
            // Đặt giá trị cho điều kiện ngày từ ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayTaoTu = LocalDate.parse(ngayTu, formatter);
            query.setParameter("ngayTu", ngayTaoTu);
        } else if (ngayDen != null && !ngayDen.isEmpty()) {
            // Đặt giá trị cho điều kiện ngày đến ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayTaoDen = LocalDate.parse(ngayDen, formatter);
            query.setParameter("ngayDen", ngayTaoDen);
        }

        if (maKh != null && !maKh.isEmpty()) {
            query.setParameter("maKh", maKh);
        }

        if (maNV != null && !maNV.isEmpty()) {
            query.setParameter("maNV", maNV);

        }

        // Đặt giá trị cho tổng tiền
        if (tongTienTu >= 0 && tongTienDen >= 0) {
            query.setParameter("tongTienTu", tongTienTu);
            query.setParameter("tongTienDen", tongTienDen);

        } else if (tongTienTu >= 0) {
            query.setParameter("tongTienTu", tongTienTu);
        } else if (tongTienDen >= 0) {
            query.setParameter("tongTienDen", tongTienDen);
        }

        return (ArrayList<HoaDon>) query.getResultList();
    }

    public static void main(String[] args) throws RemoteException {
        HoaDonImpl hdi = new HoaDonImpl();
//		ArrayList<HoaDon> list = hdi.getListHoaDon();
//		list.forEach(x->System.out.println(x));

//		HoaDon hd = new HoaDon("HD1000", LocalDate.now(), new KhachHang("KH001"), new NhanVien("NV002"));
//		
//		
//		System.out.println(hdi.themHD(hd));
        System.out.println(hdi.tinhTongTienTheoMaHoaDon("HD025"));

//		ArrayList<HoaDon> list = hdi.timKiemHoaDon("003", null, null, "KH002", "", -1, -1);
//		
//		list.forEach(x->System.out.println(x));
    }
}
