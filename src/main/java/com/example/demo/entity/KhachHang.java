package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "không được để trống tên ")
    private String ten;

    @Column(name = "gioitinh")
    @NotBlank(message = "không được để trống giới tính")
    private String gioiTinh;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaysinh")
    @NotNull(message = "không được để trống ngày sinh")
    private Date ngaySinh;

    @Column(name = "diachi")
    @NotBlank(message = "không được để trống địa chỉ")
    private String diaChi;

    @Column(name = "sodienthoai")
    @NotBlank(message = "không được để trống số điện thoại")
    private String soDienThoai;

    @Column(name = "email")
    @NotBlank(message = "không được để trống email")
    private String email;

    @Column(name = "matkhau")
    @NotBlank(message = "không được để trống mật khẩu")
    private String matKhau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    @NotNull(message = "không được để trống ngày tạo")
    private Date ngayTao;

    @Column(name = "trangthai")
    private Boolean trangThai;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "khachHang")
    private GioHang gioHang;

    @Column(name = "verificationcode")
    private String verificationCode;

}
