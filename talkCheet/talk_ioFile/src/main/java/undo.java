import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class undo {
    public static void main(String[] args) {
        FileMethods.InitFilePath();
        FileMethods.CacheInit();
        FileMethods.DisPlayCache();
//        FileMethods.AddFriendRequest("xxx","aaa");
//        FileMethods.AssentRequest("xxx","aaa");
        FileMethods.DeleteFriends("xxx","aaa");
        FileMethods.DisPlayCache();
        FileMethods.CacheEntry();
    }
}
