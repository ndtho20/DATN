package com.example.demo.service;
import com.example.demo.entity.HinhAnh;
import com.example.demo.repository.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HinhAnhService {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    public List<HinhAnh> getAll() {
        return hinhAnhRepository.findAll();
    }

    public HinhAnh getById(Integer id) {
        return hinhAnhRepository.findById(id).orElse(null);
    }

    public void addHinhAnh(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    public void updateHinhAnh(Integer id, HinhAnh updatedHinhAnh) {
        HinhAnh existingHinhAnh = hinhAnhRepository.findById(id).orElse(null);

        if (existingHinhAnh != null) {
            // Cập nhật thông tin của existingHinhAnh với các thuộc tính mới từ updatedHinhAnh
            existingHinhAnh.setDuongDan(updatedHinhAnh.getDuongDan());
            existingHinhAnh.setChiTietSanPham(updatedHinhAnh.getChiTietSanPham());
            // Cập nhật các thuộc tính khác nếu cần

            // Lưu lại vào cơ sở dữ liệu
            hinhAnhRepository.save(existingHinhAnh);
        }
    }

    public void deleteHinhAnh(Integer id) {
        hinhAnhRepository.deleteById(id);
    }
}
