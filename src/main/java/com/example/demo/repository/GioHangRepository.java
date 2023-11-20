package com.example.demo.repository;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    @Query("SELECT gct.idGioHangChiTiet,c.tenSanPham, c.giaBan, gct.soLuong, gct.tongGia " +
            "FROM GioHangChiTiet gct " +
            "JOIN gct.gioHang gh " +
            "JOIN gct.chiTietSanPham c " +
            "WHERE gh.khachHang = :khachHang")
    List<Object[]> findGioHangDetailsByKhachHang(KhachHang khachHang);

}
