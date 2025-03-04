// 주차장별 차량 보유량 순위
document.addEventListener("DOMContentLoaded", function () {
    // ✅ 데이터 가져오기
    var top5ParkingStats = top5ParkingStatsParsed;

    // ✅ 주차장별 차량 보유량 순위 (반원형)
    var parkingChart = echarts.init(document.getElementById('chart-container1'));
    var parkingOption = {
        title: { text: '주차장 차량 보유량 TOP 5', left: 'center' },
        tooltip: { trigger: "item" },
        legend: {
            bottom: '10%',
            left: 'center',
            textStyle: { fontSize: 14, color: '#333' }
        },
        series: [{
            name: '보유 차량',
            type: 'pie',
            radius: ['40%', '100%'], // ✅ 반원형 차트 적용
            center: ["50%", "75%"],
            startAngle: 180,
            endAngle: 360,
            data: top5ParkingStats.map(item => ({ value: item.carCount, name: item.parkingName })),
            itemStyle: { borderColor: "#fff", borderWidth: 2 },
            emphasis: {
                itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: "rgba(0, 0, 0, 0.5)" }
            }
        }]
    };

    parkingChart.setOption(parkingOption);
});
