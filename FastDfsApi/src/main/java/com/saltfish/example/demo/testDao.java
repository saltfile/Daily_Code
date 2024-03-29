package com.saltfish.example.demo;

import com.saltfish.example.annotation.*;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class testDao {

    //获取文件信息
    @GetFileInfo(GroupParam = "aaa",RemoteParam = "bbb")
    public FileInfo GetInfo(String aaa,String bbb){
        return null;
    }
    //获取文件元数据
    @GetFileMetaArr(GroupParam = "aaa",RemoteParam = "bbb")
    public NameValuePair[] GetMetaArr(String aaa,String bbb){return null;}
    //上传文件
    @UploadFileAddr(FilePath = "path")
    public String uploadFile(String path){return null;}
    //下载文件
    @DFSDownLoad(DFSPath = "aaa",DownPath = "bbb")
    public boolean downxxx(String aaa,String bbb){return false;}
    //删除文件
    @DFSDelete(IpAddr = "aaa")
    public boolean Delxxx(String aaa){return false;}
    //更换文件
    @DFSUpdate(Ipaddr = "aaa",LocalPath = "bbb")
    public String Updataexax(String aaa,String bbb){return "";}
}
