package com.ailk.obs.ctpass.constant;

import android.graphics.Color;

public class Constants {

	public static final int COLOR_GREEN = Color.parseColor("#008000");
	public static final int COLOR_RED = Color.parseColor("#FF0000");

	public static final int CASE_TOAST = 0;
	public static final int CASE_BIND = 1;
	public static final int CASE_CONN = 2;
	public static final int CASE_AUTH_TOKEN = 3;
	public static final int CASE_AUTH_TOKEN_OTA = 4;
	// 融合token认证
	public static final int CASE_AUTH_MixToken = 5;
	// OTP认证OMA方式
	public static final int CASE_OTP_OMA = 6;
	public static final int CASE_OTP_MixOMA = 7;
	// OTP认证OTA方式
	public static final int CASE_OTP_OTA = 8;
	// 修改PC码
	public static final int CASE_OTP_UpdatePC = 9;
}
