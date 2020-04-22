<template>
  <div class="sidebar">
    <!--标签云-->
    <div style="padding-bottom: 3%">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <h2>标签云</h2>
        </div>
        <div class="text item">
              <span class="article-tags" v-for="cloud in clouds" :key="cloud.tid" style="padding: 5%">
                <a :href="url+'/tags/'+cloud.tid">
                {{cloud.content}}
                </a>

            </span>
        </div>
      </el-card>
    </div>

    <!--搜索-->
    <div class="widget widget_search">
      <form class="navbar-form" action="/Search" method="post">
        <div class="input-group">
          <input type="text" name="keyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15"
                 autocomplete="off">
          <span class="input-group-btn">
              <button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
                            </span>
        </div>
      </form>
    </div>

    <!--最新评论-->
    <div class="widget widget_hot">
      <h3>最新评论文章</h3>
      <ul>
        <li v-for="blog in new_comment">
          <a :title="blog.title" :href="url" draggable="false">
              <span class="thumbnail">
                <img class="thumb"
                     :src="blog.image"
                     :alt="blog.title" draggable="false">
              </span><span class="text">{{blog.title}}</span>
            <span class="muted">
                <i class="glyphicon glyphicon-time"></i>
                                    {{blog.createTime}}
                </span><span class="muted"><i class="glyphicon glyphicon-eye-open"></i>{{blog.clickCount}}</span>
          </a>
        </li>

      </ul>
    </div>
    <!--推荐文章-->
    <div class="widget widget_hot">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <h2>推荐文章</h2>
        </div>
        <div class="text item">
          <li style="padding-bottom: 10%" v-for="recommend in recommends" :key="recommend.bid">
            <a :title="recommend.title" :href="url+'/show/'+recommend.bid" draggable="false">
             <span class="thumbnail">
             <img class="thumb"
                  :data-original="url+'/show/'+recommend.bid"
                  :src="recommend.image"
                  :alt="recommend.title" draggable="false">
               </span>
              <span class="text">{{recommend.title}}</span>
              <span class="muted">
                <i class="glyphicon glyphicon-time"></i>
                 {{recommend.createTime}}
              </span>
              <span class="muted"><i class="glyphicon glyphicon-eye-open"></i> {{recommend.clickCount}}</span>
            </a>
          </li>
        </div>
      </el-card>
    </div>
    <Follow></Follow>

  </div>
</template>

<script>
  import {sysConfig} from "../utils/url";
  import Follow from "../components/Follow"

  export default {
    name: "Right",
    data() {
      return {
        clouds: [],
        recommends: [],
        url: sysConfig.WEB_URL,
        new_comment:[]
      }
    },
    components: {
      Follow
    },
    async beforeMount() {
      let {data} = await this.$axios.$get("/tag/status");
      this.clouds = data;
      let recommend_data = await this.$axios.$get("/blog/recommend");
      this.recommends = recommend_data.data;
      let new_comment_data = await this.$axios.$get("/comment/newComment");
      this.new_comment = new_comment_data.data;
    }
  }
</script>

<style scoped>

</style>
