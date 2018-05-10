package com.yc.view.servlet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@SuppressWarnings("serial")
public class TestUploadServerlt extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws HttpRetryException, IOException {

		try {
			//解析http请求
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			//解析成功
			if (isMultipart == true) {
				// 获得磁盘文件条目工厂
				FileItemFactory factory = new DiskFileItemFactory();
				// 高水平的API文件上传处理，将文件保存在服务器硬盘
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置上传的单个文件的大小6M
				upload.setSizeMax(6*1024*1024);
				//设置上传的总文件的大小40M
				upload.setFileSizeMax(40*1024*1024);
				//http中的复杂表单元素都被看作是FileItem,所以可以上传多个文件
				List<FileItem> fileItemList = upload.parseRequest(request);
				//如果表单内容不为空
				if (fileItemList != null) {
					//遍历内容
					for (FileItem fileItem: fileItemList) {
						//如果是字符:<input type="text" name="mytext" value="123456"/>
						if (fileItem.getFieldName().equalsIgnoreCase("mytext")) {
							String text2 = fileItem.getString();
						}
						//如果是文件:<input type="file" name="myfile">
						if (fileItem.getFieldName().equalsIgnoreCase("myfile")) {
							InputStream in = fileItem.getInputStream();
							//写入指定目录下
							FileOutputStream fos = new FileOutputStream("D:/temp/t.json");
							byte[] buffer = new byte[1024];
							int length;
							while ((length = in.read(buffer)) != -1) {
								fos.write(buffer, 0, length);
							}
							in.close();
							fos.close();
							//清除应用服务器下的临时文件
							fileItem.delete();
						}
					}
				}
				request.getRequestDispatcher("/index.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			}
		}
	}
}
