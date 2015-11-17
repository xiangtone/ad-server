$(function () {
        $('#container').highcharts({
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: 'Average Monthly Temperature and Rainfall in Tokyo'
            },
            subtitle: {
                text: 'Source: www.adwalker.com'
            },
            xAxis: [{
                categories: ['6月1日','6月2日','6月3日','6月4日','6月5日','6月6日','6月7日','6月8日','6月9日','6月10日','6月11日','6月12日','6月13日','6月14日','6月15日','6月16日','6月17日']
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}个',
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: '实际结算数据',
                    style: {
                        color: '#89A54E'
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '按MAC结算数据',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    format: '{value}个',
                    style: {
                        color: '#4572A7'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 120,
                verticalAlign: 'top',
                y: 100,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [{
                name: '按MAC结算数据',
                color: '#4572A7',
                type: 'column',
                yAxis: 1,
                data: [32453,33457,31481,32289,21949,16145,17139,23178,26291,31709,29199,32729,33342,24466,33486,31473,26762],
            tooltip: {
                    valueSuffix: '个'
                }
    
            }, {
                name: '实际结算数据',
                color: '#89A54E',
                type: 'spline',
                data: [30000,
30000,
30000,
30000,
21949,
16145,
17139,
23178,
26291,
30000,
29199,
30000,
30000,
24466,
30000,
30000,
26762],
                tooltip: {
                    valueSuffix: '个'
                }
            }]
        });
    });
    
