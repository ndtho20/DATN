package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giohangchitiet")
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgiohangchitiet")
    private Integer idGioHangChiTiet;

    @ManyToOne
    @JoinColumn(name = "idgiohang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "idchitietsanpham")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "tonggia", precision = 18, scale = 2)
    private BigDecimal tongGia;

    @Column(name = "trangthai")
    private boolean trangThai;
}
