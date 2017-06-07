package servlet;

import dao.FileDao;
import daoimpl.FileDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 下载文件
 */
public class DownloadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        //获取文件路径
        int fileId = Integer.parseInt(request.getParameter("fileid"));
        FileDao fileDao = new FileDaoImpl();
        entity.File file = fileDao.getFileById(fileId);

        //设置返回的是下载的文件
        response.setContentType("application/x-msdownload");

        /*
         * 这个地方会引起中文乱码问题，记得最后解决
         * 文件名中文乱码
         */

        //设置响应头信息
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getFileName());
        response.setHeader("Content-Length", Long.toString(file.getFileSize()));

        //下载文件
        String path = file.getFilePath();
        ServletOutputStream sos = response.getOutputStream();
        FileInputStream fis = new FileInputStream(path);
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1) {
            sos.write(b, 0, n);
        }

        //文件流传输完成，关闭流对象
        fis.close();
        sos.flush();
        sos.close();
    }
}
