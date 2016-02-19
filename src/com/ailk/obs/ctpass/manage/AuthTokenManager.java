package com.ailk.obs.ctpass.manage;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.conn.BindServiceConnection;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.module.AuthToken;
import com.ailk.obs.ctpass.util.LocalConfig;

public class AuthTokenManager {
	private static final String TAG = AuthTokenManager.class.getSimpleName();

	public AuthToken genarateAuthToken(Object response, String pcCode, BindServiceConnection serviceConnection) {
		try {
			if (response == null) {
				return null;
			}
			JSONObject obj = (JSONObject) response;
			JSONObject resultJsonObject = obj.getJSONObject("GenReqAndRandomResponse");
			String seqId = resultJsonObject.getString("SeqID");
			String random = resultJsonObject.getString("Random");
			String token = serviceConnection.getCtpassAIDLService()
					.getCTPassToken(LocalConfig.DEVICE_NO, seqId, random);
			return new AuthToken(seqId, random, pcCode, token);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}

	public void authTokenOTA(final String cellPhone, final String pcFlag,
			final BindServiceConnection serviceConnection, final AsyncProvider mAsyncProvider, final Handler handler) {
		// 自动获取seqid等然后获取s串,再进行认证
		mAsyncProvider.getSeqIDRandom(new RequestListener() {
			@Override
			public void onComplete(Object response) {
				authTokenOTA(cellPhone, pcFlag, response, serviceConnection, mAsyncProvider, handler);
			}

			@Override
			public void onInvokerError(String e) {
				Log.e(TAG, "访问异常", new Exception());
			}
		});
	}

	public void authTokenOTA(String cellPhone, final String pcFlag, Object response,
			BindServiceConnection serviceConnection, final AsyncProvider mAsyncProvider, final Handler handler) {
		try {
			if (response == null) {
				return;
			}
			JSONObject resultJsonObject = ((JSONObject) response).getJSONObject("GenReqAndRandomResponse");
			final String seqId = resultJsonObject.getString("SeqID");
			final String random = resultJsonObject.getString("Random");

			mAsyncProvider.authTokenByOTA(cellPhone, seqId, random, pcFlag, new RequestListener() {
				@Override
				public void onComplete(final Object response) {
					final String CR = System.getProperty("line.separator");
					StringBuilder sb = new StringBuilder();
					sb.append("SeqID:").append(seqId).append(CR);
					sb.append("Radom:").append(random).append(CR);
					sb.append("PCFlag:").append(pcFlag).append(CR);

					try {
						JSONObject obj = (JSONObject) response;
						JSONObject resultJsonObject = obj.getJSONObject("AuthCTPassTokenByOTAResponse");
						String resultString = resultJsonObject.getString("ResultCode");
						if (resultString.equals("0")) {
							handler.post(new AuthTokenTask(mAsyncProvider, seqId, random, pcFlag, handler));
							Log.e(TAG, sb.toString());
						} else {
							sb.append("认证失败  错误代码：").append(resultString);

							// handler处理
							final Message msg = new Message();
							final Bundle dataBundle = new Bundle();
							msg.setData(dataBundle);
							msg.what = Constants.HandlerCase.AUTH_TOKEN_OTA;
							msg.arg1 = Integer.valueOf(pcFlag);
							dataBundle.putString("RESULT", sb.toString());
							handler.sendMessage(msg);
							Log.e(TAG, sb.toString());
						}
					} catch (JSONException e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}

				@Override
				public void onInvokerError(final String e) {
					Log.e(TAG, "访问异常", new Exception());
				}

			});

		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	public void authTokenOnly(final AuthToken authToken, AsyncProvider mAsyncProvider, final Handler handler) {
		final Message msg = new Message();
		final Bundle dataBundle = new Bundle();
		msg.setData(dataBundle);
		msg.what = Constants.HandlerCase.AUTH_TOKEN;

		if (authToken == null) {
			return;
		}
		String pcFlag = "0";
		if (authToken.getPcCode() == null || authToken.getPcCode().length() == 0) {
			pcFlag = "0";
		} else {
			pcFlag = "1";
		}
		if (authToken.getToken() == null) {
			return;
		}
		if (authToken.getToken().substring(0, 2).equals("00")) {
			mAsyncProvider.authToken(authToken.getToken(), authToken.getSeqId(), authToken.getRandom(), pcFlag,
					new RequestListener() {
						@Override
						public void onComplete(final Object response) {
							JSONObject obj = (JSONObject) response;
							try {
								JSONObject resultJsonObject = obj.getJSONObject("AuthCTPassTokenResponse");
								StringBuilder sb = new StringBuilder(authToken.toString());
								sb.append("认证结果:").append(resultJsonObject.toString());
								// handler处理
								dataBundle.putString("RESULT", sb.toString());
								dataBundle.putBoolean("flag", true);
								handler.sendMessage(msg);
							} catch (Exception e) {
								dataBundle.putString("RESULT", "访问异常：" + e.getMessage());
								handler.sendMessage(msg);
								Log.e(TAG, e.getMessage(), e);
							}
						}

						@Override
						public void onInvokerError(final String e) {
							// handler处理
							dataBundle.putString("RESULT", "访问异常：" + e);
							handler.sendMessage(msg);
							Log.e(TAG, "访问异常", new Exception());
						}

					});
		} else {
			// handler处理
			dataBundle.putString("RESULT", "获取token失败,不验证token：" + authToken.getToken());
			handler.sendMessage(msg);
			Log.e(TAG, "获取token失败,不验证token：" + authToken.toString(), new Exception());
		}

	}

}
