// 빠른예약 - 차량별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart7");

    if (!chartContainer) return;

    var carRentalChart = echarts.init(chartContainer);

    var chartOption = {
      title: {
        text: "차량별 평균 렌트 시간",
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
        data: fastCarLabels,
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
          name: "평균 렌트 시간",
          type: "bar",
          data: fastCarCounts,
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

    carRentalChart.setOption(chartOption);
    window.addEventListener("resize", carRentalChart.resize);
  });


// 빠른예약 - 지역별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart8");

    if (!chartContainer) return;

    var regionRentalChart = echarts.init(chartContainer);

    var chartOption = {
      title: {
        text: "지역별 평균 렌트 시간",
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
        data: fastRegionLabels,
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
          name: "평균 렌트 시간",
          type: "bar",
          data: fastRegionCounts,
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

    regionRentalChart.setOption(chartOption);
    window.addEventListener("resize", regionRentalChart.resize);
  });


// 빠른예약 - 사용자별 평균 렌트 시간 차트
    document.addEventListener("DOMContentLoaded", function () {
      var chartContainer = document.getElementById("chart9");

      if (!chartContainer) return;

      var userRentalChart = echarts.init(chartContainer);

      var chartOption = {
        title: {
          text: "사용자별 평균 렌트 시간",
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
          data: fastUserLabels,
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
            name: "평균 렌트 시간",
            type: "bar",
            data: fastUserCounts,
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

      userRentalChart.setOption(chartOption);
      window.addEventListener("resize", userRentalChart.resize);
    });



// 단기예약 - 차량별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart10");

    if (!chartContainer) return;

    var carRentalChart = echarts.init(chartContainer);

    var chartOption = {
      title: {
        text: "차량별 평균 렌트 시간",
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
          name: "평균 렌트 시간",
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

    carRentalChart.setOption(chartOption);
    window.addEventListener("resize", carRentalChart.resize);
  });


// 단기예약 - 지역별 평균 렌트 시간 차트

  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart11");

    if (!chartContainer) return;

    var regionRentalChart = echarts.init(chartContainer);

    var chartOption = {
      title: {
        text: "지역별 평균 렌트 시간",
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
        data: shortRegionLabels,
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
          name: "평균 렌트 시간",
          type: "bar",
          data: shortRegionCounts,
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

    regionRentalChart.setOption(chartOption);
    window.addEventListener("resize", regionRentalChart.resize);
  });


// 단기예약 - 사용자별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart12");

    if (!chartContainer) return;

    var userRentalChart = echarts.init(chartContainer);

    var chartOption = {
      title: {
        text: "사용자별 평균 렌트 시간",
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
        data: shortUserLabels,
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
          name: "평균 렌트 시간",
          type: "bar",
          data: shortUserCounts,
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

    userRentalChart.setOption(chartOption);
    window.addEventListener("resize", userRentalChart.resize);
  });
