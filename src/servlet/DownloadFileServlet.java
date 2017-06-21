package servlet;

import dao.FileDao;
import daoimpl.FileDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
        request.setCharacterEncoding("utf-8");

        //获取文件路径
        int fileId = Integer.parseInt(request.getParameter("fileid"));
        FileDao fileDao = new FileDaoImpl();
        entity.File file = fileDao.getFileById(fileId);

        //清空response信息
        response.reset();

        //设置返回的是下载的文件(二进制流文件)
        response.setContentType("application/octet-stream;charset=utf-8");

        //解决中文乱码问题
        response.setCharacterEncoding("utf-8");

        //设置响应头信息
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String(file.getFileName().getBytes("utf-8"), "ISO-8859-1"));
        response.setHeader("Content-Length", Long.toString(file.getFileSize()));

        //下载文件
        String path = file.getFilePath();

        //设置输入输出流
        BufferedOutputStream bos = new BufferedOutputStream(
                response.getOutputStream());
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(path));
        byte[] b = new byte[1024];
        int n;
        while ((n = bis.read(b)) != -1) {
            bos.write(b, 0, n);
        }

        //文件流传输完成，关闭流对象
        bos.flush();
        bis.close();
        bos.close();

        //通知jvm回收资源，释放文件占用
        System.gc();
    }
}
