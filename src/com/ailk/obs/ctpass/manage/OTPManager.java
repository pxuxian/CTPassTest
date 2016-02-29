package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import cn.com.chinatelecom.ctpass.aidl.UDunCallback;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;
import com.ailk.obs.ctpass.util.LocalConfig;

public class OTPManager {
	private static final String TAG = OTPManager.class.getSimpleName();
	private static final String APP_ID = LocalConfig.DEVICE_NO;
	private Handler handler;
	private AsyncProvider mAsyncProvider;

	public OTPManager(Handler handler, AsyncProvider mAsyncProvider) {
		this.handler = handler;
		this.mAsyncProvider = mAsyncProvider;
	}

	public void getOTPByOMA(int otpLength, final BindServiceConnection serviceConnection) {
		try {
			// 调用远程ctpass接口
			String otpString = serviceConnection.getCtpassAIDLService().getOTP(otpLength);
			if (otpString == null) {
				HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "无接入CTPass权限");
				return;
			}
			HandlerUtil.send(handler, Constants.CASE_OTP_OMA, "动态口令" + otpString, true);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", e.getMessage());
		}
	}

	public void getOTPMixByOMA(int otpLength, final String pcFlagOTPMix, final BindServiceConnection serviceConnection) {
		try {
			serviceConnection.getCtpassAIDLService().getMixedOTP(APP_ID, otpLength, pcFlagOTPMix,
					new UDunCallback.Stub() {
						@Override
						public void callBack(int resultCode, String CTPassID, boolean isEnctypted,
								final String resultDesc) throws RemoteException {
							HandlerUtil.send(handler, Constants.CASE_OTP_MixOMA, "动态口令OTP" + resultDesc, true,
									pcFlagOTPMix);
						}
					});
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "访问异常：" + e);
		}
	}

	public void getOTPByOTA(final String cellPhone, int otpLength, final String pcFlagOTP,
			final BindServiceConnection serviceConnection) {
		mAsyncProvider.genOTPByOTA(cellPhone, Integer.toString(otpLength), pcFlagOTP, new RequestListener() {
			@Override
			public void onComplete(final Object response) {
				JSONObject obj = (JSONObject) response;
				try {
					JSONObject resultJsonObject = obj.getJSONObject("GenOTPByOTAResponse");
					String resultString = resultJsonObject.getString("ResultCode");
					if (resultString.equals("0")) {
						HandlerUtil.send(handler, Constants.CASE_OTP_OTA, "动态口令申请成功", true, pcFlagOTP);
					}
				} catch (Exception e) {
					HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "DEMO测试服务器接口异常");
				}
			}

			@Override
			public void onInvokerError(String e) {
				HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "网络异常");
			}
		});
	}
}