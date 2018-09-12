package util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 配置文件读写工具类.
 */
@Slf4j
public class ConfigUtil {
    private static Properties pro = null;    //存放配置信息(String类型的键值对)
    private static final String FILE_LOCATION = "CodeHelperConfig.ini";
    public static final String CFG_WINDOW_X = "window_x";
    public static final String CFG_WINDOW_Y = "window_y";
    public static final String CFG_WINDOW_WIDTH = "window_width";
    public static final String CFG_WINDOW_HEIGHT = "window_height";
    public static final String CFG_LANGUAGE_EN = "en";

    //初始化必须运行,否则会报空指针异常
    static {
        pro = new Properties();
        readFromFile();
    }

    //从文件读取配置信息
    public static void readFromFile() {
        try (FileInputStream inStream = new FileInputStream(FILE_LOCATION);
             InputStreamReader reader = new InputStreamReader(inStream, StandardCharsets.UTF_8);) {
            pro.load(reader);
        } catch (Exception e) {
            log.warn("未发现配置文件,读取配置文件失败,详细信息:{}", e.getMessage());
        }
        log.info("配置文件载入成功!");
    }

    //将所有配置信息写到文件
    public static void writeToFile() {
        try (FileOutputStream outFile = new FileOutputStream(FILE_LOCATION)) {
            pro.store(outFile, "This is CodeHelper Config.Please don't delete this File!");
        } catch (Exception e) {
            log.warn("Failed to save config. msg:{}", e.getMessage(), e);
        }
    }


    //获取使用的语言,如果没有,默认中文
    public static int getLanguage() {
        String strLanguage = pro.getProperty("language", CFG_LANGUAGE_EN);
        return Integer.parseInt(strLanguage);
    }

    //将窗口的位置大小写入到配置文件中
    public static void saveWindowSize(String x, String y, String width, String height) {
        pro.setProperty(CFG_WINDOW_X, x);
        pro.setProperty(CFG_WINDOW_Y, y);
        pro.setProperty(CFG_WINDOW_WIDTH, width);
        pro.setProperty(CFG_WINDOW_HEIGHT, height);
        writeToFile();
    }

    /**
     * 获取指定字段的配置
     *
     * @param key          字段 key
     * @param defaultValue 字段值不存在的默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return pro.getProperty(key, defaultValue);
    }

    /**
     * 获取 properties 自行操作
     *
     * @return
     */
    public static Properties getProperties() {
        return pro;
    }

}
