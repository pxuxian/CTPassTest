package com.ailk.obs.ctpass.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地数据处理类 在Application进行初始化
 * 
 * @author shi_jincheng
 * 
 */
public class SharedPreferencesWrapper {
	/** 本地数据缓存文件的文件名 */
	private static final String CACHE_INFO_FILE_NAME = "CacheInfoData";

	public static final String MOBILE_NUMBER = "mobile_number";
	public static final String PC_CODE = "pc_code";

	public static final String SMS_ID = "sms_id";

	/** 获取当前类的配置数据实例 */
	public static SharedPreferencesWrapper getCacheInstance() {
		if (cacheInfoSFWrapper == null) {
			throw new IllegalAccessError("SharedPreferencesWrapper not init");
		}
		return cacheInfoSFWrapper;
	}

	/** 本地文件内容读取字符串 */
	public String readString(String key) {
		return mSharedPreferences.getString(key, "");
	}

	/** 本地文件内容读取长整型 */
	public long readLong(String key) {
		return mSharedPreferences.getLong(key, -1);
	}

	/** 本地文件内容读取整型 */
	public int readInt(String key) {
		return mSharedPreferences.getInt(key, -1);
	}

	/** 本地文件内容读取浮点型 */
	public float readFloat(String key) {
		return mSharedPreferences.getFloat(key, -1.0F);
	}

	/** 本地文件内容读取布尔型 */
	public boolean readBoolean(String key) {
		return mSharedPreferences.getBoolean(key, false);
	}

	/** 本地文件内容读取字符串 */
	public String readString(String key, String defaultValue) {
		return mSharedPreferences.getString(key, defaultValue);
	}

	/** 本地文件内容读取长整型 */
	public long readLong(String key, long defaultValue) {
		return mSharedPreferences.getLong(key, defaultValue);
	}

	/** 本地文件内容读取整型 */
	public int readInt(String key, int defaultValue) {
		return mSharedPreferences.getInt(key, defaultValue);
	}

	/** 本地文件内容读取浮点型 */
	public float readFloat(String key, float defaultValue) {
		return mSharedPreferences.getFloat(key, defaultValue);
	}

	/** 本地文件内容读取布尔型 */
	public boolean readBoolean(String key, boolean defaultValue) {
		return mSharedPreferences.getBoolean(key, defaultValue);
	}

	/** 本地文件内容写入字符串 */
	public void writeString(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/** 本地文件内容写入长整型 */
	public void writeLong(String key, long value) {
		editor.putLong(key, value);
		editor.commit();
	}

	/** 本地文件内容写入整型 */
	public void writeInt(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	/** 本地文件内容写入浮点型 */
	public void writeFloat(String key, float value) {
		editor.putFloat(key, value);
		editor.commit();
	}

	/** 本地文件内容写入布尔型 */
	public void writeBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	/** 是否包含指定的key */
	public boolean contains(String key) {
		return mSharedPreferences.contains(key);
	}

	/** 清空文件内容 */
	public void clear() {
		editor.clear();
		editor.commit();
	}

	/** 缓存信息数据保存文件代理 */
	private static SharedPreferencesWrapper cacheInfoSFWrapper;

	/** SharedPreferences对象，用来读数据 */
	private SharedPreferences mSharedPreferences;
	/** Editor对象，用来写数据 */
	private SharedPreferences.Editor editor;

	/** 构造器 */
	private SharedPreferencesWrapper() {
	}

	/** 初始化 */
	public static void init(Context context) {
		cacheInfoSFWrapper = new SharedPreferencesWrapper();
		// 初始化SharedPreferences对象
		cacheInfoSFWrapper.mSharedPreferences = context
				.getSharedPreferences(CACHE_INFO_FILE_NAME, Context.MODE_PRIVATE);
		// 初始化Editor对象
		cacheInfoSFWrapper.editor = cacheInfoSFWrapper.mSharedPreferences.edit();
	}

}
