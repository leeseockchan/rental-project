// 원형 차트 (Fuel Data: 차량 연료 종류별 비율)
var pieChart = echarts.init(document.getElementById('chart-container2'));
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

// 반원형 차트 (Category Data: 차량 카테고리별 비율)
var semiCircleChart = echarts.init(document.getElementById('chart-container1'));
var semiCircleOption = {
    title: {
        text: '',
        left: 'center'
    },
    tooltip: {
        trigger: "item",
    },
    legend: {
        origin: 'vertical',
        bottom: '40%',
        left: '0%',
        textStyle: {
            fontSize: 15, // 폰트 크기 키우기
            color: '#333',
        },
        itemGap: 10,  // 아이템 간의 간격
        itemWidth: 20,  // 아이템 너비
        itemHeight: 20,  // 아이템 높이
    },
    series: [{
        name: 'Category',
        type: 'pie',
        radius: ['40%', '100%'],
        center: ["70%", "80%"],
        startAngle: 180,
        endAngle: 360,
        itemStyle: {
            borderColor: "#fff",
            borderWidth: 2,
        },
        data: categoryDataParsed.map(function (item) {
            return { value: item.count, name: item.category };
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
semiCircleChart.setOption(semiCircleOption);

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