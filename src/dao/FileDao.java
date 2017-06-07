package dao;

import entity.File;

import java.util.List;

/**
 * 文件操作接口
 */
public interface FileDao {

    boolean addFile(File file);

    List<File> getUserFile(int userid);

    File getFileById(int fileid);

    boolean deleteFileById(int fileid);
}
