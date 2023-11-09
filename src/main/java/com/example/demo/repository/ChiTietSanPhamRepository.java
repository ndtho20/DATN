package com.example.demo.repository;


import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.tenSanPham LIKE %?1%")
    List<ChiTietSanPham> findChiTietSanPhamByTenSanPhamContaining(String ten);

    ChiTietSanPham findChiTietSanPhamByIdChiTietSanPham(Integer id);

}

