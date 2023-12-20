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
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnhanvien")
    private Integer idNhanVien;

    @Column(name = "hovaten")
    @NotBlank(message = "không được để trống họ và tên")
    private String hoTen;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaysinh")
    @NotNull(message = "không được để trống ngày sinh")
    private Date ngaySinh;

    @Column(name = "gioitinh")
    @NotBlank(message = "không được để trống giới tính")
    private String gioiTinh;

    @Column(name = "sodienthoai")
    @NotBlank(message = "không được để trống số điện thoại")
    private String soDienThoai;

    @Column(name = "matkhau")
    @NotBlank(message = "không được để trống mật khẩu")
    private String matKhau;

    @Column(name = "diachi")
    @NotBlank(message = "không được để trống địa chỉ")
    private String diaChi;

    @Column(name = "email")
    @NotBlank(message = "không được để trống email")
    private String email;

    @Column(name = "verificationcode")
    private String verificationCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    @NotNull(message = "không được để trống ngày tạo")
    private Date ngayTao;
    @Column(name = "trangthai")
    private Boolean trangThai;

}
