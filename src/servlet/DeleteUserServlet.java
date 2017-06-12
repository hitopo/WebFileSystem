package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;
import entity.User;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除用户servlet
 */
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("userid"));
        //删除用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserId(id);

        //先删除服务器中数据，再删除数据库中记录
        String filePath = "e://uploads" + "//" + user.getUserId();
        if (FileUtil.deleteFiles(filePath)) {
            //删除文件成功
            if (userDao.deleteUser(id)) {
                //跳转到显示用户的界面
                response.sendRedirect("ListUserServlet");
            } else {
                out.println("数据库删除用户记录失败");
            }
        } else {
            out.println("服务器删除用户文件夹失败");
        }
    }
}
