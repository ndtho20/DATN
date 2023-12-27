package com.example.demo.repository;

import com.example.demo.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {

    @Query("SELECT COUNT(c.chiTietSanPham) FROM HinhAnh c WHERE c.chiTietSanPham.idChiTietSanPham = ?1")
    int coundChitiet(int nsxId);
}
