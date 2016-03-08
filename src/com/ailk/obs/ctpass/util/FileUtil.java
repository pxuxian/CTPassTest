package com.ailk.obs.ctpass.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtil {
	
	public static List<String> read(String fileName) {
		List<String> content = new ArrayList<String>();
		BufferedReader br = null;
		try {
			FileReader reader = new FileReader(new File(fileName));
			br = new BufferedReader(reader);
			String line = br.readLine();
			while (line != null) {
				content.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public static String[] getFileNames(String dir) {
		List<File> files = readFile(dir);
		List<String> fileNames = new ArrayList<String>();
		for (File file : files) {
			fileNames.add(file.getAbsolutePath());
		}
		String[] fs = fileNames.toArray(new String[files.size()]);
		Arrays.sort(fs, Collections.reverseOrder());
		return fs;
	}

	public static List<File> readFile(String filePath) {
		File file = null;
		file = new File(filePath);
		// 得到file文件夹下面的所有文件
		List<File> fileList = new ArrayList<File>();
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				fileList.add(f);
			}
		}
		return fileList;

	}

	// 利用正规表达式获得文件名称
	public static String parseFileName(String filename) {

		filename = filename.substring(filename.lastIndexOf("/") + 1);
		return filename;
	}

}
