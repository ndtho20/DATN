package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    @Query("SELECT count(hd) FROM HoaDon hd ")
    Integer tongSoDonHang();
    @Query("SELECT count(hd) FROM HoaDon hd WHERE hd.trangThai = ?1")
    Integer soDonHangTheoTrangThai(Integer trangThai);
    @Query("SELECT dn    FROM HoaDon dn")
    Page<HoaDon> findAll(Pageable paramPageable);
    @Query("SELECT dh FROM HoaDon dh WHERE  dh.khachHang.ten LIKE %?1% ")
    Page<HoaDon> findAllPagination(String paramString, Pageable paramPageable);

    @Query("SELECT dh FROM HoaDon dh WHERE  dh.khachHang.ten LIKE %?1% AND dh.trangThai = ?2")
    Page<HoaDon> findAllPaginationStatus(String paramString, Pageable paramPageable, Integer paramInteger);

    @Query("SELECT new com.example.demo.entity.HoaDon(o.idHoaDon, o.ngayTao, o.tongTien, o.tienShipHang, o.trangThai)   " +
            " FROM HoaDon o WHERE o.ngayTao BETWEEN ?1 AND ?2 AND o.trangThai = ?3 ORDER BY o.ngayTao ASC")
    List<HoaDon> findByOrderByStatusBetween(Date paramDate1, Date paramDate2, Integer paramInteger);


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
