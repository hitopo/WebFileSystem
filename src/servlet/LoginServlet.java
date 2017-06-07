package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ajax异步处理检查用户名和密码的servlet
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //设置编码样式
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();

        //查询存不存在该用户
        password = Md5Util.encrypt(password);
        User user = userDao.checkUser(userName,password);

        //获取response的输出]
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //查询到结果
        if(user != null) {
            //将user对象保存在session中
            request.getSession().setAttribute("user",user);
            //返回值给前端
            out.print("true");
        } else {
            //没有该用户
            out.print("false");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
