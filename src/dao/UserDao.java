package dao;

import entity.User;

import java.util.ArrayList;

/**
 * 业务逻辑层接口
 */
public interface UserDao {

    User checkUser(String userName,String password);
    boolean addUser(User user);
    User getUserByUserName(String userName);

    ArrayList<User> getAllUser();

    User getUserByUserId(int userid);

    boolean updateUser(int id, String type);

    boolean deleteUser(int id);

    boolean changeUserInfo(User user);
}
