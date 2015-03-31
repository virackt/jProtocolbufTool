package me.veryvic.game.tool.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by vic on 15-3-26.
 */
public class AppConfig {

    private static Properties properties;

    static {
        InputStream is = null;
        is = AppConfig.class.getResourceAsStream("conf.properties");
        if (is == null) {
            try {
                is = new FileInputStream(PathUtil.getClassPath() + File.separator + "conf.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String key) {
        return properties.getProperty(key);
    }

    public static int getIntValue(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }


}
