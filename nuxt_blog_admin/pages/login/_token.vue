<template>
  <div align="center" class="img" style="padding-top: 10%">
    <el-card class="box-card" style="width: 30%">
      <div class="text item">
        <h2>登录</h2>
        <el-divider></el-divider>
        <el-image @click="login()" style="width: 100px; height: 100px"
                  src="http://q7h7jg5mq.bkt.clouddn.com/mayun.jpg"></el-image>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {error_message, error_login, state, success_message} from "../../utils/message";

  export default {
    async mounted() {
      const token = this.$route.params.token;
      if (token != null) {
        sessionStorage.setItem("token", token);
        let {data} = await this.$axios.$get("/user");
        if (data != null) {
          success_message(state.success_login);
          sessionStorage.setItem("user", JSON.stringify(data));
        } else {
          error_message(error_login());
        }
        window.location.href = "/"
      }
    },
    methods: {
      async login() {
        await this.$axios.$get("/auth/render").then(res => {
          window.location.href = res
        });

      }
    }
  }
</script>

<style scoped>
  .img {
    background-size: 100% 100%;
    /*background: url('') center center no-repeat;*/
    height: 100%;
    position: fixed;
    width: 100%;

  }
</style>
