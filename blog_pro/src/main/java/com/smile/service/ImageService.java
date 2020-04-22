package com.smile.service;

import com.smile.dao.ImageMapper;
import com.smile.domain.Image;
import com.smile.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-01 16:12
 */
@Service
public class ImageService {

    @Resource
    private ImageMapper imageMapper;
    @Autowired
    private RedisUtils redisUtils;

    public List<Image> all() {
        List<Image> all = redisUtils.getLunBo();
        if (all != null) {
            return all;
        }
        all = imageMapper.all();
        redisUtils.saveLunBo(all);
        return all;
    }

    public void editLunBo(Image image) {
        imageMapper.updateByPrimaryKey(image);
        List<Image> all = imageMapper.all();
        redisUtils.saveLunBo(all);
    }

    public Image background() {
        Image backGround = redisUtils.getBackGround();
        if (backGround != null) {
            return backGround;
        }
        backGround = imageMapper.background();
        redisUtils.saveBackGround(backGround);
        return backGround;
    }

    public void editBackground(Image image) {
        imageMapper.updateByPrimaryKey(image);
        redisUtils.saveBackGround(imageMapper.background());
    }
}
