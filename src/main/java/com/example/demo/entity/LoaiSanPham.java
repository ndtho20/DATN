package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idloaisanpham")
    private Integer idSanPham;
    @Column(name = "ma")
    @NotBlank(message = "không được để trống ma")
    private String ma;
    @Column(name = "ten")
    @NotBlank(message = "không được để trống tên")
    private String ten;
    @Column(name = "trangthai")
    private Boolean trangThai;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaytao")
    @NotNull(message = "không được để trống ngày tạo")
    private Date ngayTao;
    @OneToMany(mappedBy = "loaiSanPham")
    private List<ChiTietSanPham> chiTietSanPham;



}
