// Sales Report by Date
var data;
var chartOptions;
var totalGrossSales;
var totalNetSales;
var totalItems;

$(document).ready(function () {
    setupButtonEventHandlers("_date", loadSalesReportByDate);
});


function loadSalesReportByDate(period) {
    if (period == "customizedDate") {
        startDate = $("#startDate_date").val();
        endDate = $("#endDate_date").val();
        requestURL = "/statisticals/sales_report_by_date/" + startDate + "/" + endDate;
    } else {
        requestURL = "/statisticals/sales_report_by_date/" + period;
    }

    $.get(requestURL, function (responseJSON) {
        try {
            prepareChartDataForSalesReportByDate(responseJSON);
            customizeChartForSalesReportByDate(period);
            formatChartData(data, 1, 2);
            drawChartForSalesReportByDate(period);
            setSalesAmount(period, '_date', "Total Orders");
        } catch (error) {
            console.error("Error processing JSON: " + error.message);
        }
    }).fail(function () {
        console.error("Error in AJAX request");
    });
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(prepareChartDataForSalesReportByDate);
function prepareChartDataForSalesReportByDate(responseJSON) {
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Total Orders');
    data.addColumn('number', 'Tổng Doanh Thu');
    data.addColumn('number', 'Doanh Thu Thuần');
    data.addColumn('number', 'Số Đơn Hàng');

    totalGrossSales = 0.0;
    totalNetSales = 0.0;
    totalItems = 0;

    $.each(responseJSON, function (index, reportItem) {
        data.addRows([[reportItem.identifier, reportItem.grossSales, reportItem.netSales, reportItem.ordersCount]]);
        console.log(reportItem);
        totalGrossSales += parseFloat(reportItem.grossSales);
        totalNetSales += parseFloat(reportItem.netSales);
        totalItems += parseInt(reportItem.ordersCount);
    });
    console.log(responseJSON);
}

function customizeChartForSalesReportByDate(period) {
    chartOptions = {
        title: getChartTitle(period),
        'height': 360,
        legend: {position: 'top'},

        series: {
            0: {targetAxisIndex: 0},
            1: {targetAxisIndex: 0},
            2: {targetAxisIndex: 1},
        },

        vAxes: {
            0: {title: 'Doanh thu', format: 'VND '},
            1: {title: 'số đơn hàng'}
        }
    };
}

function drawChartForSalesReportByDate() {
    var salesChart = new google.visualization.ColumnChart(document.getElementById('chart_sales_by_date'));
    salesChart.draw(data, chartOptions);
}