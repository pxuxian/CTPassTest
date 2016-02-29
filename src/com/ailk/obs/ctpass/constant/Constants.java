package com.ailk.obs.ctpass.constant;

import android.graphics.Color;

public class Constants {

	public static final int COLOR_GREEN = Color.parseColor("#008000");
	public static final int COLOR_RED = Color.parseColor("#FF0000");

	public static final int CASE_TOAST = 0;
	public static final int CASE_CONN = 1;
	public static final int CASE_AUTH_TOKEN = 2;
	public static final int CASE_AUTH_TOKEN_OTA = 3;
	// 融合token认证
	public static final int CASE_AUTH_MixToken = 4;
	// OTP认证OMA方式
	public static final int CASE_OTP_OMA = 5;
	public static final int CASE_OTP_MixOMA = 6;
	// OTP认证OTA方式
	public static final int CASE_OTP_OTA = 7;
	// 修改PC码
	public static final int CASE_OTP_UpdatePC = 8;
}
