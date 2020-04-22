<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/smile' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{superior}}</el-breadcrumb-item>
      <el-breadcrumb-item>{{name}}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>
  </div>
</template>

<script>
  export default {
    name: "bread",
    data() {
      return {
        name: "",
        superior: ""
      }
    },
    beforeMount() {
      this.name = this.$route.name.split("-")[1];
      this.superior = this.$route.name.split("-")[0];
    }
  }
</script>

<style scoped>

</style>
