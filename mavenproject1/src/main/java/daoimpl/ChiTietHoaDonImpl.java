package daoimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;

public class ChiTietHoaDonImpl extends UnicastRemoteObject implements ChiTietHoaDonDao {

    private EntityManager em;

    public ChiTietHoaDonImpl() throws RemoteException{
        // TODO Auto-generated constructor stub
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public ArrayList<ChiTietHoaDon> getListHoaDon() throws RemoteException {
        String jpql = "select cthd from ChiTietHoaDon cthd";
        return (ArrayList<ChiTietHoaDon>) em.createQuery(jpql, ChiTietHoaDon.class).getResultList();
    }

    @Override
    public ArrayList<ChiTietHoaDon> getListHoaDonTheoMaHD(String maHD) throws RemoteException {

        return (ArrayList<ChiTietHoaDon>) em
                .createQuery("select cthd from ChiTietHoaDon cthd where cthd.maHD.maHD = :maHD", ChiTietHoaDon.class)
                .setParameter("maHD", maHD).getResultList();
    }

    @Override
    public boolean themHD(ChiTietHoaDon hd) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hd);
            tx.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // ==== Thống Kê ====
    // ==== Thống Kê sản phẩm ====
    @Override
    public List<ChiTietHoaDon> tKsanPham() throws RemoteException {
        List<ChiTietHoaDon> dsSanPham = new ArrayList<>();
        try {
            String jql = "SELECT cthd.maSP.maSP, cthd.maSP.tenSP, cthd.maSP.loaiSP.tenLoai, cthd.maHD.ngayTao, "
                    + "SUM(cthd.soLuong) as soLuong, " + "SUM(cthd.soLuong * cthd.donGia) as doanhThu "
                    + "FROM ChiTietHoaDon cthd "
                    + "GROUP BY cthd.maSP.maSP, cthd.maSP.tenSP, cthd.maSP.loaiSP.tenLoai, cthd.maHD.ngayTao";

            TypedQuery<Object[]> query = em.createQuery(jql, Object[].class);
            List<Object[]> resultList = query.getResultList();
            for (Object[] row : resultList) {
                String maSP = (String) row[0];
                String tenSP = (String) row[1];
                String tenLoaiSP = (String) row[2];
                LocalDate ngayTao = (LocalDate) row[3];
                Integer soLuong = ((Number) row[4]).intValue();
                Double doanhThu = (Double) row[5];

                // Tạo các đối tượng và thêm vào danh sách
                HoaDon hoaDon = new HoaDon(null, ngayTao);
                SanPham sanPham = new SanPham(maSP, tenSP, new LoaiSanPham(maSP, tenLoaiSP));
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, sanPham, soLuong, doanhThu);
                dsSanPham.add(chiTietHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }

    @Override
    public List<ChiTietHoaDon> locDateTKSP(Date fromDate, Date toDate) throws RemoteException {
        List<ChiTietHoaDon> locDateTKSP = new ArrayList<>();
        try {
            LocalDate localFromDate = fromDate.toLocalDate();
            LocalDate localToDate = toDate.toLocalDate();
            TypedQuery<ChiTietHoaDon> query = em.createNamedQuery("ChiTietHoaDon.selectChungLocDate",
                    ChiTietHoaDon.class);
            query.setParameter("fromDate", localFromDate);
            query.setParameter("toDate", localToDate);
            locDateTKSP = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locDateTKSP;
    }

    // ==== Thống Kê Hoa Don ====
    @Override
    public List<ChiTietHoaDon> tKHDlenBang() throws RemoteException {
        return em.createNamedQuery("ChiTietHoaDon.selectChung1", ChiTietHoaDon.class).getResultList();
    }

    @Override
    public List<ChiTietHoaDon> locDateTKHD(Date fromDate, Date toDate) {
        List<ChiTietHoaDon> tKHD = new ArrayList<>();
        try {
            LocalDate localFromDate = fromDate.toLocalDate();
            LocalDate localToDate = toDate.toLocalDate();

            TypedQuery<ChiTietHoaDon> query = em.createNamedQuery("ChiTietHoaDon.selectChungLocDate",
                    ChiTietHoaDon.class);
            query.setParameter("fromDate", localFromDate);
            query.setParameter("toDate", localToDate);
            tKHD = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tKHD;
    }

    // Thong Ke Khach Hang
    @Override
    public List<ChiTietHoaDon> docDLieuTKeKH() throws RemoteException {
        return em.createNamedQuery("ChiTietHoaDon.selectChung2", ChiTietHoaDon.class).getResultList();
    }

    @Override
    public List<ChiTietHoaDon> locDLieuTKeKH(Date fromDate, Date toDate) throws RemoteException {
        List<ChiTietHoaDon> tKKH = new ArrayList<>();
        try {
            // Convert java.sql.Date to java.time.LocalDate
            LocalDate localFromDate = fromDate.toLocalDate();
            LocalDate localToDate = toDate.toLocalDate();

            TypedQuery<ChiTietHoaDon> query = em.createNamedQuery("ChiTietHoaDon.selectLocDate2", ChiTietHoaDon.class);
            query.setParameter("fromDate", localFromDate);
            query.setParameter("toDate", localToDate);
            tKKH = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tKKH;
    }

    // Thong ke doanh so ban hang selectTKKH
    @Override
    public List<ChiTietHoaDon> tKDSBHlenBang() throws RemoteException {
        return em.createNamedQuery("ChiTietHoaDon.selectChung2", ChiTietHoaDon.class).getResultList();
    }

    @Override
    public List<ChiTietHoaDon> locDateDSBHNV(Date fromDate, Date toDate) throws RemoteException {
        return em.createNamedQuery("ChiTietHoaDon.selectChung2", ChiTietHoaDon.class).getResultList();
    }
    
     @Override
    public String tinhTongDoanhThuTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) throws RemoteException{
    String tongDoanhThu = "0";
    try {
        // Convert java.util.Date to java.time.LocalDate
        LocalDate localFromDate = ngayBatDau.toLocalDate();
        LocalDate localToDate = ngayKetThuc.toLocalDate();

        String jpql = "SELECT SUM(ct.soLuong * ct.donGia) " +
                      "FROM HoaDon hd " +
                      "INNER JOIN hd.setChiTietHoaDon ct " +
                      "WHERE hd.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc";

        Query query = em.createQuery(jpql);
        query.setParameter("ngayBatDau", localFromDate);
        query.setParameter("ngayKetThuc", localToDate);

        Double result = (Double) query.getSingleResult();
        DecimalFormat df = new DecimalFormat("#,###.### VNĐ");
        tongDoanhThu = result != null ? df.format(result) : "0";
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tongDoanhThu;
}

    @Override
    public List<String> findBestSellingProductNames(Date ngayBatDau, Date ngayKetThuc) throws RemoteException{
        List<String> bestSellingProductNames = new ArrayList<>();
        try {
             LocalDate localFromDate = ngayBatDau.toLocalDate();
             LocalDate localToDate = ngayKetThuc.toLocalDate();
                
            String jpql = "SELECT sp.tenSP " +
                      "FROM HoaDon hd " +
                      "INNER JOIN hd.setChiTietHoaDon ct " +
                      "INNER JOIN ct.maSP sp " +
                      "WHERE hd.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc " +
                      "GROUP BY sp.tenSP " +
                      "HAVING SUM(ct.soLuong) = (" +
                      "    SELECT MAX(totalQuantity) " +
                      "    FROM (" +
                      "        SELECT SUM(ct2.soLuong) AS totalQuantity " +
                      "        FROM HoaDon hd2 " +
                      "        INNER JOIN hd2.setChiTietHoaDon ct2 " +
                      "        WHERE hd2.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc " +
                      "        GROUP BY ct2.maSP" +
                      "    ) AS subQuery" +
                      ")";
        Query query = em.createQuery(jpql);
        query.setParameter("ngayBatDau", localFromDate);
        query.setParameter("ngayKetThuc", localToDate);
        bestSellingProductNames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return bestSellingProductNames;
    }

    @Override
    public List<Object[]> getSPbyDate(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
         List<Object[]> spList = new ArrayList<>();
    try {
        LocalDate localFromDate = ngayBatDau.toLocalDate();
        LocalDate localToDate = ngayKetThuc.toLocalDate();

        String jpql = "SELECT sp.maSP, sp.tenSP, lsp.tenLoai AS tenLoaiSP, hd.ngayTao, " +
                      "SUM(ct.soLuong) AS soLuongBan, SUM(ct.soLuong * ct.donGia) AS doanhThu " +
                      "FROM HoaDon hd " +
                      "INNER JOIN hd.setChiTietHoaDon ct " +
                      "INNER JOIN ct.maSP sp " +
                      "INNER JOIN sp.loaiSP lsp " +
                      "WHERE hd.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc " +
                      "GROUP BY sp.maSP, sp.tenSP, lsp.tenLoai, hd.ngayTao " +
                      "ORDER BY hd.ngayTao ASC";

        Query query = em.createQuery(jpql);
        query.setParameter("ngayBatDau", localFromDate);
        query.setParameter("ngayKetThuc", localToDate);
        spList = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return spList;
    }

    public static void main(String[] args) throws RemoteException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ChiTietHoaDonImpl cthdImpl = new ChiTietHoaDonImpl();

        // test TKSP
        // In kết quả
//		for (ChiTietHoaDon chiTiet : result) {
//			System.out.println("Mã sản phẩm: " + chiTiet.getMaSP().getMaSP());
//			System.out.println("Tên sản phẩm: " + chiTiet.getMaSP().getTenSP());
//			System.out.println("Ngày tạo: " + chiTiet.getMaHD().getNgayTao());
//			System.out.println("Số lượng bán: " + chiTiet.getSoLuong());
//			System.out.println("Tên loại sản phẩm: " + chiTiet.getMaSP().getLoaiSP().getTenLoai());
//			System.out.println("Doanh thu: " + chiTiet.getDonGia());
//			System.out.println("----------------------------------");
//		}
//		try {
//			Date fromDate = new Date(sdf.parse("2018-01-01").getTime());
//			Date toDate = new Date(sdf.parse("2018-12-01").getTime());
//			List<ChiTietHoaDon> filteredResults = cthdImpl.locDateTKSP(fromDate, toDate);
//			System.out.println("Filtered Results: ");
//			for (ChiTietHoaDon cthd : filteredResults) {
//				System.out.println(cthd);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        // Test TKHD
//		try {
//			Date fromDate = new Date(sdf.parse("2018-01-01").getTime()); // Start date
//			Date toDate = new Date(sdf.parse("2018-12-31").getTime()); // End date
//
//			// Set parameters and retrieve the list
//			List<ChiTietHoaDon> resultList = cthdImpl.tKHDlenBang();
//
//			// Iterate over the result list and print each object
//			for (ChiTietHoaDon cthd : resultList) {
//				// Assuming ChiTietHoaDon and HoaDon have appropriate toString methods or
//				// getters
//				System.out.println(cthd.getMaHD().getMaHD() + " " + cthd.getMaHD().getMaNV().getTenNhanVien() + " "
//						+ cthd.getMaHD().getMaKh().getTenKH() + " " + cthd.getDonGia() + " " + cthd.getMaHD().getNgayTao());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			Date fromDate = new Date(sdf.parse("2018-01-01").getTime());
//			Date toDate = new Date(sdf.parse("2018-12-01").getTime());
//			List<ChiTietHoaDon> filteredResults = cthdImpl.locDateTKHD(fromDate, toDate);
//			System.out.println("Filtered Results: ");
//			for (ChiTietHoaDon cthd : filteredResults) {
//				System.out.println(cthd);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        // Test tkKH
//		List<ChiTietHoaDon> resultList = cthdImpl.docDLieuTKeKH();
//		resultList.forEach(x -> System.out.println(x));
        try {
            Date fromDate = new Date(sdf.parse("2021-02-08").getTime());
            Date toDate = new Date(sdf.parse("2024-05-18").getTime());
            List<ChiTietHoaDon> filteredResults = cthdImpl.locDLieuTKeKH(fromDate, toDate);
            System.out.println("Filtered Results: ");
            for (ChiTietHoaDon cthd : filteredResults) {
                System.out.println(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long tinhTongSoLuongBanTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
       long tongSoLuong = 0;
    try {
        // Convert java.util.Date to java.time.LocalDate
        LocalDate localFromDate = ngayBatDau.toLocalDate();
        LocalDate localToDate = ngayKetThuc.toLocalDate();

        String jpql = "SELECT SUM(ct.soLuong) " +
                      "FROM HoaDon hd " +
                      "INNER JOIN hd.setChiTietHoaDon ct " +
                      "WHERE hd.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc";

        Query query = em.createQuery(jpql);
        query.setParameter("ngayBatDau", localFromDate);
        query.setParameter("ngayKetThuc", localToDate);

        Long result = (Long) query.getSingleResult();
        tongSoLuong = result != null ? result : 0L;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tongSoLuong;
    }

    @Override
    public List<ChiTietHoaDon> locDLieuTKeNhanVien(Date fromDate, Date toDate) throws RemoteException {
          List<ChiTietHoaDon> result = new ArrayList<>();
    try {
        // Convert java.sql.Date to java.time.LocalDate
        LocalDate localFromDate = fromDate.toLocalDate();
        LocalDate localToDate = toDate.toLocalDate();

        TypedQuery<ChiTietHoaDon> query = em.createNamedQuery("ChiTietHoaDon.selectLocDate2", ChiTietHoaDon.class);
        query.setParameter("fromDate", localFromDate);
        query.setParameter("toDate", localToDate);
        result = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
    }

}
