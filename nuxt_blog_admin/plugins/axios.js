//第一次发送ajax的时候，都会自动的来这里执行onRequest方法
export default function ({ $axios, redirect }) {
  $axios.onRequest(config => {
    //前端前置拦截器，在发起请求的时候，获取token，并放到请求头中
    let token = sessionStorage.getItem("token");
    if(token){//如果token不为空
      config.headers.authorization = token
    }
    return config
  })

  // ajax请求错误的时候执行error方法
  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status)
    if (code === 400) {
      redirect('/400')
    }
  })
}
