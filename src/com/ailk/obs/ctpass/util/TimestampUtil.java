package com.ailk.obs.ctpass.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.support.v4.util.LruCache;

/**
 * @author zouning
 * @time 2014-7-29 下午11:42:16
 * 
 */

public final class TimestampUtil {
	// private static SimpleDateFormat dateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	private static final String DEFAULT_FORMATTER_STRING = "yyyyMMddHHmmss";
	// private static SimpleDateFormat dateFormat = new SimpleDateFormat(
	// "yyyyMMddHHmmss", Locale.US);

	private static final LruCache<String, SimpleDateFormat> formatterCache = new LruCache<String, SimpleDateFormat>(
			10);

	public static SimpleDateFormat getDateFormater() {
		return getDateFormater(DEFAULT_FORMATTER_STRING);
	}

	public static SimpleDateFormat getDateFormater(final String dateFormatter) {
		SimpleDateFormat dateFormat = formatterCache.get(dateFormatter);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(dateFormatter, Locale.US);
			formatterCache.put(dateFormatter, dateFormat);
		}
		return dateFormat;
	}

	public static String getTimeStamp() {
		return getDateFormater().format(new Date());
	}

	public static Date parserToDate(final String date) throws ParseException {
		return getDateFormater().parse(date);
	}

	public static Date parserToDate(final String date,
			final String dateFormatter) throws ParseException {
		return getDateFormater(dateFormatter).parse(date);
	}

	public static String parserDateToString(final Date date)
			throws ParseException {
		return getDateFormater().format(date);
	}

	public static String parserDateToString(final Date date,
			final String dateFormatter) throws ParseException {
		SimpleDateFormat dateFormat = getDateFormater(dateFormatter);
		return dateFormat.format(date);
	}

	/**
	 * 根据给定的格式返回时间戳
	 * 
	 * @param dateFormatter
	 * @return
	 */
	public static String getTimeStamp(final String dateFormatter) {
		SimpleDateFormat dateFormat = getDateFormater(dateFormatter);
		return dateFormat.format(new Date());
	}

}
