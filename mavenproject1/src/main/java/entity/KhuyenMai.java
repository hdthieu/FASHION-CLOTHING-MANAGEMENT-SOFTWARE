/*
	@ (#) KhuyenMai.java		Apr 10, 2024
*/
package entity;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;

/**
 *
 * @author TriHieu
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "KhuyenMai.docTuBang", query = "SELECT km FROM KhuyenMai km"),
})
public class KhuyenMai implements Serializable{
	@Id
	private String maKhuyenMai;
	@Column(columnDefinition = "nvarchar(45)")
	private String tenKhuyenMai;
	@Column(columnDefinition = "nvarchar(45)")
	private String moTa;
	@Temporal(TemporalType.DATE)
	private Date ngayBatDau;
	@Temporal(TemporalType.DATE)
	private Date ngayKetThuc;
	@Column(columnDefinition = "nvarchar(45)")
	private String trangThai;

	@OneToMany(mappedBy = "khuyenMai", cascade = CascadeType.REMOVE)
	private Set<ChiTietKhuyenMai> setChiTietKhuyenMai;

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String moTa, Date ngayBatDau, Date ngayKetThuc,
			String trangThai) {
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.moTa = moTa;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.trangThai = trangThai;
	}

	// No-argument constructor
	public KhuyenMai() {
	}

	public KhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "KhuyenMai{" + "maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", moTa=" + moTa
				+ ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai=" + trangThai + '}';
	}

}
