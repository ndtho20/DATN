package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findHoaDonByTrangThai(int trangThai);

    @Query(nativeQuery = true, value = "SELECT c.tenSanPham, g.soLuong, g.tongGia, hd.TongTien, hd.NgayTao, hd.mahoaDon, kh.Ten, kh.DiaChi, kh.SoDienThoai, nv.HoVaTen, hd.TienShipHang, c.GiaBan, hd.idHoaDon FROM HoaDon hd " +
                                       "JOIN HoaDonChiTiet hdct ON hdct.idHoaDon = hd.idHoaDon " +
                                       "JOIN GioHangChiTiet g ON hdct.idGioHangChiTiet = g.idGioHangChiTiet " +
                                       "JOIN ChiTietSanPham c ON g.idChiTietSanPham = c.idChiTietSanPham " +
                                       "JOIN KhachHang kh ON kh.idKhachHang = hd.idKhachHang " +
                                       "JOIN NhanVien nv ON hd.idNhanVien = nv.idNhanVien " +
                                       "WHERE hd.idHoaDon = :idHoaDon")
    List<Object[]> findXuatHoaDon(@Param("idHoaDon") int idHoaDon);
}
