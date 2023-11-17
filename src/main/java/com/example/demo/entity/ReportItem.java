package com.example.demo.entity;

import java.util.Objects;

public class ReportItem {
    private String identifier;

    private Double grossSales;

    private Double netSales;

    private int ordersCount;

    private int productsCount;

    private int status;

    public String toString() {
        return "ReportItem(identifier=" + getIdentifier() + ", grossSales=" + getGrossSales() + ", netSales=" + getNetSales() + ", ordersCount=" + getOrdersCount() + ", productsCount=" + getProductsCount() + ", status=" + getStatus() + ")";
    }
    public ReportItem(String identifier, Double grossSales, Double netSales, int ordersCount, int productsCount, int status) {
        this.identifier = identifier;
        this.grossSales = grossSales;
        this.netSales = netSales;
        this.ordersCount = ordersCount;
        this.productsCount = productsCount;
        this.status = status;
    }

    public ReportItem() {}

    public ReportItem(String identifier, Double grossSales, Double netSales) {
        this.identifier = identifier;
        this.grossSales = grossSales;
        this.netSales = netSales;
    }

    public ReportItem(Integer status) {
        this.status = status.intValue();
    }

    public ReportItem(Double grossSales) {
        this.grossSales = grossSales;
    }

    public ReportItem(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public ReportItem(String identifier) {
        this.identifier = identifier;
    }

    public ReportItem(String identifier, Double grossSales, Double netSales, int productsCount) {
        this.identifier = identifier;
        this.grossSales = grossSales;
        this.netSales = netSales;
        this.productsCount = productsCount;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Double getGrossSales() {
        return this.grossSales;
    }

    public void setGrossSales(Double grossSales) {
        this.grossSales = grossSales;
    }

    public void setGrossSales() {
        this.grossSales = this.grossSales;
    }

    public Double getNetSales() {
        return this.netSales;
    }

    public void setNetSales(Double netSales) {
        this.netSales = netSales;
    }

    public int getOrdersCount() {
        return this.ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof com.example.demo.entity.ReportItem))
            return false;
        com.example.demo.entity.ReportItem that = (ReportItem) o;
        return getIdentifier().equals(that.getIdentifier());
    }

    public int hashCode() {
        return Objects.hash(new Object[] { getIdentifier() });
    }

    public void addGrossSales(Double amount) {
        if (this.grossSales == null)
            this.grossSales = Double.valueOf(0.0D);
        this.grossSales = Double.valueOf(this.grossSales.doubleValue() + amount.doubleValue());
    }

    public void addStatus(int amount) {
        if (this.status == 0) {
            this.status = amount;
        } else {
            this.status += amount;
        }
    }

    public void addNetSales(Double amount) {
        if (this.netSales == null)
            this.netSales = Double.valueOf(0.0D);
        this.netSales = Double.valueOf(this.netSales.doubleValue() + amount.doubleValue());
    }

    public void increaseOrderCount() {
        this.ordersCount++;
    }

    public int getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public void increaseProductsCount(int count) {
        this.productsCount += count;
    }
}
