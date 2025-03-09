// 빠른예약 - 차량별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart7");

    if (!chartContainer) return;

    var carRentalChart = echarts.init(chartContainer);

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
        data: fastCarLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0
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
            color: "#ee6666",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#ce2222"
            }
          }
        }
      ]
    };

    carRentalChart.setOption(chartOption);
  });


// 빠른예약 - 지역별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart8");

    if (!chartContainer) return;

    var regionRentalChart = echarts.init(chartContainer);

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
        data: fastRegionLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0
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
            color: "#fac858",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#cf9b25"
            }
          }
        }
      ]
    };

    regionRentalChart.setOption(chartOption);
  });


// 빠른예약 - 사용자별 평균 렌트 시간 차트
    document.addEventListener("DOMContentLoaded", function () {
      var chartContainer = document.getElementById("chart9");

      if (!chartContainer) return;

      var userRentalChart = echarts.init(chartContainer);

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
          data: fastUserLabels,
          axisLabel: {
            fontSize: 14,
            interval: 0
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
              color: "#91cc75",
              borderRadius: [5, 5, 0, 0]
            },
            emphasis: {
              itemStyle: {
                color: "#5ca33a"
              }
            }
          }
        ]
      };

      userRentalChart.setOption(chartOption);
    });



// 단기예약 - 차량별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart10");

    if (!chartContainer) return;

    var carRentalChart = echarts.init(chartContainer);

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
          interval: 0
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
            color: "#5470c6",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#23419f"
            }
          }
        }
      ]
    };

    carRentalChart.setOption(chartOption);
  });


// 단기예약 - 지역별 평균 렌트 시간 차트

  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart11");

    if (!chartContainer) return;

    var regionRentalChart = echarts.init(chartContainer);

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
        data: shortRegionLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0
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
            color: "#73c0de",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#3385a5"
            }
          }
        }
      ]
    };

    regionRentalChart.setOption(chartOption);
  });


// 단기예약 - 사용자별 평균 렌트 시간 차트
  document.addEventListener("DOMContentLoaded", function () {
    var chartContainer = document.getElementById("chart12");

    if (!chartContainer) return;

    var userRentalChart = echarts.init(chartContainer);

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
        data: shortUserLabels,
        axisLabel: {
          fontSize: 14,
          interval: 0
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
            color: "#dd73de",
            borderRadius: [5, 5, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: "#ab3cac"
            }
          }
        }
      ]
    };

    userRentalChart.setOption(chartOption);
  });
