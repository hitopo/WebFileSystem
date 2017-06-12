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
import java.io.PrintWriter;

/**
 * 修改用户资料servlet
 */
public class DoChangeUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取修改用户的编号
        int id = Integer.parseInt(request.getParameter("userid"));
        UserDao userDao = new UserDaoImpl();

        //获取当前用户对象
        User currentUser = (User) request.getSession().getAttribute("user");
        User user = new User();
        //组装user
        user.setUserId(id);
        user.setUserName(request.getParameter("userName"));
        user.setPassword(Md5Util.encrypt(request.getParameter("password")));
        user.setEmail(request.getParameter("email"));
        user.setType(currentUser.getType());
        //获取输出流对象
        PrintWriter out =  response.getWriter();
        if(userDao.changeUserInfo(user)){
            //更改会话中保存的当前用户
            request.getSession().setAttribute("user",user);

            if(!currentUser.getUserName().equals(user.getUserName())) {
                //用户修改了用户名
                //刷新主页面，及时将主页面中的用户名更改过来
                out.println("<script>");
                out.println("window.parent.location.reload()");
                out.println("</script>");
            } else {
                //没有修改用户名信息就输出修改成功
                out.println("修改资料成功");
            }
        } else {
            //修改失败
            out.println("修改资料失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
