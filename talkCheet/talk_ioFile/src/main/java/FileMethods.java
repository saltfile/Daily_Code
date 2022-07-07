import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class FileMethods {
    static Map<String,String> table = new HashMap<>();
    static Map<String, ConcurrentHashMap<String,String>> userCache = new ConcurrentHashMap<>();
    static Map<String,ConcurrentHashMap<String,String>> firendCache = new ConcurrentHashMap<>();

    static String path = null;
    static String userfile = null;
    static String firendfile = null;
    public static void InitFilePath()
    {
        String user = "/talk_ioFile/src/main/java/user";
        String firend = "/talk_ioFile/src/main/java/firend";
        String tablepath = "/talk_ioFile/src/main/java";
        try(InputStream content = FileMethods.class.getResourceAsStream("/config.yaml"))
        {
            Yaml yaml = new Yaml(new Constructor(enitry.class));
            Iterable<Object> its = yaml.loadAll(content);
            List<enitry> userEntityList = new ArrayList<>();
            for(Object it : its)
            {
                userEntityList.add((enitry) it);
            }
            if(userEntityList.size() > 0)
            {
                path = userEntityList.get(0).getProjectpath()+tablepath+"/"+"cachetable";
                userfile = userEntityList.get(0).getProjectpath()+user;
                firendfile = userEntityList.get(0).getProjectpath()+firend;
            }
            else
            {
                throw new Exception("请完成config.yml中的项目路径配置");
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
    }

    /**
     * TODO:读取用户文件更新到缓存
     */
    public static ArrayList<String> GetCacheTable(){
        ArrayList<String> res = new ArrayList<>();
        File f = new File(path);
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        ){
            String str2 = "";
            while ((str2 = reader.readLine())!=null){
                res.add(str2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    //先获取user的信息
    public static ConcurrentHashMap<String,String> GetuserArr(String str){
        ConcurrentHashMap<String,String> res = new ConcurrentHashMap<>();
        String users = userfile+"/"+str+".txt";
        File f = new File(users);
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        ){
            String str2 = "";
            while ((str2 = reader.readLine())!=null){
                res.put(str2,str2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    //在获取user 的firend列表
    public static ConcurrentHashMap<String,String> GetfirendArr(String str){
        ConcurrentHashMap<String,String> res = new ConcurrentHashMap<>();
        File f = new File(firendfile+"/"+str+".txt");
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        ){
            String str2 = "";
            while ((str2 = reader.readLine())!=null){
                res.put(str2,str2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    //程序开启时读取配置文件将文件信息加载进内存中


    //TODO:后面要完成文件数据清除再载入的步骤



    //缓存清除
    private static void FileClear(){
        for (String k:userCache.keySet()){
            FileUserClear(k);
        }
        for (String k:firendCache.keySet()){
            FileFirendClear(k);
        }
    }

    private static void FileUserClear(String name){
        String path = userfile+"/"+name+".txt";
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void  FileFirendClear(String name){
        String path = firendfile+"/"+name+".txt";
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void FileWrite(String path,ConcurrentHashMap<String,String> arr){
        File file = new File(path);
        try (
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            ){
            for(String i:arr.keySet())
            {
                writer.write(i+"\n");
                writer.flush();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void FileTableLoad()
    {
        ConcurrentHashMap<String,String> tables = new ConcurrentHashMap<>();
        for (String k:userCache.keySet())
        {
            tables.put(k,k);
        }
        FileWrite(path,tables);
    }

    private static void FileUserLoad(){
        for (String k:userCache.keySet())
        {
            String path = userfile+"/"+k+".txt";
            FileWrite(path,userCache.get(k));
        }
    }

    private static void FileFirendLoad(){
        for(String k:firendCache.keySet())
        {
            String path = firendfile+"/"+k+".txt";
            FileWrite(path,firendCache.get(k));
        }
    }

    //缓存初始化
    public static void CacheInit(){
        ArrayList<String> cache = GetCacheTable();
        for(String i:cache)
        {
            table.put(i,i);
            userCache.put(i,GetuserArr(i));
            firendCache.put(i,GetfirendArr(i));
        }
    }

    //缓存载入
    public static void CacheEntry()
    {
        FileClear();
        FileTableLoad();
        FileUserLoad();
        FileFirendLoad();
    }


    public static void DisPlayCache()
    {
        System.out.println("缓存信息展示");
        System.out.println("======用户列表=====");
        System.out.print(" :[ ");
        for (String mes: table.keySet())
        {
            System.out.print(mes+"   ");
        }
        System.out.print("  ]");
        System.out.println();
        System.out.println("======用户请求表=====");
        for (String k:userCache.keySet())
        {
            System.out.print(k+" :[ ");
            ConcurrentHashMap<String,String> val = userCache.get(k);
            for (String mes:val.keySet())
            {
                System.out.print(mes+"  ");
            }
            System.out.print("  ]");
            System.out.println();
        }
        System.out.println("======用户好友表=====");
        for (String k:firendCache.keySet())
        {
            System.out.print(k+" :[ ");
            ConcurrentHashMap<String,String> val = firendCache.get(k);
            for (String mes:val.keySet())
            {
                System.out.print(mes+"  ");
            }
            System.out.print("  ]");
            System.out.println();
        }
    }

    /**
     * 添加用户的好友请求
     * @param user 被添加的用户
     * @param firend 好友的申请名
     */
    public static boolean AddFriendRequest(String user,String firend)
    {
        if (userCache.get(user).containsKey(firend))return true;
        userCache.get(user).put(firend,firend);
        if (userCache.get(user).containsKey(firend))return true;
        return false;
    }

    /**
     * 同意好友申请
     * @param user 用户
     * @param firend 加用户的好友
     * @return
     */
    public static boolean AssentRequest(String user,String firend){
        if(firendCache.get(user).containsKey(firend))return true;
        userCache.get(user).remove(firend);
        firendCache.get(user).put(firend,firend);
        firendCache.get(firend).put(user,user);
        if(firendCache.get(user).containsKey(firend))return true;
        return false;
    }

    /**
     * 删除好友
     * @param user
     * @param firend
     * @return
     */
    public static boolean DeleteFriends(String user,String firend){
        if(!firendCache.get(user).containsKey(firend))return true;
        firendCache.get(user).remove(firend);
        firendCache.get(firend).remove(user);
        if (!firendCache.get(user).containsKey(firend))return true;
        return false;
    }




}
