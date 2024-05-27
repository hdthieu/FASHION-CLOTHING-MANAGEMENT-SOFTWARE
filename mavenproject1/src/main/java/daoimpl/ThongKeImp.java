/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import dao.ThongKe_DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ThongKeImp  extends UnicastRemoteObject implements ThongKe_DAO{
    private EntityManager em;
    public ThongKeImp() throws RemoteException{
     em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public Map<String, Double> findNhanVienWithMaxDoanhThuMap(Date fromDate, Date toDate) throws RemoteException {
       Map<String, Double> employeesWithMaxRevenue = new HashMap<>();
    try {
        String nativeSql = "SELECT NV.tenNhanVien, SUM(CTHD.soLuong * CTHD.donGiaBan) AS doanhSoBanHang " +
                           "FROM NhanVien NV " +
                           "JOIN HoaDon HD ON NV.maNhanVien = HD.maNhanVien " +
                           "JOIN ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.maHoaDon " +
                           "WHERE HD.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc " +
                           "GROUP BY NV.maNhanVien, NV.tenNhanVien " +
                           "HAVING SUM(CTHD.soLuong * CTHD.donGiaBan) = (" +
                           "    SELECT MAX(tongDoanhThu) " +
                           "    FROM (" +
                           "        SELECT SUM(CTHD2.soLuong * CTHD2.donGiaBan) AS tongDoanhThu " +
                           "        FROM NhanVien NV2 " +
                           "        JOIN HoaDon HD2 ON NV2.maNhanVien = HD2.maNhanVien " +
                           "        JOIN ChiTietHoaDon CTHD2 ON HD2.maHoaDon = CTHD2.maHoaDon " +
                           "        WHERE HD2.ngayTao BETWEEN :ngayBatDau AND :ngayKetThuc " +
                           "        GROUP BY NV2.maNhanVien " +
                           "    ) AS subquery" +
                           ")";
        Query query = em.createNativeQuery(nativeSql);
        query.setParameter("ngayBatDau", fromDate);
        query.setParameter("ngayKetThuc", toDate);
        List<Object[]> results = query.getResultList();
        
        for (Object[] result : results) {
            String tenNhanVien = (String) result[0];
            Double doanhSoBanHang = (Double) result[1];
            employeesWithMaxRevenue.put(tenNhanVien, doanhSoBanHang);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employeesWithMaxRevenue;
    }
    
    
}
