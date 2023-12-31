package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chitietsanpham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idchitietsanpham")
    private Integer idChiTietSanPham;

    @Column(name = "tensanpham")
    @NotBlank(message = "không được để trống tên sản phẩm")
    private String tenSanPham;

    @ManyToOne
    @JoinColumn(name = "idloaisanpham")
    private LoaiSanPham loaiSanPham;

    @ManyToOne
    @JoinColumn(name = "idchatlieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "idsize")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "idphongcach")
    private PhongCach phongCach;

    @ManyToOne
    @JoinColumn(name = "idnsx")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    private MauSac mauSac;

    @Column(name = "soluongconlai")
    @NotNull(message = "không được để trống số lượng ")
    private Integer soLuong;

    @Column(name = "giaban")
    @NotNull(message = "không được để trống giá bán")
    private BigInteger giaBan;

    @Column(name = "trangthai")
    private Boolean trangThai;

    public Integer getIdLoaiSanPham() {
        return loaiSanPham != null ? loaiSanPham.getIdSanPham() : null;
    }

    @OneToMany(mappedBy = "chiTietSanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HinhAnh> danhSachHinhAnh = new ArrayList<>();

}
