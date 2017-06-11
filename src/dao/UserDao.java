package dao;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑层接口
 */
public interface UserDao {

    User checkUser(String userName,String password);

    boolean addUser(User user);

    User getUserByUserName(String userName);

    int getRowCount();
    ArrayList<User> listUser(int startRow, int size);


    User getUserByUserId(int userid);

    boolean updateUser(int id, String type);

    boolean deleteUser(int id);

    boolean changeUserInfo(User user);
}
