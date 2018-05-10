package com.yc.view.job;

import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.yc.utils.files.PropertiesUtil;
import com.yc.view.service.ChartjsonInit;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.DateUtil;
import com.yc.view.utils.ProjectUtils;

public class EdiJobHelper extends AbstractEdiJobHelper{
	protected ChartjsonInit chartjsonInit;
	String repeatIntervalFile = "quartzRepeatInterval.properties";
	public EdiJobHelper(){
		
	}
	
	public void doHandleJob() {
		logger.info("EdiJobHelper[doHandleJob] started....................");
		while (true) {
			System.out.println("........."+DateUtil.format(new Date(), DateUtil.dmy_hms));
			
			try {
				int num = getRefreshInterval();
				System.out.println("EdiJobHelper[doHandleJob] sleep -------"+num);
				Thread.sleep(num);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
			if (!isApplicationAvailable) {
				logger.error("EdiJobHelper[doHandleJob] end....................");
				break;
			}
		}
	}
	
	public void doLeftTopJob(){
		logger.info("EdiJobHelper[doLeftTopJob] started....................");
		chartjsonInit = (ChartjsonInit) applicationContext.getBean(ChartjsonInit.BEAN);
		URL url = this.getClass().getResource(repeatIntervalFile);
		int time = 0,globalTime = 20;
		String tt = PropertiesUtil.getPropertiesKey(url.getPath(), "doLeftTopJob");
		if(StringUtils.isEmpty(tt)){
			time = globalTime;
		}else{
			if(ProjectUtils.isNumeric1(tt)){
				time = Integer.valueOf(tt);
			}else{
				time = globalTime;
			}
		}
//		String jsonFile = "leftTop_"+DateUtil.format(new Date(), DateUtil.yyMMddHHmmssSSS)+".json";
		int num = 1000*time;
		Boolean beTrue = num >= 1000;
		while (beTrue) {
			String json = chartjsonInit.leftTop();
			String jsonFile = ChartGlobal.LEFT_TOP+ChartGlobal.jsonEnd;
			ProjectUtils.createTxt(jsonFile,json.trim(), ChartGlobal.encodeing);
			try {
				System.out.println("doLeftTopJob..."+DateUtil.format(new Date(), DateUtil.dmy_hms)+" -- "+time+"s");
				Thread.sleep(num);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
			if (!isApplicationAvailable) {
				logger.error("EdiJobHelper[doLeftTopJob] end....................");
				break;
			}
		}
		logger.info("EdiJobHelper[doLeftTopJob] over....................");
	}
}
