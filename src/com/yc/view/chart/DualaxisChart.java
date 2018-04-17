package com.yc.view.chart;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

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

import com.yc.utils.files.PropertiesUtil;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartUtils;
import com.yc.view.utils.Serie;

/**
 * 
 * @author yc
 *       <p>
 *       2个Y轴图形<br/>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 *       </p>
 */

public class DualaxisChart {

	public static JFreeChart createChart() {
		String[] categories = { "1999-12-31", "2000-12-31", "2001-12-31", "2002-12-31", "2003-12-31", "2004-12-31", "2005-12-31", "2006-12-31", "2007-12-31",
				"2008-12-31", "2009-12-31", "2010-12-31", "2011-12-31", "2012-12-31", "2013-12-31" };
		for (int i = 0; i < categories.length; i++) {
			categories[i]=categories[i].substring(0, 4);
		}
		Vector<Serie> seriesNetProfit = new Vector<Serie>();
		// 净利润
		Object[] netProfit = { 92669.20, 95790.47, 106187.80, 128530.88, 156608.82, 193003.08, 255800.38, 335302.66, 549877.54, 1251596.81, 1321658.11,
				1917721.09, 2728598.10, 3418600.00, 4092200.00 };
		// 股利支付率
		Object[] payoutRatio = { "39.01", "--", "45.39", "30.46", "27.50", "24.34", "19.90", "19.48", "12.67", "10.40", "10.02", "11.97", "20.51", "30.01",
				" --" };
		seriesNetProfit.add(new Serie("净利润", netProfit));

		Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
		seriesPayoutRatio.add(new Serie("股利支付率", payoutRatio));

		DefaultCategoryDataset datasetNetProfit = ChartUtils.createDefaultCategoryDataset(seriesNetProfit, categories);
		JFreeChart chart = ChartFactory.createBarChart("", "", "净利润(万元)", datasetNetProfit);
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
		// 设置坐标轴
		ChartUtils.setXAixs(chart.getCategoryPlot());
		ChartUtils.setYAixs(chart.getCategoryPlot());
		// linechart
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		DefaultCategoryDataset datasetPayoutRatio = ChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, categories);
		categoryplot.setDataset(1, datasetPayoutRatio);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		// X轴刻度
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 右侧Y轴
		NumberAxis numberaxis = new NumberAxis("股利支付率(%)");
		categoryplot.setRangeAxis(1, numberaxis);
		// 隐藏Y刻度
		numberaxis.setAxisLineVisible(false);
		numberaxis.setTickMarksVisible(false);

		// 设置折线图样式
		LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
		lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
		lineRenderer.setBaseShapesVisible(true);// 数据点绘制形状
		categoryplot.setRenderer(1, lineRenderer);
		
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// 绘制Z-index, 将折线图在前面
		chart.getLegend().setPosition(RectangleEdge.TOP);//标注在顶部
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}
	public ChartPanel getChartPanel(){
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(createChart());
		return chartPanel;
	}

	public static void main(String[] args) {
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

//		try {
//			outPng();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String sizetwo = PropertiesUtil.getPropertiesKey(ChartGlobal.PORTMESG, ChartGlobal.SIZE_TWO);
		ChartUtils.saveAsFile(new DualaxisChart().getJFreeChart(), sizetwo+"/02.png", 1024, 420);
	}
	static JFreeChart chart;
	public JFreeChart getJFreeChart(){
    	return chart;
    }
	ChartPanel frame1;
    public DualaxisChart(){
    	chart = createChart();
    	frame1=new ChartPanel(chart,true);
    }
	
    public static void outPng() throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream("02.jpeg");
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new DualaxisChart().getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
    
}