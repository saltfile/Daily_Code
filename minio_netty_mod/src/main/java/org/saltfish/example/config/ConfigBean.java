package org.saltfish.example.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigBean {
    private static ConfigBean config = new ConfigBean();
    private Map<String, Object> obj = null;


    private ConfigBean(){
        load();
    }

    public static ConfigBean getInstance(){
        return config;
    }
    public Object getVal(String key){
        return this.obj.get(key);
    }

    public void load(){
        try(InputStream context = ConfigBean.class.getResourceAsStream("/applicaiton.yml")){
            Yaml yaml = new Yaml();
            obj = (Map<String, Object>) yaml.load(context);
        }catch (Exception o){
            o.printStackTrace();
        }



    }


}
