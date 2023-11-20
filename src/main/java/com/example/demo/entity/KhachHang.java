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
@Table(name = "khachhang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkhachhang")
    private Integer idKhachHang;

    @Column(name = "ten")
    private String ten;

    @Column(name = "gioitinh")
    private String gioiTinh;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "matkhau")
    private String matKhau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "trangthai")
    private Boolean trangThai;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "khachHang")
    private GioHang gioHang;

}
