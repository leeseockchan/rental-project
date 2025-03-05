// 성별 비율 그래프
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
        series: [
            {
                type: "pie",
                radius: ["40%", "100%"],
                center: ["50%", "70%"],
                startAngle: 180,
                avoidLabelOverlap: false,
                      itemStyle: {
                        borderRadius: 5,
                        borderColor: '#fff',
                        borderWidth: 2
                },
                label: {
                    show: true,
                    position: "outside",
                    formatter: "{b} {d}%",
                },
                data: [
                    { value: maleUserCount, name: "남성", itemStyle: { color: "#3498db" } },
                    { value: femaleUserCount, name: "여성", itemStyle: { color: "#e74c3c" } },
                    { value: maleUserCount + femaleUserCount, name: "", itemStyle: { color: "transparent" } },
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
        xAxis: {
            type: "category",
            data: ["20대", "30대", "40대", "50대", "60대 이상"]
        },
        yAxis: {
            type: "value"
        },
        series: [
            {
                type: "bar",
                data: [
                    ageStats.twenties,
                    ageStats.thirties,
                    ageStats.forties,
                    ageStats.fifties,
                    ageStats.sixties
                ],
                itemStyle: {
                    color: "#3498db"
                }
            }
        ]
    };

    myChart.setOption(option);
});

