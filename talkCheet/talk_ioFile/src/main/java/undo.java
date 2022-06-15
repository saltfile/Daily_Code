import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class undo {
    public static void main(String[] args) {
        FileMethods.InitFilePath();
        ArrayList<String> arrayList = FileMethods.GetfirendArr("xxx");
        for(String i:arrayList ){
            System.out.println(i);
        }


    }
}
