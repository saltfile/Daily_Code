package org.example;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class WebD{
    public void DownLoad(String url,String filename) throws MalformedURLException {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(filename));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


class WebFileP extends Thread{
    String url;
    String filename;
    public WebFileP(String url,String filename){
        this.filename = filename;
        this.url = url;
    }



    @Override
    public void run() {
        try {
            new WebD().DownLoad(this.url,this.filename);
            System.out.println("已下载"+filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}




public class FileWeb {
    public static void main(String[] args) {
        WebFileP webFileP = new WebFileP("https://www.baidu.com/img/flexible/logo/pc/result.png", "1.png");
        webFileP.start();


    }

}
