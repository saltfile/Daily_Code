window.onload= function () {


    var chartDom = document.getElementById('pm');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        title: {
            text: '峰值表'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['广州', '湖南', '山东', '北京', '天津']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '广州',
                type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210,100,150,130,160,110]
            },
            {
                name: '湖南',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310,280,300,200,260,300]
            },
            {
                name: '山东',
                type: 'line',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410,250,350,421,380,330]
            },
            {
                name: '北京',
                type: 'line',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320,450,350,470,335,360]
            },
            {
                name: '天津',
                type: 'line',
                stack: '总量',
                data: [820, 932, 901, 934, 750, 800, 950,750,850,780,880,900]
            }
        ]
    };

    myChart.setOption(option);
}


