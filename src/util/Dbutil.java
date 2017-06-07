package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类
 */
public class Dbutil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/webfilesystem?useUnicode=true&characterEncoding=utf-8";
    private static final String userName = "root";
    private static final String password = "";
    private static Connection conn = null;

    public Dbutil() {
    }

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}





