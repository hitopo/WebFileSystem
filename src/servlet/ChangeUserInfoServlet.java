package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户更改资料的servlet
 */
public class ChangeUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        UserDao userDao = new UserDaoImpl();
        User currentUser = (User) request.getSession().getAttribute("user");
        int id = currentUser.getUserId();
        //获取用户信息
        User user = userDao.getUserByUserId(id);
        if (user != null) {
            //获取到用户
            request.setAttribute("user", user);
            //跳转到changeinfo.jsp显示修改的数据
            request.getRequestDispatcher("../changeinfo.jsp").forward(request, response);
        }
    }
}
