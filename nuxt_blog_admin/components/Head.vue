<template>
  <div style="background-color: #545c64">
    <el-menu class="el-menu-demo" id="head" mode="horizontal"
             background-color="#545c64"
             text-color="#fff" style="">
      <el-menu-item index="1">
        <div style="padding-right: 60%;font-weight: bolder;font-size: 30px">Coder博客</div>
      </el-menu-item>
      <span style="padding-left: 50%"></span>
      <el-menu-item index="2"><a href="/">首页</a></el-menu-item>
      <el-menu-item index="3"><a href="/about">关于我</a></el-menu-item>
      <el-menu-item index="4"><a href="/messageBoard">留言板</a></el-menu-item>
      <el-submenu index="5">
        <template slot="title">
          <el-avatar :src="image_url"></el-avatar>
        </template>
        <div v-if="user.avatar==''"><a href="/login">
          <el-menu-item>登录</el-menu-item>
        </a></div>
        <div v-else>
          <el-menu-item index="5-1" @click="user_center()">个人中心</el-menu-item>
          <el-menu-item index="5-2" @click="out()">退出登录</el-menu-item>
        </div>
      </el-submenu>
    </el-menu>

    <!--dialog-->
    <el-drawer
      :visible.sync="drawer"
      :with-header="false">
      <el-tabs type="border-card" tab-position="left" style="width: 100%;height: 100%">
        <el-tab-pane icon="el-icon-user" label="个人中心">
          <el-form :inline="true" :model="user" class="demo-form-inline">
            <el-form-item label="头像">
              <el-avatar :size="50" :src="user.avatar"></el-avatar>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="user.nickname" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio v-model="user.gender" :label="0" border>男</el-radio>
              <el-radio v-model="user.gender" :label="1" border>女</el-radio>
            </el-form-item>
            <el-form-item label="生日">
              <el-date-picker
                v-model="user.birthday"
                type="date"
                format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="user.email" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="QQ">
              <el-input v-model="user.qq" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="职业">
              <el-input v-model="user.work" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="简介">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="user.userDesc">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="editUser()">查询</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="配置管理">配置管理</el-tab-pane>
        <el-tab-pane label="角色管理">角色管理</el-tab-pane>
        <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
      </el-tabs>
    </el-drawer>

  </div>
</template>

<script>
  import {state, error_message, success_update} from "../utils/message";

  export default {
    data() {
      return {
        user: {
          avatar: ""
        },
        drawer: false,
        image_url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      }
    },
    methods: {
      async editUser() {
        let {code} = await this.$axios.$put("/user", this.user);
        if (code != 0) {
          error_message(state.error_common);
        } else {
          sessionStorage.setItem("user", JSON.stringify(this.user))
          success_update();
        }
      },
      out() {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("user");
        window.location.reload();
        this.image_url = "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      },
      user_center() {
        this.drawer = true;
      }
    },
    async mounted() {
      let user = sessionStorage.getItem("user");
      if (user != null) {
        user = JSON.parse(user);
        this.user = user;
        this.image_url = user.avatar;
      }

    }
  }
</script>

<style scoped>
  #head {
    width: 100%;
    z-index: 10;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    flex-direction: row
  }
</style>
