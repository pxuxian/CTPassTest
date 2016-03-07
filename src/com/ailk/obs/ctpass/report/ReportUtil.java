package com.ailk.obs.ctpass.report;

import java.util.Date;

import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.DateUtil;

public class ReportUtil {

	public static void report(String caseId, String castName, boolean flag) {
		// 生成fileName
		String fileName = DateUtil.formatDate(new Date());
		LogUtil.write(LogUtil.REPORT_DIRE, fileName, caseId + ":" + castName + ":" + (flag == true ? "Pass" : "NoPass"));
	}
}
