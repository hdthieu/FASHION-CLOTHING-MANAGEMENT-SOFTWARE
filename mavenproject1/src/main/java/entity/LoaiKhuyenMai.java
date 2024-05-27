/*
	@ (#) LoaiKhuyenMai.java		Apr 10, 2024
*/
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

/*
@author: Đào Thanh Phú
@date: Apr 10, 2024
*/

public class LoaiKhuyenMai implements Serializable{
    private String maLoaiKMai;
    @Column(columnDefinition = "nvarchar(45)")
    private String tenLoaiKM;

    public LoaiKhuyenMai(String maLoaiKMai, String tenLoaiKM) {
        this.maLoaiKMai = maLoaiKMai;
        this.tenLoaiKM = tenLoaiKM;
    }

    public String getMaLoaiKMai() {
        return maLoaiKMai;
    }

    public String getTenLoaiKM() {
        return tenLoaiKM;
    }

    public void setMaLoaiKMai(String maLoaiKMai) {
        this.maLoaiKMai = maLoaiKMai;
    }

    public void setTenLoaiKM(String tenLoaiKM) {
        this.tenLoaiKM = tenLoaiKM;
    }

    @Override
    public String toString() {
        return "loaiKhuyenMai{" + "maLoaiKMai=" + maLoaiKMai + ", tenLoaiKM=" + tenLoaiKM + '}';
    }

}

