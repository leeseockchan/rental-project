// ✅ 차량 등급별 차트 (반원형)
var gradeChart = echarts.init(document.getElementById('chart-container1'));
var gradeOption = {
    title: { text: '', left: 'center' },
    tooltip: { trigger: "item" },
    legend: {
        bottom: '10%',
        left: 'center',
        textStyle: { fontSize: 14, color: '#333' }
    },
    series: [{
        name: '차량 등급',
        type: 'pie',
        radius: ['40%', '100%'],
        center: ["50%", "70%"],
        startAngle: 180,
        endAngle: 360,
        data: carGradeParsed.map(item => ({ value: item.count, name: item.category })), // 데이터 키 수정
        itemStyle: { borderColor: "#fff", borderWidth: 2 },
        emphasis: {
            itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: "rgba(0, 0, 0, 0.5)" }
        }
    }]
};
gradeChart.setOption(gradeOption);

// ✅ 보유 차량 순위 (원형)
var rankingPieChart = echarts.init(document.getElementById('chart-container2'));
var rankingPieOption = {
    title: { text: '', left: 'center' },
    tooltip: { trigger: "item" },
    legend: { bottom: '5%', left: "center" },
    series: [{
        name: '보유 차량',
        type: 'pie',
        radius: ["30%", "70%"],
        center: ["50%", "40%"],
        data: carRankingParsed.map(item => ({ value: item.value, name: item.label })),
        itemStyle: { borderRadius: 0, borderColor: "#fff", borderWidth: 2 },
        label: { show: false, position: 'center'},
        emphasis: {
            itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: "rgba(0, 0, 0, 0.5)" },
            label: { show: true, fontSize: 20, fontWeight: 'bold'}
        }
    }]
};
rankingPieChart.setOption(rankingPieOption);

// ✅ 제조사별 차량 대수 (세로 막대 그래프)
var brandChart = echarts.init(document.getElementById('chart-container3'));
var brandOption = {
    title: { text: '', left: 'center' },
    tooltip: { trigger: 'axis' },
    xAxis: {
        type: 'category',
        data: carBrandParsed.map(item => item.manufacturer), // label → manufacturer 수정
        axisLabel: { interval: 0, rotate: 0 }
    },
    yAxis: {
        type: 'value',
        name: '차량 수',
        axisLabel: { formatter: '{value} 대' }
    },
    series: [{
        name: '제조사별 차량',
        type: 'bar',
        data: carBrandParsed.map(item => item.count), // value → count 수정
        itemStyle: {
            normal: {
                color: function (params) {
                    var colors = ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'];
                    return colors[params.dataIndex % colors.length];
                }
            }
        }
    }]
};
brandChart.setOption(brandOption);