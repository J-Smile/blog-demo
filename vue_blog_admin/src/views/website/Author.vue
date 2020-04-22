<template>
  <div align="center">
    <bread></bread>
    <div style="display: flex;flex-direction: row;height: 100%;">
      <div style="flex: 1;">
        <el-card class="box-card" style="width: 80%">
          <div slot="header" class="clearfix" align="center">
            <h1>作者信息</h1>
          </div>
          <el-form :inline="true" :model="author" align="center" class="demo-form-inline">

            <img v-if="author.image" style="width: 30%" :src="author.image" class="avatar"> <br>
            <el-form-item label="姓名">
              <el-input v-model="author.name" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="工作">
              <el-input v-model="author.work" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="author.userDescribe" type="textarea" label="" placeholder=""></el-input>
            </el-form-item>
            <div>
              <el-button type="primary" @click="update_author()">更新信息</el-button>
            </div>

          </el-form>
        </el-card>
      </div>
      <div style="flex: 1;">
        <el-card class="box-card" style="width: 80%">
          <div slot="header" class="clearfix" align="center">
            <h1>号码信息</h1>
          </div>
          <el-form :inline="true" :model="num" align="center" class="demo-form-inline">
            <el-form-item label="QQ">
              <el-input v-model="num.qq" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="微信">
              <el-input v-model="num.wx" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="num.mail" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="GitHub">
              <el-input v-model="num.github" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="Gitee">
              <el-input v-model="num.gitee" placeholder=""></el-input>
            </el-form-item>
            <div>
              <el-button type="primary" @click="update_number()">更新信息</el-button>
            </div>
          </el-form>
        </el-card>
      </div>

    </div>


  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {author, update_author, number, update_number} from "../../api/api";
  import {success, error_common} from "../../utils/message";

  export default {
    name: "Author",
    components: {
      bread
    },
    data() {
      return {
        author: {},
        num: {}
      }
    },
    async created() {
      let {data} = await author();
      let res = await number();

      this.author = data.data;
      this.num = res.data.data;
      console.log(this.num)
    },
    methods: {
      update_number() {
        update_number(this.num).then(res => {
          success("更新成功")
        }).catch(err => {
          error_common();
        })
      },
      update_author() {
        update_author(this.author).then(res => {
          success("更新成功")
        }).catch(err => {
          error_common();
        })
      },
      // handleAvatarSuccess(res, file) {
      //   this.img.image = "http://q7h7jg5mq.bkt.clouddn.com/" + res.data;
      //   this.dialog = false;
      //   // 上传成功 修改 数据库 轮播图数据
      //   editUserImg(this.img).then(res => {
      //     if (res.data.code === 0) {
      //       this.$message({
      //         message: '头像修改成功',
      //         type: 'success'
      //       });
      //       this.initBG();
      //     } else {
      //       this.$message.error('网络出问题，请稍后再试！');
      //     }
      //   }).catch(() => {
      //     this.$message.error('网络出问题，请稍后再试！');
      //   })
      // },
      // beforeAvatarUpload(file) {
      //   const isJPG = file.type === 'image/JPG';
      //   const isLt2M = file.size / 1024 / 1024 < 2;
      //   if (!isJPG) {
      //     this.$message.error('上传头像图片只能是 PNG 格式!');
      //   }
      //   if (!isLt2M) {
      //     this.$message.error('上传头像图片大小不能超过 2MB!');
      //   }
      //   return isJPG && isLt2M;
      // }
    }
  }
</script>

<style scoped>

</style>
