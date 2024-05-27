package daoimpl;

import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDao;
import entity.NhaCungCap;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao {

    private EntityManager em;

    public NhanVienImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
    }

    @Override
    public boolean create(NhanVien nhanVien) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nhanVien);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(NhanVien nhanVien) throws RemoteException {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            NhanVien nv = em.find(NhanVien.class, nhanVien.getMaNhanVien());
            if (nv != null) {
                nv.setTenNhanVien(nhanVien.getTenNhanVien());
                nv.setGioiTinh(nhanVien.getGioiTinh());
                nv.setNgaySinh(nhanVien.getNgaySinh());
                nv.setDiaChi(nhanVien.getDiaChi());
                nv.setSDT(nhanVien.getSDT());
                nv.setEmail(nhanVien.getEmail());
                nv.setHinhAnh(nhanVien.getHinhAnh());
                nv.setChucVu(nhanVien.getChucVu());
                em.merge(nv);
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
    public boolean delete(String maNV) throws RemoteException {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            NhanVien nhanvien = em.find(NhanVien.class, maNV);
            em.remove(nhanvien);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public ArrayList<NhanVien> docTuBang() throws RemoteException {
        return (ArrayList<NhanVien>) em.createNamedQuery("NhanVien.docTuBang", NhanVien.class).getResultList();
    }

    @Override
    public ArrayList<NhanVien> searchEmployees(
            String maNhanVien,
            String tenNhanVien,
            String gioiTinh,
            String ngaySinh,
            String soDienThoai,
            String email,
            String diaChi,
            String chucVu
    ) throws RemoteException {
       TypedQuery<NhanVien> query = em.createNamedQuery("NhanVien.searchEmployees", NhanVien.class);
        query.setParameter("maNhanVien", maNhanVien);
        query.setParameter("tenNhanVien", tenNhanVien);
        query.setParameter("gioiTinh", gioiTinh);
        query.setParameter("soDienThoai", soDienThoai);
        query.setParameter("email", email);
        query.setParameter("diaChi", diaChi);
        query.setParameter("chucVu", chucVu);
        query.setParameter("ngaySinh", ngaySinh);

        // Make sure at least one attribute is provided
        if (maNhanVien == null && tenNhanVien == null && gioiTinh == null &&
            soDienThoai == null && email == null && diaChi == null &&
            chucVu == null && ngaySinh == null) {
            throw new IllegalArgumentException("");
        }

        return (ArrayList<NhanVien>) query.getResultList();
    }

    @Override
    public NhanVien getNhanVienTheoMa(String maNV) throws RemoteException {
        return em.find(NhanVien.class, maNV);
    }

    public static void main(String[] args) throws RemoteException {
        NhanVienDao nhanVienDao = new NhanVienImpl();
        // test docTuBang
//		ArrayList<NhanVien> nhanViens = nhanVienDao.docTuBang();
//		for (NhanVien nhanVien : nhanViens) {
//			System.out.println(nhanVien);
//		}
        // test thêm
//		NhanVien nhanVien = new NhanVien("NV021", "Nguyễn Văn A", "Male", "1999-01-01", "0123456789", "Hà Nội",
//				"Nhân viên", "hinh-anh.jpg");
//		boolean themnv=nhanVienDao.create(nhanVien);
//		if (themnv) {
//			System.out.println("Thêm thành công");
//		} else {
//			System.out.println("Thêm thất bại");
//		}
        // test xóa
//		boolean xoanv=nhanVienDao.delete("NV021");	
//		if (xoanv) {
//			System.out.println("Xóa thành công");
//		} else {
//			System.out.println("Xóa thất bại");
//		}
        // test update

//		NhanVien nv = new NhanVien("NV002", "Nguyễn Văn A", "Male", "1999-01-01", "0123456789", "Hà Nội", "Nhân Viên Bán Hàng",
//				"hinh-anh.jpg");
//		boolean updatenv=nhanVienDao.update(nv);
//		if (updatenv) {
//			System.out.println("Update thành công");
//		} else {
//			System.out.println("Update thất bại");
//		}
//		 test searchEmployees
        try {
            ArrayList<NhanVien> nhanViens = nhanVienDao.searchEmployees("NV007", null, null, null, null, null, null,
                    null);
            System.out.println(nhanViens);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // test tim theo ma
//		System.out.println(nhanVienDao.getNhanVienByMa("NV002"));
    }

}
