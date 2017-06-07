package servlet;

import dao.FileDao;
import dao.UserDao;
import daoimpl.FileDaoImpl;
import daoimpl.UserDaoImpl;
import entity.User;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除用户servlet
 */
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("userid"));
        //删除用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserId(id);

        if(userDao.deleteUser(id)) {
            //删除成功
            //从本机磁盘中删除保存用户文件的文件夹
            FileUtil.deleteFiles("e://uploads" + "//"+user.getUserName());
            //跳转到显示界面
            response.sendRedirect("ListUserServlet");
        }
    }
}
