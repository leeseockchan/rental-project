document.addEventListener("DOMContentLoaded", function () {
    var chartDom = document.getElementById("chart-container1");
    var myChart = echarts.init(chartDom);

    // 데이터 가져오기
    var maleUserCount = parseInt(chartDom.getAttribute("data-male"), 10);
    var femaleUserCount = parseInt(chartDom.getAttribute("data-female"), 10);

    var option = {
        title: {
            text: "",
            left: "center"
        },
        tooltip: { trigger: "item" },
        series: [
            {
                name: '성별 비율',
                type: "pie",
                radius: ['40%', '100%'], // 반원형 차트 적용
                center: ["50%", "75%"], // 차트의 중앙 위치 설정
                startAngle: 180, // 차트를 반원으로 시작
                endAngle: 360, // 끝 각도 설정
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 5,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: true,
                    position: "outside",
                    formatter: "{b} {d}%", // 라벨 포맷
                    fontSize: 20
                },
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)"
                    }
                },
                data: [
                    { value: maleUserCount, name: "남성", itemStyle: { color: "#3498db" } },
                    { value: femaleUserCount, name: "여성", itemStyle: { color: "#e74c3c" } }
                ]
            }
        ]
    };

    myChart.setOption(option);
});

// 연령대 비율 그래프
document.addEventListener("DOMContentLoaded", function () {
    var chartDom = document.getElementById("chart-container2");
    var myChart = echarts.init(chartDom);

    var option = {
        title: {
            text: "",
            left: "center"
        },
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: "category",
            data: ["20대", "30대", "40대", "50대", "60대 이상"],
        },
        yAxis: {
            type: "value"
        },
        series: [
            {
                name: '연령대별 사용자',
                type: "bar",
                data: [
                    ageStats.twenties,
                    ageStats.thirties,
                    ageStats.forties,
                    ageStats.fifties,
                    ageStats.sixties
                ],
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // 색상 배열을 사용하여 각 막대에 색을 다르게 적용
                            var colors = ['#ee6666', '#fac858', '#91cc75', '#5470c6', '#73c0de'];
                            return colors[params.dataIndex % colors.length];
                        }
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
});