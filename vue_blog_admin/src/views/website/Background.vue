<template>
  <!--背景图片设置-->
  <div>
    <bread></bread>
    <el-image :src="img.image" @click="edit()"></el-image>

    <el-dialog title="修改轮播图" width="100%" :visible.sync="dialog" align="center">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:10010/image"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="img.image" :src="img.image" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {editBackground, background} from "../../api/api";

  export default {
    name: "Background",
    components: {
      bread
    },
    data() {
      return {
        imageUrl: '',
        dialog: false,
        img: {},
      }
    },
    created() {
      this.initBG();
    },
    methods: {
      edit() {
        this.dialog = true;
      },
      initBG() {
        background().then(res => {
          this.img = res.data.data;
        }).catch(() => {
          this.$message.error('网络出问题，请稍后再试！');
        })
      },
      handleAvatarSuccess(res, file) {
        this.img.image = "http://q7h7jg5mq.bkt.clouddn.com/" + res.data;
        this.dialog = false;
        // 上传成功 修改 数据库 轮播图数据
        editBackground(this.img).then(res => {
          if (res.data.code === 0) {
            this.$message({
              message: '轮播图修改成功',
              type: 'success'
            });
            this.initBG();
          } else {
            this.$message.error('网络出问题，请稍后再试！');
          }
        }).catch(() => {
          this.$message.error('网络出问题，请稍后再试！');
        })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 PNG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>

<style scoped>

</style>
