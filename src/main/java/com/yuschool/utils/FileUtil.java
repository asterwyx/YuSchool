package com.yuschool.utils;

import java.io.*;

public class FileUtil {

    public static final String DATA_ROOT = FileUtil.class.getResource("/data").getPath();

    /**
     * 将某个文件的全部内容读入一个缓冲区
      * @param buffer 存储缓冲区
     * @param resourceName 要读取的资源文件的全路径名
     * @throws IOException 文件不存在错误或者其它IO错误
     */
    public static void readToBuffer(StringBuffer buffer, String resourceName) throws IOException {
        try (InputStream is = FileUtil.class.getResourceAsStream(resourceName)) {
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                buffer.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
        }
    }

    /**
     * 读取文本文件内容
     * @param resourceName 资源文件的全路径名
     * @return 文本内容
     * @throws IOException 异常
     * @author asterwyx
     */
    public static String readFile(String resourceName) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtil.readToBuffer(sb, resourceName);
        return sb.toString();
    }

    /**
     * 根据常用文件名生成唯一绝对path
     * @param raw 常用文件名，不带路径
     * @return 唯一绝对path
     */
    public static String getAppropriateFilePath(String raw) {
        if (raw == null || "".equals(raw)) {
            return "";
        }
        String[] names = raw.split("\\.");
        if (names.length < 2) {
            // 没有扩展名
            return DATA_ROOT + "/" + CodeUtil.generateUniqueCode();
        } else {
            return DATA_ROOT + "/" + CodeUtil.generateUniqueCode() + "." + names[names.length - 1];
        }
    }
}
