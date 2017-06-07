package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import javafx.scene.control.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理Ajax请求
 */
public class DoRegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserName(userName);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(user.getUserName() != null) {
            //不是空说明有该用户
            //用户名不可用
            //返回false
            out.println("false");
        } else{
            out.println("true");
        }
    }
}
