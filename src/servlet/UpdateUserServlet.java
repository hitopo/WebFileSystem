package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import jdk.management.resource.internal.ResourceNatives;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *处理管理员修改用户权限的servlet
 */
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取想要修改的userID
        int userid = Integer.parseInt(request.getParameter("userid"));
        UserDao userDao = new UserDaoImpl();
        //获取user对象
        User user = userDao.getUserByUserId(userid);
        //将user对象放置到request中
        request.setAttribute("user",user);
        //请求转发
        request.getRequestDispatcher("../updateuser.jsp").forward(request,response);
    }
}
