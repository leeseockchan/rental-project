// 빠른예약 - 차량별 예약 횟수
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("carPopularChart");

    if (!chartContainer) return;

    var carPopularChart = echarts.init(chartContainer);

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
        axisPointer: {
          type: "shadow"
        }
      },
      xAxis: {
        type: "category",
        data: carLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0,
          rotate: 45
        }
      },
      yAxis: {
        type: "value",
        axisLabel: {
          fontSize: 14
        }
      },
      series: [
        {
          name: "예약 횟수",
          type: "bar",
          data: carCounts,
          itemStyle: {
            color: "#4A90E2",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#357ABD"
            }
          }
        }
      ]
    };

    carPopularChart.setOption(chartOption);
    window.addEventListener("resize", carPopularChart.resize);
  });


// 단기 예약 - 차량별 예약 횟수 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("shortCarPopularChart");

    if (!chartContainer) return;

    var shortCarPopularChart = echarts.init(chartContainer);

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
        axisPointer: {
          type: "shadow"
        }
      },
      xAxis: {
        type: "category",
        data: shortCarLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0,
          rotate: 45
        }
      },
      yAxis: {
        type: "value",
        axisLabel: {
          fontSize: 14
        }
      },
      series: [
        {
          name: "예약 횟수",
          type: "bar",
          data: shortCarCounts,
          itemStyle: {
            color: "#4A90E2",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#357ABD"
            }
          }
        }
      ]
    };

    shortCarPopularChart.setOption(chartOption);
    window.addEventListener("resize", shortCarPopularChart.resize);
  });
