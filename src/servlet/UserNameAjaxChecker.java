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
public class UserNameAjaxChecker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取用户名
        String userName = request.getParameter("userName");

        //获取用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserName(userName);

        //获取当前用户对象
        User currentUser = (User) request.getSession().getAttribute("user");

        response.setContentType("text/html;charset=utf-8");

        //获取返回的response流对象
        PrintWriter out = response.getWriter();
        if (user.getUserName() != null) {
            //不是空说明有该用户
            if (currentUser == null) {
                //sessinon中不存在用户说明是注册界面，返回不可用
                out.print("false");
            } else {
                //session存在用户信息说明是修改用户信息页面
                //检查传过来的userName和当前用户名是否一致
                if (userName.equals(currentUser.getUserName())) {
                    //一致说明没有改用户名
                    out.print("true");
                } else {
                    out.print("false");
                }
            }
        } else {
            //没有用户直接返回成功
            out.print("true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
