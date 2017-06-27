package servlet;

import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 检验文件是否存在
 */
public class CheckExistingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");


        String fileName =  request.getParameter("filename");
        String userId = request.getParameter("userId");

        //凭拼接文件路径，查询文件是否在服务器主机中存在
        String path = "E:/uploads/"+userId+"/"+fileName;

        PrintWriter out = response.getWriter();
        if(FileUtil.checkExisting(path)) {
            //文件存在
            out.print(1);
        } else {
            //文件不存在
            out.print(0);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
