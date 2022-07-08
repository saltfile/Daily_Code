package com.example.demo.contoller;

import com.example.demo.entiry.user_Tab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class imgContoller {
    private String filepath = "/home/saltfish/test/";

    @RequestMapping("/load")
    public String login(){
        return  "/upload.html";
    }

    @RequestMapping(value = "/Upload")
    @ResponseBody
    public String Upload(MultipartFile file){

        //存放图片的文件夹
        File filee=new File(filepath);
        if(!filee.exists()){
            filee.mkdirs();
        }
        System.out.println(file.getOriginalFilename());

        String realFileName = file.getOriginalFilename();
        //文件后缀
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        /***************文件处理*********************/
        String newFileName = UUID.randomUUID()+realFileName;
        String newFilePath=filepath+newFileName;
        System.out.println(newFilePath);
        //新文件的路径
        try {
            file.transferTo(new File(newFilePath));
            //将传来的文件写入新建的文件
            System.out.println("上传图片成功进行上传文件测试");
            return "y";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "n";
    }
}
