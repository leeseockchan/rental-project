// 빠른예약 - 차량별 예약 횟수 (원형 차트)
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("carPopularChart");

    if (!chartContainer) return;

    var carPopularChart = echarts.init(chartContainer);

    var chartOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c}건 ({d}%)" // 차량명: 예약 횟수 (퍼센트)
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '예약 횟수',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 20,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: carLabels.map((label, index) => ({
                    value: carCounts[index],
                    name: label
                }))
            }
        ]
    };

    carPopularChart.setOption(chartOption);
    window.addEventListener("resize", carPopularChart.resize);
});

// 단기 예약 - 차량별 예약 횟수 (원형 차트)
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("shortCarPopularChart");

    if (!chartContainer) return;

    var shortCarPopularChart = echarts.init(chartContainer);

    var chartOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c}건 ({d}%)"
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '예약 횟수',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 20,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: shortCarLabels.map((label, index) => ({
                    value: shortCarCounts[index],
                    name: label
                }))
            }
        ]
    };

    shortCarPopularChart.setOption(chartOption);
    window.addEventListener("resize", shortCarPopularChart.resize);
});
