package com.example.demo.repository;


import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.gioHang.idGioHang = :gioHangId AND ghct.trangThai = true")
    List<GioHangChiTiet> findByGioHangIdAndTrangThai(Integer gioHangId);

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.gioHang.idGioHang = :gioHangId AND ghct.chiTietSanPham.idChiTietSanPham = :chiTietSanPhamId")
    GioHangChiTiet findByGioHangIdAndChiTietSanPhamId(Integer gioHangId, Integer chiTietSanPhamId);

}
