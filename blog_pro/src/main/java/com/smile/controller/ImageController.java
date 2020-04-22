package com.smile.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.smile.domain.Image;
import com.smile.service.ImageService;
import com.smile.utils.MultipartToFileUtils;
import com.smile.utils.QiniuCloudUtil;
import com.smile.vo.BaseResult;
import com.smile.vo.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: Smile
 * @description: 图片上传
 * @create: 2020-03-23 19:27
 */

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;


    @PostMapping
    public BaseResult upload(MultipartFile file) {
        try {
            File newFile = MultipartToFileUtils.multipartToFile(file);
            String url = QiniuCloudUtil.upload(newFile, file.getOriginalFilename());
            newFile.delete();
            return BaseResult.success(url);
        } catch (IOException e) {
            return BaseResult.error(CodeMessage.IO_ERROR);
        }
    }

    /**
     * 获取所有轮播图
     * @return
     */
    @GetMapping
    public BaseResult all() {
        List<Image> all = imageService.all();
        return BaseResult.success(all);
    }

    /**
     * 获取背景图片
     * @return
     */
    @GetMapping("bg")
    public BaseResult background() {
        return BaseResult.success(imageService.background());
    }

    @PutMapping
    public BaseResult editLunBo(@RequestBody Image image) {
        imageService.editLunBo(image);
        return BaseResult.success();
    }

    @PutMapping("bg")
    public BaseResult editBackground(@RequestBody Image image) {
        imageService.editBackground(image);
        return BaseResult.success();
    }

    @PutMapping("user")
    public BaseResult editUserImg(@RequestBody Image image) {
        imageService.editBackground(image);
        return BaseResult.success();
    }

}