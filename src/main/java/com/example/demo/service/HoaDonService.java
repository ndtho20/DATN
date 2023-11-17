package com.example.demo.service;

import com.example.demo.entity.*;

import com.example.demo.repository.HoaDonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    ;

    public List<HoaDon> getHoaDonByTrangThai(int trangThai) {
        return hoaDonRepository.findHoaDonByTrangThai(trangThai);
    }

    @Transactional
    public boolean updateTrangThaiHoaDon(int hoaDonId, int newTrangThai) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            hoaDon.setTrangThai(newTrangThai);
            hoaDonRepository.save(hoaDon);
            return true; // Cập nhật thành công
        } else {
            return false; // Hóa đơn không tồn tại
        }
    }

    @Transactional
    public HoaDon xuLyThanhToan(String maHD, NhanVien nv, KhachHang kh, List<GioHangChiTiet> gioHangChiTietList) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(new Date());
        hoaDon.setTrangThai(3);
        hoaDon.setMaHoaDon(maHD);
        hoaDon.setNhanVien(nv);
        hoaDon.setKhachHang(kh);
        hoaDon = hoaDonRepository.save(hoaDon); // Lưu hóa đơn vào cơ sở dữ liệu
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setGioHangChiTiet(gioHangChiTiet);
            hoaDonChiTiet.setGhiChu("");
            hoaDonChiTietService.createHoaDonChiTiet(hoaDonChiTiet);
            totalAmount = totalAmount.add(gioHangChiTiet.getTongGia());
            gioHangChiTietService.updateTrangThaiToFalse(gioHangChiTiet);
        }
        hoaDon.setTongTien(totalAmount);
        hoaDon = hoaDonRepository.save(hoaDon); // Cập nhật tổng tiền vào hóa đơn
        return hoaDon;
    }

    public Integer soDonHangTheoTrangThai(Integer trangThaiDonhang) {
        return this.hoaDonRepository.soDonHangTheoTrangThai(trangThaiDonhang);
    }

    public Integer tongSoDonHang() {
        return this.hoaDonRepository.tongSoDonHang();
    }

    public Page<HoaDon> listByPage(int pageNumber, String sortField, String sortDir, String keyword) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10, Sort.by(SortOrder(sortField, sortDir)));
        if (keyword != null)

            return this.hoaDonRepository.findAllPagination(keyword, (Pageable) pageRequest);

        return this.hoaDonRepository.findAll((Pageable) pageRequest);
    }

    public Page<HoaDon> listByPageStatus(int pageNumber, String sortField, String sortDir, String keyword, int status) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10, Sort.by(SortOrder(sortField, sortDir)));
        if (keyword != null)
            return this.hoaDonRepository.findAllPaginationStatus(keyword, (Pageable) pageRequest, Integer.valueOf(status));
        return this.hoaDonRepository.findAll((Pageable) pageRequest);
    }

    public List<Sort.Order> SortOrder(String sort, String sortDirection) {
        Sort.Direction direction = null;
        List<Sort.Order> sorts = new ArrayList<>();
        if (sortDirection != null) {
            direction = Sort.Direction.fromString(sortDirection);

        }else {
            direction = Sort.Direction.DESC;
        }
        sorts.add(new Sort.Order(direction, sort));
        return sorts;
    }
}

