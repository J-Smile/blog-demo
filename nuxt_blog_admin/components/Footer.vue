<template>
  <div class="footer" >
    <div class="container">
      <p>本站[<a :href="url">Coder博客</a>]的部分内容来源于网络，若侵犯到您的利益，请联系站长删除！谢谢！</p>
    </div>
  </div>
</template>

<script>
  import {sysConfig} from "../utils/url";

  export default {
    name: "Footer",
    data(){
      return{
        url:sysConfig.WEB_URL
      }
    }
  }
</script>

<style scoped>
.footer{padding-bottom:10px;
}
</style>
