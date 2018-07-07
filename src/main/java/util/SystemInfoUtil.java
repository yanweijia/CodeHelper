package util;

import java.io.File;

public class SystemInfoUtil {
    private SystemInfoUtil() {
    }


    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    /**
     * 获取磁盘使用信息
     *
     * @return
     */
    public static String getDiskInfo() {
        StringBuilder buffer = new StringBuilder();
        File[] roots = File.listRoots();
        // 获取磁盘分区列表
        for (File file: roots) {
            buffer.append(file.getPath()).append(" Info:").append('\n');
            // 空闲空间
            buffer.append("freeMem = ").append(file.getFreeSpace() / 1024 / 1024 / 1024).append("G").append('\n');
            // 可用空间
            buffer.append("usedMem = ").append(file.getUsableSpace() / 1024 / 1024 / 1024).append("G").append('\n');
            // 总空间
            buffer.append("TotalMem = ").append(file.getTotalSpace() / 1024 / 1024 / 1024).append("G").append('\n');
        }
        return buffer.toString();
    }

    public static String getUserCountry() {
        return System.getProperty("user.country");
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取程序工作目录
     *
     * @return
     */
    public static String getWorkDir() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取操作系统的架构,如 x86_64
     *
     * @return
     */
    public static String getOSArch() {
        return System.getProperty("os.arch");
    }

    /**
     * 获取系统位数,如 64
     *
     * @return
     */
    public static String getDataModel() {
        return System.getProperty("sun.arch.data.model");
    }

    /**
     * 文件编码,影响生成的文件内容
     *
     * @return
     */
    public static String getFileEncoding() {
        return System.getProperty("file.encoding");
    }

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getUserLanguage() {
        return System.getProperty("user.language");
    }

    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    /**
     * 获取 Java 类版本,如 52.0, 代表的是 jdk1.8版本
     *
     * @return
     */
    public static String getJavaClassVersion() {
        return System.getProperty("java.class.version");
    }

    /**
     * 用户所在时区,如 Asia/Shanghai
     *
     * @return
     */
    public static String getUserTimezone() {
        return System.getProperty("user.timezone");
    }

    /**
     * jnuEncoding 影响文件名的创建
     *
     * @return
     */
    public static String getJnuEncoding() {
        return System.getProperty("sun.jnu.encoding");
    }
}
