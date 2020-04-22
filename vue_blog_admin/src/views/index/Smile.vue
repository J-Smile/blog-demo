<template>
  <div>
    <el-row>
      <el-col :span="6" style="padding: 3%">
        <div>
          <el-card class="box-card">
            今日访问数：{{ip}}
          </el-card>
        </div>
      </el-col>
      <el-col :span="6" style="padding: 3%">
        <div>
          <el-card class="box-card">
            用户数：{{userNum}}
          </el-card>
        </div>
      </el-col >
      <el-col :span="6" style="padding: 3%">
        <div>
          <el-card class="box-card">
            评论数：{{comment}}
          </el-card>
        </div>
      </el-col>
      <el-col :span="6" style="padding: 3%">
        <div>
          <el-card class="box-card">
            文章数：{{blog}}
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row style="height: 300px;">
      <div id="flow" style="width: 100%;height: 100%;"></div>
    </el-row>
    <el-row>
      <el-col :span="5">
        <div id="sort" style="width: 100%;height: 100%;"></div>
      </el-col>
      <el-col :span="5" style="padding: 3%">

      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {index, record} from "../../api/api";
  import echarts from "echarts/lib/echarts"

  export default {
    name: "Smile",
    data() {
      return {
        ip: 1,
        userNum: 1,
        comment: 1,
        blog: 1
      }
    },
    async mounted() {
      let res = await record();
      const flow_data = res.data.data;
      const myChart = echarts.init(document.getElementById('flow'));
      myChart.setOption({
        title: {
          text: '日访问详情',
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          data: flow_data.map(e => e.time)
        },
        yAxis: {
          type: 'value'
        },
        dataZoom: [
          {
            type: 'slider',
            xAxisIndex: 0,
            filterMode: 'empty'
          },
          {
            type: 'slider',
            yAxisIndex: 0,
            filterMode: 'empty'
          },
          {
            type: 'inside',
            xAxisIndex: 0,
            filterMode: 'empty'
          },
          {
            type: 'inside',
            yAxisIndex: 0,
            filterMode: 'empty'
          }
        ],
        series: [{
          type: 'line',
          symbolSize: 15,
          data: flow_data.map(e => e.num)
        }]
      });



    },
    created: async function () {
      let {data} = await index();
      let strings = data.data.toString().split("-");
      this.ip = strings[0]
      this.userNum = strings[1]
      this.comment = strings[2]
      this.blog = strings[3];
    }
  }
</script>

<style scoped>

</style>
