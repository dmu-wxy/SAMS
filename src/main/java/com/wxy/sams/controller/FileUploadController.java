package com.wxy.sams.controller;

import com.wxy.sams.model.RespBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upload")
    public RespBean upload(MultipartFile multipartFile, HttpServletRequest request){
        String format = simpleDateFormat.format(new Date());
        String realPath = request.getServletContext().getRealPath("/datas") + format;
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String oldName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            multipartFile.transferTo(new File(folder,newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/datas/" + format + newName;
            return RespBean.ok(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespBean.error("未知错误");
    }
}
