package com.example.demo.service;


import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class GioHangService {

    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private GioHangRepository gioHangRepository;

    public BigInteger calculateTotalPriceOfGioHang(int gioHangId) {
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.getGioHangChiTietByGioHang(gioHangId);

        BigInteger tongTien = BigInteger.ZERO;
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien = tongTien.add(gioHangChiTiet.getTongGia());
        }

        return tongTien;
    }

    public List<Object[]> findGioHangDetailsByKhachHang(KhachHang khachHang){
        return gioHangRepository.findGioHangDetailsByKhachHang(khachHang);
    };

}
