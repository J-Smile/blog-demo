import {Loading} from "element-ui";

export const start = () => {
  return Loading.service({fullscreen: true});
}
export const close_loading = (params) => {
  params.close();
}
