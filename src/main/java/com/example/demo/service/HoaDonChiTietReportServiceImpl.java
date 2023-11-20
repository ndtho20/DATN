package com.example.demo.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import com.example.demo.entity.*;
import com.example.demo.repository.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonChiTietReportServiceImpl extends AbstractReportService {
    @Autowired
    private HoaDonChiTietRepository repo;

    protected List<ReportItem> getReportDataByDateRangeInternal(Date startDate, Date endDate, ReportType reportType) {
        List<HoaDonChiTiet> listHoaDonChiTiets = new ArrayList<>();
        if (reportType.equals(ReportType.CATEGORY)) {
            listHoaDonChiTiets.addAll(this.repo.findWithCategoryAndTimeBetween(startDate, endDate, Integer.valueOf(3)));
            listHoaDonChiTiets.addAll(this.repo.findWithCategoryAndTimeBetween(startDate, endDate, Integer.valueOf(5)));
        } else if (reportType.equals(ReportType.PRODUCT)) {
            listHoaDonChiTiets.addAll(this.repo.findWithProductAndTimeBetween(startDate, endDate, Integer.valueOf(3)));
            listHoaDonChiTiets.addAll(this.repo.findWithProductAndTimeBetween(startDate, endDate, Integer.valueOf(5)));
        } else if (reportType.equals(ReportType.ORDERDETAIL)) {
            listHoaDonChiTiets.addAll(this.repo.findWithOrderDetailAndTimeBetween(startDate, endDate, Integer.valueOf(3)));
            listHoaDonChiTiets.addAll(this.repo.findWithOrderDetailAndTimeBetween(startDate, endDate, Integer.valueOf(5)));
        }

        Set<String> identifiers = new HashSet<>();
        List<ReportItem> listReportItems = new ArrayList<>();

        for (HoaDonChiTiet detail : listHoaDonChiTiets) {
            String identifier = "";
            if (reportType.equals(ReportType.CATEGORY)) {
                GioHangChiTiet gioHangChiTiet = detail.getGioHangChiTiet();
                if (gioHangChiTiet != null) {
                    ChiTietSanPham chiTietSanPham = gioHangChiTiet.getChiTietSanPham();
                    if (chiTietSanPham != null) {
                        LoaiSanPham loaiSanPham = chiTietSanPham.getLoaiSanPham();
                        if (loaiSanPham != null) {
                            identifier = loaiSanPham.getTen();
                        }
                    }
                }
            } else if (reportType.equals(ReportType.PRODUCT)) {
                GioHangChiTiet gioHangChiTiet = detail.getGioHangChiTiet();
                if (gioHangChiTiet != null) {
                    ChiTietSanPham chiTietSanPham = gioHangChiTiet.getChiTietSanPham();
                    if (chiTietSanPham != null) {
                        identifier = chiTietSanPham.getTenSanPham();
                    }
                }
            } else if (reportType.equals(ReportType.ORDERDETAIL)) {
                identifier = String.valueOf(detail.getIdHoaDonChiTiet());
            }

            if (!identifiers.contains(identifier)) {
                listReportItems.add(new ReportItem(identifier));
                identifiers.add(identifier);
            }
        }

        for (HoaDonChiTiet detail : listHoaDonChiTiets) {
            String identifier = "";
            if (reportType.equals(ReportType.CATEGORY)) {
                GioHangChiTiet gioHangChiTiet = detail.getGioHangChiTiet();
                if (gioHangChiTiet != null) {
                    ChiTietSanPham chiTietSanPham = gioHangChiTiet.getChiTietSanPham();
                    if (chiTietSanPham != null) {
                        LoaiSanPham loaiSanPham = chiTietSanPham.getLoaiSanPham();
                        if (loaiSanPham != null) {
                            identifier = loaiSanPham.getTen();
                        }
                    }
                }
            } else if (reportType.equals(ReportType.PRODUCT)) {
                GioHangChiTiet gioHangChiTiet = detail.getGioHangChiTiet();
                if (gioHangChiTiet != null) {
                    ChiTietSanPham chiTietSanPham = gioHangChiTiet.getChiTietSanPham();
                    if (chiTietSanPham != null) {
                        identifier = chiTietSanPham.getTenSanPham();
                    }
                }
            } else if (reportType.equals(ReportType.ORDERDETAIL)) {
                identifier = String.valueOf(detail.getIdHoaDonChiTiet());
            }

            ReportItem reportItem = new ReportItem(identifier);

            GioHangChiTiet gioHangChiTiet = detail.getGioHangChiTiet();
            if (gioHangChiTiet != null) {
                ChiTietSanPham chiTietSanPham = gioHangChiTiet.getChiTietSanPham();
                if (chiTietSanPham != null) {
                    BigInteger giaBan = chiTietSanPham.getGiaBan();
                    Integer soLuong = gioHangChiTiet.getSoLuong();
                    if (giaBan != null && soLuong != null) {
                        double netSales = giaBan.doubleValue() * soLuong.intValue();
                        double grossSales = (detail.getHoaDon().getTienShipHang() == null) ? netSales : netSales + detail.getHoaDon().getTienShipHang().doubleValue();

                        int itemIndex = listReportItems.indexOf(reportItem);
                        if (itemIndex >= 0) {
                            reportItem = listReportItems.get(itemIndex);
                            reportItem.addGrossSales(Double.valueOf(grossSales));
                            reportItem.addNetSales(Double.valueOf(netSales));
                            reportItem.increaseProductsCount(soLuong.intValue());
                            continue;
                        }
                        listReportItems.add(new ReportItem(identifier, Double.valueOf(grossSales), Double.valueOf(netSales), soLuong.intValue()));
                    }
                }
            }
        }

        return listReportItems;
    }


    private void printReportData(List<ReportItem> listReportItems) {
        for (ReportItem item : listReportItems) {
            System.out.printf("%-20s, %.3f, %.3f, %d \n", new Object[] { item
                    .getIdentifier(), item.getGrossSales(), item.getNetSales(), Integer.valueOf(item.getOrdersCount()) });
        }
    }

    private void printRawData(List<HoaDonChiTiet> listHoaDonChiTiets) {
        for (HoaDonChiTiet detail : listHoaDonChiTiets) {
            System.out.printf("%d, %-20s, %.3f, %.3f \n", new Object[] { detail
                    .getGioHangChiTiet().getSoLuong(), detail.getGioHangChiTiet().getChiTietSanPham().getTenSanPham().substring(0, 20),
                    Double.valueOf(detail.getGioHangChiTiet().getChiTietSanPham().getGiaBan().doubleValue() * detail.getGioHangChiTiet().getSoLuong().intValue()), detail.getHoaDon().getTienShipHang() });
        }
    }

}