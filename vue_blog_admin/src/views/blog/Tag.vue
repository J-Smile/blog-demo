<template>
  <div>
    <bread></bread>
    <!--表格-->
    <el-table
      :data="tags"
      style="width: 100%">
      <el-table-column
        label="ID"
        type="index"
        width="150">
      </el-table-column>
      <el-table-column
        prop="content"
        label="标签"
        width="300">
      </el-table-column>

      <el-table-column
        prop="status"
        label="状态"
        width="300">
        <template slot-scope="scope">
          <div v-if="scope.row.status"><el-tag type="success">开启</el-tag></div>
          <div v-else><el-tag type="danger">关闭</el-tag></div>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="openDialog(scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.tid)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--dialog-->
    <el-dialog :title="title" :visible.sync="dialog">
      <el-form :model="tag">
        <el-form-item label="名  称" :label-width="formLabelWidth">
          <el-input v-model="tag.content" autocomplete="off" placeholder="请填写标签"></el-input>
        </el-form-item>
        <el-form-item v-if="this.title.startsWith('修')" label="启用" :label-width="formLabelWidth">
          <el-switch
            style="display: block"
            v-model="tag.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="开启"
            inactive-text="关闭">
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button type="primary" @click="saveTag()">确 定</el-button>
      </div>
    </el-dialog>

    <!--添加按钮-->
    <div class="button_add">
      <el-button style="width: 75px;height: 75px" type="success" icon="el-icon-plus" @click="openDialog()"
                 circle></el-button>
    </div>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {edit, save,name} from "../../api/api";

  export default {
    name: "Tag",
    components: {
      bread
    },
    data() {
      return {
        tags: [],
        tag: {},
        formLabelWidth: '120px',
        dialog: false,
        title: '',
      }
    },
    methods: {

      async openDialog(row) {
        if (row) {
          this.title = "修 改 标 签";
          let {data} = await edit(name.tag, row.tid);
          this.tag = data.data;
        } else {
          this.title = "新 增 标 签";
          this.tag = {}
        }
        this.dialog = true;
      },
      async initTag() {
        let {data} = await this.$axios.get("/tag")
        this.tags = data.data
      },
      async handleDelete(tid) {
        let {data} = await this.$axios.delete("/tag/" + tid);
        if (data.code === 0) {
          this.$message({
            message: '删除成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        this.initTag()
      },

      async saveTag() {
        let {data}= await save(name.tag,this.tag);
        if (data.code === 0) {
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        this.dialog = false;
        this.initTag()
      }
    },
    created() {
      this.initTag()
    }
  }
</script>

<style scoped>

  .button_add {
    position: fixed;
    top: 85%;
    left: 92%;
    height: 598px;
    width: 230px;
    z-index: 1040;
  }

</style>
