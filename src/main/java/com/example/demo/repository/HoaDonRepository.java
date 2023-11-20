package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findHoaDonByTrangThai(int trangThai);

    List<HoaDon> findHoaDonByKhachHang(KhachHang kh);

    @Query("SELECT c.tenSanPham, g.soLuong, g.tongGia " +
            "FROM HoaDonChiTiet h " +
            "JOIN GioHangChiTiet g ON h.gioHangChiTiet.idGioHangChiTiet = g.idGioHangChiTiet " +
            "JOIN ChiTietSanPham c ON g.chiTietSanPham.idChiTietSanPham = c.idChiTietSanPham " +
            "WHERE h.hoaDon.idHoaDon = :idHoaDon")
    List<Object[]> findChiTietDonHang(int idHoaDon);


    @Query("SELECT h FROM HoaDon h WHERE h.khachHang = ?1 AND h.trangThai = ?2")
    List<HoaDon> findHoaDonByKhachHangAndTrangThai(KhachHang khachHang, int trangThai);

}
