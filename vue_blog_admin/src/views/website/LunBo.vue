<template>
  <div>
    <bread></bread>
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="(item,i) in img" :key="i">
        <el-image :src="item.image" @click="edit(item)"></el-image>
      </el-carousel-item>
    </el-carousel>


    <el-dialog title="修改轮播图" :visible.sync="dialog" align="center">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:10010/image"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="editImage.image" :src="editImage.image" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {lunbo, editLunBo} from "../../api/api";

  export default {
    components: {
      bread
    },
    data() {
      return {
        imageUrl: '',
        dialog: false,
        editImage: {},
        img: [],
      }
    },
    created() {
      this.initLunBo();
    },
    methods: {
      edit(image) {
        this.dialog = true;
        this.editImage = image;
      },
      initLunBo() {
        lunbo().then(res => {
          this.img = res.data.data;
        }).catch(() => {
          this.$message.error('网络出问题，请稍后再试！');
        })
      },
      handleAvatarSuccess(res, file) {
        this.editImage.image = "http://q7h7jg5mq.bkt.clouddn.com/" + res.data;
        this.dialog = false;
        // 上传成功 修改 数据库 轮播图数据
        editLunBo(this.editImage).then(res => {
          console.log(res.data)
          if (res.data.code === 0) {
            this.$message({
              message: '轮播图修改成功',
              type: 'success'
            });
            this.initLunBo();
          } else {
            this.$message.error('网络出问题，请稍后再试！');
          }
        }).catch(() => {
          this.$message.error('网络出问题，请稍后再试！');
        })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
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
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }


  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

