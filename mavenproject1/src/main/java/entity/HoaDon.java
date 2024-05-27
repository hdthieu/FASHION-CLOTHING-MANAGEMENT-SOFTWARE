/*
	@ (#) HoaDon.java		Apr 10, 2024
 */
package entity;

import java.time.LocalDate;
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
public class HoaDon implements Serializable{

	@Id
	@Column(name = "maHoaDon")
	private String maHD;

	@Column(name = "ngayTao")
	private LocalDate ngayTao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKH")
	private KhachHang maKh;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien")
	private NhanVien maNV;

//    @OneToMany(mappedBy = "maHD", cascade = CascadeType.REMOVE)
	@OneToMany(mappedBy = "maHD", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ChiTietHoaDon> setChiTietHoaDon;

	public HoaDon(String maHD, LocalDate ngayTao, KhachHang maKh, NhanVien maNV) {
		this.maHD = maHD;
		this.ngayTao = ngayTao;
		this.maKh = maKh;
		this.maNV = maNV;
	}

	public HoaDon(LocalDate ngayTao, KhachHang maKh, NhanVien maNV) {
		super();
		this.ngayTao = ngayTao;
		this.maKh = maKh;
		this.maNV = maNV;
	}

	public HoaDon() {
	}

	public HoaDon(String maHD) {
		this.maHD = maHD;
	}

	public HoaDon(String maHD, LocalDate ngayTao) {
		this.maHD = maHD;
		this.ngayTao = ngayTao;
	}

	public HoaDon(LocalDate ngayTao, KhachHang maKh) {
		this.maKh = maKh;
		this.ngayTao = ngayTao;
	}

	public HoaDon(NhanVien maNV) {
		this.maNV = maNV;
	}

	public HoaDon(LocalDate ngayTao, NhanVien maNV) {
		this.ngayTao = ngayTao;
		this.maNV = maNV;
	}

	public HoaDon(LocalDate ngayTao) {

		this.ngayTao = ngayTao;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public KhachHang getMaKh() {
		return maKh;
	}

	public void setMaKh(KhachHang maKh) {
		this.maKh = maKh;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}

	@Override
	public String toString() {
		return "HoaDon{" + "maHD=" + maHD + ", ngayTao=" + ngayTao + ", maKh=" + maKh + ", maNV=" + maNV + '}';
	}

}
