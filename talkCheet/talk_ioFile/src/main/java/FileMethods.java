import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileMethods {
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
        String path = userfile+"/"+name;
        File f = new File(path);
        return f.exists();
    }
    public static boolean FirendIsExist(String name){
        String path = firendfile+"/"+name;
        File f = new File(path);
        return f.exists();
    }

    public static boolean CreateUser(String name){
        try {
            boolean res = true;
            if (UserIsExist(name)) {
                File file = new File(userfile+"/"+name);
                boolean b = file.createNewFile();
                res = res && b;
            }
            if(FirendIsExist(name)){
                File file = new File(firendfile+"/"+name);
                boolean b = file.createNewFile();
                res = res && b;
            }
            return res;
        }catch (Exception e){
            System.err.println("创建文件失败");
        }finally {
            return false;
        }
}
}
