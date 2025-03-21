// 🔄 빠른 예약 - 가장 많이 반납한 지역 TOP 5
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart5"); // 차트가 표시될 요소 ID

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var fastReturnLocationsChart = echarts.init(chartContainer);

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
            formatter: "{b}  {c}건"
        },
        xAxis: {
            type: "category", // X축을 카테고리로 설정 (지역 표시)
            data: returnLocationLabels, // Thymeleaf에서 전달된 데이터 사용
            axisLabel: {
                fontSize: 14,
                interval: 0, // 라벨 표시 간격 설정
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
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: "rgba(0, 0, 0, 0.5)"
                        }
                },
            }
        ]
    };

    fastReturnLocationsChart.setOption(chartOption);
});
