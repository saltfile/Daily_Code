import java.io.File;

public class FileMethods {
    static String userfile = "/home/saltfish/github.com/apache/talkCheet/talk_ioFile/src/main/java/user" ;
    public static boolean UserIsExist(String name){
        String path = userfile+"/"+name;
        File f = new File(path);
        return f.exists();
    }







}
