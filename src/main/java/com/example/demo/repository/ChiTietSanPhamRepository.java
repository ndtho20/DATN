package com.example.demo.repository;


import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.tenSanPham LIKE %?1%")
    List<ChiTietSanPham> findChiTietSanPhamByTenSanPhamContaining(String ten);

    ChiTietSanPham findChiTietSanPhamByIdChiTietSanPham(Integer id);
    @Query("SELECT p from ChiTietSanPham p Where p.loaiSanPham.idSanPham=?1")
    List<ChiTietSanPham> findByCategoryId(String cid);
    @Query("SELECT s.idSize, s.ten FROM ChiTietSanPham c JOIN c.size s WHERE c.chatLieu.idChatLieu = :idChatLieu AND c.nsx.idNSX = :idNSX AND c.mauSac.idMauSac = :idMauSac AND c.phongCach.idPhongCach = :idPhongCach AND c.giaBan = :giaBan AND c.loaiSanPham.idSanPham = :idLoaiSanPham AND c.tenSanPham = :tenSanPham")
    List<String> findRelatedSizeNames(
            @Param("idChatLieu") Integer idChatLieu, @Param("idNSX") Integer idNSX, @Param("idMauSac") Integer idMauSac,
            @Param("idPhongCach") Integer idPhongCach, @Param("giaBan") BigInteger giaBan, @Param("idLoaiSanPham") Integer idLoaiSanPham,
            @Param("tenSanPham") String tenSanPham);
}

