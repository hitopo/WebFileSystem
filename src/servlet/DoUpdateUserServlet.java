package servlet;

import dao.UserDao;
import daoimpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理管理员的修改用户权限的操作
 */
@WebServlet(name = "DoUpdateUserServlet")
public class DoUpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("userid"));
        String type=request.getParameter("type");
        UserDao userDao = new UserDaoImpl();
        //改变权限成功之后跳转到显示的界面
        if(userDao.updateUser(id,type)) {
            response.sendRedirect("ListUserServlet");
        } else{
            response.getWriter().println("更新权限失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
