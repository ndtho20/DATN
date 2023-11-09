package com.example.demo.service;


import com.example.demo.entity.GioHang;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAll() {
        return khachHangRepository.getKhachHang();
    }

    ;

    public KhachHang addKhachHang(KhachHang khachHang) {
        khachHang.setNgayTao(new Date());
        khachHang.setVaiTro(false);
        return khachHangRepository.save(khachHang);
    }

    public KhachHang getById(int id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    public void updateKhachHang(int Id, KhachHang KhachHang) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.findById(Id);

        if (existingKhachHang.isPresent()) {
            KhachHang updatedKhachHang = existingKhachHang.get();
            updatedKhachHang.setTen(KhachHang.getTen());
            updatedKhachHang.setGioiTinh(KhachHang.getGioiTinh());
            updatedKhachHang.setNgaySinh(KhachHang.getNgaySinh());
            updatedKhachHang.setDiaChi(KhachHang.getDiaChi());
            updatedKhachHang.setSoDienThoai(KhachHang.getSoDienThoai());
            updatedKhachHang.setEmail(KhachHang.getEmail());
            updatedKhachHang.setMatKhau(KhachHang.getMatKhau());
            updatedKhachHang.setNgayTao(KhachHang.getNgayTao());
            updatedKhachHang.setTrangThai(KhachHang.getTrangThai());
            khachHangRepository.save(updatedKhachHang);
        }
    }

    public void deleteKhachHang(int id) {
        khachHangRepository.deleteById(id);
    }

    public KhachHang searchKhachHang(String sdt) {
        return khachHangRepository.findKhachHangBySoDienThoai(sdt);
    }

    public KhachHang createKhachHangWithGioHang(KhachHang khachHang) {
        // Tạo một đối tượng Giỏ Hàng mới
        GioHang gioHang = new GioHang();
        gioHang.setKhachHang(khachHang);
        gioHang.setNgayTao(new Date());
        // Thiết lập quan hệ giữa Khách Hàng và Giỏ Hàng
        khachHang.setGioHang(gioHang);
        // Lưu cả Khách Hàng và Giỏ Hàng vào cơ sở dữ liệu
        khachHang = khachHangRepository.save(khachHang);

        return khachHang;
    }
}
