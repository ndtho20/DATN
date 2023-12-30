package com.example.demo.repository;


import com.example.demo.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {

    @Query("SELECT p FROM LoaiSanPham p WHERE p.ma = ?1")
    List<LoaiSanPham> findByMa(String ma);
}