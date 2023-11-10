package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    List<HoaDon> findHoaDonByTrangThai(int trangThai);
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


    @Query("SELECT o.idHoaDon, o.ngayTao, o.tongTien, o.tienShipHang, o.trangThai FROM HoaDon o WHERE o.ngayTao BETWEEN ?1 AND ?2 AND o.trangThai = ?3 ORDER BY o.ngayTao ASC")
    List<HoaDon> findByOrderByStatusBetween(Date paramDate1, Date paramDate2, Integer paramInteger);

}
