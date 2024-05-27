/*
	@ (#) NhanVien.java		Apr 10, 2024
*/
package entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author dinhh
 */
@Entity
@Table(name = "NhanVien")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "NhanVien.docTuBang", 
        query = "SELECT * FROM [dbo].[NhanVien]", 
        resultClass = NhanVien.class
    ),
    @NamedNativeQuery(
    	    name = "NhanVien.searchEmployees",
    	    query = "SELECT * FROM [dbo].[NhanVien] WHERE 1=1 " +
    	            "AND (:maNhanVien IS NULL OR [maNhanVien] LIKE '%' + :maNhanVien + '%') " +
    	            "AND (:tenNhanVien IS NULL OR [tenNhanVien] LIKE '%' + :tenNhanVien + '%') " +
    	            "AND (:gioiTinh IS NULL OR [gioiTinh] = :gioiTinh) " +
    	            "AND (:ngaySinh IS NULL OR CONVERT(VARCHAR, [ngaySinh], 120) LIKE :ngaySinh) " +
    	            "AND (:soDienThoai IS NULL OR [soDienThoai] LIKE '%' + :soDienThoai + '%') " +
    	            "AND (:email IS NULL OR [email] LIKE '%' + :email + '%') " +
    	            "AND (:diaChi IS NULL OR [diaChi] LIKE '%' + :diaChi + '%') " +
    	            "AND (:chucVu IS NULL OR [chucVu] LIKE '%' + :chucVu + '%')",
    	    resultClass = NhanVien.class
    	)
})
public class NhanVien implements Serializable{
	@Id
    private String maNhanVien;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenNhanVien;
    private String gioiTinh;
    private String ngaySinh;
    @Column(name = "soDienThoai")
    private String SDT;
    private String email;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    private String diaChi;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    private String chucVu;
    private String hinhAnh;


//  @OneToOne(mappedBy = "nhanVien")
//  private TaiKhoan taiKhoan;
    
    @OneToMany(mappedBy = "maNV", cascade = CascadeType.REMOVE)
    private Set<HoaDon> setHoaDon;
    
    @OneToMany(mappedBy = "maNV", cascade = CascadeType.REMOVE)
    private Set<DonDatHang> setDonDatHang;
    
    
    public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh, String SDT, String email, String diaChi, String chucVu, String hinhAnh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.hinhAnh = hinhAnh;
    }

	public NhanVien() {
	}
    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh,String ngaySinh, String SDT, String email, String diaChi, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
         this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.chucVu=chucVu;
    }

    public NhanVien(String maNhanVien, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;

    }

    public String getEmail() {
        return email;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    

    @Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", SDT=" + SDT + ", email=" + email + ", diaChi=" + diaChi + ", chucVu="
				+ chucVu + ", hinhAnh=" + hinhAnh + "]";
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maNhanVien);
        hash = 59 * hash + Objects.hashCode(this.SDT);
        return hash;
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
        final NhanVien other = (NhanVien) obj;
        if (!Objects.equals(this.maNhanVien, other.maNhanVien)) {
            return false;
        }
        return Objects.equals(this.SDT, other.SDT);
    }
    @Converter(autoApply = true)
	public static abstract class GioiTinhConverter implements AttributeConverter<Boolean, String> {
		@Override
		public String convertToDatabaseColumn(Boolean attribute) {
			if (attribute == null) {
				return null;
			}
			return attribute ? "Male" : "Female";
		}
		@Override
		public Boolean convertToEntityAttribute(String dbData) {
			return "Male".equals(dbData);
		}
	}

}

