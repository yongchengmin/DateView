package com.yc.view.job;

import java.util.Date;

import com.yc.view.utils.DateUtil;

public class EdiJobHelper extends AbstractEdiJobHelper{

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
}
