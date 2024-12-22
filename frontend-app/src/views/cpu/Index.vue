<script setup>
import * as echarts from 'echarts';
import { getCpuLoad } from '@/api/CpuLoadApi.js'
import { onMounted, ref } from 'vue'

const chartDomList = ref([])
const cpuLoadList = ref([])
const initChartDom = () => {
  for (let i = 0; i < 8; i++) {
    let chartDom = document.getElementById(`cpu-${i + 1}`);
    let myChart = echarts.init(chartDom);
    chartDomList.value.push(myChart)
  }
}

const getCpuLoadData = () => {
  getCpuLoad().then(response => {
    const data = response.data;
    for (let i = 0; i < 8; i++) {
      cpuLoadList.value[i] || (cpuLoadList.value[i] = [])
      if (cpuLoadList.value[i].length >= 60) {
        cpuLoadList.value.shift()
      }
      cpuLoadList.value[i].push(data[i])
      drawCpuLoad(i, cpuLoadList.value[i])
    }
    getCpuLoadData();
  })
}

const drawCpuLoad = (cpuIndex, cpuData) => {
  let option = {
    title: {
      text: 'CPU-' + (cpuIndex + 1)
    },
    grid: {
      left: 0,
      right: 0,
      top: 0,
      bottom: 5
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
    },
    yAxis: {
      type: 'value',
      min: 0,
      max: 1
    },
    series: [
      {
        data: cpuData,
        type: 'line',
        areaStyle: {},
        smooth: true,
        symbol: 'none'
      }
    ]
  };
  chartDomList.value[cpuIndex].setOption(option);
}

onMounted(() => {
  initChartDom()
  getCpuLoadData()
})
</script>

<template>
  <a-space wrap>
    <div v-for="index in 8" :id="`cpu-${index}`" style="width: 240px;height: 240px"></div>
  </a-space>
</template>

<style scoped>

</style>