/*
	@ (#) DonDatHang.java		Apr 10, 2024
*/
package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
@Entity
public class DonDatHang implements Serializable{
	@Id
	@Column(name = "maDonDatHang")
	private String maDDH;
	
	private LocalDate ngayTao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKH")
	private KhachHang maKh;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
	private NhanVien maNV;
	@Column(name = "tinhTrang", columnDefinition = "nvarchar(255)", nullable = false)
	private String tinhTrang;

	@OneToMany(mappedBy = "maDDH", cascade = CascadeType.REMOVE)
	private Set<ChiTietDonDatHang> dsChiTietDonDatHang;
	public DonDatHang(String maDDH, LocalDate ngayTao, KhachHang maKh, NhanVien maNV, String tinhTrang) {
		this.maDDH = maDDH;
		this.ngayTao = ngayTao;
		this.maKh = maKh;
		this.maNV = maNV;
		this.tinhTrang = tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public DonDatHang(String maDDH) {
		this.maDDH = maDDH;
	}

	public DonDatHang() {

	}

	public DonDatHang(String maDDH, LocalDate ngayTao) {
		this.maDDH = maDDH;
		this.ngayTao = ngayTao;

	}

	public String getMaDDH() {
		return maDDH;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public KhachHang getMaKh() {
		return maKh;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaDDH(String maDDH) {
		this.maDDH = maDDH;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public void setMaKh(KhachHang maKh) {
		this.maKh = maKh;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}

	@Override
	public String toString() {
		return "DonDatHang{" + "maDDH=" + maDDH + ", ngayTao=" + ngayTao + ", maKh=" + maKh + ", maNV=" + maNV + '}';
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
		final DonDatHang other = (DonDatHang) obj;
		if (!Objects.equals(this.maDDH, other.maDDH)) {
			return false;
		}
		if (!Objects.equals(this.maKh, other.maKh)) {
			return false;
		}
		return Objects.equals(this.maNV, other.maNV);
	}
}
