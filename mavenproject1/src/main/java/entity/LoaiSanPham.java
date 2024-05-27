/*
	@ (#) LoaiSanPham.java		Apr 10, 2024
*/
package entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/
@Entity
public class LoaiSanPham implements Serializable{
	@Id
	private String maLoai;
	@Column(columnDefinition = "nvarchar(45)", nullable = false)
	private String tenLoai;

	@OneToMany(mappedBy = "loaiSP", cascade = CascadeType.REMOVE)
	private Set<SanPham> setSanPham;

	public LoaiSanPham(String maLoai, String tenLoai) {
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	
	
	public LoaiSanPham() {
		super();
	}



	public LoaiSanPham(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "LoaiSanPham{" + "maLoai=" + maLoai + ", tenLoai=" + tenLoai + '}';
	}

}
