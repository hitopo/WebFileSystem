package daoimpl;

import dao.FileDao;
import entity.File;
import sun.util.resources.cldr.tn.CurrencyNames_tn;
import util.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现文件功能接口
 */
public class FileDaoImpl implements FileDao {
    //数据库连接
    private Connection conn = Dbutil.getConnection();

    /**
     * 添加文件到数据库中
     *
     * @param file 添加的文件实体
     * @return 成功与否
     */
    @Override
    public boolean addFile(File file) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "INSERT INTO files(filename,filesize,time,filepath,userid) VALUES" +
                "(?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, file.getFileName());
            pstmt.setLong(2, file.getFileSize());
            pstmt.setString(3, file.getTime());
            pstmt.setString(4, file.getFilePath());
            pstmt.setInt(5, file.getUser().getUserId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Dbutil.close(pstmt,null);
        }
        return result == 1;
    }

    /**
     * 获取用户的文件列表
     *
     * @param userid 用户id
     * @return 文件列表
     */
    @Override
    public List<File> listFile(int userid,int startRow,int size) {
        List<File> files = new ArrayList<File>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM files WHERE userid=? LIMIT ?,?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userid);
            pstmt.setInt(2,startRow);
            pstmt.setInt(3,size);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                File file = new File();
                file.setFileId(rs.getInt("fileid"));
                file.setFileName(rs.getString("filename"));
                file.setFilePath(rs.getString("filepath"));
                file.setTime(rs.getString("time"));
                file.setFileSize(rs.getLong("filesize"));
                file.setUser(new UserDaoImpl().getUserByUserId(userid));
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.close(pstmt,rs);
        }
        return files;
    }

    /**
     * 获取文件
     *
     * @param fileid 文件id
     * @return 文件对象
     */
    @Override
    public File getFileById(int fileid) {
        File file = new File();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT  * FROM files WHERE fileid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fileid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                file.setFileId(rs.getInt("fileid"));
                file.setFileName(rs.getString("filename"));
                file.setFileSize(rs.getLong("filesize"));
                file.setTime(rs.getString("time"));
                file.setFilePath(rs.getString("filepath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
           Dbutil.close(pstmt,rs);
        }
        return file;
    }

    /**
     * 根据文件id删除数据库中的文件
     * @param fileid 文件id
     * @return 成功与否
     */
    @Override
    public boolean deleteFileById(int fileid) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "DELETE FROM files WHERE fileid=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fileid);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Dbutil.close(pstmt,null);
        }
        return result == 1;
    }

    @Override
    public int getRowCount(int userid) {
        PreparedStatement pstmt = null;
        int count = 0;
        ResultSet rs = null;
        String sql = "SELECT * FROM files WHERE userid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  0;
        } finally {
            Dbutil.close(pstmt,rs);
        }
        return count;
    }
}
