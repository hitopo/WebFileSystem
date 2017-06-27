package util;

import java.io.File;

/**
 * 文件工具类
 * 实现对本机文件的操作
 */
public class FileUtil {
    /**
     * 从本机删除文件
     *
     * @param filePath 文件路径
     * @return 成功与否
     */
    public static boolean deleteFile(String filePath) {
        //通知jvm释放系统资源解除文件占用
        System.gc();
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * 从本机删除文件夹下面的所有文件
     *
     * @param filePath 文件夹绝对路径
     * @return 成功与否
     */
    public static boolean deleteFiles(String filePath) {
        //通知jvm释放系统资源解除文件占用
        System.gc();
        File fileDec = new File(filePath);
        if (fileDec.isDirectory()) {
            //文件路径是一个目录
            String[] childFile = fileDec.list();
            if(childFile != null) {
                for (String aChildFile : childFile) {
                    //递归删除文件夹下的所有子文件
                    //拼凑新的文件路径
                    String newPath = filePath + "\\" + aChildFile;
                    boolean success = deleteFiles(newPath);
                    if(!success) {
                        return false;
                    }
                }
            }
        }
        //childfile==null说明文件夹是空的文件夹，直接删除即可
        return fileDec.delete();
    }

    /**
     * 检查文件是否存在
     * @param path 文件路径
     * @return 是否存在
     */
    public static boolean checkExisting(String path) {
        File file = new File(path);
        return file.exists();
    }
}
