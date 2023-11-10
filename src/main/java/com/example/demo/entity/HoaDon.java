package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhoadon")
    private Integer idHoaDon;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    private Date ngayTao;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "ngaygiaohang")
    private Date ngayGiaoHang;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "ngaynhan")
    private Date ngayNhan;

    @Column(name = "mahoadon")
    private String maHoaDon;

    @Column(name = "phuongthucmuahang")
    private boolean phuongThucMuaHang;

    @Column(name = "tienshiphang")
    private Double tienShipHang;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "httt")
    private boolean hTTT;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @Column(name = "diachidonhang")
    private String diaChiDonHang;

    @ManyToOne
    @JoinColumn(name = "idnhanvien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "idkhachhang")
    private KhachHang khachHang;
}
