import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FileMethods {

    static Map<String,ArrayList> userCache = new HashMap<>();
    static Map<String,ArrayList> firendCache = new HashMap<>();

    static String path = null;
    static String userfile = null;
    static String firendfile = null;
    public static void InitFilePath(){
        String user = "/talk_ioFile/src/main/java/user";
        String firend = "/talk_ioFile/src/main/java/firend";
        try(InputStream content = FileMethods.class.getResourceAsStream("/config.yaml")) {
            Yaml yaml = new Yaml(new Constructor(enitry.class));
            Iterable<Object> its = yaml.loadAll(content);
            List<enitry> userEntityList = new ArrayList<>();
            for(Object it : its) {
                userEntityList.add((enitry) it);
            }
            if(userEntityList.size() > 0){
                path = userEntityList.get(0).getProjectpath()+"/"+"cachetable";
                userfile = userEntityList.get(0).getProjectpath()+user;
                firendfile = userEntityList.get(0).getProjectpath()+firend;
            }else {
                throw new Exception("请完成config.yml中的项目路径配置");
            }
        } catch(Exception ex) {
            System.err.println(ex);
        }
    }


    public static boolean UserIsExist(String name){
        String path = userfile+"/"+name+".txt";
        File f = new File(path);
        return f.exists();
    }
    public static boolean FirendIsExist(String name){
        String path = firendfile+"/"+name+".txt";
        File f = new File(path);
        return f.exists();
    }

    public static boolean CreateUser(String name){
        boolean res = false;
        try {

            if (!UserIsExist(name)) {
                File file = new File(userfile+"/"+name+".txt");
                boolean b = file.createNewFile();
                res = b;
            }
            if(!FirendIsExist(name)){
                File file = new File(firendfile+"/"+name+".txt");
                boolean b = file.createNewFile();
                res = b;
            }
        }catch (Exception e){
            System.err.println("创建文件失败");
        }finally {
            return res;
        }
    }

    public static boolean DeleteUser(String name){
        String upath = userfile+"/"+name;
        String fpath = firendfile+"/"+name;
        boolean res = false;
        if(UserIsExist(name)){
            File userfile = new File(upath);
            res = userfile.delete();
        }
        if (FirendIsExist(name)){
            File firendfile = new File(fpath);
            res = firendfile.delete();
        }
        return res;
    }

    public static boolean SelFirend(String name,String firend){
        boolean res = false;
        File f2 = new File(firendfile+"/"+name+".txt");
        try(
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(f2)));
        ){
            String str2;
            while ((str2 = reader2.readLine()).length()>0){
                if (firend.equals(str2)){
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean SelUser(String name,String firend){
        boolean res = false;
        File f = new File(userfile+"/"+name+".txt");
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        ){
            String str2;
            while ((str2 = reader.readLine()).length()>0){
                if (firend.equals(str2)){
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static void WriterUser(String user,String str){
        String path = userfile+"/"+user+".txt";
        try (
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path),true)));
        ){

            writer.write(str+"\n");
            writer.flush();
            writer.close();
        }catch (Exception e) {
            System.err.println("写入时出错");
        }
    }
    public static void WriterFirend(String user,String str){
        String path = firendfile+"/"+user+".txt";
        try (
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path),true)));
        ){
            writer.write(str+"\n");
            writer.flush();
            writer.close();
        }catch (Exception e) {
            System.err.println("写入时出错");
        }
    }





    public static void AddFirend(String user,String name){
        boolean res = false;
        if(!UserIsExist(user)||!UserIsExist(name)){
            return ;
        }
        if(!SelUser(user,name)&&!SelFirend(user, name)){
            WriterUser(user, name);
        }else if (SelUser(user, name)&&!SelFirend(user, name)){

            WriterFirend(user, name);
        }

    }

    /**
     * TODO:读取用户文件更新到缓存
     */


    //先获取user的信息
    public static ArrayList<String> GetuserArr(String str){
        ArrayList<String> res = new ArrayList<>();
        File f = new File(userfile+"/"+str+".txt");
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


    //在获取user 的firend列表
    public static ArrayList<String> GetfirendArr(String str){
        ArrayList<String> res = new ArrayList<>();
        File f = new File(firendfile+"/"+str+".txt");
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





    public static void ReadCache(){
        File file = new File(path);
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ){
            String str2;

        }catch (Exception e){
            e.printStackTrace();
        }
    }






    //更新成缓存
    public static void CacheInit(){

    }



}
