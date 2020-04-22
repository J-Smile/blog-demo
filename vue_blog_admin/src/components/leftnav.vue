<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path"
           unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff"
           active-text-color="#ffd04b">
    <div class="logobox">
      <img class="logoimg" src="../assets/img/logo.png" alt="">
    </div>
    <el-menu-item index="/smile">
      <i class="el-icon-menu"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span>博客管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/blog"><i class="el-icon-folder"></i>博客中心</el-menu-item>
        <el-menu-item index="/blog_add"><i class="el-icon-edit"></i>添加博客</el-menu-item>
        <el-menu-item index="/sort"><i class="el-icon-tickets"></i>分类管理</el-menu-item>
        <el-menu-item index="/tag"><i class="el-icon-document-remove"></i>标签管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span>网站管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/lunbo"><i class="el-icon-folder"></i>轮播图</el-menu-item>
        <el-menu-item index="/background"><i class="el-icon-edit"></i>背景图片</el-menu-item>
        <el-menu-item index="/author"><i class="el-icon-tickets"></i>信息中心</el-menu-item>
        <el-menu-item index="/about"><i class="el-icon-document-remove"></i>关于我</el-menu-item>
      </el-menu-item-group>
    </el-submenu>

  </el-menu>

</template>
<script>


  export default {
    name: 'leftnav',
    data() {
      return {
        collapsed: false,
      }
    },

    created() {
      // 监听
      this.$root.Bus.$on('toggle', value => {
        this.collapsed = !value
      })
    }
  }
</script>
<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 240px;
    min-height: 400px;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    border: none;
    text-align: left;
  }

  .el-menu-item-group__title {
    padding: 0px;
  }

  .el-menu-bg {
    background-color: #1f2d3d !important;
  }

  .el-menu {
    border: none;
  }

  .logobox {
    height: 40px;
    line-height: 40px;
    color: #9d9d9d;
    font-size: 20px;
    text-align: center;
    padding: 20px 0px;
  }

  .logoimg {
    height: 40px;
  }
</style>
