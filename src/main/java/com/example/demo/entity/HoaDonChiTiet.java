package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
