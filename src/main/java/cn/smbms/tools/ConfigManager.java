package cn.smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;
    private static ConfigManager configManager;
    private ConfigManager(){
        String config="";
        properties=new Properties();
        InputStream is=ConfigManager.class.getClassLoader().getResourceAsStream(config);
        try {
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized  static ConfigManager getInstance(){
        if (configManager==null){
            configManager=new ConfigManager();
        }
        return  configManager;
    }
}
