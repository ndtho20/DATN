package com.example.demo.repository;


import com.example.demo.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {


    @Query("SELECT d.gioHangChiTiet.chiTietSanPham.loaiSanPham.ten, d.gioHangChiTiet.soLuong, d.gioHangChiTiet.chiTietSanPham.giaBan, d.hoaDon.tienShipHang  FROM HoaDonChiTiet d WHERE d.hoaDon.ngayTao BETWEEN ?1 AND ?2 AND d.hoaDon.trangThai = ?3")
    List<HoaDonChiTiet> findWithCategoryAndTimeBetween(Date paramDate1, Date paramDate2, Integer paramInteger);

    @Query("SELECT d.gioHangChiTiet.soLuong, d.gioHangChiTiet.chiTietSanPham.tenSanPham,d.gioHangChiTiet.chiTietSanPham.giaBan, d.hoaDon.tienShipHang  FROM HoaDonChiTiet d WHERE d.hoaDon.ngayTao BETWEEN ?1 AND ?2 AND d.hoaDon.trangThai = ?3")
    List<HoaDonChiTiet> findWithProductAndTimeBetween(Date paramDate1, Date paramDate2, Integer paramInteger);

    @Query("SELECT d.gioHangChiTiet.chiTietSanPham.idChiTietSanPham, d.gioHangChiTiet.soLuong, d.gioHangChiTiet.chiTietSanPham.giaBan, d.hoaDon.tienShipHang  FROM HoaDonChiTiet d WHERE d.hoaDon.ngayTao BETWEEN ?1 AND ?2 AND d.hoaDon.trangThai = ?3")
    List<HoaDonChiTiet> findWithOrderDetailAndTimeBetween(Date paramDate1, Date paramDate2, Integer paramInteger);

    @Query("SELECT count(hd) FROM HoaDonChiTiet hd WHERE hd.hoaDon.idHoaDon = ?1")
    Integer countHD(Integer paramInteger);
}
