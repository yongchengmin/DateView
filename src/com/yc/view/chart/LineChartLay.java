package com.yc.view.chart;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.sf.json.JSONObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import com.yc.utils.esbUtils.FileUtil;
import com.yc.view.chart.demo.LineChart;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartJsonUtils;
import com.yc.view.utils.ChartUtils;
import com.yc.view.utils.Serie;

/**
 * 
 * @author yc
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 *       </p>
 */
public class LineChartLay {

	public static DefaultCategoryDataset createDataset(JSONObject jsonObject) {
		// 标注类别
		String categorie = jsonObject.getString(ChartJsonUtils.CATEGORIES);
		if(categorie!=null && categorie.length()>0){
			categorie = categorie.substring(1, categorie.length()-1);
			String[] categories = categorie.split(ChartJsonUtils.COMMA);
			
			String y = jsonObject.getString(ChartJsonUtils.Y);
			JSONObject jsonY = JSONObject.fromObject(y);
			
			Vector<Serie> series = new Vector<Serie>();
			for(String c : categories){
				c = c.replace("\"","").replace("\"","");
				String yc = jsonY.getString(c.replace("\"","").replace("\"",""));
				if(yc!=null && yc.length()>0){
					yc = yc.substring(1, yc.length()-1);
					String[] yt = yc.split(ChartJsonUtils.COMMA);
					Double[] doe = new Double[yt.length];
					int i = 0;
					for(String value : yt){
						if (ChartUtils.isNumber(value)) {
							doe[i] = Double.parseDouble(value);
							i++;
						}
					}
					series.add(new Serie(c, doe));
				}else{
					return null;
				}
			}
			
			String xc = jsonObject.getString(ChartJsonUtils.X);
			if(xc!=null && xc.length()>0){
				xc = xc.substring(1, xc.length()-1);
			}else{
				return null;
			}
			String[] xx = xc.split(ChartJsonUtils.COMMA);
			String[] x = new String[xx.length];
			int i = 0;
			for(String value : xx){
				x[i] = value.replace("\"","").replace("\"","");
				i++;
			}
			DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, x);
			return dataset;
		} else {
			return null;
		}
	}

	public static JFreeChart createChart(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		String title = jsonObject.getString(ChartJsonUtils.TITLE);
		String categoryAxisLabel = jsonObject.getString(ChartJsonUtils.CATEGORYAXISLABEL);
		String valueAxisLabel = jsonObject.getString(ChartJsonUtils.VALUEAXISLABEL);
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createLineChart(title,categoryAxisLabel, valueAxisLabel, createDataset(jsonObject));
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

	public static ChartPanel getChartPanel(String json){
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(createChart(json));
		return chartPanel;
	}
	
	public static void main(String[] args) throws IOException {
		
		URL url = LineChart.class.getResource("LineChart.json");
		final String pathname = url.getPath();
		File file = new File(pathname);
		if(!file.exists()){
			System.out.println(pathname+"  is null");
			return;
		}
		final String json = FileUtil.readStrTxt(file, ChartGlobal.encodeing);
		System.out.println(json);
		
		chartFrame(json);
		
//		outPng(json);
		
//		String sizetwo = PropertiesUtil.getPropertiesKey(ChartGlobal.PORTMESG, ChartGlobal.SIZE_TWO);
//		ChartUtils.saveAsFile(new LineChartLay().getJFreeChart(), sizetwo+"/03.png", 1024, 420);
	}

	static JFreeChart chart;
	public JFreeChart getJFreeChart(){
    	return chart;
    }
	ChartPanel frame1;
    public LineChartLay(String json){
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
    	 OutputStream os = new FileOutputStream("LineChartLay.jpeg");
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new LineChartLay(json).getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
}