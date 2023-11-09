package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnhanvien")
    private Integer idNhanVien;

    @Column(name = "hovaten")
    private String hoTen;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "gioitinh")
    private String gioiTinh;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "vaitro")
    private Boolean vaiTro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "trangthai")
    private Boolean trangThai;

}
