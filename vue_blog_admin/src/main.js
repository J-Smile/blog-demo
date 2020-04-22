// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
// 引入element UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App';
// 引入路由
import router from './router';
// 引入状态管理
import store from './vuex/store';
// 引入icon
import './assets/icon/iconfont.css'

// 引入echarts
import echarts from 'echarts'

Vue.prototype.$echarts = echarts
import axios from 'axios';

axios.defaults.baseURL = "http://localhost:10010"
// axios.defaults.baseURL = "http://blog-api.jpwdesign.design"
Vue.prototype.$axios = axios;

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor);

Vue.config.productionTip = false;

// 使用element UI
Vue.use(ElementUI);


axios.interceptors.request.use(req => {
  let token = sessionStorage.getItem("token");
  if (token) {
    req.headers.authorization = token;
  }
  return req;
});

// 路由拦截器
router.beforeEach((to, from, next) => {
  if (to.path !== "/") {
   if (sessionStorage.getItem("token")!=null){
     next();
   }else {
     ElementUI.Message.warning("请登录后进行访问！")
     next("/")
   }
  }else {
    next();
  }

});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store, //使用store vuex状态管理
  components: {App},
  template: '<App/>',
  data: {
    // 空的实例放到根组件下，所有的子组件都能调用
    Bus: new Vue()
  }

})
