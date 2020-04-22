<template>
  <div>
    <bread></bread>

    <!--表格-->
    <el-table
      :data="data"
      style="width: 100%">
      <el-table-column
        label="ID"
        type="index"
        width="100">
      </el-table-column>
      <el-table-column
        prop="title"
        label="标题"
        width="150">
      </el-table-column>
      <el-table-column
        prop="clickCount"
        label="点击数"
        width="150">
      </el-table-column>
      <el-table-column
        label="图片"
        width="150">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.image"></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        width="150">
        <template slot-scope="scope">
          <div v-if="scope.row.status">
            <el-tag type="success">开启</el-tag>
          </div>
          <div v-else>
            <el-tag type="danger">关闭</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="author"
        label="作者"
        width="150">
      </el-table-column>
      <el-table-column
        label="是否发布"
        width="150">
        <template slot-scope="scope">
          <div v-if="scope.row.isPublish">
            <el-tag type="success">发布</el-tag>
          </div>
          <div v-else>
            <el-tag type="danger">未发布</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-video-camera" circle @click="show(scope.row)"></el-button>
          <el-button type="primary" icon="el-icon-edit" circle @click="edit(scope.row)"></el-button>
          <el-button type="primary" icon="el-icon-delete" circle @click="del_blog(scope.row)"></el-button>

          <el-button v-if="scope.row.level" type="primary" icon="el-icon-star-on" circle
                     @click="recommend(scope.row)"></el-button>
          <el-button v-else type="primary" icon="el-icon-star-off" circle @click="recommend(scope.row)"></el-button>

        </template>
      </el-table-column>
    </el-table>


    <pagination v-bind:child-msg="pageparm" @callFather="callFather"></pagination>

    <el-dialog title="文章" :visible.sync="dialog" width="100%">
      <article v-html="article"></article>
      <!--      <div v-html="article"></div>-->
    </el-dialog>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import pagination from '../../components/Pagination'
  import {find_blog, del, name, recommend} from '../../api/api';

  export default {
    name: "Blog",
    components: {
      bread,
      pagination
    },
    data() {
      return {
        dialog: false,
        data: [],
        formInline: {
          pageNum: 1,
          pageSize: 4,
        },
        pageparm: {
          currentPage: 1,
          pageSize: 4,
          total: 10
        },
        article: ""
      }
    },
    created() {
      this.init();
    },
    methods: {
      // 推荐 修改
      async recommend(row) {
        let {data} = await recommend(row.bid);
        if (data.code === 0) {
          if (data.data) {
            this.$message({
              message: '推荐成功！',
              type: 'success'
            });
          } else {
            this.$message({
              message: '取消推荐！',
              type: 'success'
            });
          }
        } else {
          this.$message.error(data.msg);
        }
        this.init();
      },
      async del_blog(row) {
        let {data} = await del(name.blog, row.bid);
        if (data.code === 0) {
          this.$message({
            message: '博客删除成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        await this.init();
      },
      edit(row) {
        this.$router.push("/blog_edit/" + row.bid)
      },
      show(row) {
        this.article = row.html;
        this.dialog = true;
      },
      callFather(parm) {
        this.formInline.pageNum = parm.currentPage;
        this.formInline.pageSize = parm.pageSize;
        this.init();
      },
      async init() {
        let {data} = await find_blog(this.formInline.pageNum, this.formInline.pageSize);
        this.data = data.data.list;
        this.pageparm.currentPage = this.formInline.pageNum;
        this.pageparm.pageSize = this.formInline.pageSize;
        this.pageparm.total = data.data.total
      }
    }
  }
</script>

<style scoped>

</style>
