package com.ailk.obs.ctpass.report;

import java.util.Date;

import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.DateUtil;

public class ReportUtil {

	// 生成fileName
	public static String createReport() {
		return DateUtil.formatDate(new Date());
	}

	// 测试结果写入报告
	public static void report(String fileName, String caseId, String caseName, boolean flag) {
		LogUtil.write(LogUtil.REPORT_DIRE, fileName, caseId + ":" + caseName + ":" + (flag == true ? "Pass" : "NoPass"));
	}
}
