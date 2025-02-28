document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("fastReservationChart");

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
        xAxis: {
            type: "category",
            data: fastHourLabels, // ✅ Thymeleaf에서 전달된 데이터 사용
            axisLabel: {
                fontSize: 14
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
                type: "bar",
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
    window.addEventListener("resize", fastReservationChart.resize);
});
