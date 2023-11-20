package com.example.demo.service;
import com.example.demo.entity.ReportItem;
import com.example.demo.entity.ReportType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public abstract class AbstractReportService {
    public DateFormat dateFormatter;

    public static final int ORDERS_PER_PAGE = 4;

    public List<ReportItem> getReportDataLast7Days(ReportType reportType) {
        return getReportDataLastXDays(7, reportType);
    }

    public List<ReportItem> getReportDataLast28Days(ReportType reportType) {
        return getReportDataLastXDays(28, reportType);
    }

    protected List<ReportItem> getReportDataLastXDays(int days, ReportType reportType) {
        Date endTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(5, -(days - 1));
        Date startTime = cal.getTime();
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return getReportDataByDateRangeInternal(startTime, endTime, reportType);
    }

    public List<ReportItem> getReportDataLast6Months(ReportType reportType) {
        return getReportDataLastXMonths(6, reportType);
    }

    public List<ReportItem> getReportDataLastYear(ReportType reportType) {
        return getReportDataLastXMonths(12, reportType);
    }

    protected List<ReportItem> getReportDataLastXMonths(int months, ReportType reportType) {
        Date endTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(2, -(months - 1));
        Date startTime = cal.getTime();
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        this.dateFormatter = new SimpleDateFormat("yyyy-MM");
        return getReportDataByDateRangeInternal(startTime, endTime, reportType);
    }

    public List<ReportItem> getReportDataByDateRange(Date startTime, Date endTime, ReportType reportType) {
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return getReportDataByDateRangeInternal(startTime, endTime, reportType);
    }

    protected abstract List<ReportItem> getReportDataByDateRangeInternal(Date paramDate1, Date paramDate2, ReportType paramReportType);
}
