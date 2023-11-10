package com.example.demo.service;

import java.util.*;

import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.ReportItem;
import com.example.demo.entity.ReportType;
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
            List<HoaDonChiTiet> combinedList1 = this.repo.findWithCategoryAndTimeBetween(startDate, endDate, Integer.valueOf(3));
            List<HoaDonChiTiet> combinedList2 = this.repo.findWithCategoryAndTimeBetween(startDate, endDate, Integer.valueOf(5));
            listHoaDonChiTiets.addAll(combinedList1);
            listHoaDonChiTiets.addAll(combinedList2);
        } else if (reportType.equals(ReportType.PRODUCT)) {
            List<HoaDonChiTiet> combinedList1 = this.repo.findWithProductAndTimeBetween(startDate, endDate, Integer.valueOf(3));
            List<HoaDonChiTiet> combinedList2 = this.repo.findWithProductAndTimeBetween(startDate, endDate, Integer.valueOf(5));
            listHoaDonChiTiets.addAll(combinedList1);
            listHoaDonChiTiets.addAll(combinedList2);
        } else if (reportType.equals(ReportType.ORDERDETAIL)) {
            List<HoaDonChiTiet> combinedList1 = this.repo.findWithOrderDetailAndTimeBetween(startDate, endDate, Integer.valueOf(3));
            List<HoaDonChiTiet> combinedList2 = this.repo.findWithOrderDetailAndTimeBetween(startDate, endDate, Integer.valueOf(5));
            listHoaDonChiTiets.addAll(combinedList1);
            listHoaDonChiTiets.addAll(combinedList2);
        }
        Set<String> identifiers = new HashSet<>();
        List<ReportItem> listReportItems = new ArrayList<>();
        for (HoaDonChiTiet detail : listHoaDonChiTiets) {
            String identifier = "";
            if (reportType.equals(ReportType.CATEGORY)) {
                identifier = detail.getGioHangChiTiet().getChiTietSanPham().getLoaiSanPham().getTen();
            } else if (reportType.equals(ReportType.PRODUCT)) {
                identifier = detail.getGioHangChiTiet().getChiTietSanPham().getTenSanPham();
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
                identifier = detail.getGioHangChiTiet().getChiTietSanPham().getLoaiSanPham().getTen();
            } else if (reportType.equals(ReportType.PRODUCT)) {
                identifier = detail.getGioHangChiTiet().getChiTietSanPham().getTenSanPham();
            } else if (reportType.equals(ReportType.ORDERDETAIL)) {
                identifier = String.valueOf(detail.getIdHoaDonChiTiet());
            }
            Integer soLuong = this.repo.countHD(detail.getHoaDon().getIdHoaDon());
            ReportItem reportItem = new ReportItem(identifier);
            double netSales = detail.getGioHangChiTiet().getChiTietSanPham().getGiaBan().doubleValue() * detail.getGioHangChiTiet().getSoLuong().intValue();
            double grossSales = 0.0D;
            if (detail.getHoaDon().getTienShipHang() == null) {
                grossSales = netSales;
            } else {
                grossSales = netSales + detail.getHoaDon().getTienShipHang().doubleValue();
            }
            int itemIndex = listReportItems.indexOf(reportItem);
            if (itemIndex >= 0) {
                reportItem = listReportItems.get(itemIndex);
                reportItem.addGrossSales(Double.valueOf(grossSales));
                reportItem.addNetSales(Double.valueOf(netSales));
                reportItem.increaseProductsCount(detail.getGioHangChiTiet().getSoLuong().intValue());
                continue;
            }
            listReportItems.add(new ReportItem(identifier, Double.valueOf(grossSales), Double.valueOf(netSales), detail.getGioHangChiTiet().getSoLuong().intValue()));
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