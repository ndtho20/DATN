package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoadonchitiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhoadonchitiet")
    private Integer idHoaDonChiTiet;

    @Column(name = "ghichu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "idhoadon")
    private HoaDon hoaDon;

    @OneToOne
    @JoinColumn(name = "idgiohangchitiet")
    private GioHangChiTiet gioHangChiTiet;

    public HoaDonChiTiet(String tenSanPham, int soLuong, BigDecimal giaBan, double tienShipHang) {
        this.gioHangChiTiet = new GioHangChiTiet();
        this.gioHangChiTiet.setSoLuong(soLuong);
        this.ghiChu = tenSanPham;
        this.hoaDon = new HoaDon();
        this.hoaDon.setTienShipHang(tienShipHang);
    }
    public HoaDonChiTiet(int soLuong, String tenSanPham, BigDecimal giaBan, double tienShipHang) {
        this.gioHangChiTiet = new GioHangChiTiet();
        this.gioHangChiTiet.setSoLuong(soLuong);
        this.ghiChu = tenSanPham;
        this.hoaDon = new HoaDon();
        this.hoaDon.setTienShipHang(tienShipHang);
    }
    public HoaDonChiTiet(int idChiTietSanPham, int soLuong, BigDecimal giaBan, double tienShipHang) {
        // Khởi tạo đối tượng HoaDonChiTiet với các tham số từ truy vấn JPQL
        this.gioHangChiTiet = new GioHangChiTiet();
        this.gioHangChiTiet.setChiTietSanPham(new ChiTietSanPham());
        this.gioHangChiTiet.getChiTietSanPham().setIdChiTietSanPham(idChiTietSanPham);
        this.gioHangChiTiet.setSoLuong(soLuong);
        this.gioHangChiTiet.getChiTietSanPham().setGiaBan(giaBan);
        this.hoaDon = new HoaDon();
        this.hoaDon.setTienShipHang(tienShipHang);
    }
}
