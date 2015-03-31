package me.veryvic.game.tool.util;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vic on 15-3-27.
 */
public class PathUtil {
    private static final String ROOT_PATH;
    private static final String CLASS_PATH;

    static {
        URL url = PathUtil.class.getResource("/");
        if (url != null) {
            CLASS_PATH = url.getPath();
        } else {
            CLASS_PATH = System.class.getResource("/").getPath();
        }
        final String patternStr = "/\\w{0,}-{0,}classes/";
        final Pattern pattern = Pattern.compile(patternStr);
        final Matcher matcher = pattern.matcher(CLASS_PATH);
        if (matcher.find()) {
            ROOT_PATH = matcher.replaceAll("/");
        } else {
            ROOT_PATH = CLASS_PATH.substring(0, CLASS_PATH.indexOf("classes/"));
        }
    }

    public static String getRootPath() {
        return ROOT_PATH;
    }

    public static String getClassPath() {
        return CLASS_PATH;
    }
}
