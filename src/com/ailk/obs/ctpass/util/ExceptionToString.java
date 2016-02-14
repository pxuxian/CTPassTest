package com.ailk.obs.ctpass.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author wj
 * 
 */

public class ExceptionToString {

	public static String converToStr(Throwable exception) {
		PrintWriter pw = null;
		try {
			StringWriter sw = new StringWriter();
			pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			String msg = sw.toString();
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		return exception.getMessage();
	}
}
