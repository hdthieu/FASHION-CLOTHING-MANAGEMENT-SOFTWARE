/*
	@ (#) TaiKhoan.java		Apr 10, 2024
 */
package entity;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
 */
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author dinhh
 */
@Entity
@Table(name = "TaiKhoan")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "TaiKhoan.docTuBang",
            query = "SELECT * FROM [dbo].[TaiKhoan]",
            resultClass = TaiKhoan.class
    ),
    @NamedNativeQuery(name = "TaiKhoan.getTaiKhoanTheoTK",
            query = "SELECT * FROM [dbo].[TaiKhoan] WHERE tenTaiKhoan = ?",
            resultClass = TaiKhoan.class)
})
public class TaiKhoan implements Serializable {

    @Id
    private String tenTaiKhoan;
    private String matKhau;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String quyenTruyCap;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maNhanVien")
    private NhanVien maNhanVien;
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "maNhanVien")
//    private NhanVien maNhanVien;

//  public TaiKhoan(String maNhanVien){
//       this.maNhanVien=maNhanVien;
//  }
    public TaiKhoan(NhanVien maNhanVien, String tenTaiKhoan, String matKhau, String quyenTruyCap) {
        super();
        this.maNhanVien = maNhanVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyenTruyCap = quyenTruyCap;

    }

    public TaiKhoan() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyenTruyCap() {
        return quyenTruyCap;
    }

    public void setQuyenTruyCap(String quyenTruyCap) {
        this.quyenTruyCap = quyenTruyCap;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    @Override
    public String toString() {
        return "TaiKhoan [tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", quyenTruyCap=" + quyenTruyCap
                + ", maNhanVien=" + maNhanVien + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaiKhoan other = (TaiKhoan) obj;
        if (!Objects.equals(this.tenTaiKhoan, other.tenTaiKhoan)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        if (!Objects.equals(this.quyenTruyCap, other.quyenTruyCap)) {
            return false;
        }
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }
}
