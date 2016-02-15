package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.util.Log;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.conn.BindServiceConnection;
import com.ailk.obs.ctpass.module.AuthToken;
import com.ailk.obs.ctpass.module.TestResult;
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

	public void authTokenOnly(final AuthToken authToken, AsyncProvider mAsyncProvider) {
		try {
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
									StringBuilder sb = new StringBuilder(authToken.toString());
									JSONObject resultJsonObject = obj.getJSONObject("AuthCTPassTokenResponse");
									sb.append("认证结果:");
									sb.append(resultJsonObject.toString());
									TestResult.dataMap.put("authTokenReturn", sb.toString());
									TestResult.resultMap.put("authTokenResult", true);
								} catch (Exception e) {
									Log.e(TAG, e.getMessage(), e);
								}
							}

							@Override
							public void onInvokerError(final String e) {
								Log.e(TAG, "访问网络异常", new Exception());
							}

						});
			} else {
				Log.e(TAG, "获取token失败,不验证token：" + authToken.toString(), new Exception());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
