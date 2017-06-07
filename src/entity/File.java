package entity;

/**
 * 文件实体类
 */
public class File {
    //文件id
    private int fileId;
    //文件名
    private String fileName;
    //文件持有人
    private User user;
    //文件路径
    private String filePath;
    //文件呢大小
    private long fileSize;
    //上传时间
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public File() {
    }

    public int getFileId() {

        return fileId;
    }

    public void setFileId(int fileId) {

        this.fileId = fileId;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public String getFilePath() {

        return filePath;
    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", user=" + user +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", time='" + time + '\'' +
                '}';
    }
}
