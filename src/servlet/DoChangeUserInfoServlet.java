package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import oracle.jrockit.jfr.events.RequestableEventEnvironment;
import util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改用户资料servlet
 */
public class DoChangeUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("userid"));
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        //组装user
        user.setUserId(id);
        user.setUserName(request.getParameter("userName"));
        user.setPassword(Md5Util.encrypt(request.getParameter("password")));
        user.setEmail(request.getParameter("email"));
        if(userDao.changeUserInfo(user)){
            response.getWriter().println("<script>alert('修改成功');</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
