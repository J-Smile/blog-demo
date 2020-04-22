import ElementUI from 'element-ui';

export const state = {
  error_common:"网络错误，请稍后再试！",
  success_login:"登录成功！",
  error_login:"登录失败！",
  success_comment:"评论成功",
  success_add:"添加成功"

}
export const success_message = (message) => {
  ElementUI.Message.success(message)
};
export const success_update = () => {
  ElementUI.Message.success("更新成功")
};
export const error_login = () => {
  ElementUI.Message.error("请登录后再试试！")
}
export const error_message = (message) => {
  ElementUI.Message.error(message)
}
