<template>
  <div align="center">
    <el-image style=" width:100%; padding-top:3%" :src="image"></el-image>
    <div style=" height: 100%; width: 70%;display: flex;flex-direction: row;padding-top: 3%">
      <div style="flex: 1;height: 100%">
        <el-avatar shape="square" style="height: 90px;width: 90px"
                   :src="user_img">
        </el-avatar>
      </div>
      <div style="flex: 5">
        <el-input
          type="textarea"
          placeholder="请输入内容"
          v-model="comment.text"
          maxlength="300"
          show-word-limit :rows="4">
        </el-input>
        <div align="right" style="padding-top: 1%">
          <el-button type="info" @click="cancle_message()">取消评论</el-button>
          <el-button type="success_message" @click="save_message()">发送评论</el-button>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div style=" height: 100%; width: 70%;display: flex;flex-direction: row;padding-top: 3%">
      {{messages}}
    </div>

  </div>
</template>

<script>
  import {success_message, state, error_message} from "../../utils/message";

  export default {
    name: "index",
    layout: 'headfoot',
    data() {
      return {
        comment: {
          text: '',
        },
        messages: [],
        image: "",
        user_img:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      }
    },
    async asyncData({$axios}) {
      let {data} = await $axios.get("/comment/message");
      let image = await $axios.$get("/image/bg");
      return {
        messages: data,
        image: image.data.image
      }
    },
    methods: {
      cancle_message() {
        this.comment.text = ""
      },
      async save_message() {
        if (this.comment.text.length > 0) {
          let res = await this.$axios.$post("/comment/message", this.comment);
          if (res.code == 0) {
            success_message(state.success_add);
          } else if (res.code = 1100) {
            error_message(res.msg);
            sessionStorage.removeItem("token");
            sessionStorage.removeItem("user");
          } else {
            error_message(data.msg)
          }
          this.comment = {}
          window.location.reload();
        }
      }
    },
    mounted: async function () {
      let user = sessionStorage.getItem("user");
      if (user) {
        user = JSON.parse(user);
        this.user_img = user.avatar;
      }
    }
  }
</script>

<style scoped>

</style>
