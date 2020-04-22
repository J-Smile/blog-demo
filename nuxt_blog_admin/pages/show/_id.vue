<template>
  <div class="container" style="padding-top: 5%">
    <div class="content-wrap">
      <div class="content">
        <!--文章-->
        <el-card class="box-card">
          <div slot="header" class="clearfix" align="center">
            <div>
              <h1>{{blog.title}}</h1>
              <div style="padding-top: 1%">
                 <span style="padding-right: 2%">
               <i class="el-icon-time"> {{blog.createTime}}</i>
             </span>
                <span style="padding-right: 2%">
             <i class="glyphicon glyphicon-globe"></i> Coder博客
             </span>
                <span style="padding-right: 2%">
               <i class="glyphicon glyphicon-list"> </i> {{blog.sortName}}
             </span>
                <span style="padding-right: 2%">
              <i class="glyphicon glyphicon-eye-open"></i> {{blog.clickCount}}
             </span>
                <span style="padding-right: 2%">
                <i class="glyphicon glyphicon-comment"> {{blog.discussCount}}</i>
              </span>
              </div>
            </div>
          </div>
          <div>
            <article v-html="blog.html"></article>
          </div>
        </el-card>

        <!--标签-->
        <div class="article-tags">
          标签：<a :href="url+'/tags/'+tag.tid" rel="tag" v-for="tag in blog.tags">{{tag.content}}</a>
        </div>

        <el-divider></el-divider>
        <!--推荐-->
        <div class="relates">
          <div class="title">
            <h3>相关推荐</h3>
          </div>
          <ul>
            <li v-for="recommend in recommends">
              <a :href="url+'/show/'+recommend.bid" title="">{{recommend.title}}</a>
            </li>
          </ul>
        </div>
        <el-divider></el-divider>
        <!--评论-->
        <div class="title" id="comment">
          <h3>评论</h3>
        </div>
        <div id="respond">
          <div style=" height: 100%;display: flex;flex-direction: row">
            <div style="flex: 1;height: 100%">
              <el-avatar shape="square" style="height: 90px;width: 90px"
                         :src="image"></el-avatar>
            </div>
            <div style="flex: 5">
              <el-input
                type="textarea"
                v-model="comment.text"
                placeholder="请输入内容"
                maxlength="300"
                show-word-limit :rows="4">
              </el-input>
              <div align="right" style="padding-top: 1%">
                <el-button type="info" @click="cancle_message()">取消评论</el-button>
                <el-button type="success_message" @click="save_message()">发送评论</el-button>
              </div>
            </div>
          </div>
        </div>

        <el-divider></el-divider>

        <div id="postcomments" v-if="comments!=null">
          <ol id="comment_list" class="commentlist">
            <li class="comment-content" v-for="comment in comments">
              <div class="comment-main" style="display: flex;flex-direction: row">
                <div style="flex: 1">
                  <el-avatar shape="square" style="height: 90px;width: 90px"
                             :src="comment.user.avatar"></el-avatar>
                </div>
                <div style="flex: 5">
                  <p >
                    <a class="address"
                       :href="url" rel="nofollow"
                       target="_blank">{{comment.user.nickname}}</a>
                    <span
                      class="time">{{comment.time}}</span><br>{{comment.text}}</p>
                </div>


              </div>
            </li>
          </ol>
        </div>

        <div v-else align="center">快来抢占沙发~</div>
      </div>
    </div>
    <Right></Right>

  </div>
</template>

<script>
  import Right from "../../components/Right";
  import {sysConfig} from "../../utils/url";
  import {error_login, success_message, state, error_message} from "../../utils/message";
  import {close_loading, start} from "../../utils/loading";

  export default {
    layout: 'headfoot',
    components: {
      Right
    },
    data() {
      return {
        blog: {},
        comment: {
          text: ""
        },
        recommends: [],
        url: sysConfig.WEB_URL,
        image: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
        comments: []
      }
    },
    async asyncData({$axios}) {
      let res = await $axios.$get("/blog/recommend");
      return {recommends: res.data}
    },
    methods: {
      cancle_message() {
        this.comment.text = ""
      },
      async save_message() {
        let user = sessionStorage.getItem("user");
        if (!user) {
          error_login();
        } else {
          this.comment.bid = this.$route.params.id;
          let {data} = await this.$axios.post("comment", this.comment);
          if (data.code == 0) {
            success_message(state.success_comment);
            window.location.reload();
          } else if (data.code = 1100) {
            error_message(data.msg);
            sessionStorage.removeItem("token");
            sessionStorage.removeItem("user");
            window.location.reload();
          } else {
            error_message(data.msg)
          }
          this.comment = {}
        }

      }
    },
    mounted: async function () {
      const loading = start();
      let user = sessionStorage.getItem("user");
      if (user) {
        user = JSON.parse(user);
        this.image = user.avatar;
      }
      let blog_list = await this.$axios.$get("/blog/" + this.$route.params.id);
      if (blog_list.code == 0) {
        this.blog = blog_list.data;
        let {data} = await this.$axios.$get("/comment/" + this.$route.params.id);
        this.comments = data;
        close_loading(loading);
      } else {
        await this.$router.push("/")
      }

    }
  }
</script>

<style scoped>

</style>
