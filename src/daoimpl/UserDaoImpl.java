package daoimpl;

import dao.UserDao;
import entity.User;
import util.Dbutil;
import util.Md5Util;

import javax.management.AttributeList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户逻辑处理类，UserDao接口
 */
public class UserDaoImpl implements UserDao {
    //数据库连接
    private Connection conn = Dbutil.getConnection();

    /**
     * 用户登录时检查用户名和密码
     */
    @Override
    public User checkUser(String userName, String password) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getString("type"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.close(pstmt,rs);
        }
        //返回结果
        return user;
    }

    /**
     * 注册用户时添加用户进数据库
     */
    @Override
    public boolean addUser(User user) {
        int result;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO users(username,password,type,email) " +
                "VALUES (?,?,?,?)";
        try {
            //设置参数
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getType());
            pstmt.setString(4, user.getEmail());
            //执行sql语句
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
     * 根据name获取user对象
     */
    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            rs = pstmt.executeQuery(sql);
            if (rs.next()) {
                user.setUserId(rs.getInt("userid"));
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.close(pstmt,rs);
        }
        return user;
    }

    /**
     * 获取记录行数
     * @return 记录行数
     */
    @Override
    public int getRowCount() {
        int count = 0;
        String sql = "SELECT * FROM users";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 获取记录列表
     * @param startRow 开始行数
     * @param size 每一页的大小
     * @return 列表
     */
    @Override
    public ArrayList<User> listUser(int startRow,int size) {
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users LIMIT ?,?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,startRow);
            pstmt.setInt(2,size);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbutil.close(pstmt,rs);
        }
        return users;
    }

    /**
     * 根据id获取user对象
     */
    @Override
    public User getUserByUserId(int userid) {
        User user = new User();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE userid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //设置user参数
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
           Dbutil.close(pstmt,rs);
        }
        return user;
    }

    /**
     * 更改用户权限
     */
    @Override
    public boolean updateUser(int id, String type) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "UPDATE users SET type=? WHERE userid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setInt(2, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
           Dbutil.close(pstmt,rs);
        }
        return result == 1;
    }

    /**
     * 根据id删除用户
     */
    @Override
    public boolean deleteUser(int id) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "DELETE FROM users WHERE userid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
     * 修改用信息
     */
    @Override
    public boolean changeUserInfo(User user) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "UPDATE users SET username=?,password=?,email=? WHERE userid=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getUserId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Dbutil.close(pstmt,null);
        }
        return result == 1;
    }
}
