<template>
  <div>
    <bread></bread>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="标题" style="width: 100%">
        <el-input v-model="blog.title" placeholder="标题" style="width: 900px"></el-input>
        <el-button type="primary" @click="onSubmit">发布</el-button>
      </el-form-item>


        <mavon-editor
          v-model="blog.content"
          ref="md"
          @change="change"
          style="min-height: 600px"/>


      <el-dialog title="发布" :visible.sync="dialog">
        <el-form :model="blog">
          <el-form-item label="图片" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:10010/image"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="blog.image" width="50%" :src="blog.image" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="原创" :label-width="formLabelWidth">
            <el-switch
              v-model="blog.isOriginal"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>
          <el-form-item v-if="!blog.isOriginal" label="出处" :label-width="formLabelWidth">
            <el-input v-model="blog.articlesPart" placeholder="请写下出处"></el-input>
          </el-form-item>
          <el-form-item label="分类" :label-width="formLabelWidth">
            <el-select v-model="blog.sortId" placeholder="请选择分类">
              <el-option
                v-for="sort in sorts"
                :key="sort.sid"
                :label="sort.content"
                :value="sort.sid">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标签" :label-width="formLabelWidth">
            <el-checkbox-group v-model="blog.tids">
              <el-checkbox v-for="(tag,index) in tags" :key="index" :label="tag.tid">{{tag.content}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="推荐" :label-width="formLabelWidth">
            <el-switch
              v-model="blog.level"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialog = false">取 消</el-button>
          <el-button type="primary" @click="edit_blog()">确 定</el-button>
        </div>
      </el-dialog>

    </el-form>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {edit_blog, blog_id, getSortStatus, getTagStatus} from "../../api/api";
  import {quillEditor} from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'

  export default {
    name: "blog_edit",
    data() {
      return {
        blog: {},
        sorts: [],
        tags: [],
        formLabelWidth: "180px",
        dialog:false,
        html:''
      }
    },
    components: {
      bread,
      quillEditor
    },
    async created() {
      let {data} = await blog_id(this.$route.params.id);
      this.blog = data.data;
      let res1 = await getSortStatus();
      this.sorts = res1.data.data;
      let res = await getTagStatus();
      this.tags = res.data.data;

    },
    methods: {
      change(value, render) {
        // render 为 markdown 解析后的结果[html]
        this.html = render;
      },
      async edit_blog() {
        this.blog.html = this.html;
        let {data} = await edit_blog(this.blog);
        if (data.code === 0) {
          this.$message({
            message: '博客修改成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        this.dialog = false;
      },
      onSubmit() {
        this.dialog = true;
      },
      handleAvatarSuccess(res, file) {
        this.blog.image = "http://q7h7jg5mq.bkt.clouddn.com/" + res.data;
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

</style>
