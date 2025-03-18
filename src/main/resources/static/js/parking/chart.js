document.addEventListener("DOMContentLoaded", function () {
    // 주차장별 차량 보유량 순위 (반원형)
    var top5ParkingStats = top5ParkingStatsParsed;

    var parkingChart1 = echarts.init(document.getElementById('chart-container1'));
    var parkingOption1 = {
        title: { text: '' },
        tooltip: { trigger: "item" },
        legend: {
            bottom: '20%',
            left: 'left',
            textStyle: { fontSize: 14, color: '#333' },
            orient: 'vertical'
        },
        series: [{
            name: '보유 차량',
            type: 'pie',
            radius: ['40%', '100%'], // 반원형 차트 적용
            center: ["65%", "75%"],
            startAngle: 180,
            endAngle: 360,
            data: top5ParkingStats.map(item => ({ value: item.carCount, name: item.parkingName })),
            itemStyle: { borderColor: "#fff", borderWidth: 2 },
            emphasis: {
                itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: "rgba(0, 0, 0, 0.5)" }
            }
        }]
    };
    parkingChart1.setOption(parkingOption1);


    // 지역별 주차장 개수 (원형))
    var parkingCountByRegion = parkingCountByRegionParsed;
    var chartContainer2 = document.getElementById('chart-container2');
    // 원형 차트 스타일
    var parkingChart2 = echarts.init(chartContainer2);

    var parkingOption2 = {
        title: {
            text: '', // 제목
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}: {c}개' // 툴팁 포맷
        },
        legend: {
            bottom: '20%',
            left: 'left',
            textStyle: {
                fontSize: 14,
                color: '#333'
            },
            orient: 'vertical'
        },
        series: [{
            name: '주차장 개수',
            type: 'pie', // 차트 유형을 원형 차트(pie)로 설정
            radius: ['35%', '80%'], // 원형 차트의 반지름
            center: ["70%", "45%"],
            label: { show: false, position: 'center'},
            data: parkingCountByRegion.map(item => ({
                name: item.region,
                value: item.parking_count
            })),
            itemStyle: {
                borderColor: '#fff',
                borderWidth: 2
            },
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                label: { show: true, fontSize: 20, fontWeight: 'bold'}
            }
        }]
    };
    parkingChart2.setOption(parkingOption2);

});