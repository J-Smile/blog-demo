<template>
  <div>
    <bread></bread>
    <div>
      <mavon-editor
        v-model="author.about"
        ref="md"
        style="min-height: 600px"
        @change="change"
      />
      <button @click="submit">提交</button>
    </div>
  </div>
</template>

<script>

  import bread from "../../components/bread";
  import {author, update_author} from "../../api/api";
  import {error_common, success} from "../../utils/message";

  export default {
    name: "About",
    components: {
      bread,
    },
    data() {
      return {
        author: {},
        html: ""
      }
    },
    async created() {
      let {data} = await author();
      this.author = data.data;
    },
    methods: {
      change(value, render) {
        // render 为 markdown 解析后的结果[html]
        this.html = render;
      },
      submit() {
        console.log(this.html)
        this.author.html = this.html;
        update_author(this.author).then(res => {
          success("更新成功")
        }).catch(err => {
          error_common();
        })
      }
    },
    mounted() {

    }

  }
</script>

<style scoped>

</style>
