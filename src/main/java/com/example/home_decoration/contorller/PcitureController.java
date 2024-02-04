package com.example.home_decoration.contorller;

import com.example.home_decoration.model.Result;
import com.example.home_decoration.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/picture")
public class PcitureController {
    @Autowired
    PictureServiceImpl pictureService;

    /**
     * 项目图片上传
     * @param file
     * @param o_id
     * @return
     */
    @PostMapping("/projectupload")
    public Result<String> userUpload(MultipartFile file, @RequestParam("o_id") Integer o_id) {
        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setResultFailed("上传失败");
            return result;
        }
        //获取文件名和后缀
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + "-" + fileName;
        //上传路径
        String filePath = "/home/picture/engineering/";
        String path = filePath + newFileName;
        File newFile = new File(path);
        //如果文件夹不存在，创建文件夹路径
        if (!newFile.getParentFile().exists()) {
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = "www.zhikao168.com:8083/images/engineering/" + newFileName;
        result = pictureService.projectUpload(url, o_id);
        return result;
    }

    @GetMapping("/project")
    public Result<String> getProjectUrl(@RequestParam("o_id") Integer o_id) {
        return pictureService.getProjectUrl(o_id);
    }
}
