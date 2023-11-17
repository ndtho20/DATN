// Sales Report by Product
var data;
var chartOptions;

$(document).ready(function() {
    setupButtonEventHandlers("_product", loadSalesReportByDateForProduct);
});

function loadSalesReportByDateForProduct(period) {
    if (period == "customizedDate") {
        startDate = $("#startDate_product").val();
        endDate = $("#endDate_product").val();

        requestURL =  "/statisticals/product/" + startDate + "/" + endDate;
    } else {
        requestURL =   "/statisticals/product/" + period;
    }

    $.get(requestURL, function(responseJSON) {
        console.log(responseJSON)
        prepareChartDataForSalesReportByProduct(responseJSON);
        customizeChartForSalesReportByProduct();
        formatChartData(data, 2, 3);
        drawChartForSalesReportByProduct(period);
        setSalesAmount(period, '_product', "test");
    });
}
// google.charts.load('current', {'packages':['corechart']});
// google.charts.setOnLoadCallback(prepareChartDataForSalesReportByProduct);
function prepareChartDataForSalesReportByProduct(responseJSON) {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Sản phẩm');
    data.addColumn('number', 'Số lượng');
    data.addColumn('number', 'Tổng doanh thu');
    data.addColumn('number', 'Doanh thu thuần');

    var totalGrossSales = 0;
    var totalNetSales = 0;
    var totalItems = 0;

    $.each(responseJSON, function(index, reportItem) {
        data.addRow([
            reportItem.identifier,
            parseInt(reportItem.productsCount),
            parseInt(reportItem.grossSales),
            parseInt(reportItem.netSales)
        ]);

        totalGrossSales += parseInt(reportItem.grossSales);
        totalNetSales += parseInt(reportItem.netSales);
        totalItems += parseInt(reportItem.productsCount);
    });

    console.log(data);

    // Đoạn này có thể làm bất kỳ xử lý nào bạn cần với data hoặc các biến tổng.
}


function customizeChartForSalesReportByProduct() {
    chartOptions = {
        height: 360, width: '98%',
        showRowNumber: true,
        page: 'enable',
        sortColumn: 2,
        sortAscending: false
    };
}
google.charts.load('current', { 'packages': ['table'] });

google.charts.setOnLoadCallback(drawChartForSalesReportByProduct);
function drawChartForSalesReportByProduct() {
    var formatter = new google.visualization.NumberFormat({
        groupingSymbol: '.',
        decimalSymbol: ',',
        fractionDigits: 0
    });

    formatter.format(data, 1); // Định dạng cột thứ hai (Tổng doanh thu)
    formatter.format(data, 2); // Định dạng cột thứ ba (Doanh thu thuần)

    var salesChart = new google.visualization.Table(document.getElementById('chart_sales_by_product'));
    salesChart.draw(data, chartOptions);
}
