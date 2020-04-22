package com.smile.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author yintianhao
 * @createTime 07 21:07
 * @description 七牛云工具
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "8VCkovoPsHDDl6hmcURjZHjIoG_2c0ede_0jJGAc";
    private static final String SECRET_KEY = "-vNfH9HF6JHOTAmND4iYgfz2JvsoqRhCi5pGoa3G";

    // 要上传的空间名
    private static final String bucketname = "blog-image-cc";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //上传
    public static String upload(File file, String key) throws IOException{
        // 创建上传对象，Zone*代表地区
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            // 调用put方法上传
            String token = auth.uploadToken(bucketname);
            if(StringUtils.isEmpty(token)) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            Response res = uploadManager.put(file,key,token);
            if (res.isOK()){
                return key;
            }
        }catch (QiniuException e) {
            Response r = e.response;
            e.printStackTrace();
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error "+e1.error());
            }
        }
        return null;
    }
}

