package com.ailk.obs.ctpass.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

public class LogUtil {
	private final static String LOG_PATH = "/sdcard/ctpass/";
	private final static String LOG_NAME = "ctpass.txt";

	// public static void wirte(String text) {
	// File file = new File(LOG_PATH, LOG_NAME);
	// FileWriter fileWirter = null;
	// BufferedWriter bufWriter = null;
	// try {
	// fileWirter = new FileWriter(file, true);
	// bufWriter = new BufferedWriter(fileWirter);
	// bufWriter.write(text);
	// bufWriter.newLine();
	// bufWriter.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (bufWriter != null) {
	// try {
	// bufWriter.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// if (fileWirter != null) {
	// try {
	// fileWirter.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// }

	public static synchronized void write(String content) {
		try {
			FileWriter writer = new FileWriter(getFile(), true);
			writer.write(content);
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String CACHE_DIR_NAME = "ctpass";

	/**
	 * 获取日志文件路径
	 * 
	 * @return
	 */
	public static String getFile() {
		File sdDir = null;

		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			sdDir = Environment.getExternalStorageDirectory();

		File cacheDir = new File(sdDir + File.separator + CACHE_DIR_NAME);
		if (!cacheDir.exists())
			cacheDir.mkdir();

		File filePath = new File(cacheDir + File.separator + LOG_NAME);

		return filePath.toString();
	}
}
