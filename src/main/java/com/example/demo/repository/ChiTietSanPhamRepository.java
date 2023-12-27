package com.example.demo.repository;


import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("SELECT p FROM ChiTietSanPham p WHERE p.loaiSanPham.idSanPham = :categoryId")
    List<ChiTietSanPham> findByCategoryId(@Param("categoryId") Integer categoryId);


    @Query("SELECT s.idSize, s.ma FROM ChiTietSanPham c JOIN c.size s WHERE c.chatLieu.idChatLieu = :idChatLieu AND c.nsx.idNSX = :idNSX AND c.mauSac.idMauSac = :idMauSac AND c.phongCach.idPhongCach = :idPhongCach AND c.giaBan = :giaBan AND c.loaiSanPham.idSanPham = :idLoaiSanPham AND c.tenSanPham = :tenSanPham")
    List<String> findRelatedSizeNames(
            @Param("idChatLieu") Integer idChatLieu, @Param("idNSX") Integer idNSX, @Param("idMauSac") Integer idMauSac,
            @Param("idPhongCach") Integer idPhongCach, @Param("giaBan") BigInteger giaBan, @Param("idLoaiSanPham") Integer idLoaiSanPham,
            @Param("tenSanPham") String tenSanPham);

    @Query("SELECT c FROM ChiTietSanPham c WHERE c.size.ma = :size")
    List<ChiTietSanPham> findBySize(@Param("size") String size);

    @Query("SELECT c FROM ChiTietSanPham c WHERE c.mauSac.ma = :color")
    List<ChiTietSanPham> findByColor(@Param("color") String color);

    @Query("SELECT c FROM ChiTietSanPham c WHERE c.loaiSanPham.idSanPham = :categoryId AND c.mauSac.ma = :color AND c.size.ma = :size")
    List<ChiTietSanPham> findByLoaiSanPhamIdSanPhamAndMauSacMaAndSizeMa(
            @Param("categoryId") Integer categoryId,
            @Param("color") String color,
            @Param("size") String size);
    @Query("SELECT c FROM ChiTietSanPham c WHERE c.loaiSanPham.idSanPham = :categoryId AND c.mauSac.ma = :color")
    List<ChiTietSanPham> findByLoaiSanPhamIdSanPhamAndMauSacMa(
            @Param("categoryId") Integer categoryId,
            @Param("color") String color);
    @Query("SELECT c FROM ChiTietSanPham c WHERE c.loaiSanPham.idSanPham = :categoryId AND c.size.ma = :size")
    List<ChiTietSanPham> findByLoaiSanPhamIdSanPhamAndSizeMa(
            @Param("categoryId") Integer categoryId,
            @Param("size") String size);
    @Query("SELECT c FROM ChiTietSanPham c WHERE c.mauSac.ma = :color AND c.size.ma = :size")
    List<ChiTietSanPham> findByMauSacMaAndSizeMa(
            @Param("color") String color,
            @Param("size") String size);
    @Query("SELECT COUNT(c) FROM ChiTietSanPham c WHERE c.loaiSanPham.idSanPham = :categoryId")
    int countByLoaiSanPham_IdSanPham(@Param("categoryId") String  categoryId);

}

