package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理用户注册的servlet
 */
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取参数
        String userName = request.getParameter("userName");
        //将password转化
        String password = Md5Util.encrypt(request.getParameter("password"));
        String email = request.getParameter("email");
        //将用户类型设置为用户
        String type = "用户";
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setType(type);
        //调用模型层方法添加用户
        UserDao userdao = new UserDaoImpl();
        if(userdao.addUser(user)) {
             request.getSession().setAttribute("user",user);
             response.sendRedirect("../main.jsp");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('注册失败！')</script>");
            response.sendRedirect("../reg.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
