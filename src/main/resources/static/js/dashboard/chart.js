// 성별, 반원 그래프
document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart-container1");

    // ✅ 차트 컨테이너가 없으면 실행하지 않음 (오류 방지)
    if (!chartContainer) return;

    var semiCircleChart = echarts.init(chartContainer);

    var semiCircleOption = {
        title: {
            text: "성별 인원 통계",
            left: "center",
            top: "5%",
            textStyle: {
                fontSize: 20,
                fontWeight: "bold"
            }
        },
        tooltip: {
            trigger: "item",
            formatter: "{b}: {c}명 ({d}%)"
        },
        legend: {
            orient: "horizontal",
            bottom: "5%",
            left: "center",
            textStyle: {
                fontSize: 14,
                color: "#333"
            },
            itemGap: 10,
            itemWidth: 20,
            itemHeight: 20,
            data: ["남성", "여성"]
        },
        series: [
            {
                name: "성별",
                type: "pie",
                radius: ['40%', '100%'], // 반원형
                center: ['50%', '70%'],
                startAngle: 180,
                endAngle: 360,
                avoidLabelOverlap: false,
                itemStyle: {
                    borderColor: "#fff",
                    borderWidth: 2
                },
                label: {
                    show: true,
                    position: "inside",
                    fontSize: 14,
                    fontWeight: "bold",
                    formatter: "{b}\n{c}명"
                },
                data: [
                    { value: maleCount, name: "남성", itemStyle: { color: "#4A90E2" } }, // 남성: 파랑
                    { value: femaleCount, name: "여성", itemStyle: { color: "#E94E77" } } // 여성: 핑크
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 15,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.3)"
                    }
                }
            }
        ]
    };

    semiCircleChart.setOption(semiCircleOption);
    window.addEventListener("resize", semiCircleChart.resize);
});


// 연령별, 막대 그래프
document.addEventListener("DOMContentLoaded", function () {
    var chartDom = document.getElementById('chart-container2');
    var myChart = echarts.init(chartDom);

    var option = {
        title: {
            text: '연령대별 인원 수',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: window.ageLabels // HTML에서 전달된 변수 사용
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '인원 수',
            type: 'bar',
            data: window.ageValues, // HTML에서 전달된 변수 사용
            itemStyle: {
                color: '#4CAF50'
            }
        }]
    };

    myChart.setOption(option);
});


// 원형 차트 (Fuel Data: 차량 연료 종류별 비율)
var pieChart = echarts.init(document.getElementById(''));
var pieOption = {
    title: {
        text: '',
        left: 'center'
    },
    tooltip: {
        trigger: "item",
    },
    legend: {
        top: '0',
        left: "center",
    },
    series: [{
        name: 'Fuel',
        type: 'pie',
        radius: ["30%", "70%"],
        avoidLabelOverlap: false,
        itemStyle: {
            borderRadius: 10,
            borderColor: "#fff",
            borderWidth: 2,
        },
        data: fuelDataParsed.map(function (item) {
            return { value: item.count, name: item.fuel };
        }),
        emphasis: {
            itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
            },
        }
    }]
};
pieChart.setOption(pieOption);


// 세로 차트 (Manufacturer Data: 제조사별 차량 수)
var verticalBarChart = echarts.init(document.getElementById('chart-container3'));
var verticalBarOption = {
    title: {
        text: '',
        left: 'center'
    },
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        data: manufacturerDataParsed.map(function (item) {
            return item.manufacturer;
        }),
        name: '제조사',
        nameLocation: 'middle',
        axisLabel: {
            interval: 0, // 모든 레이블을 표시하려면 이 값을 설정
            rotate: 45 // 레이블 각도 조정 (중복되는 텍스트 방지)
        }
    },
    yAxis: {
        type: 'value',
        name: '차량 수',
        axisLabel: {
            formatter: '{value} 대'
        }
    },
    series: [{
        name: '차량 수',
        type: 'bar',
        data: manufacturerDataParsed.map(function (item) {
            return item.count;
        }),
        itemStyle: {
            normal: {
                color: function (params) {
                    var colors = ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'];
                    return colors[params.dataIndex];  // 각 데이터의 인덱스에 해당하는 색상 사용
                }
            }
        }
    }]
};
verticalBarChart.setOption(verticalBarOption);


// 가로 차트 (Manufacturer Data: 제조사별 차량 수)
var horizontalBarChart = echarts.init(document.getElementById('chart-container4'));
var barOption = {
    title: {
        text: '',
        left: 'center'
    },
    tooltip: {
        trigger: "item",
    },
    legend: {
        orient: "vertical",
        left: "left",
    },
    xAxis: {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: manufacturerDataParsed.map(function (item) {
            return item.manufacturer;
        })
    },
    series: [{
        name: 'Count',
        type: 'bar',
        data: manufacturerDataParsed.map(function (item) {
            return item.count;
        })
    }]
};
horizontalBarChart.setOption(barOption);