package com.example.demo.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demo.entity.ReportItem;
import com.example.demo.entity.ReportType;
import com.example.demo.service.HoaDonChiTietReportServiceImpl;
import com.example.demo.service.MasterOrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ReportRestController {
    @Autowired
    private MasterOrderReportService masterOrderReportService;

    @Autowired
    private HoaDonChiTietReportServiceImpl orderDetailReportService;

    @Autowired
    HttpServletRequest request;

    @GetMapping({"/statisticals/sales_report_by_date/{period}"})
    public List<ReportItem> getReportDataByDatePeriod(@PathVariable("period") String period) {
        System.out.println("Report peroid: " + period);
        switch (period) {
            case "last_7_days":
                return this.masterOrderReportService.getReportDataLast7Days(ReportType.DAY);
            case "last_28_days":
                return this.masterOrderReportService.getReportDataLast28Days(ReportType.DAY);
            case "last_6_months":
                return this.masterOrderReportService.getReportDataLast6Months(ReportType.MONTH);
            case "last_year":
                return this.masterOrderReportService.getReportDataLastYear(ReportType.MONTH);
        }
        return this.masterOrderReportService.getReportDataLast7Days(ReportType.DAY);
    }

    @GetMapping({"/statisticals/sales_report_by_date/{startDate}/{endDate}"})
    public List<ReportItem> getReportDataByDatePeriod(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException {
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = dateFormater.parse(startDate);
        Date endTime = dateFormater.parse(endDate);
        return this.masterOrderReportService.getReportDataByDateRange(startTime, endTime, ReportType.DAY);
    }

    @GetMapping({"/statisticals/{groupBy}/{startDate}/{endDate}"})
    public List<ReportItem> getReportDataByCategoryOrProductDateRange(@PathVariable("groupBy") String groupBy, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException {
        ReportType reportType = ReportType.valueOf(groupBy.toUpperCase());
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = dateFormatter.parse(startDate);
        Date endTime = dateFormatter.parse(endDate);
        return this.orderDetailReportService.getReportDataByDateRange(startTime, endTime, reportType);
    }

    @GetMapping({"/statisticals/{groupBy}/{period}"})
    public List<ReportItem> getReportDataByCategoryOrProduct(@PathVariable("groupBy") String groupBy, @PathVariable("period") String period) {
        ReportType reportType = ReportType.valueOf(groupBy.toUpperCase());
        switch (period) {
            case "last_7_days":
                return this.orderDetailReportService.getReportDataLast7Days(reportType);
            case "last_28_days":
                return this.orderDetailReportService.getReportDataLast28Days(reportType);
            case "last_6_months":
                return this.orderDetailReportService.getReportDataLast6Months(reportType);
            case "last_year":
                return this.orderDetailReportService.getReportDataLastYear(reportType);
        }
        return this.orderDetailReportService.getReportDataLast7Days(reportType);
    }
}
