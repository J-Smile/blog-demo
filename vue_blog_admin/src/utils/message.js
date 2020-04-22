import ElementUI from 'element-ui';

export  const success=(message)=>{
  ElementUI.Message.success(message)
};
export  const error_common=()=>{
  ElementUI.Message.error("网络错误，请稍后再试！")
}
export  const error=(message)=>{
  ElementUI.Message.error(message)
}
