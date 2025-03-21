document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart1");

    if (!chartContainer) return;

    var fastReservationChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "",
            left: "center",
            textStyle: {
                fontSize: 18,
                fontWeight: "bold"
            }
        },
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "line"
            },
            formatter: "{c}건"
        },
        xAxis: {
            type: "category",
            data: fastHourLabels,
            axisLabel: {
                fontSize: 14,
                interval: 0
            }
        },
        yAxis: {
            type: "value",
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "예약 횟수",
                type: "line",
                data: fastHourCounts,
                smooth: true, // 부드러운 곡선
                symbol: "circle", // 데이터 포인트 원형으로 표시
                symbolSize: 8,
                lineStyle: {
                    color: "#4A90E2",
                    width: 3
                },
                itemStyle: {
                    color: "#4A90E2"
                },
                emphasis: {
                    itemStyle: {
                        color: "#357ABD"
                    }
                }
            }
        ]
    };

    fastReservationChart.setOption(chartOption);
});

document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart2");

    if (!chartContainer) return;

    var fastReservationChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "",
            left: "center",
            textStyle: {
                fontSize: 18,
                fontWeight: "bold"
            }
        },
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "line"
            },
            formatter: "{c}건"
        },
        xAxis: {
            type: "category",
            data: shortRentalHours,
            axisLabel: {
                fontSize: 14,
                interval: 0
            }
        },
        yAxis: {
            type: "value",
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "예약 횟수",
                type: "line",
                data: rentalCounts,
                smooth: true,
                symbol: "circle",
                symbolSize: 8,
                lineStyle: {
                    color: "#91cc75",
                    width: 3
                },
                itemStyle: {
                    color: "#91cc75"
                },
                emphasis: {
                    itemStyle: {
                        color: "#62964a"
                    }
                }
            }
        ]
    };

    fastReservationChart.setOption(chartOption);
});
