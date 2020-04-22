<template>
  <div>
    <bread></bread>

    <!--dialog-->
    <el-dialog :title="title" :model="sort" :visible.sync="dialog">
      <el-form :model="sort">
        <el-form-item label="名  称：" :label-width="formLabelWidth">
          <el-input v-model="sort.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="this.title.startsWith('修')" label="启用" :label-width="formLabelWidth">
          <el-switch
            style="display: block"
            v-model="sort.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="开启"
            inactive-text="关闭">
          </el-switch>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button type="primary" @click="saveSort()">确 定</el-button>
      </div>
    </el-dialog>

    <el-table
      :data="sorts"
      style="width: 100%">
      <el-table-column
        label="ID"
        type="index"
        width="200">
      </el-table-column>
      <el-table-column
        prop="content"
        label="分类"
        width="300">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="300">
        <template slot-scope="scope">
          <div v-if="scope.row.status">
            <el-tag type="success">开启</el-tag>
          </div>
          <div v-else>
            <el-tag type="danger">关闭</el-tag>
          </div>
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
            @click="handleDelete(scope.row.sid)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--添加按钮-->
    <div class="button_add">
      <el-button style="width: 75px;height: 75px" type="success" icon="el-icon-plus" @click="openDialog()"
                 circle></el-button>
    </div>
  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {edit, save, name} from "../../api/api";

  export default {
    name: "Sort",
    components: {
      bread
    },
    data() {
      return {
        sorts: [],
        sort: {},
        title: "",
        addUpdate: true,
        formLabelWidth: '120px',
        dialog: false,
      }
    },
    methods: {
      async openDialog(row) {
        if (row) {
          this.title = "修 改 标 签";
          let {data} = await edit(name.sort, row.sid);
          this.sort = data.data;
        } else {
          this.title = "新 增 标 签";
          this.sort = {}
        }
        this.dialog = true;
      },
      async initSort() {
        let {data} = await this.$axios.get("/sort");
        this.sorts = data.data

      },
      async handleDelete(sid) {
        let {data} = await this.$axios.delete("/sort/" + sid);
        if (data.code === 0) {
          this.$message({
            message: '删除成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        this.initSort()
      },
      async saveSort() {
        let {data} = await save(name.sort, this.sort);
        if (data.code === 0) {
          this.$message({
            message: '添加成功！',
            type: 'success'
          });
        } else {
          this.$message.error(data.msg);
        }
        this.dialog = false;
        this.initSort()
      },

    },
    created() {
      this.initSort()
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
