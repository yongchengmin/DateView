package com.yc.view.chart;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.sf.json.JSONObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import com.yc.utils.esbUtils.FileUtil;
import com.yc.view.chart.demo.PieChart;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartJsonUtils;
import com.yc.view.utils.ChartUtils;
/**
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 * 
 *       </p>
 */
public class PieChartLay {

	public static DefaultPieDataset createDataset(JSONObject jsonObject) {
		String categorie = jsonObject.getString(ChartJsonUtils.CATEGORIES);
		if(categorie!=null && categorie.length()>0){
			categorie = categorie.substring(1, categorie.length()-1);
			String[] categories = categorie.split(ChartJsonUtils.COMMA);
			
			Object[] datas = new Object[categories.length];
			String[] x = new String[categories.length];
			String y = jsonObject.getString(ChartJsonUtils.Y);
			JSONObject jsonY = JSONObject.fromObject(y);
			int i = 0;
			for(String c : categories){
				c = c.replace("\"","").replace("\"","");
				x[i] = c;
				String yc = jsonY.getString(c.replace("\"","").replace("\"",""));
				if(yc!=null && yc.length()>0){
					datas[i] = yc;
				} else {
					return null;
				}
				i++;
			}
			DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(x, datas);
			return dataset;
		} else {
			return null;
		}
//		String[] categories = { "Bananas", "Kiwi", "Mixed nuts", "Oranges", "Apples", "Pears", "Clementines", "Reddish (bag)", "Grapes (bunch)" };
//		Object[] datas = { 8, 3, 1, 6, 8, 4, 4, 1, 1 };
	}

	public static JFreeChart createChart(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		String title = jsonObject.getString(ChartJsonUtils.TITLE);
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createPieChart(title, createDataset(jsonObject));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setPieRender(chart.getPlot());
		/**
		 * 可以注释测试
		 */
		// plot.setSimpleLabels(true);//简单标签,不绘制线条
		// plot.setLabelGenerator(null);//不显示数字
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		return chart;
	}

	public static ChartPanel getChartPanel(String json){
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(createChart(json));
		return chartPanel;
	}

	public static void main(String[] args) throws IOException {
		URL url = PieChart.class.getResource("PieChart.json");
		final String pathname = url.getPath();
		File file = new File(pathname);
		if(!file.exists()){
			System.out.println(pathname+"  is null");
			return;
		}
		final String json = FileUtil.readStrTxt(file, ChartGlobal.encodeing);
		System.out.println(json);
		
//		chartFrame(json);

		outPng(json);
	}

	static JFreeChart chart;
	public JFreeChart getJFreeChart(){
    	return chart;
    }
	ChartPanel frame1;
    public PieChartLay(String json){
    	chart = createChart(json);
    	frame1=new ChartPanel(chart,true);
    }
    public static void chartFrame(final String json){
    	final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = getChartPanel(json);
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});
    }
    public static void outPng(String json) throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream("PieChartLay.jpeg");
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new PieChartLay(json).getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
}