package com.example.demo.service;


import com.example.demo.entity.GioHangChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GioHangService {

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    public BigDecimal calculateTotalPriceOfGioHang(int gioHangId) {
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.getGioHangChiTietByGioHang(gioHangId);

        BigDecimal tongTien = BigDecimal.ZERO;
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien = tongTien.add(gioHangChiTiet.getTongGia());
        }

        return tongTien;
    }

}
