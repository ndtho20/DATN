package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.ReportItem;
import com.example.demo.entity.ReportType;
import com.example.demo.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterOrderReportService extends AbstractReportService {
    @Autowired
    private HoaDonRepository repo;

    protected List<ReportItem> getReportDataByDateRangeInternal(Date startTime, Date endTime, ReportType reportType) {
        List<HoaDon> combinedList1 = this.repo.findByOrderByStatusBetween(startTime, endTime, Integer.valueOf(3));
        List<HoaDon> combinedList2 = this.repo.findByOrderByStatusBetween(startTime, endTime, Integer.valueOf(5));
        List<HoaDon> listOrders = new ArrayList<>();
        listOrders.addAll(combinedList1);
        listOrders.addAll(combinedList2);
        printRawData(listOrders);
        System.out.println("Trth");
        printRawDataStatus(listOrders);
        List<ReportItem> listReportItems = createReportData(startTime, endTime, reportType, Integer.valueOf(3));
        calculateSalesForReportData(listOrders, listReportItems);
        printReportData(listReportItems);
        return listReportItems;
    }

    private void calculateSalesForReportData(List<HoaDon> listOrders, List<ReportItem> listReportItems) {
        for (HoaDon order : listOrders) {
            String orderDateString = this.dateFormatter.format(order.getNgayTao());
            ReportItem reportItem = new ReportItem(orderDateString);
            int itemIndex = listReportItems.indexOf(reportItem);
            for (ReportItem item : listReportItems) {
                if (item.getGrossSales() == null || item.getNetSales() == null) {
                    item.setGrossSales(Double.valueOf(0.0D));
                    item.setNetSales(Double.valueOf(0.0D));
                }
            }
            if (itemIndex >= 0) {
                reportItem = listReportItems.get(itemIndex);
                reportItem.addNetSales(order.getTongTien().doubleValue());
                if (order.getTienShipHang() != null) {
                    reportItem.addGrossSales(Double.valueOf(order.getTongTien().doubleValue() + order.getTienShipHang().doubleValue()));
                } else {
                    reportItem.addGrossSales(order.getTongTien().doubleValue());
                }
                reportItem.increaseOrderCount();
                reportItem.addStatus(order.getTrangThai().intValue());
            }
        }
    }

    private void printReportData(List<ReportItem> listReportItems) {
        listReportItems.forEach(item -> System.out.printf("%s, %.3f, %.3f, %d \n", new Object[] { item.getIdentifier(), item.getGrossSales(), item.getNetSales(), Integer.valueOf(item.getOrdersCount()) }));
    }

    private List<ReportItem> createReportData(Date startTime, Date endTime, ReportType reportType, Integer statusList) {
        List<ReportItem> listReportItems = new ArrayList<>();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(startTime);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(endTime);
        List<HoaDon> combinedList1 = this.repo.findByOrderByStatusBetween(startTime, endTime, Integer.valueOf(3));
        List<HoaDon> combinedList2 = this.repo.findByOrderByStatusBetween(startTime, endTime, Integer.valueOf(5));
        List<HoaDon> listOrders = new ArrayList<>();
        listOrders.addAll(combinedList1);
        listOrders.addAll(combinedList2);
        while (true) {
            String dateString = this.dateFormatter.format(startDate.getTime());
            boolean hasMatchingStatus = false;
            for (HoaDon order : listOrders) {
                if (statusList.intValue() == 3 || statusList.intValue() == 5) {
                    hasMatchingStatus = true;
                    break;
                }
            }
            if (hasMatchingStatus)
                listReportItems.add(new ReportItem(dateString));
            if (reportType.equals(ReportType.DAY)) {
                startDate.add(5, 1);
            } else if (reportType.equals(ReportType.MONTH)) {
                startDate.add(2, 1);
            }
            if (!startDate.before(endDate))
                return listReportItems;
        }
    }

    private void printRawData(List<HoaDon> listOrders) {
        listOrders.forEach(order -> System.out.printf("%-3d | %s | %.3f | %.3f \n", new Object[] { order.getIdHoaDon(), order.getNgayTao(), order.getTongTien(), order.getTienShipHang() }));
    }

    private void printRawDataStatus(List<HoaDon> listOrders) {
        listOrders.forEach(order -> System.out.printf("%-3d | %s | %.3f | %.3f | %-3d\n", new Object[] { order.getIdHoaDon(), order.getNgayTao(), order.getTongTien(), order.getTienShipHang(), order.getTrangThai() }));
    }
}

