package com.example.demo.service;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.GioHangChiTietRepository;
import com.example.demo.repository.GioHangRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public List<GioHangChiTiet> getGioHangChiTietByGioHang(Integer idGioHang) {
        return gioHangChiTietRepository.findByGioHangIdAndTrangThai(idGioHang);
    }

    public GioHangChiTiet addOrUpdateCartItem(Integer gioHangId, Integer chiTietSanPhamId) {
        // Tìm kiếm giỏ hàng chi tiết dựa trên giỏ hàng và chi tiết sản phẩm
        GioHang gioHang = gioHangRepository.findById(gioHangId).orElse(null);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(chiTietSanPhamId).orElse(null);
        GioHangChiTiet existingCartItem =
                gioHangChiTietRepository.findByGioHangIdAndChiTietSanPhamId(gioHangId, chiTietSanPhamId);
        if (existingCartItem != null) {
            // Giỏ hàng chi tiết đã tồn tại, tăng số lượng lên 1
            existingCartItem.setSoLuong(existingCartItem.getSoLuong() + 1);
            existingCartItem.setTrangThai(true);
            existingCartItem.setTongGia(BigDecimal.valueOf(existingCartItem.getSoLuong()).multiply(chiTietSanPham.getGiaBan()));
            gioHangChiTietRepository.save(existingCartItem);
        } else {
            if (gioHang != null && chiTietSanPham != null) {
                // Tạo một đối tượng GioHangChiTiet và kết nối nó với GioHang và ChiTietSanPham
                GioHangChiTiet newCartItem = new GioHangChiTiet();
                newCartItem.setGioHang(gioHang);
                newCartItem.setChiTietSanPham(chiTietSanPham);
                newCartItem.setSoLuong(1);
                newCartItem.setTrangThai(true);
                // Thực hiện tính toán tổng giá và các hoạt động khác
                newCartItem.setTongGia(BigDecimal.valueOf(newCartItem.getSoLuong()).multiply(chiTietSanPham.getGiaBan()));
                // Lưu đối tượng GioHangChiTiet vào cơ sở dữ liệu
                gioHangChiTietRepository.save(newCartItem);
            } else {// Xử lý trường hợp không tìm thấy GioHang hoặc ChiTietSanPham
            }
        }
        return existingCartItem;
    }


    public void updateCartItemQuantity(Integer gioHangChiTietId, Integer soLuong) {
        Optional<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId);
        ChiTietSanPham chiTietSanPham = gioHangChiTiet.get().getChiTietSanPham();

        GioHangChiTiet existingCartItem = gioHangChiTietRepository.findById(gioHangChiTietId).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setSoLuong(soLuong);
            existingCartItem.setTongGia(BigDecimal.valueOf(existingCartItem.getSoLuong()).multiply(chiTietSanPham.getGiaBan()));
            gioHangChiTietRepository.save(existingCartItem);
        }
    }


    @Transactional
    public GioHangChiTiet updateTrangThaiToFalse(GioHangChiTiet gioHangChiTiet) {
        if (gioHangChiTiet != null) {
            gioHangChiTiet.setTrangThai(false);
            return gioHangChiTietRepository.save(gioHangChiTiet);
        } else {
            // Xử lý trường hợp giỏ hàng chi tiết là null
            return null;
        }
    }

    public void deleteCartItem(Integer gioHangChiTietId) {
        Optional<GioHangChiTiet> gioHangChiTietOptional = gioHangChiTietRepository.findById(gioHangChiTietId);

        if (gioHangChiTietOptional.isPresent()) {
            GioHangChiTiet gioHangChiTiet = gioHangChiTietOptional.get();
            gioHangChiTietRepository.delete(gioHangChiTiet);
        } else {
            // Xử lý trường hợp không tìm thấy giỏ hàng chi tiết
        }
    }

}
