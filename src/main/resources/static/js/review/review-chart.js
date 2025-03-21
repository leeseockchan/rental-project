// 각각의 그래프를 단색으로.
document.addEventListener("DOMContentLoaded", function () {
    // 차량 상태 만족도 차트
    createChart("chart-container2", "차량 상태 만족도", carConditionStats, '#ee6666');
    // 예약 과정 만족도 차트
    createChart("chart-container3", "예약 과정 만족도", reservationProcessStats, '#91cc75');
    // 가격 만족도 차트
    createChart("chart-container4", "가격 만족도", priceStats, '#73c0de');
});

function createChart(containerId, title, data, color) {
    // rating과 count 값을 추출하여 별점별 배열로 변환
    let ratings = [];
    let counts = [];

    // 데이터 정렬: 별점 5가 가장 앞에 오도록 내림차순 정렬
    data.sort((a, b) => b.rating - a.rating);

    data.forEach(item => {
        ratings.push(item.rating);  // rating 값을 x축에 사용
        counts.push(item.count);    // count 값을 y축에 사용
    });

    let chart = echarts.init(document.getElementById(containerId));

    let option = {
        title: {
            text: title,
            left: "center"
        },
        tooltip: {
            trigger: "axis",
            formatter: '{c}개'
        },
        xAxis: {
            type: "category",
            data: ratings  // 별점 값 (rating)
        },
        yAxis: {
            type: "value"
        },
        series: [
            {
                name: title,
                type: "bar",
                data: counts,  // 개수 값 (count)
                itemStyle: {
                    color: color  // 고정된 색상 지정
                }
            }
        ]
    };

    chart.setOption(option);
}

