<template>
  <div class="container" style="padding-top: 5%;display: flex;flex-direction: row">
    <div class="content-wrap">
      <!--中间内容-->
      <div class="content">
        <!--文章-->
        <div class="excerpt excerpt-2" v-if="blogs.length>0" v-for="blog in blogs">
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
                <a class="cat" href="http://www.muzhuangnet.com/list/java/"
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
                 :href="url+'/show/711.html#comment'"
                 title="评论" target="_blank"
                 draggable="false"><i
                class="glyphicon glyphicon-comment"></i> {{blog.discussCount}}</a>
            </p>
          </article>
        </div>
        <div v-if="blogs.length==0">
          到底了~~~
        </div>
      </div>
    </div>

    <Right></Right>
  </div>
</template>

<script>
  import Right from "../../components/Right";
  import {sysConfig} from "../../utils/url";

  export default {
    layout: 'headfoot',
    components: {
      Right
    },
    data() {
      return {
        blogs: [],
        url:sysConfig.WEB_URL
      }
    },
    async mounted() {
      const id = this.$route.params.id;
      let {data} = await this.$axios.$get("/tag/tags/" + id);
      this.blogs = data

    }
  }
</script>

<style scoped>

</style>
