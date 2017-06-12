package servlet;

import dao.FileDao;
import daoimpl.FileDaoImpl;
import entity.File;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除文件的servlet
 */
public class DeleteFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取输出流
        PrintWriter out = response.getWriter();

        //获取想要删除的文件ID
        int fileid = Integer.parseInt(request.getParameter("fileid"));

        FileDao fileDao = new FileDaoImpl();

        //获取文件对象
        File file = fileDao.getFileById(fileid);

        //先将文件从本地删除删除成功后再删除数据库文件记录
        //获取文件路径
        String path = file.getFilePath();
        if (FileUtil.deleteFile(path)) {
            //删除文件成功，删除数据库文件记录
            if (fileDao.deleteFileById(fileid)) {
                //删除记录成功，跳转到显示文件界面
                response.sendRedirect("ListFileServlet");
            } else {
                out.println("数据库删除记录失败");
            }
        } else {
            //删除失败，给出提示
            out.println("服务器删除文件失败");
        }

    }
}
