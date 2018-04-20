package com.yc.view.chart;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;

import net.sf.json.JSONObject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.yc.utils.esbUtils.FileUtil;
import com.yc.view.chart.demo.DualaxisChart;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartJsonUtils;
import com.yc.view.utils.ChartUtils;
import com.yc.view.utils.Serie;

public class DualaxisChartLay {
	public static JFreeChart createChart(String pathname) {
		/*******获取json数据部分***********************/
		File file = new File(pathname);
		if(!file.exists()){
			return null;
		}
		String json = FileUtil.readStrTxt(file, ChartGlobal.encodeing);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String title = jsonObject.getString(ChartJsonUtils.TITLE);
		String categoryAxisLabel = jsonObject.getString(ChartJsonUtils.CATEGORYAXISLABEL);
		String valueAxisLabel = jsonObject.getString(ChartJsonUtils.VALUEAXISLABEL);
		String numberaxis = jsonObject.getString(ChartJsonUtils.NUMBERAXIS);
		String y = jsonObject.getString(ChartJsonUtils.Y);
		JSONObject jsonY = JSONObject.fromObject(y);
		String categorie = jsonObject.getString(ChartJsonUtils.CATEGORIES);
		JSONObject jsonCategorie = JSONObject.fromObject(categorie);
		/*******柱状数据源***********************/
		String pillar = jsonCategorie.getString(ChartJsonUtils.PILLAR);//柱状key
		pillar = pillar.replace("\"","").replace("\"","");
		Vector<Serie> seriesNetProfit = new Vector<Serie>();
		
		String yc = jsonY.getString(pillar);
		yc = yc.substring(1, yc.length()-1);
		String[] yt = yc.split(ChartJsonUtils.COMMA);
		Double[] doe = new Double[yt.length];
		int i = 0;
		for(String value : yt){
			if (ChartUtils.isNumber(value)) {
				doe[i] = Double.parseDouble(value);
			}else{
				doe[i] = 0D;
			}
			i++;
		}
		seriesNetProfit.add(new Serie(pillar, doe));
		/////公共X轴部分
		String xc = jsonObject.getString(ChartJsonUtils.X);
		xc = xc.substring(1, xc.length()-1);
		String[] xx = xc.split(ChartJsonUtils.COMMA);
		String[] x = new String[xx.length];
		i = 0;
		for(String value : xx){
			x[i] = value.replace("\"","").replace("\"","");
			i++;
		}
		
		DefaultCategoryDataset datasetNetProfit = ChartUtils.createDefaultCategoryDataset(seriesNetProfit, x);
		
		/*******折线数据源***********************/
		String line = jsonCategorie.getString(ChartJsonUtils.LINE);//折线key
		line = line.replace("\"","").replace("\"","");
		Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
		
		yc = jsonY.getString(line);
		yc = yc.substring(1, yc.length()-1);
		yt = yc.split(ChartJsonUtils.COMMA);
		doe = new Double[yt.length];
		i = 0;
		for(String value : yt){
			if (ChartUtils.isNumber(value)) {
				doe[i] = Double.parseDouble(value);
			}else{
//				doe[i] = 0D;
			}
			i++;
		}
		seriesPayoutRatio.add(new Serie(line, doe));
		DefaultCategoryDataset datasetPayoutRatio = ChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, x);
		/*******数据展示部分***********************/
		JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, datasetNetProfit);
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
		// 设置坐标轴
		ChartUtils.setXAixs(chart.getCategoryPlot());
		ChartUtils.setYAixs(chart.getCategoryPlot());
		// linechart
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setDataset(1, datasetPayoutRatio);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		// X轴刻度
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 右侧Y轴
		NumberAxis numberAxis = new NumberAxis(numberaxis);
		categoryplot.setRangeAxis(1, numberAxis);
		// 隐藏Y刻度
		numberAxis.setAxisLineVisible(false);
		numberAxis.setTickMarksVisible(false);
		// 设置折线图样式
		LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
		lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
		lineRenderer.setBaseShapesVisible(true);// 数据点绘制形状
		categoryplot.setRenderer(1, lineRenderer);
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// 绘制Z-index,将折线图在前面
		
		chart.getLegend().setPosition(RectangleEdge.TOP);//标注在顶部
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}
	
	public ChartPanel getChartPanel(String pathname){
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(createChart(pathname));
		return chartPanel;
	}

	public static void main(String[] args) {
		URL url = DualaxisChart.class.getResource("DualaxisChart.json");//test,从json路径下拷贝一份至demo路径下测试
		String pathname = url.getPath();
//		final JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(1024, 420);
//		frame.setLocationRelativeTo(null);
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				// 创建图形
//				ChartPanel chartPanel = new DualaxisChart().getChartPanel();
//				frame.getContentPane().add(chartPanel);
//				frame.setVisible(true);
//			}
//		});

		try {
			outPng(pathname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String sizetwo = PropertiesUtil.getPropertiesKey(ChartGlobal.PORTMESG, ChartGlobal.SIZE_TWO);
//		ChartUtils.saveAsFile(new DualaxisChartLay().getJFreeChart(), sizetwo+"/02.png", 1024, 420);
	}
	static JFreeChart chart;
	public JFreeChart getJFreeChart(){
    	return chart;
    }
	ChartPanel frame1;
    public DualaxisChartLay(String pathname){
    	chart = createChart(pathname);
    	frame1=new ChartPanel(chart,true);
    }
	
    public static void outPng(String pathname) throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream("02.jpeg");
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new DualaxisChartLay(pathname).getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
}
