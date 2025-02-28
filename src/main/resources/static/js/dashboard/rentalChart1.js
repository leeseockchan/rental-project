document.addEventListener("DOMContentLoaded", function () {
    var chartDom = document.getElementById('rentalChart1');
    var rentalChart = echarts.init(chartDom);

    // Thymeleaf에서 데이터 가져오기
    var hours = [];
    var counts = [];
    document.querySelectorAll("table tr:nth-child(n+2)").forEach(row => {
        hours.push(row.cells[0].innerText.replace("시", ""));  // "시" 제거
        counts.push(parseInt(row.cells[1].innerText));  // 숫자로 변환
    });

    var option = {
        title: {
            text: '빠른 예약 시간대 TOP 5',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: hours,
            axisLabel: { formatter: '{value}시' } // X축 레이블에 "시" 추가
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '예약 횟수',
            type: 'bar',
            data: counts,
            itemStyle: { color: '#FFC107' } // 노란색 막대
        }]
    };

    rentalChart.setOption(option);
});
