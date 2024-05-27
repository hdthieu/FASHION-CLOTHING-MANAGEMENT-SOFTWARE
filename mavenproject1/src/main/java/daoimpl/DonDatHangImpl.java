package daoimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.DonDatHangDao;
import entity.DonDatHang;
import entity.NhanVien;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DonDatHangImpl extends UnicastRemoteObject implements DonDatHangDao {

    private EntityManager em;

    public DonDatHangImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<DonDatHang> getListDonDatHang() throws RemoteException {
        return (ArrayList<DonDatHang>) em.createQuery("SELECT d FROM DonDatHang d", DonDatHang.class).getResultList();
    }

    @Override
    public boolean themDDH(DonDatHang ddh) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(ddh);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public double tinhTongTienTheoMaDonDatHang(String maDonDatHang) throws RemoteException {
        TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(c.soLuong * c.donGia) FROM ChiTietDonDatHang c WHERE c.maDDH.maDDH = :maDDH", Double.class);
        query.setParameter("maDDH", maDonDatHang);
        Double total = query.getSingleResult();
        return total != null ? total : 0;
    }

    @Override
    public String layTrangThaiDonHang(String maDonDatHang) throws RemoteException {
        TypedQuery<String> query = em.createQuery("SELECT d.tinhTrang FROM DonDatHang d WHERE d.maDDH = :maDDH",
                String.class);
        query.setParameter("maDDH", maDonDatHang);
        return query.getSingleResult();
    }

    @Override
    public boolean capNhatTrangThaiDonHang(String maDonHang, String trangThaiMoi) throws RemoteException {
        em.getTransaction().begin();
        DonDatHang ddh = em.find(DonDatHang.class, maDonHang);
        if (ddh != null) {
            ddh.setTinhTrang(trangThaiMoi);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    public ArrayList<DonDatHang> timKiemDonDatHang(String ma, String ngayTu, String ngayDen, String maKh, String maNV,
            double tongTienTu, double tongTienDen, String soDienThoai) throws RemoteException {
        String jpql = "SELECT d FROM DonDatHang d "
                + "JOIN d.dsChiTietDonDatHang ctddh WHERE 1=1";

        // Thêm điều kiện tìm kiếm
        if (ma != null && !ma.isEmpty()) {
            jpql += " AND d.maDDH LIKE CONCAT('%', :ma, '%')";
        }

        // Thêm điều kiện ngày tạo
        if (ngayTu != null && !ngayTu.isEmpty() && ngayDen != null && !ngayDen.isEmpty()) {
            jpql += " AND d.ngayTao BETWEEN :ngayTu AND :ngayDen";
        } else if (ngayTu != null && !ngayTu.isEmpty()) {
            jpql += " AND d.ngayTao >= :ngayTu";
        } else if (ngayDen != null && !ngayDen.isEmpty()) {
            jpql += " AND d.ngayTao <= :ngayDen";
        }

        // Điều kiện khác
        if (maKh != null && !maKh.isEmpty()) {
            jpql += " AND d.maKh.maKH LIKE CONCAT('%', :maKh, '%')";
        }
        if (maNV != null && !maNV.isEmpty()) {
            jpql += " AND d.maNV.maNhanVien LIKE CONCAT('%', :maNV, '%')";
        }
        if (soDienThoai != null && !soDienThoai.isEmpty()) {
            jpql += " AND d.maKh.soDienThoai LIKE CONCAT('%', :soDienThoai, '%')";
        }

        // Nhóm các kết quả
        jpql += " GROUP BY d.maDDH, d.ngayTao, d.maKh.maKH, d.maNV.maNhanVien, d.tinhTrang";

        // Điều kiện tổng tiền
        String havingClause = "";
        if (tongTienTu >= 0 && tongTienDen >= 0) {
            havingClause = " HAVING SUM(ctddh.soLuong * ctddh.donGia) BETWEEN :tongTienTu AND :tongTienDen";
        } else if (tongTienTu >= 0) {
            havingClause = " HAVING SUM(ctddh.soLuong * ctddh.donGia) >= :tongTienTu";
        } else if (tongTienDen >= 0) {
            havingClause = " HAVING SUM(ctddh.soLuong * ctddh.donGia) <= :tongTienDen";
        }
        jpql += havingClause;

        TypedQuery<DonDatHang> query = em.createQuery(jpql, DonDatHang.class);

        // Đặt tham số cho truy vấn
        if (ma != null && !ma.isEmpty()) {
            query.setParameter("ma", ma);
        }
        // Xử lý ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (ngayTu != null && !ngayTu.isEmpty()) {
            LocalDate ngayTaoTu = LocalDate.parse(ngayTu, formatter);
            query.setParameter("ngayTu", ngayTaoTu);
        }
        if (ngayDen != null && !ngayDen.isEmpty()) {
            LocalDate ngayTaoDen = LocalDate.parse(ngayDen, formatter);
            query.setParameter("ngayDen", ngayTaoDen);
        }
        // Đặt tham số cho tổng tiền nếu có
        if (!havingClause.isEmpty()) {
            if (tongTienTu >= 0) {
                query.setParameter("tongTienTu", tongTienTu);
            }
            if (tongTienDen >= 0) {
                query.setParameter("tongTienDen", tongTienDen);
            }
        }
        if (maKh != null && !maKh.isEmpty()) {
            query.setParameter("maKh", maKh);
        }
        if (maNV != null && !maNV.isEmpty()) {
            query.setParameter("maNV", maNV);
        }
        if (soDienThoai != null && !soDienThoai.isEmpty()) {
            query.setParameter("soDienThoai", soDienThoai);
        }

        List<DonDatHang> resultList = query.getResultList();
        return new ArrayList<>(resultList);
    }

    public static void main(String[] args) throws RemoteException {
        // Test getListDonDatHang
        DonDatHangDao ddh = new DonDatHangImpl();
//		ddh.getListDonDatHang().forEach(System.out::println);
        // Test themDDH
//		LocalDate ngayTao = LocalDate.now();
//		DonDatHang newddh=new DonDatHang("DDH030",ngayTao,new KhachHang("KH001"),new NhanVien("NV002"),"Chưa Thanh Toán");
//		ddh.themDDH(newddh);
        // Test tinhTongTienTheoMaDonDatHang
//		System.out.println(ddh.tinhTongTienTheoMaDonDatHang("DDH001"));
        // Test layTrangThaiDonDatHang
//		System.out.println(ddh.layTrangThaiDonHang("DDH001"));
        // Test capNhatTrangThaiDonHang
//		ddh.capNhatTrangThaiDonHang("DDH012","Đã Thanh Toán");
//		System.out.println(ddh.layTrangThaiDonHang("DDH012"));
        // Test timKiemDonDatHang
        ArrayList<DonDatHang> list = ddh.timKiemDonDatHang("DDH001", null, null, null, null, 0, 0, null);
        System.out.println(list);
    }

}
