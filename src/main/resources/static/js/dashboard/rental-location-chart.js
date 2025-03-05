// 빠른 예약 - 가장 많이 대여한 지역 TOP 5
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart3"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var fastRentalLocationsChart = echarts.init(chartContainer);

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
                type: "shadow"
            }
        },
        xAxis: {
            type: "category", // X축을 카테고리로 설정 (지역 표시)
            data: fastRentalLocationLabels, // `rentalLocationLabels` 대신 `fastRentalLocationLabels` 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        yAxis: {
            type: "value", // Y축을 값으로 설정 (대여 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "대여 횟수",
                type: "bar", // 막대 그래프
                data: fastRentalLocationCounts, // `rentalLocationCounts` 대신 `fastRentalLocationCounts` 사용
                itemStyle: {
                    normal: {
                        color: function (params) {
                            var colors = ['#ee6666', '#fac858', '#91cc75', '#5470c6', '#73c0de'];
                            return colors[params.dataIndex % colors.length];
                        }
                    },
                    borderRadius: [5, 5, 0, 0] // 막대 상단 둥글게
                },
                emphasis: {
                    itemStyle: {
                        color: "#357ABD"
                    }
                }
            }
        ]
    };

    fastRentalLocationsChart.setOption(chartOption);
    window.addEventListener("resize", fastRentalLocationsChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});


// 단기 예약 - 가장 많이 대여한 지역 TOP 5
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart4"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var shortRentalLocationsChart = echarts.init(chartContainer);

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
                type: "shadow"
            }
        },
        xAxis: {
            type: "category", // X축을 카테고리로 설정 (지역 표시)
            data: shortRentalLocationLabels, // `rentalLocationLabels` 대신 `shortRentalLocationLabels` 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        yAxis: {
            type: "value", // Y축을 값으로 설정 (대여 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "대여 횟수",
                type: "bar", // 막대 그래프
                data: shortRentalLocationCounts, // `rentalLocationCounts` 대신 `shortRentalLocationCounts` 사용
                itemStyle: {
                    normal: {
                        color: function (params) {
                            var colors = ['#ee6666', '#fac858', '#91cc75', '#5470c6', '#73c0de'];
                            return colors[params.dataIndex % colors.length];
                        }
                    },
                    borderRadius: [5, 5, 0, 0] // 막대 상단 둥글게
                },
                emphasis: {
                    itemStyle: {
                        color: "#357ABD"
                    }
                }
            }
        ]
    };

    shortRentalLocationsChart.setOption(chartOption);
    window.addEventListener("resize", shortRentalLocationsChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});
