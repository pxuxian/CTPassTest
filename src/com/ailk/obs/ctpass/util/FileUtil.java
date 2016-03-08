package com.ailk.obs.ctpass.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

	public static List<File> ReadFile(String filePath) {
		File file = null;
		file = new File(filePath);
		// 得到file文件夹下面的所有文件
		File[] files = file.listFiles();
		List<File> fileList = new ArrayList<File>();
		for (File f : files) {
			fileList.add(f);
		}
		return fileList;

	}

	// 利用正规表达式获得文件名称
	public static String parseFileName(String filename) {
		String regex = "\\w+\\.xml";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(filename);
		String caseName = "";
		while (m.find()) {
			caseName = m.group();
		}
		return caseName;
	}

}
