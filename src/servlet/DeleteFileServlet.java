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

/**
 * 删除文件的servlet
 */
public class DeleteFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取想要删除的文件ID
        int fileid = Integer.parseInt(request.getParameter("fileid"));

        FileDao fileDao = new FileDaoImpl();

        File file = fileDao.getFileById(fileid);
        //从数据库中将file信息删除
        if(fileDao.deleteFileById(fileid)) {
            //还必须将本机文件进行删除
            //获取文件路径
            String path = file.getFilePath();

            System.out.println(path);
            if(FileUtil.deleteFile(path)) {
                //删除文件成功，跳转到显示文件界面
                response.sendRedirect("ListFileServlet");
            } else{
                //删除失败，给出提示
                response.getWriter().println("服务器删除文件失败");
            }
        } else {
            response.getWriter().println("删除文件失败");
        }
    }
}