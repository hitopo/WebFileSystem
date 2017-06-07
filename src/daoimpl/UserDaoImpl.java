package daoimpl;

import dao.UserDao;
import entity.User;
import util.Dbutil;
import util.Md5Util;

import java.sql.*;
import java.util.ArrayList;

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
        Statement stmt = null;
        ResultSet rs = null;
        //改造userName和password，因为SQL语句的字符串必须加引号
        userName = "\'" + userName + "\'";
        //将password加密之后和数据库中的进行比较
        password = "\'" + password + "\'";
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username=" + userName +
                    "AND password = " + password;
            rs = stmt.executeQuery(sql);
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
            //关闭相关资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            //关闭资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result == 1;
    }

    /**
     * 根据name获取user对象
     */
    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        Statement stmt = null;
        ResultSet rs = null;
        userName = "\'" + userName + "\'";
        String sql = "SELECT * FROM users WHERE username=" + userName;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
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
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 获取所有的user对象
     * 保存在容器中
     */
    @Override
    public ArrayList<User> getAllUser() {
        ArrayList<User> userList = new ArrayList<User>();
        User user = null;
        ResultSet rs = null;
        Statement stmt = null;

        String sql = "SELECT * FROM users";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;

    }

    /**
     * 根据id获取user对象
     */
    @Override
    public User getUserByUserId(int userid) {
        User user = new User();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE userid = " + userid;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
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
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result == 1;
    }
//
//    public static void main(String[] args) {
//        UserDao ud = new UserDaoImpl();
//        User user = new User();
//        user.setUserId(3);
//        user.setUserName("小苏");
//        user.setPassword(Md5Util.encrypt("000000"));
//        user.setEmail("000000@qq.com");
//        System.out.println(ud.changeUserInfo(user));
//
//    }
}
