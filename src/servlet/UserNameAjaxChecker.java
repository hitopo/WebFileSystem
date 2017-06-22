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
 * 处理注册界面和修改信息界面的userName Ajax检测
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
            if ("ChangeInfo".equals(request.getParameter("location"))) {
                //修改信息界面的ajax
                if (userName.equals(currentUser.getUserName())) {
                    //一致说明没有改用户名
                    out.print("true");
                } else {
                    out.print("false");
                }
            } else {
                //注册用户界面的ajax
                out.print("false");
            }
        } else {
            //没有用户直接返回成功
            out.print("true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        doPost(request, response);
    }
}
