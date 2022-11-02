package org.example;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import sun.security.krb5.Config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

public class HdfsClient {
    FileSystem fs = null;
    FSDataInputStream in = null;
    FSDataOutputStream out = null;
    public void testmkdir() throws URISyntaxException, IOException {
        //节点链接
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
        conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作



        FileSystem fs = null;
        FSDataInputStream in = null;
        FSDataOutputStream out = null;

        fs = FileSystem.get(conf);
        fs.mkdirs(new Path("hdfs://master:9000/testfile"));//创建文件夹
        fs.copyFromLocalFile(new Path("/opt/testfile/f1.txt"), new Path(
                "hdfs://master:9000/testfile/f1.txt"));//上传文件到规定文件夹下
        FileStatus fst = fs.getFileStatus(new Path(
                "hdfs://master:9000/testfile/f1.txt"));
        if (!fst.isDir()) {
            System.out.println("这是个文件");
        }
//        System.out.println("路径：" + fst.getPath());
//        System.out.println("长度：" + fst.getLen());
//        System.out.println("文件修改日期："
//                + new Timestamp(fst.getModificationTime()).toString());
//        System.out.println("上次文件访问日期："
//                + new Timestamp(fst.getAccessTime()).toString());
//        System.out.println("文件备份数：" + fst.getReplication());
//        System.out.println("文件块大小：" + fst.getBlockSize());
//        System.out.println("文件所有者：" + fst.getOwner());
//        System.out.println("文件所在分组：" + fst.getGroup());
//        System.out.println("文件权限：" + fst.getPermission().toString());
//
//        fst = fs.getFileStatus(new Path("hdfs://master:9000/testfile"));
//        if (fst.isDir()) {
//            System.out.println("这是个目录");
//        }
//        System.out.println("目录路径：" + fst.getPath());
//        System.out.println("目录长度：" + fst.getLen());
//        System.out.println("目录修改日期："
//                + new Timestamp(fst.getModificationTime()).toString());
//        System.out.println("上次目录访问日期："
//                + new Timestamp(fst.getAccessTime()).toString());
//        System.out.println("目录备份数：" + fst.getReplication());
//        System.out.println("目录块大小：" + fst.getBlockSize());
//        System.out.println("目录所有者：" + fst.getOwner());
//        System.out.println("目录所在分组：" + fst.getGroup());
//        System.out.println("目录权限：" + fst.getPermission().toString());
//
//
//        in = fs.open(new Path(
//                "hdfs://master:9000/testfile/f1.txt"));
//        in.seek(0);
//        out = fs.create(new Path(
//                "hdfs://master:9000/testfile/f1.txt"), new Progressable() {
//            @Override
//            public void progress() {
//                System.out.println(".");
//            }
//        });
//        IOUtils.copyBytes(in, out, 4096, true);
//        fs.delete(new Path("hdfs://master:9000/testfile"));




    }
    public void filestatus() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
        conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
        // 查看元数据：
        String uri = "hdfs://master:9000/testfile/f1.txt";
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FileStatus fst = fs.getFileStatus(new Path(uri));
        if (!fst.isDir()) {
            System.out.println("这是个文件");
        }
        System.out.println("路径：" + fst.getPath());
        System.out.println("长度：" + fst.getLen());
        System.out.println("文件修改日期："
                + new Timestamp(fst.getModificationTime()).toString());
        System.out.println("上次文件访问日期："
                + new Timestamp(fst.getAccessTime()).toString());
        System.out.println("文件备份数：" + fst.getReplication());
        System.out.println("文件块大小：" + fst.getBlockSize());
        System.out.println("文件所有者：" + fst.getOwner());
        System.out.println("文件所在分组：" + fst.getGroup());
        System.out.println("文件权限：" + fst.getPermission().toString());
        fs.close();
    }





}
