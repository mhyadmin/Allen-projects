package com.allen.upload.controller;

import com.allen.upload.base.Result;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @program: Allen-projects
 * @description: AbsolutePathController
 * @author: allen小哥
 * @Date: 2020-01-01 11:38
 **/
@RestController
@RequestMapping("/absolute")
@Api(tags = "上传绝对路径接口")
@Slf4j
public class AbsolutePathController {

    @PostMapping("uploadToProject")
    @ApiOperation(value = "文件上传")
    public Result uploadToProject(MultipartFile file , HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取项目下static的路径
        File staticFilePath = ResourceUtils.getFile("classpath:static");

        File targetFile2 = new File(staticFilePath+File.separator+"images");
        if (!targetFile2.exists()&& !targetFile2.isDirectory()){
            targetFile2.mkdir();
        }
        File targetFile = new File(targetFile2,file.getOriginalFilename());

        log.info("targetFile=="+targetFile);
        //上传到项目static路径下
        file.transferTo(targetFile);
        // 绝对路径显示  images/file.getOriginalFilename()
        return Result.ok("上传成功").setData("http://localhost:9001/images/"+file.getOriginalFilename());
    }


}
