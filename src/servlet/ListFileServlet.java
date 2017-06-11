package servlet;

import dao.FileDao;
import daoimpl.FileDaoImpl;
import entity.File;
import entity.Page;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 显示当前用户所有文件
 */
public class ListFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        //数据库中查找user对应的文件
        int userid = user.getUserId();
        FileDao fileDao = new FileDaoImpl();
        int rowCount = fileDao.getRowCount(userid);
        //创建分页对象
        Page page = new Page(9, request.getParameter("num"), rowCount);
        List<File> files = fileDao.listFile(userid,page.getStartRow(),page.getSize());
        //获取到了user的file列表
        request.setAttribute("fileList", files);
        request.setAttribute("page",page);
        //跳转到显示文件的jsp
        request.getRequestDispatcher("../listfile.jsp").forward(request, response);

    }
}
