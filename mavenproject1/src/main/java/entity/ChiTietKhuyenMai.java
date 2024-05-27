/*
	@ (#) ChiTietKhuyenMai.java		Apr 10, 2024
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
//    @NamedQuery(name = "KhuyenMai.docTuBang", query = "SELECT DISTINCT km FROM KhuyenMai km WHERE EXISTS (SELECT 1 FROM ChiTietKhuyenMai ctkm WHERE ctkm.khuyenMai = km)")
    @NamedQuery(name = "ChiTietKhuyenMai.docTuBang", query = "SELECT DISTINCT ctkm FROM ChiTietKhuyenMai ctkm JOIN ctkm.khuyenMai km JOIN ctkm.sanPham sp JOIN sp.loaiSP lsp")
})
public class ChiTietKhuyenMai implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maKhuyenMai")
    private KhuyenMai khuyenMai;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maSP")
    private SanPham sanPham;
    @Column(nullable = false)
    private double giaGiam;

    public ChiTietKhuyenMai(KhuyenMai khuyenMai, SanPham sanPham, double giaGiam) {
        this.khuyenMai = khuyenMai;
        this.sanPham = sanPham;
        this.giaGiam = giaGiam;
    }

    // No-argument constructor
    public ChiTietKhuyenMai() {
    }

    public ChiTietKhuyenMai(KhuyenMai khuyenMai, double giaGiam) {
        this.khuyenMai = khuyenMai;
        this.giaGiam = giaGiam;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }

    @Override
    public String toString() {
        return "ChiTietKhuyenMai{" + "khuyenMai=" + khuyenMai + ", sanPham=" + sanPham + ", giaGiam=" + giaGiam + '}';
    }

}
