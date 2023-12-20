package com.example.demo.service;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.GioHangChiTietRepository;
import com.example.demo.repository.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    public List<ChiTietSanPham> searchSanPham(String ten) {
        return chiTietSanPhamRepository.findChiTietSanPhamByTenSanPhamContaining(ten);
    }

    public ChiTietSanPham findChiTietSanPhamById(Integer idChiTietSanPham) {
        return chiTietSanPhamRepository.findChiTietSanPhamByIdChiTietSanPham(idChiTietSanPham);
    }



    public List<ChiTietSanPham> findByCategoryId(String cid) {
        return chiTietSanPhamRepository.findByCategoryId(cid);
    }


    public List<String> findRelatedSizeNames(Integer id) {
        ChiTietSanPham item = getById(id);

        // Lấy các thông tin của item để truy vấn danh sách các size tương ứng
        int idChatLieu = item.getChatLieu().getIdChatLieu();
        int idNSX = item.getNsx().getIdNSX();
        int idMauSac = item.getMauSac().getIdMauSac();
        int idPhongCach = item.getPhongCach().getIdPhongCach();
        BigInteger giaBan = item.getGiaBan();
        int idLoaiSanPham = item.getLoaiSanPham().getIdSanPham();
        String tenSanPham = item.getTenSanPham();


        // Gọi phương thức từ repository để lấy danh sách các tên size tương ứng
        return chiTietSanPhamRepository.findRelatedSizeNames(
                idChatLieu, idNSX, idMauSac, idPhongCach, giaBan, idLoaiSanPham, tenSanPham);

    }
    public ChiTietSanPham getById(Integer id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    public void addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        chiTietSanPhamRepository.save(chiTietSanPham);
    }

    public void updateChiTietSanPham(Integer id, ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham existingChiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
        if (existingChiTietSanPham != null) {
            // Cập nhật thông tin của chi tiết sản phẩm
            existingChiTietSanPham.setTenSanPham(chiTietSanPham.getTenSanPham());
            existingChiTietSanPham.setLoaiSanPham(chiTietSanPham.getLoaiSanPham());
            existingChiTietSanPham.setChatLieu(chiTietSanPham.getChatLieu());
            existingChiTietSanPham.setSize(chiTietSanPham.getSize());
            existingChiTietSanPham.setPhongCach(chiTietSanPham.getPhongCach());
            existingChiTietSanPham.setNsx(chiTietSanPham.getNsx());
            existingChiTietSanPham.setMauSac(chiTietSanPham.getMauSac());
            existingChiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong());
            existingChiTietSanPham.setGiaBan(chiTietSanPham.getGiaBan());
            existingChiTietSanPham.setTrangThai(chiTietSanPham.getTrangThai());
            // Cập nhật các trường khác
            // ...
            chiTietSanPhamRepository.save(existingChiTietSanPham);
        }
    }

    public void deleteChiTietSanPham(Integer id) {
        if(gioHangChiTietRepository.countByChiTietSanPhamId(id)==0||hinhAnhRepository.coundChitiet(id)==0){
            chiTietSanPhamRepository.deleteById(id);
        }else {
            ChiTietSanPham existingChiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
            existingChiTietSanPham.setTrangThai(false);
            chiTietSanPhamRepository.save(existingChiTietSanPham);
        }
    }
}


