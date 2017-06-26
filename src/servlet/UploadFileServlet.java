package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileDao;
import daoimpl.FileDaoImpl;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 处理文件上传的servlet
 */
public class UploadFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式，避免乱码
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //设置响应头
        response.setContentType("text/html;charset=utf-8");
        //获取输出流对象
        PrintWriter out = response.getWriter();
        //实例化硬件工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建servlet上传文件类
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        //解析请求，获取文件上传对象
        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            //文件停止下载
            e.printStackTrace();
//            out.print("1");
            return;
        }

        //获取当前用户，即将其保存在用户名的文件夹之下
        User user = (User) request.getSession().getAttribute("user");

        //保存根目录
        String Filedic = "e://uploads//" + user.getUserId() + "//";
        File uploadFile = new File(Filedic);
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        //文件列表迭代器
        Iterator<FileItem> it = items.iterator();
        entity.File file;
        //迭代访问上传过来的文件
        while (it.hasNext()) {
            file = new entity.File();
            FileItem item = it.next();
            //获取文件名称
            String fileName = item.getName();
            if (item.getName() == null) {
                //获取文件名为空就跳出去
                continue;
            }
            //获取文件大小
            long fileSize = item.getSize();
            //实际上传路径
            String path = uploadFile + "\\" + fileName;
            File fileTemp = new File(path);
            try {
                //保存文件
                item.write(fileTemp);
            } catch (Exception e) {
                //保存文件失败
//                e.printStackTrace();
//                out.print("2");
                return;
            }

            //获取当前时间，设置为上传时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date());

            //设置文件属性
            file.setFileName(fileName);
            file.setFileSize(fileSize);
            file.setUser(user);
            file.setTime(time);
            file.setFilePath(path);

            //将文件保存在数据库中
            FileDao fileDao = new FileDaoImpl();
            fileDao.addFile(file);
                //保存文件到数据库失败
//                out.print("3");
        }
    }
}
