/*
	@ (#) ChiTietDonHang.java		Apr 10, 2024
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
ok
 */
@Entity
public class ChiTietDonDatHang implements Serializable{

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maDonDatHang")
	private DonDatHang maDDH;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSP")
	private SanPham maSP;

	@Column(name = "soLuong", nullable = false)
	private int soLuong;

	@Column(name = "donGiaBan", nullable = false)
	private Double donGia;

	public ChiTietDonDatHang(DonDatHang maDDH, SanPham maSP, int soLuong, Double donGia) {
		this.maDDH = maDDH;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public ChiTietDonDatHang() {
	}

	public ChiTietDonDatHang(DonDatHang maDDH) {
		this.maDDH = maDDH;
	}

	public ChiTietDonDatHang(DonDatHang maDDH, int soLuong, Double donGia) {
		this.maDDH = maDDH;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public DonDatHang getMaDDH() {
		return maDDH;
	}

	public void setMaDDH(DonDatHang maDDH) {
		this.maDDH = maDDH;
	}

	public SanPham getMaSP() {
		return maSP;
	}

	public void setMaSP(SanPham maSP) {
		this.maSP = maSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "ChiTietDonDatHang [maDDH=" + maDDH + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia
				+ "]";
	}

}
