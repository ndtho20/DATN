package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hinhanh")
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhinhanh")
    private Integer idHinhAnh;

    @Column(name = "duongdan")
    private String duongDan;

    @ManyToOne
    @JoinColumn(name = "idchitietsanpham")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "trangthai")
    private Boolean trangThai;

    @Column(name = "ngaytao")
    private Date ngayTao;
    @PrePersist
    protected void onCreate() {
        this.ngayTao = new Date();
    }
}
