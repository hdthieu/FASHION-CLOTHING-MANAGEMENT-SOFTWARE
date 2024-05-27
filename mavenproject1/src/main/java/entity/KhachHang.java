/*
	@ (#) KhachHang.java		Apr 10, 2024
*/
package entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
@Entity
@NamedQueries({ @NamedQuery(name = "KhachHang.docTuBang", query = "select kh from KhachHang kh"),
				@NamedQuery(name = "KhachHang.layTenKhachHang",
							query = "SELECT CONCAT(k.maKH, ' - ', k.tenKH) AS hoTen FROM KhachHang k WHERE k.maKH = :maKH")
		 })
public class KhachHang implements Serializable{
	@Id
	private String maKH;
	@Column(columnDefinition = "nvarchar(45)", nullable = false)
	private String tenKH;
	@Column(columnDefinition = "nvarchar(45)", nullable = false)
	private String diaChi;
	@Column(nullable = false)
	private String soDienThoai;
	@Convert(converter = GioiTinhConverter.class)
	//@Column(columnDefinition = "nvarchar(45)")
	private boolean gioiTinh;

	@OneToMany(mappedBy = "maKh", cascade = CascadeType.REMOVE)
	private Set<HoaDon> setHoaDon;

	@OneToMany(mappedBy = "maKh", cascade = CascadeType.REMOVE)
	private Set<DonDatHang> setDonDatHang;

	public KhachHang(String maKH, String tenKH, String diaChi, String soDienThoai, boolean gioiTinh) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
	}
	
	public KhachHang() {
		super();
	}

	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH, boolean gioiTinh) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
	}

	public KhachHang(String maKH, String tenKH) {
		this.maKH = maKH;
		this.tenKH = tenKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.maKH);
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
		final KhachHang other = (KhachHang) obj;
		return Objects.equals(this.maKH, other.maKH);
	}

	@Override
	public String toString() {
		return "KhachHang{" + "maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + ", gioiTinh=" + gioiTinh + '}';
	}

	// các trường và phương thức của KhachHang

	@Converter(autoApply = true)
	public static class GioiTinhConverter implements AttributeConverter<Boolean, String> {
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
