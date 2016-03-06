package com.ailk.obs.ctpass.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

public class LogUtil {
	public static final String CACHE_DIR = "ctpass";
	public static final String REPORT_DIRE = "report";

	public static synchronized void write(String path, String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(getFile(path, fileName), true);
			writer.write(content);
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getFile(String path, String fileName) {
		File sdDir = null;
		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		File cacheDir = new File(sdDir + File.separator + CACHE_DIR + File.separator + path);
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
		File filePath = new File(cacheDir + File.separator + fileName);
		return filePath.toString();
	}
}
