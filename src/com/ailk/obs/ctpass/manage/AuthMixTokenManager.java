package com.ailk.obs.ctpass.manage;

import java.net.URLEncoder;

import org.json.JSONObject;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import cn.com.chinatelecom.ctpass.aidl.UDunCallback;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.ExceptionToString;
import com.ailk.obs.ctpass.util.HandlerUtil;
import com.ailk.obs.ctpass.util.LocalConfig;
import com.ailk.obs.ctpass.util.TimestampUtil;

public class AuthMixTokenManager {
	private Handler handler;
	private AsyncProvider mAsyncProvider;
	private String seqId;
	private String random;
	private String pCFlagString = "";
	private static final String APP_ID = LocalConfig.DEVICE_NO;
	private static final String TAG = AuthMixTokenManager.class.getSimpleName();

	public AuthMixTokenManager(Handler handler, AsyncProvider mAsyncProvider) {
		this.handler = handler;
		this.mAsyncProvider = mAsyncProvider;
	}

	public void authMixToken(final String pCFlag, final BindServiceConnection serviceConnection) {
		pCFlagString = pCFlag;
		mAsyncProvider.getSeqIDRandom(new RequestListener() {
			@Override
			public void onComplete(Object seqIDAndRadomResponse) {
				try {
					JSONObject obj = (JSONObject) seqIDAndRadomResponse;
					JSONObject resultJsonObject = obj.getJSONObject("GenReqAndRandomResponse");
					seqId = resultJsonObject.getString("SeqID");
					random = resultJsonObject.getString("Random");
					// 获取融合token
					serviceConnection.getCtpassAIDLService().getMixedTokenM(APP_ID, seqId, random, pCFlagString,
							mixTokenCallBack);
				} catch (Exception e) {
					HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, "访问异常：" + e, false);
					Log.e(TAG, e.getMessage(), e);
				}
			}

			@Override
			public void onInvokerError(String e) {
				HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, "访问异常：" + e, false);
			}
		});
	}

	private UDunCallback mixTokenCallBack = new UDunCallback.Stub() {
		@Override
		public void callBack(int resultCode, String CTPassID, boolean isEnctypted, final String resultDesc)
				throws RemoteException {
			String cr = System.getProperty("line.separator");
			final StringBuilder sb = new StringBuilder();
			sb.append("APPID:").append(APP_ID).append(cr);
			sb.append("SeqID:").append(seqId).append(cr);
			sb.append("Radom:").append(random).append(cr);
			sb.append("融合Token:").append(cr).append(resultDesc).append(cr);
			sb.append("Token生成时间:").append(TimestampUtil.getTimeStamp()).append(cr);
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "Token参数:" + cr + sb.toString());
			authMixTokenResult(resultDesc, sb);
		}
	};

	private void authMixTokenResult(final String mixToken, final StringBuilder logSB) {
		try {
			mAsyncProvider.authMixToken(URLEncoder.encode(mixToken, "UTF-8"), seqId, random, pCFlagString,
					new RequestListener() {
						@Override
						public void onComplete(final Object response) {
							JSONObject obj = (JSONObject) response;
							try {
								JSONObject resultJsonObject = obj.getJSONObject("AuthCTPassTokenComResponse");
								logSB.append("认证结果:").append("\n");
								logSB.append(resultJsonObject.toString());

								int resultCode = resultJsonObject.getInt("ResultCode");
								if (resultCode == 85) {
									logSB.append("\n认证方式为OTA,等待认证结果");
									handler.post(new AuthTokenTask(mAsyncProvider, seqId, random, pCFlagString,
											handler, Constants.CASE_AUTH_MixToken));
								} else {
									HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, logSB.toString(), true,
											pCFlagString);
								}
								
							} catch (Exception e) {
								Log.e(TAG, e.getMessage(), e);
								HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, "访问异常：" + e, false);
							}
						}

						@Override
						public void onInvokerError(final String e) {
							HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, "访问异常：" + e, false);
						}
					});
		} catch (Exception e) {
			HandlerUtil.send(handler, Constants.CASE_AUTH_MixToken, "发生异常：" + ExceptionToString.converToStr(e));
		}
	}

}
