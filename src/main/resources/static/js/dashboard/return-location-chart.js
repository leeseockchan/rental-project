// 🔄 빠른 예약 - 가장 많이 반납한 지역 TOP 5
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart5"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var fastReturnLocationsChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "반납 횟수 상위 지역",
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
            data: returnLocationLabels, // Thymeleaf에서 전달된 데이터 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        yAxis: {
            type: "value", // Y축을 값으로 설정 (반납 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "반납 횟수",
                type: "bar", // 막대 그래프
                data: returnLocationCounts, // Thymeleaf에서 전달된 반납 횟수 데이터 사용
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

    fastReturnLocationsChart.setOption(chartOption);
    window.addEventListener("resize", fastReturnLocationsChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});

//🔄 단기 예약 - 가장 많이 반납한 지역 TOP 5
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart6"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var shortReturnLocationsChart = echarts.init(chartContainer);

    var chartOption = {
        title: {
            text: "반납 횟수 상위 지역 (짧은 대여 시간)",
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
            data: shortReturnLocationLabels, // Thymeleaf에서 전달된 데이터 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
                rotate: 45 // 라벨을 회전하여 표시
            }
        },
        yAxis: {
            type: "value", // Y축을 값으로 설정 (반납 횟수 표시)
            axisLabel: {
                fontSize: 14
            }
        },
        series: [
            {
                name: "반납 횟수",
                type: "bar", // 막대 그래프
                data: shortReturnLocationCounts, // Thymeleaf에서 전달된 반납 횟수 데이터 사용
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

    shortReturnLocationsChart.setOption(chartOption);
    window.addEventListener("resize", shortReturnLocationsChart.resize); // 윈도우 크기 조정 시 차트 크기 조정
});
