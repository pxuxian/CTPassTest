package com.ailk.obs.ctpass.manage;

import android.os.Handler;
import android.util.Log;

import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class OTPManager {
	private static final String TAG = OTPManager.class.getSimpleName();

	public void getOTPByOMA(int otpLength, final BindServiceConnection serviceConnection, Handler handler) {
		try {
			//调用远程ctpass接口
		 String otpString = serviceConnection.getCtpassAIDLService().getOTP(otpLength);
			if (otpString == null) {
				HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "无接入CTPass权限");
				return;
			}
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "获取OTP值:" + otpString);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", e.getMessage());
		}
	}
}