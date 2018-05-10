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
public class UploadServerlt extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws HttpRetryException, IOException {

		try {
			//解析http请求
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			//解析成功
			if (isMultipart == true) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置上传的单个文件的大小6M
				upload.setSizeMax(6*1024*1024);
				//设置上传的总文件的大小40M
				upload.setFileSizeMax(40*1024*1024);
				List<FileItem> fileItemList = upload.parseRequest(request);
				String dispatcher = "/index.jsp";
				//如果表单内容不为空
				if (fileItemList != null) {
					//遍历内容
					for (FileItem fileItem: fileItemList) {
						//如果是文件:<input type="file" name="left_top_demo">
						if (fileItem.getFieldName().equalsIgnoreCase("left_top_demo")) {
							InputStream in = fileItem.getInputStream();
							//写入指定目录下
							FileOutputStream fos = new FileOutputStream("left_top_demo.json");
							byte[] buffer = new byte[1024];
							int length;
							while ((length = in.read(buffer)) != -1) {
								fos.write(buffer, 0, length);
							}
							in.close();
							fos.close();
							//清除应用服务器下的临时文件
							fileItem.delete();
							dispatcher = "/left_top_up.jsp";
							break;
						}
					}
				}
				request.getRequestDispatcher(dispatcher).forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.getRequestDispatcher("/error.html").forward(request,response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			}
		}
	}
}
