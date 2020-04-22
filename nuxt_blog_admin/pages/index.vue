<template>
  <div class="container" style="padding-top: 5%;display: flex;flex-direction: row">
    <div class="content-wrap">
      <!--中间内容-->
      <div class="content">
        <!--轮播图-->
        <div style="padding-bottom: 3%;">
          <el-carousel height="350px">
            <el-carousel-item v-for="item in images" :key="item.id">
              <el-image :src="item.image"></el-image>
            </el-carousel-item>
          </el-carousel>
        </div>

        <!--文章-->
        <div class="excerpt excerpt-2" v-for="blog in blogs">
          <article>
            <a class="focus" :href="url+'/show/'+blog.bid"
               :title="blog.title" target="_blank"
               draggable="false">
              <img class="thumb"
                   :data-original="blog.image"
                   :src="blog.image"
                   draggable="false" style="display: inline;">
            </a>
            <header>
              <h1>
                <a class="cat" href="#"
                   :title="blog.sortName" draggable="false">{{blog.sortName}}<i></i></a>
                <a :href="url+'/show/'+blog.bid"
                   :title="blog.title" target="_blank"
                   draggable="false">{{blog.title}}</a>
              </h1>
            </header>
            <p class="meta">
              <time class="time"><i class="glyphicon glyphicon-time"></i> {{blog.createTime}}</time>
              <span class="views"><i class="glyphicon glyphicon-eye-open"></i> {{blog.clickCount}}</span>
              <a class="comment"
                 href="http://www.muzhuangnet.com/show/711.html#comment"
                 title="评论" target="_blank"
                 draggable="false"><i
                class="glyphicon glyphicon-comment"></i> {{blog.discussCount}}</a>
            </p>
          </article>
        </div>
      </div>
    </div>
    <Right></Right>

  </div>
</template>

<script>
  import Right from "../components/Right";
  import {sysConfig} from "../utils/url";

  export default {
    layout: 'headfoot',
    components: {
      Right
    },
    async asyncData({$axios}) {
      let {data} = await $axios.$get("/image");
      let res_blog = await $axios.$get("/blog/index");
      return {
        images: data,
        blogs: res_blog.data
      }
    },
    data() {
      return {
        url: sysConfig.WEB_URL,
        images: [],
        blogs: [],
      }
    },
    mounted() {
      this.$axios.$get("/record")
    },
    methods: {},
    head: {
      title: "首页"
    }
  }
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

</style>
