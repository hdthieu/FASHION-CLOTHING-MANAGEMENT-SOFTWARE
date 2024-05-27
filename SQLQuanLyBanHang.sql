﻿ INSERT INTO dbo.KhachHang (maKH, tenKH, diaChi, soDienThoai, gioiTinh)
VALUES 
  ('KH001', N'Nguyễn Vãn Tèo', N'123 Lê Vãn, HCM', '0123456789', N'Male'),
  ('KH002', N'Trần Thị Bích', N'256 Nguyễn Trãi, Hà Nội', '0987654321', N'Female'),
  ('KH003', N'Lê Thị Ánh', N'789 Xô Viết Nghệ Tĩnh, Huế', '0912345678', N'Female'),
  ('KH004', N'Phạm Vãn Ðồng', N'321 Trần Phú, Ðà Nẵng', '0923456789', N'Male'),
  ('KH005', N'Nguyễn Vãn Cường', N'456 Lê Lai, Hà Nội', '0123456789', N'Male'),
  ('KH006', N'Trần Thị Mai', N'789 Nguyễn Đình Chính, Đà Nẵng', '0987654321', N'Female'),
  ('KH007', N'Hoàng Văn Hùng', N'123 Phan Kế Bính, HCM', '0912345678', N'Male'),
  ('KH008', N'Lê Thị Lan', N'456 Lê Văn Sĩ, Hà Nội', '0923456789', N'Female'),
  ('KH009', N'Phạm Văn Bình', N'789 Trần Hưng Đạo, HCM', '0901234567', N'Male'),
  ('KH010', N'Nguyễn Thị Hằng', N'321 Lý Thường Kiệt, Cần Thơ', '0934567890', N'Female'),
  ('KH011', N'Đỗ Thị Hồng', N'123 Điện Biên Phủ, HCM', '0934567891', N'Female'),
  ('KH012', N'Bùi Văn Nam', N'234 Lý Thường Kiệt, Hà Nội', '0934567892', N'Male'),
  ('KH013', N'Trần Văn Bảo', N'345 Nguyễn Trãi, Nha Trang', '0934567893', N'Male'),
  ('KH014', N'Lê Thị Nga', N'456 Lê Hồng Phong, Hải Phòng', '0934567894', N'Female'),
  ('KH015', N'Phạm Văn Hải', N'567 Trần Hưng Đạo, Cần Thơ', '0934567895', N'Male'),
  ('KH016', N'Nguyễn Thị Lan', N'678 Phan Đình Phùng, Đà Nẵng', '0934567896', N'Female'),
  ('KH017', N'Vũ Văn Hùng', N'789 Lê Duẩn, Vinh', '0934567897', N'Male'),
  ('KH018', N'Trần Thị Hà', N'890 Nguyễn Văn Cừ, Huế', '0934567898', N'Female'),
  ('KH019', N'Ngô Văn Khoa', N'901 Trần Phú, Quy Nhơn', '0934567899', N'Male'),
  ('KH020', N'Lưu Thị Thanh', N'1012 Lê Lợi, Hải Dương', '0934567810', N'Female');

-- Thêm d? li?u vào b?ng NhanVien
-- Thêm d? li?u vào b?ng NhanVien ð? s?a ð?i (QL004 ð?i thành NV004)
INSERT INTO dbo.NhanVien (maNhanVien, tenNhanVien, diaChi, soDienThoai, email, gioiTinh, ngaySinh, hinhAnh, chucVu)
VALUES 
  ('QL001', N'Nguyễn Vãn', N'Quận Gò Vấp, HCM', '0987563874', 'hieu13200303@gmail.com', N'Male', '1990-01-15', 'nv1.jpg', N'Quản Lý'), 
  ('NV002', N'Lê Thị Hồng', N'Quận Bình Thạnh, HCM', '0123456789','phamtran29223@gmail.com', N'Female', '1995-05-05', 'nv3.jfif', N'Nhân Viên Bán Hàng'),
  ('NV003', N'Ðoàn Vãn Hậu', N'Quận 1, HCM', '0934567891','hdthieu2003@gmail.com', N'Male', '1992-08-20', 'nv2.jpg', N'Nhân Viên Bán Hàng'),
  ('NV004', N'Trần Thị Thảo', N'Quận 3, HCM', '0945678912','dinhhung12122003@gmail.com', N'Female', '1994-04-15', 'nv3.jpg', N'Nhân Viên Bán Hàng'),
  ('NV005', N'Trần Thị Thơm', N'Quận 3, HCM', '0945678911','dinhhung12122003@gmail.com', N'Female', '1994-04-15', 'nv3.jpg', N'Nhân Viên Bán Hàng'),
  ('NV006', N'Nguyễn Thị Hoa', N'Quận 7, HCM', '0987654321', 'hoanguyen@gmail.com', N'Female', '1994-02-25', 'nv6.jpg', N'Nhân Viên Bán Hàng'),
  ('NV007', N'Võ Văn Nam', N'Quận Phú Nhuận, HCM', '0912345678', 'vonam@gmail.com', N'Male', '1990-10-10', 'nv7.jpg', N'Nhân Viên Bán Hàng'),
  ('NV008', N'Lê Thị Kim', N'Quận Bình Thạnh, HCM', '0901234567', 'lekim@gmail.com', N'Female', '1993-07-15', 'nv8.jpg', N'Nhân Viên Bán Hàng'),
  ('NV009', N'Phan Văn Minh', N'Quận 2, HCM', '0945678912', 'minhphan@gmail.com', N'Male', '1995-11-20', 'nv9.jpg', N'Nhân Viên Bán Hàng'),
  ('NV010', N'Huỳnh Thị Linh', N'Quận Tân Bình, HCM', '0967890123', 'linhhuynh@gmail.com', N'Female', '1992-03-12', 'nv10.jpg', N'Nhân Viên Bán Hàng'),
  ('NV011', N'Nguyễn Thị Mai', N'Quận Thủ Đức, HCM', '0987123456', 'mainguyen@gmail.com', N'Female', '1996-06-10', 'nv11.jpg', N'Nhân Viên Bán Hàng'),
  ('NV012', N'Phạm Văn Hòa', N'Quận 5, HCM', '0987987654', 'hoapham@gmail.com', N'Male', '1988-12-15', 'nv12.jpg', N'Nhân Viên Bán Hàng'),
  ('NV013', N'Lê Văn Tú', N'Quận 9, HCM', '0987765432', 'tule@gmail.com', N'Male', '1990-07-20', 'nv13.jpg', N'Nhân Viên Bán Hàng'),
  ('NV014', N'Trần Thị Hạnh', N'Quận Tân Phú, HCM', '0987890123', 'hanhtran@gmail.com', N'Female', '1993-04-05', 'nv14.jpg', N'Nhân Viên Bán Hàng'),
  ('NV015', N'Đỗ Thị Thanh', N'Quận 6, HCM', '0987012345', 'thanhdo@gmail.com', N'Female', '1992-01-25', 'nv15.jpg', N'Nhân Viên Bán Hàng'),
  ('NV016', N'Bùi Văn Kỳ', N'Quận 8, HCM', '0987234567', 'kybui@gmail.com', N'Male', '1991-11-30', 'nv16.jpg', N'Nhân Viên Bán Hàng'),
  ('NV017', N'Hoàng Thị Ngọc', N'Quận 11, HCM', '0987345678', 'ngochuang@gmail.com', N'Female', '1989-03-15', 'nv17.jpg', N'Nhân Viên Bán Hàng'),
  ('NV018', N'Lưu Văn Thành', N'Quận Gò Vấp, HCM', '0987456789', 'thanhluu@gmail.com', N'Male', '1993-08-20', 'nv18.jpg', N'Nhân Viên Bán Hàng'),
  ('NV019', N'Ngô Thị Hương', N'Quận Bình Tân, HCM', '0987567890', 'huongngo@gmail.com', N'Female', '1994-05-30', 'nv19.jpg', N'Nhân Viên Bán Hàng'),
  ('NV020', N'Trương Văn Duy', N'Quận 12, HCM', '0987678901', 'duytruong@gmail.com', N'Male', '1995-02-10', 'nv20.jpg', N'Nhân Viên Bán Hàng');

-- Thêm d? li?u vào b?ng TaiKhoan
INSERT INTO dbo.TaiKhoan (tenTaiKhoan, matKhau, quyenTruyCap, maNhanVien)
VALUES 
  (N'nguyenvan', '123', N'Quản Lý', 'QL001'), 
  (N'laivantao', '123', N'Nhân Viên Bán Hàng', 'NV002'),
  (N'doanvanhau', 'abc123', N'Nhân Viên Bán Hàng', 'NV003'),
  (N'tranthithao', 'xyz789', N'Nhân Viên Bán Hàng', 'NV004'),
  (N'tranthithom', 'xyz789', N'Nhân Viên Bán Hàng', 'NV005'),
  (N'nguyencuong', '123', N'Nhân Viên Bán Hàng', 'NV006'), 
  (N'vonam', '456', N'Nhân Viên Bán Hàng', 'NV007'),
  (N'lekim', '789', N'Nhân Viên Bán Hàng', 'NV008'),
  (N'minhphan', 'abc123', N'Nhân Viên Bán Hàng', 'NV009'),
  (N'linhhuynh', 'xyz789', N'Nhân Viên Bán Hàng', 'NV010'),
  (N'mainguyen', '123456', N'Nhân Viên Bán Hàng', 'NV011'), 
  (N'hoapham', 'abcdef', N'Nhân Viên Bán Hàng', 'NV012'),
  (N'tule', '123abc', N'Nhân Viên Bán Hàng', 'NV013'),
  (N'hanhtran', '456def', N'Nhân Viên Bán Hàng', 'NV014'),
  (N'thanhdo', '789ghi', N'Nhân Viên Bán Hàng', 'NV015'),
  (N'kybui', '321jkl', N'Nhân Viên Bán Hàng', 'NV016'), 
  (N'ngochuang', '654mno', N'Nhân Viên Bán Hàng', 'NV017'),
  (N'thanhluu', '987pqr', N'Nhân Viên Bán Hàng', 'NV018'),
  (N'huongngo', '159stu', N'Nhân Viên Bán Hàng', 'NV019'),
  (N'duytruong', '753vwx', N'Nhân Viên Bán Hàng', 'NV020');

-- Thêm d? li?u vào b?ng NhaCungCap
INSERT INTO dbo.NhaCungCap (maNhaCungCap, tenNhaCungCap, diaChi, soDienThoai)
VALUES 
  ('NCC001', N'Công Ty Quần Jean', N'789 Hoàng Vân, Ðồng Tháp', '0987654321'),
  ('NCC002', N'Công Ty Áo Yame', N'456 Lê Vãn Việt, HCM', '0123456789'),
  ('NCC003', N'Công Ty Giày Thể Thao', N'101 Hai Ba Trung, Da Nang', '0933123456'),
  ('NCC004', N'Công Ty Phụ Kiện TR', N'202 Le Loi, Hue', '0944234567'),
  ('NCC005', N'Công Ty Áo Sơ Mi', N'123 Lê Lợi, Hà Nội', '0987654321'),
  ('NCC006', N'Công Ty Giày Thời Trang', N'456 Lê Thanh Nghị, Đà Nẵng', '0912345678'),
  ('NCC007', N'Công Ty Phụ Kiện Đẹp', N'101 Hai Bà Trưng, HCM', '0923456789'),
  ('NCC008', N'Công Ty Quần Jean Nam', N'202 Lê Lợi, Huế', '0901234567'),
  ('NCC009', N'Công Ty Túi Xách Fashion', N'111 Lê Lai, Hà Nội', '0945678912'),
  ('NCC010', N'Công Ty Quần Style Love', N'789 Hùng Vương, Cần Thơ', '0987123456'),
  ('NCC011', N'Công Ty Quần Jean Đẹp', N'456 Hải Phòng, Vĩnh Long', '0123987654'),
  ('NCC012', N'Công Ty Thời Trang Trẻ', N'101 Thanh Niên, Nha Trang', '0933456789'),
  ('NCC013', N'Công Ty Giày Đẹp', N'202 Nguyễn Du, Đà Lạt', '0944567890'),
  ('NCC014', N'Công Ty Áo Thun Cool', N'123 Trần Hưng Đạo, Bình Dương', '0987765432'),
  ('NCC015', N'Công Ty Phụ Kiện TR', N'456 Phan Đình Phùng, Phú Yên', '0912987654'),
  ('NCC016', N'Công Ty Giày Cao Cấp', N'101 Lý Thái Tổ, Đồng Nai', '0923456712'),
  ('NCC017', N'Công Ty Quần Áo Thể Thao', N'202 Trường Chinh, Tây Ninh', '0901234578'),
  ('NCC018', N'Công Ty Balo Hiện Đại', N'111 Điện Biên Phủ, Gia Lai', '0945678123'),
  ('NCC019', N'Công Ty Quần Short', N'333 Lê Lai, Kon Tum', '0987123489'),
  ('NCC020', N'Công Ty Phụ Kiện Phong Th', N'444 Hồ Chí Minh, Quảng Nam', '0934567123');
-- Thêm d? li?u vào b?ng LoaiSP
INSERT INTO dbo.LoaiSanPham (maLoai, tenLoai)
VALUES 
  ('L001', N'Quần'), 
  ('L002', N'Áo'), 
  ('L003', N'Giày'),
  ('L004', N'Túi Xách');

-- Thêm d? li?u vào b?ng SanPham
INSERT INTO dbo.SanPham (maSP, tenSP, giaBan, giaNhap, soLuong, maloaiSP, maNhaCungCap, hinhAnh)
VALUES 
  ('SP001', N'Quần Louis', 2000000, 1500000, 500, 'L001', 'NCC001', 'quanlouis.jfif'),
  ('SP002', N'Áo Yame SCVT', 1800000, 1300000, 350, 'L002', 'NCC002', 'yameSCTV.jfif'),
  ('SP003', N'Giày Adidas', 1500000, 1100000, 250, 'L003', 'NCC003', 'addidas.jpg'),
  ('SP004', N'Túi Xách Gucci', 3000000, 2400000, 520, 'L004', 'NCC004', 'TuiGucciHorsebit.jpg'),
  ('SP005', N'Áo Ngôi Sao', 3000000, 2600000, 520, 'L002', 'NCC012', 'Ao_Co_Do.jpg'),
  ('SP006', N'Quần âu QA14', 3000000, 2600000, 520, 'L001', 'NCC001', 'au_QA14.jpeg'),
  ('SP007', N'Áo Bomber', 3000000, 2600000, 520, 'L002', 'NCC017', 'aoBomber.jpg'),
  ('SP008', N'Áo Omachi', 100000, 60000, 520, 'L002', 'NCC012', 'aoOmachi.jfif'),
  ('SP009', N'Áo Paradox', 300000, 150000, 600, 'L002', 'NCC020', 'AoParadox.jfif'),
  ('SP010', N'Quần Âu QA100', 450000, 300000, 550, 'L001', 'NCC012', 'Au_QA100.jpg'),
  ('SP011', N'Quần AuAdino', 900000, 450000, 580, 'L001', 'NCC011', 'AuAdino.jpg'),
  ('SP012', N'Áo Baby Tea', 2000000, 1500000, 500, 'L002', 'NCC012', 'babyTea.png'),
  ('SP013', N'Túi Xách Balki', 1800000, 1300000, 350, 'L004', 'NCC009', 'Balki.jpg'),
  ('SP014', N'Giày Da bitis X', 1500000, 1100000, 250, 'L003', 'NCC006', 'bitisXDMM.jpg'),
  ('SP015', N'Giày Da BN0130', 300000, 240000, 520, 'L003', 'NCC006', 'daBN0130.jpg'),
  ('SP016', N'Giày Da PQ', 3000000, 2600000, 520, 'L003', 'NCC016', 'DaPQ.jfif'),
  ('SP017', N'Quần Jean xanh', 180000, 100000, 520, 'L001', 'NCC019', 'jeannd.jpeg'),
  ('SP018', N'Quần ống rộng vàng', 300000, 200000, 520, 'L001', 'NCC008', 'quanongrong.jpg'),
  ('SP019', N'Quần GalaMi', 300000, 200000, 530, 'L001', 'NCC008', 'quanGalaMi.jfif'),
  ('SP020', N'Quần Tây Đen X-2', 300000, 150000, 600, 'L001', 'NCC014', 'QuanTayX2.jpg'),
  ('SP021', N'Quần short X2', 450000, 300000, 550, 'L001', 'NCC004', 'shortX2.jfif'),
  ('SP022', N'Sơ mi xám XT1', 320000, 250000, 580, 'L002', 'NCC002', 'somixamX1.jpg'),
  ('SP023', N'Sơ mi xanh XX1', 200000, 150000, 500, 'L002', 'NCC002', 'somixanhXX1.jpg'),
  ('SP024', N'Quần tây BAGY', 250000, 190000, 400, 'L001', 'NCC010', 'tayBAGY.jpg'),
  ('SP025', N'Quần Tây Coolmax', 300000, 150000, 300, 'L001', 'NCC010', 'tayCoolmax.jpg'),
  ('SP026', N'Túi Floralpunk', 450000, 300000, 280, 'L004', 'NCC004', 'Floralpunk.png'),
  ('SP027', N'Túi Xách Halerendar', 900000, 450000, 540, 'L004', 'NCC007', 'halerendar.jpg'),
  ('SP028', N'Quần PW2', 380000, 250000, 300, 'L001', 'NCC019', 'PW2.jfif'),
  ('SP029', N'Giày Da SIG-01', 450000, 300000, 290, 'L003', 'NCC016', 'daSIG-06.jpg'),
  ('SP030', N'Quần Tây Nam Kẻ sọc', 200000, 100000, 540, 'L001', 'NCC013', 'taynamkely.jpg');
  -- Thêm d? li?u vào b?ng HoaDon
INSERT INTO dbo.HoaDon (maHoaDon, ngayTao, maKH, maNhanVien)
VALUES 
  ('HD002', '2017-05-14', 'KH002', 'NV002'),
  ('HD003', '2017-11-05', 'KH002', 'NV002'),
  ('HD004', '2018-01-20', 'KH003', 'NV003'),
  ('HD005', '2018-02-22', 'KH004', 'NV004'),
  ('HD006', '2018-06-15', 'KH005', 'NV006'),
  ('HD007', '2020-01-20', 'KH006', 'NV007'),
  ('HD008', '2020-02-10', 'KH007', 'NV008'),
  ('HD009', '2020-02-12', 'KH008', 'NV009'),
  ('HD010', '2020-02-13', 'KH009', 'NV010'),
  ('HD011', '2020-02-20', 'KH015', 'NV014'),
  ('HD012', '2020-03-05', 'KH002', 'NV002'),
  ('HD013', '2020-03-10', 'KH003', 'NV012'),
  ('HD014', '2020-03-22', 'KH016', 'NV016'),
  ('HD015', '2020-04-15', 'KH017', 'NV006'),
  ('HD016', '2020-05-20', 'KH011', 'NV011'),
  ('HD017', '2020-05-21', 'KH014', 'NV020'),
  ('HD018', '2020-05-30', 'KH019', 'NV017'),
  ('HD019', '2020-06-08', 'KH009', 'NV010'),
  ('HD020', '2021-01-08', 'KH020', 'NV011'),
  ('HD021', '2021-01-20', 'KH012', 'NV011'),
  ('HD022', '2021-01-21', 'KH016', 'NV020'),
  ('HD023', '2021-02-03', 'KH014', 'NV018'),
  ('HD024', '2021-02-08', 'KH005', 'NV004'),
  ('HD025', '2021-02-09', 'KH020', 'NV011');

-- Thêm d? li?u vào b?ng HoaDon
INSERT INTO dbo.ChiTietHoaDon (maHoaDon, maSP, soLuong, donGiaBan)
VALUES 

  ('HD002', 'SP002', 3, 1200000),
  ('HD003', 'SP001', 2, 2000000),
  ('HD004', 'SP003', 1, 1500000),
  ('HD005', 'SP004', 2, 3000000),
  ('HD006', 'SP009', 3, 800000),
  ('HD007', 'SP010', 5, 500000),
  ('HD008', 'SP011', 2, 1200000),
  ('HD009', 'SP005', 4, 700000),
  ('HD010', 'SP006', 3, 900000),
  ('HD011', 'SP001', 2, 6000000),
  ('HD011', 'SP002', 2, 3600000),
  ('HD012', 'SP015', 2, 6000000),
  ('HD012', 'SP017', 3, 480000),
  ('HD013', 'SP015', 2, 6000000),
  ('HD013', 'SP017', 3, 480000),
  ('HD014', 'SP018', 1, 300000),
  ('HD014', 'SP019', 2, 600000),
  ('HD015', 'SP020', 3, 900000),
  ('HD016', 'SP021', 4, 1800000),
  ('HD017', 'SP022', 2, 640000),
  ('HD018', 'SP023', 1, 200000),
  ('HD019', 'SP024', 3, 750000),
  ('HD020', 'SP025', 2, 600000),
  ('HD021', 'SP026', 1, 450000),
  ('HD022', 'SP027', 2, 1800000),
  ('HD023', 'SP028', 3, 1140000),
  ('HD024', 'SP029', 1, 450000),
  ('HD025', 'SP030', 2, 400000);

-- Chèn d? li?u vào PhieuDatHang
INSERT INTO dbo.DonDatHang (maDonDatHang, ngayTao, maKH, maNhanVien,tinhTrang)
VALUES ('DDH001', '2023-11-13', 'KH003', 'NV002',N'Đã Thanh Toán'),
       ('DDH002', '2023-11-14', 'KH004', 'NV007',N'Chưa Thanh Toán'),
       ('DDH003', '2023-11-15', 'KH005', 'NV003',N'Chưa Thanh Toán'),
	   ('DDH004', '2023-04-10', 'KH008', 'NV006',N'Chưa Thanh Toán'),
	   ('DDH005', '2023-05-15', 'KH007', 'NV009',N'Đã Thanh Toán'),
	   ('DDH006', '2023-06-20', 'KH011', 'NV003',N'Chưa Thanh Toán'),
	   ('DDH007', '2023-07-25', 'KH003', 'NV009',N'Đã Thanh Toán'),
	   ('DDH008', '2023-08-30', 'KH015', 'NV010',N'Đã Hủy'),
		('DDH009', '2023-09-05', 'KH019', 'NV011', N'Chưa Thanh Toán'),
		('DDH010', '2023-10-10', 'KH011', 'NV012', N'Chưa Thanh Toán'),
		('DDH011', '2023-11-15', 'KH010', 'NV018', N'Đã Thanh Toán'),
		('DDH012', '2023-11-20', 'KH013', 'NV004', N'Chưa Thanh Toán'),
		('DDH013', '2023-01-25', 'KH018', 'NV015', N'Đã Thanh Toán'),
		('DDH014', '2023-02-28', 'KH015', 'NV017', N'Chưa Thanh Toán'),
		('DDH015', '2023-03-05', 'KH011', 'NV011', N'Đã Thanh Toán'),
		('DDH016', '2023-04-10', 'KH013', 'NV013', N'Chưa Thanh Toán'),
		('DDH017', '2023-05-15', 'KH018', 'NV010', N'Đã Thanh Toán'),
		('DDH018', '2023-06-20', 'KH019', 'NV020', N'Chưa Thanh Toán'),
		('DDH019', '2023-07-25', 'KH015', 'NV019', N'Đã Thanh Toán'),
		('DDH020', '2023-08-30', 'KH016', 'NV006', N'Đã Hủy');

INSERT INTO dbo.ChiTietDonDatHang (maDonDatHang, donGiaBan, soLuong, maSP)
VALUES ('DDH001', 300000, 5, 'SP020'),
       ('DDH002', 350000, 3, 'SP007'),
       ('DDH002', 1800000, 4, 'SP002'),
	   ('DDH003', 100000, 4, 'SP008'),
	   ('DDH003', 1500000, 7, 'SP003'),
	   ('DDH003', 300000, 3, 'SP018'),
	   ('DDH004', 300000, 5, 'SP021'),
	   ('DDH005', 900000, 6, 'SP027'),
	   ('DDH005', 1800000, 5, 'SP013'),
       ('DDH005', 300000, 3, 'SP018'),
       ('DDH006', 3000000, 4, 'SP006'),
	   ('DDH006', 3000000, 5, 'SP005'),
	   ('DDH007', 1800000, 3, 'SP013'),
	   ('DDH008', 1500000, 3, 'SP014'),
	   ('DDH009', 3000000, 6, 'SP005'),
	    ('DDH010', 2000000, 3, 'SP001'),
       ('DDH010', 180000, 4, 'SP017'),
	   ('DDH011', 3000000, 5, 'SP016'),
	   ('DDH011', 450000, 7, 'SP010'),
	   ('DDH012', 300000, 3, 'SP015'),
	   ('DDH012', 450000, 5, 'SP029'),
	   ('DDH013', 320000, 6, 'SP022'),
	    ('DDH014', 1500000, 3, 'SP014'),
       ('DDH015', 3000000, 3, 'SP006'),
	   ('DDH015', 300000, 6, 'SP009'),
	   ('DDH016', 100000, 8, 'SP008'),
	   ('DDH017', 3000000, 9, 'SP016'),
	   ('DDH018', 600000, 3, 'SP006'),
	   ('DDH019', 1800000, 1, 'SP002'),
	    ('DDH019', 2000000,8, 'SP012'),
	   ('DDH020', 380000, 5, 'SP028'),
	   ('DDH020', 300000, 6, 'SP025');
-- Thêm d? li?u vào b?ng KhuyenMai
INSERT INTO dbo.KhuyenMai (maKhuyenMai, tenKhuyenMai, moTa, ngayBatDau, ngayKetThuc, trangThai)
VALUES 
  ('KM001', N'Giảm Giá Mùa Hè', N'Giảm giá 30.000 cho Áo', '2020-01-01', '2020-06-30', 'off'),
  ('KM002', N'Black Friday', N'Giảm giá 35.000 cho Quần', '2020-02-24', '2020-11-25', 'off'),
  ('KM003', N'Giảm Giá Mùa Thu', N'Giảm giá 15000 cho tất cả sản phẩm', '2020-09-01', '2020-09-30', 'off'),
  ('KM004', N'Khuyến Mãi Tết Nguyên Đán', N'Giảm giá 20000 cho đợt Tết', '2020-11-15', '2020-12-15','off'), 
  ('KM005', N'Khuyến Mãi Sốc', N'Giảm giá 30000', '2021-05-15', '2021-05-30', 'off'),
  ('KM006', N'Khuyến Mãi Tuyệt Vời', N'Giảm giá 20000 cho khách hàng', '2021-06-16', '2021-06-18', 'off'),
  ('KM007', N'Khuyến Mãi', N'Giảm giá 20000 cho Áo', '2021-01-15', '2021-01-18', 'off');

-- Thêm dữ liệu vào bảng ChiTietKhuyenMai
INSERT INTO dbo.ChiTietKhuyenMai (maKhuyenMai, maSP, giaGiam)
VALUES 
  ('KM001', 'SP001', 30000), 
  ('KM001', 'SP006', 30000),
  ('KM001', 'SP010', 30000), 
  ('KM002', 'SP006', 35000), 
  ('KM002', 'SP010', 35000),
  ('KM003', 'SP007', 15000),
  ('KM003', 'SP005', 15000),
  ('KM004', 'SP004', 20000),
  ('KM005', 'SP022', 30000), 
  ('KM005', 'SP023', 30000), 
  ('KM006', 'SP017', 20000),
  ('KM006', 'SP018', 20000),
  ('KM007', 'SP007', 20000),
  ('KM007', 'SP008', 20000);