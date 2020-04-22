import axios from 'axios';

export const name = {
  blog: "blog",
  sort: "sort",
  tag: "tag",
};

/**
 * 删除
 */
export const del = (path, id) => {
  return axios.delete(path + "/" + id)
};
/**
 * 修改前
 */
//修改前
export const edit = (path, id) => {
  return axios.get(path + "/" + id)
};
/**
 * 存 对象
 */
export const save = (path, params) => {
  return axios.post("/" + path, params)
};

/**
 * 获取所有分类（开启）
 */
export const getSortStatus = () => {
  return axios.get("/sort/status")
};
/**
 * 获取所有标签（开启）
 */
export const getTagStatus = () => {
  return axios.get("/tag/status")
};
/**
 * 添加博客
 */
export const add_blog = (params) => {
  return axios.post("/blog", params)
};
/**
 * 查找博客
 */
export const find_blog = (page, size) => {
  return axios.get("/blog/" + page + "/" + size)
};
/**
 * 查找博客(id)
 */
export const blog_id = (id) => {
  return axios.get("/blog/" + id)
};

/**
 * 修改博客
 */
export const edit_blog = (params) => {
  return axios.put("/blog", params)
};
/**
 * 推荐博客
 */
export const recommend = (params) => {
  return axios.post("/blog/" + params)
};
//轮播图
export const lunbo = () => {
  return axios.get("/image");
};

//修改轮播图
export const editLunBo = (params) => {
  return axios.put("/image", params)
};

//背景图片
export const background = () => {
  return axios.get("/image/bg");
};

//修改背景图片
export const editBackground = (params) => {
  return axios.put("/image/bg", params)
};

// 登录接口
export const login = (params) => {
  return axios.post("/user/login", params)
};

//查询作者信息
export const author = () => {
  return axios.get("/author")
};
//查询号码信息
export const number = () => {
  return axios.get("/num")
};
//修改号码信息
export const editNumber = (params) => {
  return axios.put("/num", params)
};
//修改作者头像
export const editUserImg = (params) => {
  return axios.put("/image/user", params)
};

//更新作者信息
export const update_author = (params) => {
  return axios.put("/author", params)
};
//更新号码信息
export const update_number = (params) => {
  return axios.put("/num", params)
};
//首页获取消息
export const index = () => {
  return axios.get("/smile", )
};
//首页获取流量
export const record = () => {
  return axios.get("/record/get")
};
//首页获取分类
export const sort = () => {
  return axios.get("/sort")
};
