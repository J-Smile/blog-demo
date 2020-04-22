<template>
  <div>
    <el-image style=" width:100%; padding-top:3%"
              :src="image">
    </el-image>
    <div class="container" style="display: flex;flex-direction: row">
      <div style="flex: 4;padding-top: 2%" align="right">
        <el-card shadow="hover" style="width: 85%" align="center">
          <article v-html="author.html"></article>
        </el-card>
      </div>
      <div style="flex: 2;">
        <div style="padding: 5%">
          <el-card shadow="hover" align="center">
            <el-avatar :src="author.image" :size="50"></el-avatar>
            <p style="padding: 5%;font-weight: 700; font-size: 16px;margin-bottom: 10px;">{{author.name}}</p>
            <p style="color: #595959; text-align: center;">{{author.work}}</p>
            <p style="padding: 20px; color: #999;   line-height: 26px; text-align: justify">{{author.userDescribe}}</p>
          </el-card>
        </div>
        <div style="padding: 5%">
          <Follow></Follow>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import Follow from "../../components/Follow";

  export default {
    name: "index",
    layout: 'headfoot',
    components: {
      Follow,
    },
    data() {
      return {
        author: {},
        image: "",
      }
    },
    async asyncData({$axios}) {
      let {data} = await $axios.$get("/author");
      let image = await $axios.$get("/image/bg");
      return {
        author: data,
        image: image.data.image,
      }
    },
  }
</script>

<style scoped>
  .hometitle {
    font-size: 18px;
    color: #282828;
    font-weight: 600;
    margin: 0;
    text-transform: uppercase;
    padding-bottom: 15px;
    margin-bottom: 25px;
    position: relative;
  }

  .hometitle:after {
    content: "";
    background-color: #282828;
    left: 0;
    width: 50px;
    height: 2px;
    bottom: 0;
    position: absolute;
    -webkit-transition: 0.5s;
    -moz-transition: 0.5s;
    -ms-transition: 0.5s;
    -o-transition: 0.5s;
    transition: 0.5s;
  }


</style>
