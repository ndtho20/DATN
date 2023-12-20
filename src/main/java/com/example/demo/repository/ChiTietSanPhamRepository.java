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

    @Query("SELECT COUNT(c.nsx) FROM ChiTietSanPham c WHERE c.nsx.idNSX = ?1")
    int countByNhaSanXuatId(int nsxId);

    @Query("SELECT COUNT(c.chatLieu) FROM ChiTietSanPham c WHERE c.chatLieu.idChatLieu = ?1")
    int countByChatLieuId(int nsxId);

    @Query("SELECT COUNT(c.size) FROM ChiTietSanPham c WHERE c.size.idSize = ?1")
    int countBySizeId(int nsxId);

    @Query("SELECT COUNT(c.phongCach) FROM ChiTietSanPham c WHERE c.phongCach.idPhongCach = ?1")
    int countByPhongCachId(int nsxId);

    @Query("SELECT COUNT(c.mauSac) FROM ChiTietSanPham c WHERE c.mauSac.idMauSac = ?1")
    int countByMauSacId(int nsxId);

}

