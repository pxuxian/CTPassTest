package com.ailk.obs.ctpass.manage;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import cn.com.chinatelecom.ctpass.aidl.UDunCallback;

import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;
import com.ailk.obs.ctpass.util.LocalConfig;

public class OTPManager {
	private static final String TAG = OTPManager.class.getSimpleName();
	private static final String APP_ID = LocalConfig.DEVICE_NO;
	private Handler handler;

	public OTPManager(Handler handler) {
		this.handler = handler;
	}

	public void getOTPByOMA(int otpLength, final BindServiceConnection serviceConnection) {
		try {
			// 调用远程ctpass接口
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

	public void getOTPMixByOMA(int otpLength, String pcFlagOTPMix, final BindServiceConnection serviceConnection) {
		try {
			serviceConnection.getCtpassAIDLService().getMixedOTP(APP_ID, otpLength, pcFlagOTPMix,
					new UDunCallback.Stub() {
						@Override
						public void callBack(int resultCode, String CTPassID, boolean isEnctypted,
								final String resultDesc) throws RemoteException {
							if (resultCode == 200) {
								HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "OTP" + resultDesc);
								HandlerUtil.send(handler, Constants.CASE_AUTH_TOKEN, "OTP" + resultDesc, true);
							}else{
								
							}
						}
					});
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			HandlerUtil.send(handler, Constants.CASE_AUTH_TOKEN, "访问异常：" + e, false);
		}
	}
}