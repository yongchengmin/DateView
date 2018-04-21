package com.yc.view.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class VerifyServlet
 */
 
public class VerifyServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
// private static Logger log = Logger.getLogger(VerifyServlet.class);  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
}


/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		// for (int i = 0; i < 4; i++) {
		// String rand = String.valueOf(random.nextInt(10));
		// sRand += rand;
		// // 将认证码显示到图象中
		// g.setColor(new Color(20 + random.nextInt(110), 20 + random
		// .nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		// g.drawString(rand, 13 * i + 6, 16);
		// }
		
		//呈现加减乘除
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
		.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand, 13  + 6, 16);
		int op = 4;
		int opera = random.nextInt(op);
		String operator = null;
		switch (opera) {
			case 1:
				operator = "+";
			break;
			case 2:
				operator = "-";
			break;
			case 3:
				operator = "*";
			break;
			case 4:
				operator = "/";
			break;
				default:
			break;
		}
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
		.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(operator, 13*2  + 6, 16);
		
		String rand3 = String.valueOf(random.nextInt(10));
		sRand += rand;
		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
		.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand3, 13*3  + 6, 16);
		
		String rand4 = "=";
		sRand += rand;
		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
		.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand4, 13*4  + 6, 16);
		
		// 将认证码存入SESSION
		request.getSession().setAttribute("rand", sRand);
		
		// 图象生效
		g.dispose();
		response.reset();
		// 清除掉原来默认的text/html
		response.setContentType("image/jpeg");
		// 重设为image/jpeg
		
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
	} catch (Exception ex) {
		// log.error("#-----Authimage failed-----#");
		// log.error(ex);
	}
}

private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
	Random random = new Random();
	if (fc > 255){
		fc = 255;
	}
	if (bc > 255){
		bc = 255;
	}
	int r = fc + random.nextInt(bc - fc);
	int g = fc + random.nextInt(bc - fc);
	int b = fc + random.nextInt(bc - fc);
	return new Color(r, g, b);
}
}
