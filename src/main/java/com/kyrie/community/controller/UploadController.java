package com.kyrie.community.controller;

import com.kyrie.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author kyrie
 * @date 2019/10/11 - 11:23
 */
@Controller
public class UploadController {
    // 文件上传路径
    public static final String UPLOAD_PATH = "/upload/";

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile imageFile = multipartRequest.getFile("editormd-image-file");

        // 获取上传的原始文件名
        String fileName = imageFile.getOriginalFilename();

        // 获取文件的后缀名
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File file = new File(filePath);

        // 如果文件夹不存在
        if (!file.exists()) {
            file.mkdir();
        }

        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            //写入文件
            imageFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * getScheme() : 获取协议名称，如http或https
         * getServerName() : 获取服务器名称/ip/域名
         * getServerPort() : 获取服务器端口
         */
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //返回结果
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传成功");
        fileDTO.setUrl(serverPath + UPLOAD_PATH + file.getName());
        return fileDTO;
    }
}
