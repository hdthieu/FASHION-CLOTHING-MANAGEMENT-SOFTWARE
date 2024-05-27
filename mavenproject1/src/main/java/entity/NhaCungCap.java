/*
	@ (#) NhaCungCap.java		Apr 10, 2024
*/
package entity;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author dinhh
 */
@Table(name = "NhaCungCap")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "NhaCungCap.docTuBang", 
        query = "SELECT * FROM [dbo].[NhaCungCap]", 
        resultClass = NhaCungCap.class
    ),
    @NamedNativeQuery(
            name = "NhaCungCap.searchNhaCungCap",
            query = "SELECT * FROM [dbo].[NhaCungCap] WHERE 1=1 " +
                    "AND (:maNhaCungCap IS NULL OR [maNhaCungCap] LIKE '%' + :maNhaCungCap + '%') " +
                    "AND (:tenNhaCungCap IS NULL OR [tenNhaCungCap] LIKE '%' + :tenNhaCungCap + '%') " +
                    "AND (:diaChi IS NULL OR [diaChi] LIKE '%' + :diaChi + '%') " +
                    "AND (:soDienThoai IS NULL OR [soDienThoai] LIKE '%' + :soDienThoai + '%')",
            resultClass = NhaCungCap.class
        )
})
@Entity
public class NhaCungCap implements Serializable{
	@Id
	private String maNhaCungCap;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String tenNhaCungCap;
	@Column(name = "soDienThoai")
	private String sdt;
	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	private String diaChi;
	@OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.REMOVE)
	private Set<SanPham> setSanPham;

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public NhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public NhaCungCap() {
	
	}

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdt, String diaChi) {
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.sdt = sdt;
		this.diaChi = diaChi;

	}

	@Override
	public String toString() {
		return "NhaCungCap{" + "maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", sdt=" + sdt
				+ ", diaChi=" + diaChi + '}';
	}

	@Override
	public int hashCode() {
		int hash = 7;
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
		final NhaCungCap other = (NhaCungCap) obj;
		if (!Objects.equals(this.maNhaCungCap, other.maNhaCungCap)) {
			return false;
		}
		if (!Objects.equals(this.tenNhaCungCap, other.tenNhaCungCap)) {
			return false;
		}
		if (!Objects.equals(this.sdt, other.sdt)) {
			return false;
		}
		return Objects.equals(this.diaChi, other.diaChi);
	}
}
