package com.example.demo.repository;


import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.gioHang.idGioHang = :gioHangId AND ghct.trangThai = true")
    List<GioHangChiTiet> findByGioHangIdAndTrangThai(Integer gioHangId);

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.gioHang.idGioHang = :gioHangId AND ghct.chiTietSanPham.idChiTietSanPham = :chiTietSanPhamId AND ghct.trangThai = true ")
    GioHangChiTiet findByGioHangIdAndChiTietSanPhamId(Integer gioHangId, Integer chiTietSanPhamId);

    @Query("SELECT c.tenSanPham, c.giaBan, gct.soLuong, gct.tongGia " +
            "FROM GioHangChiTiet gct " +
            "JOIN gct.chiTietSanPham c " +
            "WHERE gct.idGioHangChiTiet IN ?1")
    List<Object[]> findDetailsById(List<Integer> idGioHangChiTiet);

    @Query("SELECT gct.idGioHangChiTiet,c.tenSanPham, c.giaBan, gct.soLuong, gct.tongGia " +
            "FROM GioHangChiTiet gct " +
            "JOIN gct.gioHang gh " +
            "JOIN gct.chiTietSanPham c " +
            "WHERE gh.khachHang = :khachHang and gct.trangThai = true")
    List<Object[]> findGioHangDetailsByKhachHang(KhachHang khachHang);

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.idGioHangChiTiet IN :ids")
    List<GioHangChiTiet> findByIds(List<Integer> ids);

    @Query("SELECT COUNT(c.chiTietSanPham) FROM GioHangChiTiet c WHERE c.chiTietSanPham.idChiTietSanPham = ?1")
    int countByChiTietSanPhamId(int nsxId);
}
