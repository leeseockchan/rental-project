document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart1"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var fastReservationChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "빠른 예약 시간대 TOP 5",
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
        yAxis: {
            type: "category", // Y축을 카테고리로 설정 (가로 막대 그래프에서 시간대 표시)
            data: fastHourLabels, // ✅ Thymeleaf에서 전달된 데이터 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        xAxis: {
            type: "value", // X축을 값으로 설정 (예약 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "예약 횟수",
                type: "bar", // 막대 그래프
                data: fastHourCounts, // ✅ Thymeleaf에서 전달된 데이터 사용
                itemStyle: {
                    color: "#4A90E2", // 막대 색상 (파랑)
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

    fastReservationChart.setOption(chartOption);
    window.addEventListener("resize", fastReservationChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});

document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart2"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var fastReservationChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "빠른 예약 시간대 TOP",
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
        yAxis: {
            type: "category", // Y축을 카테고리로 설정 (가로 막대 그래프에서 시간대 표시)
            data: shortRentalHours, // Thymeleaf에서 전달된 시간대 데이터 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        xAxis: {
            type: "value", // X축을 값으로 설정 (예약 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "예약 횟수",
                type: "bar", // 막대 그래프
                data: rentalCounts, // Thymeleaf에서 전달된 예약 횟수 데이터 사용
                itemStyle: {
                    color: "#42A5F5", // 막대 색상 (파랑)
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

    fastReservationChart.setOption(chartOption);
    window.addEventListener("resize", fastReservationChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});
